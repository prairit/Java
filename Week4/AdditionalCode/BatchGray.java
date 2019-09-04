import java.io.File;
import edu.duke.*;
class BatchGray
{
    ImageResource convertGray (ImageResource inImage)
    {
        ImageResource outImage=new ImageResource (inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel:outImage.pixels())
        {
            Pixel inPixel=inImage.getPixel(pixel.getX(),pixel.getY());
            int average= (inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    void testConvertGray()
    {
        ImageResource inImage=new ImageResource();
        ImageResource outImage=convertGray(inImage);
        outImage.draw();
    }
    void multipleFilesGrayConversion()
    {
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            ImageResource inImage=new ImageResource(f);
            String newName="Gray-"+inImage.getFileName();
            ImageResource outImage=convertGray(inImage);
            outImage.setFileName(newName);
            outImage.save();
            outImage.draw();
        }
    }
}