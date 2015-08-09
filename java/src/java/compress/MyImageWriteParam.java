package compress;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdulnizam
 */// Reads the jpeg image in infile, compresses the image,
// and writes it back out to outfile.
// compressionQuality ranges between 0 and 1,
// 0-lowest, 1-highest.


// This class overrides the setCompressionQuality() method to workaround
// a problem in compressing JPEG images using the javax.imageio package.
import javax.imageio.plugins.jpeg.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;

public class MyImageWriteParam extends JPEGImageWriteParam {
      public void compressJpegFile(File infile, File outfile, float compressionQuality) {
    try {
        // Retrieve jpg image to be compressed
        RenderedImage rendImage = ImageIO.read(infile);
        // Find a jpeg writer
        ImageWriter writer = null;
        Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
        if (iter.hasNext()) {
            writer = (ImageWriter)iter.next();
        }

        // Prepare output file
        ImageOutputStream ios = ImageIO.createImageOutputStream(new FileOutputStream(outfile,false));
        writer.setOutput(ios);

        // Set the compression quality
        ImageWriteParam iwparam = new MyImageWriteParam();
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
        iwparam.setCompressionQuality(compressionQuality);

        // Write the image
        writer.write(null, new IIOImage(rendImage, null, null), iwparam);

        // Cleanup
        ios.flush();
        writer.dispose();
        ios.close();
    } catch (IOException e) {
    }
    }

    public MyImageWriteParam() {
        super(Locale.getDefault());
    }

    // This method accepts quality levels between 0 (lowest) and 1 (highest) and simply converts
    // it to a range between 0 and 256; this is not a correct conversion algorithm.
    // However, a proper alternative is a lot more complicated.
    // This should do until the bug is fixed.
   /* public void setCompressionQuality(float quality) {
        if (quality < 0.0F || quality > 1.0F) {
            throw new IllegalArgumentException("Quality out-of-bounds!");
        }
        this.compressionQuality = 256 - (quality * 256);
    }*/
}
