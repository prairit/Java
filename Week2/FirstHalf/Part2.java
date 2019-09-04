class Part2
{
    int howMany (String stringa,String stringb)
    {
        int counter=0,index=0;String temp;
        while(true)
        {
            index=stringb.indexOf(stringa,index);
            if(index==-1)
            break;
            counter++;
            index+=stringa.length();
            System.out.println(stringb.substring(index,stringb.length()));
        }
        return counter;
    }
    void testHowMany()
    {
        int result=howMany("GAA","ATGAACGAATTGAATC");
        System.out.println("The number of occurences is "+result);
        
        result=howMany("AA","ATAAAA");
        System.out.println("The number of occurences is "+result);
        
        result=howMany("NA","BANANANANA");
        System.out.println("The number of occurences is "+result);
        
    }
}