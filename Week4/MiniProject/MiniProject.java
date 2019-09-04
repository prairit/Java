import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;
class MiniProject
{
    void totalBirths(FileResource fr)
    {
        int numGirls=0,numBoys=0,numTotal=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            int num=Integer.parseInt(rec.get(2));
            if(rec.get(1).equals("M"))
                numBoys+=num;
            else
                numGirls+=num;
            numTotal+=num;
        }
        System.out.println("Total number of boys are "+numBoys);
        System.out.println("Total number of girls are "+numGirls);
        System.out.println("Total number of name are "+numTotal);
    }
    void testTotalBirths()
    {
        FileResource fr=new FileResource ();
        totalBirths(fr);
    }
    int getRank(int year,String name,String gender)
    {
        String fileName="C:/Users/Acer/Documents/java/BabyNames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        int rank=0,flag=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            if(gender.equalsIgnoreCase(rec.get(1)))
                rank++;
            if((rec.get(0)).equalsIgnoreCase(name) && gender.equalsIgnoreCase(rec.get(1)))
            {
                flag=1;
                break;
            }
        }
        if(flag==0)
            return -1;
        return rank;
    }
    void testGetRank()
    {
        int rank=getRank(2012,"Mason","F");
        System.out.println("The rank of the name is "+rank);
    }
    String getName(int year,int rank,String gender)
    {
        int counter=0;
        String name="No Name";
        String fileName="C:/Users/Acer/Documents/java/BabyNames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            if(gender.equalsIgnoreCase(rec.get(1)))
                counter++;
            if(counter==rank)
                name=rec.get(0);
        }
        return name;
    }
    void testGetName()
    {
        String name=getName(2012,2,"M");
        System.out.println("The name at the said position is "+name);
    }
    void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int rank=getRank(year,name,gender);
        String newName=getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
    }
    int yearOfHighestRank(String name,String gender)
    {
        int year=1880,minYear=9999999,minRank=9999999;
        while(year!=2015)
        {
            int rank=getRank(year,name,gender);
            if(rank!=-1 && rank<minRank)
                {
                    minYear=year;
                    minRank=rank;
                }
            year++;
        }
        return minYear;//return minRank;
    }
    int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        int rank=getRank(year,name,gender);
        String fileName="C:/Users/Acer/Documents/java/BabyNames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        int counter=1,births=0,totalBirths=0;
        for(CSVRecord rec:fr.getCSVParser(false))
        {
            if(counter<rank && gender.equalsIgnoreCase(rec.get(1)))
            {
                counter++;
                births=Integer.parseInt(rec.get(2));
                totalBirths+=births;
            }
        }
        return totalBirths;
    }
    void testGetTotalBirthsRankedHigher()
    {
        int totalBirths=getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println("Total births higher than the given name is "+totalBirths);
    }
    double getAverageRank(String name,String gender)
    {
        int year=1880,counter=0,totalRank=0,rank;
        while(year!=2015)
        {
            rank=getRank(year,name,gender);
            if(rank!=-1 )
                {
                    totalRank+=rank;
                    counter++;
                }
            year++;
        }
        if(totalRank==0)
            return -1;
        return ((double)totalRank)/counter;
    }
}