import edu.duke.*;
import java.io.File;

class Part4
{
    public static void main()
    {
        URLResource ur= new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String temp;int pos,startPos,endPos;
        for(String url : ur.words())
        {
            temp=url.toLowerCase();
            pos=temp.indexOf("youtube");
            if(pos!=-1)
            {
                startPos=temp.indexOf("\"");
                endPos=temp.indexOf("\"",pos+7);
                temp=url.substring(startPos+1,endPos);
                System.out.println("The YouTube link is "+temp);
            }
        }
    }
}