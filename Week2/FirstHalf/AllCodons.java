public class AllCodons
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
    void testFindGene()
    {
        String dna="ATCGTAGGGCATTAGACT";
        String gene=findGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="ATGTCGGGCATATAAGACT";
        gene=findGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="ATCATGTCGTGAGGCTAAATTTAGAGACT";
        gene=findGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="ATCGATGTCGGGCATTACGACT";
        gene=findGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
    }
    void printAllGenes(String dna)
    {
        String gene = findGene(dna);
        while(gene != "")
        {        
        System.out.println(gene);
        int index=dna.indexOf(gene);
        dna=dna.substring(index+gene.length(),dna.length());
        gene = findGene(dna);
        }
    }
}