import java.io.File;
import edu.duke.*;
class BatchInvert
{
    ImageResource convertInvert (ImageResource inImage)
    {
        ImageResource outImage=new ImageResource (inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel:outImage.pixels())
        {
            Pixel inPixel=inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getGreen());
            pixel.setBlue(255-inPixel.getBlue());
        }
        return outImage;
    }
    void testConvertInvert()
    {
        ImageResource inImage=new ImageResource();
        ImageResource outImage=convertInvert(inImage);
        outImage.draw();
    }
    void multipleFilesInvertConversion()
    {
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            ImageResource inImage=new ImageResource(f);
            String newName="inverted-"+inImage.getFileName();
            ImageResource outImage=convertInvert(inImage);
            outImage.setFileName(newName);
            outImage.save();
            outImage.draw();
        }
    }
}