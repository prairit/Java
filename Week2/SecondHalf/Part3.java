import edu.duke.*;
class Part3
{
    public int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        int currIndex=dna.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1)
        {
            if((currIndex-startIndex)%3 == 0)
            return (currIndex);
            currIndex=dna.indexOf(stopCodon,currIndex+1);
        }        
        return dna.length();
    }
    public String findGene(String dna)
    {
        int startIndex=dna.indexOf("ATG");
        if(startIndex == -1)
            return "";
        int taaIndex=findStopCodon(dna,startIndex,"TAA");
        int tagIndex=findStopCodon(dna,startIndex,"TAG");
        int tgaIndex=findStopCodon(dna,startIndex,"TGA");
        int minIndex=Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
        if(minIndex == dna.length())
        return "";
        return dna.substring(startIndex,minIndex+3);
    }
    StorageResource storeGenes(String dna)
    {
        StorageResource currGene = new StorageResource();
        String gene = findGene(dna);
        while(gene != "")
        {
        currGene.add(gene);
        int index=dna.indexOf(gene);
        dna=dna.substring(index+gene.length(),dna.length());
        gene = findGene(dna);
        }
        return currGene;
    }
    void testStoreGenes ()
    {
        StorageResource result= new StorageResource();
        result=storeGenes("ATGTAAGATGCCCTAGT");
        for(String str:result.data())
            {
            System.out.println(str);
            }
        result=storeGenes("ATGTAAGATGCCCTAGTATGCCAGGCTGA");
        for(String str:result.data())
            {
            System.out.println(str);
            }
    }
    float cgRatio(String dna)
    {
        int ratio=0;
        String str=dna;
        while(dna.contains("C")||dna.contains("G"))
        {
            ratio++;
            if(dna.contains("C")&&dna.contains("G"))
            dna=dna.substring((Math.min(dna.indexOf("C"),dna.indexOf("G")))+1,dna.length());
            else if(dna.contains("C"))
            dna=dna.substring((dna.indexOf("C"))+1,dna.length());
            else if(dna.contains("G"))
            dna=dna.substring((dna.indexOf("G"))+1,dna.length());
        }
        return (float)ratio/str.length();
    }
    void processGenes(StorageResource sr)
    {
        int counter=0,countercg=0,longest=0,dnacounter=0;
        String longestString=new String();
        for(String str:sr.data())
        {
            if(str.length()>60)
            {
                System.out.print("string longer than 60 ");
                System.out.println(str);
                counter++;
            }
            if(cgRatio(str)>0.35)
            {
                System.out.print("string having greater than .35 CG ratio ");
                System.out.println(str);
                countercg++;
            }
            if(str.length()>longest)
            {
                longestString=str;
                longest=longestString.length();
            }
            dnacounter++;
        }
        System.out.println("No of string with length greater than 9 is "+counter);
        System.out.println("No of string with cg ratio greater than .35 is "+countercg);
        System.out.println("Longest string is "+longest+" "+longestString);
        System.out.println("Total codons are "+dnacounter);
    }
    void testProcessGenes()
    {
        FileResource fr = new FileResource("dna.fa");
        String dna = fr.asString();
        StorageResource result= new StorageResource();
        result = storeGenes(dna.toUpperCase());
        processGenes(result);
    }
}