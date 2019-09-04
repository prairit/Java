import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;
class Assignment1
{
    CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord lowestSoFar=null;
        for(CSVRecord current:parser)
        {
            if(lowestSoFar== null)
            lowestSoFar=current;
            double currentTemp=Double.parseDouble(current.get("TemperatureF"));
            double lowestTemp=Double.parseDouble(lowestSoFar.get("TemperatureF"));
            if(currentTemp<lowestTemp && !(currentTemp<-100))
            lowestSoFar=current;
        }
        return lowestSoFar;
    }
    void testColdestHourInFile()
    {
        FileResource fr=new FileResource();
        CSVParser parser= fr.getCSVParser();
        CSVRecord result = coldestHourInFile(parser);
        System.out.println("The coldest temperature of the day is "+result.get("TemperatureF")+" at "+result.get("DateUTC"));
    }
    String fileWithColdestTemperature()
    {
        DirectoryResource dr =new DirectoryResource();
        CSVRecord lowestRec=null;
        String lowestFile="";
        for (File f : dr.selectedFiles())
        {
            FileResource fr= new FileResource(f);
            CSVParser parser=fr.getCSVParser();   
            CSVRecord curr= coldestHourInFile(parser);
            if(lowestRec==null)
                {
                    lowestRec=curr;
                    lowestFile=f.getName();
                }
            double currTemp=Double.parseDouble(curr.get("TemperatureF"));
            double lowest=Double.parseDouble(lowestRec.get("TemperatureF"));
            if(currTemp<lowest)
                {
                    lowestFile=f.getName();
                    lowestRec=curr;
                }
        }
        return lowestFile;
    }
    void testFileWithColdestTemperature()
    {
        String str=fileWithColdestTemperature();
        System.out.println("Coldest day was in file weather "+str);
        FileResource fr=new FileResource(str);
        CSVParser parser=fr.getCSVParser();
        double coldestTemp=Double.parseDouble(coldestHourInFile(parser).get("TemperatureF"));
        System.out.println("Coldest temperature on that day was"+coldestTemp);
        System.out.println("All the temperatures on the coldest day were:");
        parser=fr.getCSVParser();
        for(CSVRecord rec:parser)
        {
            System.out.print(rec.get("DateUTC"));
            System.out.print(" |  "+rec.get("TemperatureF"));
            System.out.println();
        }
    }
    CSVRecord lowestHumidityInFile (CSVParser parser)
    {
        CSVRecord lowestHumidity=null;double lowest,curr=100.0;
        for(CSVRecord rec:parser)
        {
            if(lowestHumidity==null)
                lowestHumidity=rec;
            lowest=Double.parseDouble(lowestHumidity.get("Humidity"));
            if(!(rec.get("Humidity").contains("N/A")))
                curr=Double.parseDouble(rec.get("Humidity"));
            if(curr<lowest)
                lowestHumidity=rec;
        }
        return lowestHumidity;
    }
    void testLowestHumidityInFile ()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was"+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr=new DirectoryResource();
        CSVRecord lowestSoFar=null;
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVRecord curr=lowestHumidityInFile(fr.getCSVParser());
            if(lowestSoFar == null)
                lowestSoFar=curr;
            double currHum=Double.parseDouble(curr.get("Humidity"));
            double lowestHum=Double.parseDouble(lowestSoFar.get("Humidity"));
            if(currHum<lowestHum)
                lowestSoFar=curr;
        }
        return lowestSoFar;
    }
    void testLowestHumidityInManyFiles()
    {
        CSVRecord csv=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    double averageTemperatureInFile(CSVParser parser)
    {
        double average=0.0;int c=0;
        for(CSVRecord rec:parser)
        {
            double currTemp=Double.parseDouble(rec.get("TemperatureF"));
            average+=currTemp;
            c++;
        }
        return average/c;
    }
    void testAverageTemperatureInFile()
    {
        FileResource fr=new FileResource();
        double res=averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+res);
    }
    double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {
        double average=0.0d;int c=0;
        for(CSVRecord rec:parser)
        {
            double currTemp=Double.parseDouble(rec.get("TemperatureF"));
            double currHum=Double.parseDouble(rec.get("Humidity"));
            if(currHum>=value)
            {
                average+=currTemp;
                c++;
            }
        }
        if(average==0.0d)
            return average;
        else
            return average/c;
    }
    void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr= new FileResource();
        double average=averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if(average==0.0d)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is "+average);
    }
}