import edu.duke.*;
class Part1
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
    void testCountGenes ()
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
}