class Part1
{
    String findSimpleGene(String dna)
    {
        int startIndex=dna.indexOf("ATG");
        int endIndex=dna.indexOf("TAA",startIndex+3);
        if(startIndex == -1 || endIndex == -1)
            return "";
        else if((endIndex-startIndex)%3 == 0)
            return dna.substring(startIndex,endIndex+3);
        return "";
    }
    void testSimpleGene()
    {
        String dna="AATTAAGTAACCC";
        String gene=findSimpleGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="ATATGAAATTGGATCACA";
        gene=findSimpleGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="AAGGTGAAGATTACC";
        gene=findSimpleGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="AACGTATGCCATACTAACC";
        gene=findSimpleGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="AACGTATGCCATACATAACC";
        dna="AAATGCCCTAACTAGATTAAGAAACC";
        gene=findSimpleGene(dna);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
    }
}