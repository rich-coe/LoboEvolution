/*
 * MIT License
 *
 * Copyright (c) 2014 - 2024 LoboEvolution
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Contact info: ivan.difrancesco@yahoo.it
 */
package org.jpedal.jbig2.jai;

import lombok.extern.slf4j.Slf4j;
import org.jpedal.jbig2.JBIG2Decoder;
import org.jpedal.jbig2.JBIG2Exception;
import org.jpedal.jbig2.image.JBIG2Bitmap;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>JBIG2ImageReader class.</p>
 */
@Slf4j
public class JBIG2ImageReader extends ImageReader {

    private JBIG2Decoder decoder;
    private ImageInputStream stream;
    private boolean readFile;

    /**
     * <p>Constructor for JBIG2ImageReader.</p>
     *
     * @param originatingProvider a {@link javax.imageio.spi.ImageReaderSpi} object.
     */
    protected JBIG2ImageReader(final ImageReaderSpi originatingProvider) {
        // Save the identity of the ImageReaderSpi subclass that invoked this
        // constructor.
        super(originatingProvider);
    }

    private static BufferedImage scaleImage(final Raster ras, final int pX, final int pY, final int dComp, final int dBufferedImage) {

        int w = ras.getWidth();
        int h = ras.getHeight();
        int d = dBufferedImage;
        final int g = dBufferedImage;
        int comp = dComp;

        byte[] data = ((DataBufferByte) ras.getDataBuffer()).getData();

        // see what we could reduce to and still be big enough for page
        int newW = w, newH = h;

        int sampling = 1;
        final int smallestH = pY << 2; // double so comparison works
        final int smallestW = pX << 2;

        // cannot be smaller than page
        while (newW > smallestW && newH > smallestH) {
            sampling = sampling << 1;
            newW = newW >> 1;
            newH = newH >> 1;
        }

        int scaleX = w / pX;
        if (scaleX < 1)
            scaleX = 1;

        int scaleY = h / pY;
        if (scaleY < 1)
            scaleY = 1;

        // choose smaller value so at least size of page
        sampling = scaleX;
        if (sampling > scaleY)
            sampling = scaleY;

        // switch to 8 bit and reduce bw image size by averaging
        if (sampling > 1) {

            newW = w / sampling;
            newH = h / sampling;

            if (d == 1) {

                final int size = newW * newH;

                final byte[] newData = new byte[size];

                final int[] flag = {1, 2, 4, 8, 16, 32, 64, 128};

                final int origLineLength = (w + 7) >> 3;

                int bit;
                byte currentByte;

                // scan all pixels and down-sample
                for (int y = 0; y < newH; y++) {
                    for (int x = 0; x < newW; x++) {

                        int bytes = 0, count = 0;

                        // allow for edges in number of pixels left
                        int wCount = sampling, hCount = sampling;
                        final int wGapLeft = w - x;
                        final int hGapLeft = h - y;
                        if (wCount > wGapLeft)
                            wCount = wGapLeft;
                        if (hCount > hGapLeft)
                            hCount = hGapLeft;

                        // count pixels in sample we will make into a pixel (ie
                        // 2x2 is 4 pixels , 4x4 is 16 pixels)
                        for (int yy = 0; yy < hCount; yy++) {
                            for (int xx = 0; xx < wCount; xx++) {

                                currentByte = data[((yy + (y * sampling)) * origLineLength) + (((x * sampling) + xx) >> 3)];

                                bit = currentByte & flag[7 - (((x * sampling) + xx) & 7)];

                                if (bit != 0)
                                    bytes++;
                                count++;
                            }
                        }

                        // set value as white or average of pixels
                        final int offset = x + (newW * y);
                        if (count > 0) {
                            newData[offset] = (byte) ((255 * bytes) / count);
                        } else {
                            newData[offset] = (byte) 255;
                        }
                    }
                }

                data = newData;

                h = newH;
                w = newW;
                d = 8;

                // imageMask=false;

            } else if (d == 8) {

                int x = 0, y = 0, xx = 0, yy = 0, jj = 0, origLineLength = 0;
                try {

                    // black and white
                    if (w * h == data.length)
                        comp = 1;

                    final byte[] newData = new byte[newW * newH * comp];

                    origLineLength = w * comp;

                    for (y = 0; y < newH; y++) {
                        for (x = 0; x < newW; x++) {

                            // allow for edges in number of pixels left
                            int wCount = sampling, hCount = sampling;
                            final int wGapLeft = w - x;
                            final int hGapLeft = h - y;
                            if (wCount > wGapLeft)
                                wCount = wGapLeft;
                            if (hCount > hGapLeft)
                                hCount = hGapLeft;

                            for (jj = 0; jj < comp; jj++) {
                                int byteTotal = 0, count = 0;
                                // count pixels in sample we will make into a
                                // pixel (ie 2x2 is 4 pixels , 4x4 is 16 pixels)
                                for (yy = 0; yy < hCount; yy++) {
                                    for (xx = 0; xx < wCount; xx++) {

                                        byteTotal = byteTotal + (data[((yy + (y * sampling)) * origLineLength) + (((x * sampling * comp) + (xx * comp) + jj))] & 255);

                                        count++;
                                    }
                                }

                                // set value as white or average of pixels
                                if (count > 0)
                                    // if (index==null)
                                    newData[jj + (x * comp) + (newW * y * comp)] = (byte) ((byteTotal) / count);
                                    // else
                                    // newData[x+(newW*y)]=(byte)(((index[1] &
                                    // 255)*byteTotal)/count);
                                else {
                                    // if (index==null)
                                    // newData[jj+x+(newW*y*comp)]=(byte) 255;
                                    // else
                                    // newData[x+(newW*y)]=index[0];
                                }
                            }
                        }
                    }

                    data = newData;
                    h = newH;
                    w = newW;

                } catch (final Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        if (sampling > 1) {
            final int[] bands = {0};
            final Raster raster = Raster.createInterleavedRaster(new DataBufferByte(data, data.length), w, h, w, 1, bands, null);

            final BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
            image.setData(raster);

            return image;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setInput(final Object input, final boolean seekForwardOnly, final boolean ignoreMetadata) {
        super.setInput(input, seekForwardOnly, ignoreMetadata);

        if (input == null) {
            this.stream = null;
            return;
        }

        // The input source must be an ImageInputStream because the originating
        // provider -- the JBIG2ImageReaderSpi class -- passes
        // STANDARD_INPUT_TYPE
        // -- an array consisting only of ImageInputStream -- to its superclass
        // in its constructor call.

        if (input instanceof ImageInputStream)
            this.stream = (ImageInputStream) input;
        else
            throw new IllegalArgumentException("ImageInputStream expected!");
    }

    /**
     * {@inheritDoc}
     */
    public BufferedImage read(final int imageIndex, final ImageReadParam param) throws IOException {

        BufferedImage dst = null;
        try {
            // Calculate and return a Rectangle that identifies the region of
            // the
            // source image that should be read:
            //
            // 1. If param is null, the upper-left corner of the region is (0,
            // 0),
            // and the width and height are specified by the width and height
            // arguments. In other words, the entire image is read.
            //
            // 2. If param is not null
            //
            // 2.1 If param.getSourceRegion() returns a non-null Rectangle, the
            // region is calculated as the intersection of param's Rectangle
            // and the earlier (0, 0, width, height Rectangle).
            //
            // 2.2 param.getSubsamplingXOffset() is added to the region's x
            // coordinate and subtracted from its width.
            //
            // 2.3 param.getSubsamplingYOffset() is added to the region's y
            // coordinate and subtracted from its height.

            final int width = getWidth(imageIndex);
            final int height = getHeight(imageIndex);

            final Rectangle sourceRegion = getSourceRegion(param, width, height);

            // Source subsampling is used to return a scaled-down source image.
            // Default 1 values for X and Y subsampling indicate that a
            // non-scaled
            // source image will be returned.

            int sourceXSubsampling = 1;
            int sourceYSubsampling = 1;

            // The destination offset determines the starting location in the
            // destination where decoded pixels are placed. Default (0, 0)
            // values indicate the upper-left corner.

            Point destinationOffset = new Point(0, 0);

            // If param is not null, override the source subsampling, and
            // destination offset defaults.

            if (param != null) {
                sourceXSubsampling = param.getSourceXSubsampling();
                sourceYSubsampling = param.getSourceYSubsampling();
                destinationOffset = param.getDestinationOffset();
            }

            // Obtain a BufferedImage into which decoded pixels will be placed.
            // This destination will be returned to the application.
            //
            // 1. If param is not null
            //
            // 1.1 If param.getDestination() returns a BufferedImage
            //
            // 1.1.1 Return this BufferedImage
            //
            // Else
            //
            // 1.1.2 Invoke param.getDestinationType ().
            //
            // 1.1.3 If the returned ImageTypeSpecifier equals
            // getImageTypes (0) (see below), return its BufferedImage.
            //
            // 2. If param is null or a BufferedImage has not been obtained
            //
            // 2.1 Return getImageTypes (0)'s BufferedImage.

            dst = getDestination(param, getImageTypes(0), width, height);

            // Create a WritableRaster for the destination.

            final WritableRaster wrDst = dst.getRaster();

            final JBIG2Bitmap bitmap = decoder.getPageAsJBIG2Bitmap(imageIndex).getSlice(sourceRegion.x, sourceRegion.y, sourceRegion.width, sourceRegion.height);

            final BufferedImage image = bitmap.getBufferedImage();

            final int newWidth = (int) (image.getWidth() * (1 / (double) sourceXSubsampling));
            final int newHeight = (int) (image.getHeight() * (1 / (double) sourceYSubsampling));

            final BufferedImage scaledImage = scaleImage(image.getRaster(), newWidth, newHeight, 1, 1);

            Raster raster = null;

            if (scaledImage != null) {
                raster = scaledImage.getRaster();
            } else
                raster = image.getRaster();

            wrDst.setRect(destinationOffset.x, destinationOffset.y, raster);

        } catch (final RuntimeException e) {
            log.error(e.getMessage(), e);
        }

        return dst;

    }

    /**
     * {@inheritDoc}
     */
    public IIOMetadata getImageMetadata(final int imageIndex) throws IOException {
        return null;
    }

    /**
     * <p>getStreamMetadata.</p>
     *
     * @return a {@link javax.imageio.metadata.IIOMetadata} object.
     * @throws java.io.IOException if any.
     */
    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<ImageTypeSpecifier> getImageTypes(final int imageIndex) throws IOException {
        readFile();

        checkIndex(imageIndex);

        // Create a List of ImageTypeSpecifiers that identify the possible image
        // types to which the single JBIG2 image can be decoded. An
        // ImageTypeSpecifier is used with ImageReader's getDestination() method
        // to return an appropriate BufferedImage that contains the decoded
        // image, and is accessed by an application.

        final List<ImageTypeSpecifier> l = new ArrayList<>();

        // The JBIG2 reader only uses a single List entry. This entry describes
        // a
        // BufferedImage of TYPE_INT_RGB, which is a commonly used image type.

        l.add(ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_BYTE_BINARY));

        // Return an iterator that retrieves elements from the list.

        return l.iterator();
    }

    /**
     * {@inheritDoc}
     */
    public int getNumImages(final boolean allowSearch) throws IOException {
        readFile();

        return decoder.getNumberOfPages();
    }

    /**
     * {@inheritDoc}
     */
    public int getHeight(final int imageIndex) throws IOException {
        readFile();

        checkIndex(imageIndex);

        return decoder.getPageAsJBIG2Bitmap(imageIndex).getHeight();
    }

    /**
     * {@inheritDoc}
     */
    public int getWidth(final int imageIndex) throws IOException {
        readFile();

        checkIndex(imageIndex);

        return decoder.getPageAsJBIG2Bitmap(imageIndex).getWidth();
    }

    private void checkIndex(final int imageIndex) {
        final int noOfPages = decoder.getNumberOfPages();
        if (imageIndex < 0 || imageIndex > noOfPages)
            throw new IndexOutOfBoundsException("Bad index!");
    }

    private void readFile() {
        // Do not allow this header to be read more than once.

        if (readFile)
            return;

        // Make sure that the application has set the input source.

        if (stream == null)
            throw new IllegalStateException("No input stream!");

        // Read the header.

        decoder = new JBIG2Decoder();

        try {
            final byte[] data;
            final int size = (int) stream.length();
            if (size == -1) {
                final ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final byte[] temp = new byte[8192];
                for (int len = 0; (len = stream.read(temp)) > 0; ) {
                    bos.write(temp, 0, len);
                }
                bos.close();
                data = bos.toByteArray();
            } else {
                data = new byte[size];
                stream.readFully(data);
            }

            decoder.decodeJBIG2(data);

        } catch (final IOException | JBIG2Exception e) {
            log.error(e.getMessage(), e);
        }

        readFile = true;
    }
}
