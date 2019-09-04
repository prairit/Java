import edu.duke.*;
class Part2
{
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
            System.out.println(ratio+" "+dna);
        }
        return (float)ratio/str.length();
    }
    void test()
    {
        System.out.println(cgRatio("ATGCCATAG"));
    }
}