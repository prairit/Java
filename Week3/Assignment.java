import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
class Assignment
{
    public void tester()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        String info=countryInfo(parser,"Nauru");
        System.out.println(info);
        parser=fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        parser=fr.getCSVParser();
        int number=numberOfExporters(parser,"cocoa");
        System.out.println("number of exporters are "+number);
        parser=fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    String countryInfo(CSVParser parser,String country)
    {
         for(CSVRecord record:parser)
         {
             String name=record.get("Country");
             if(name.equalsIgnoreCase(country))
             {
                 String result=country+": "+record.get("Exports")+"\n"+record.get("Value (dollars)");
                 return result;
             }
         }
         return "NOT FOUND";
    }
    void listExportersTwoProducts (CSVParser parser,String exportItem1,String exportItem2)
    {
        for(CSVRecord record:parser)
        {
            String export=record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2))
            System.out.println("Countries with the two exports are "+record.get("Country"));
        }
    }
    int numberOfExporters(CSVParser parser, String exportItem)
    {
        int counter=0;
        for(CSVRecord record:parser)
        {
            if((record.get("Exports")).contains(exportItem))
            counter++;
        }
        return counter;
    }
    void bigExporters(CSVParser parser,String amount)
    {
        for(CSVRecord record:parser)
        {
            String value=record.get("Value (dollars)");
            if(value.length()>amount.length())
            System.out.println("Huge Export countries are "+record.get("Country"));
        }
    }
}