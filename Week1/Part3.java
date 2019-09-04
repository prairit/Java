class Part3
{
    boolean twoOccurrences(String stringa,String stringb)
    {
        int pos1,pos2;
        pos1=stringb.indexOf(stringa);
        if(pos1!=-1)
        {
            pos2=stringb.indexOf(stringa,pos1+stringa.length());
            if(pos2!=-1)
            return true;
        }
        return false;
    }
    void testing()
    {
        boolean result = twoOccurrences("by","A story by Abby Long");
        System.out.println("The result is "+result);
        result = twoOccurrences("a","banana");
        System.out.println("The result is "+result);
        result = twoOccurrences("atg","ctgtatgta");
        System.out.println("The result is "+result);
        String str=lastPart("an","banana");
        System.out.println("String after an is "+str);
        str=lastPart("zoo","forest");
        System.out.println("String after zoo is "+str);
    }
    String lastPart(String stringa,String stringb)
    {
        int pos1=stringb.indexOf(stringa);
        if(pos1 == -1)
        {
            return stringb;
        }
        return stringb.substring(pos1+stringa.length());
    }
}