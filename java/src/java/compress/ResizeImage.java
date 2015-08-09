/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compress;
import java.awt.Toolkit;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;
import javax.imageio.ImageIO;
/**
 *
 * @author abdulnizam
 */
public class ResizeImage {
public ResizeImage(String path,File infile)
{
    try
    {
        int[] resolution={32,50,75,200};
        BufferedImage bi=ImageIO.read(infile);
        ImageProducer ip=bi.getSource();
        Image image1=Toolkit.getDefaultToolkit().createImage(ip);
        for(int i=0;i<resolution.length;i++)
        {
            Image image=image1.getScaledInstance(resolution[i], resolution[i], Image.SCALE_SMOOTH);
            BufferedImage bi1=new BufferedImage(resolution[i],resolution[i],BufferedImage.TYPE_INT_RGB);
            Graphics2D g=bi1.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            File outfile=new File(path+"images/"+resolution[i]+"/"+resolution[i]+"_"+infile.getName());
            ImageIO.write(bi1, "jpg",new FileOutputStream(outfile,false));
            MyImageWriteParam m=new MyImageWriteParam();
            m.compressJpegFile(outfile,outfile,1f);
        }
    }
    catch(Exception ie)
    {
        System.out.println(ie);
    }
}
}
