class Part2
{
    String findSimpleGene(String dna,int startCodon,int stopCodon)
    {
        boolean flag=true;char ch;
        for(int i=0;i<dna.length();i++)
        {
            ch=dna.charAt(i);
            if(! Character.isUpperCase(ch))
            flag=false;
        }
        dna=dna.toUpperCase();
        dna=dna.substring(startCodon,stopCodon+3);
        int startIndex=dna.indexOf("ATG");
        int endIndex=dna.indexOf("TAA",startIndex+3);
        if(startIndex == -1 || endIndex == -1)
            return "";
        else if((endIndex-startIndex)%3 == 0){
            if(flag)
            return dna.substring(startIndex,endIndex+3);
            else
            return (dna.substring(startIndex,endIndex+3)).toLowerCase();
        }
        return "";
    }
    void testSimpleGene()
    {
        String dna="AATTAAGTAACCC";
        String gene=findSimpleGene(dna,1,9);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="ATATGAAATTGGATCACA";
        gene=findSimpleGene(dna,0,10);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="AAGGTGAAGATTACC";
        gene=findSimpleGene(dna,4,7);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="AACGTATGCCATACTAACC";
        gene=findSimpleGene(dna,0,15);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="AACGTATGCCATACATAACC";
        gene=findSimpleGene(dna,0,16);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
        
        dna="atcatatggcaacataagcattatgccac";
        gene=findSimpleGene(dna,0,16);
        System.out.println("The dna is "+dna);
        System.out.println("The gene is "+gene);
    }
}