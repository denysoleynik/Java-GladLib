import edu.duke.*;
import java.util.*;

public class DNA {
    private HashMap<String,Integer> map;
    
    public DNA(){
        map = new HashMap<String,Integer>();
    }
    
 public void buildCodonMap(int start, String dna) {
     map.clear();
     int counts = 0;
     
     for (int i=start; i<dna.length()-2; i+= 3) {
         String codon = dna.substring(i,i+3);
         if (!map.containsKey(codon)) {
             map.put(codon,1);
             counts ++;
            }
         else {
             map.put(codon,map.get(codon)+1);
            }
        }
      System.out.println("Reading frame starting with " + start +
                           ", results in " + counts + " unique codons");
    }
    
 public String getMostCommonCodon() {
    int maxvalue = 0; 
    String maxCodon = "";
    for (String codon : map.keySet())
    if (map.get(codon) > maxvalue) {
        maxCodon = codon;
        maxvalue = map.get(codon);
    }
    
   return "and most common codon is " + maxCodon + " with count " + maxvalue;
}

public void printCodonCounts (int start, int end) {
    for (String s : map.keySet()) {
    int hashcount = map.get(s);
    if (hashcount >= start && hashcount <= end) {
        System.out.println( s + "\t" + map.get(s));
    }
}
}
public void test(){
        int start = 0;
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        buildCodonMap(start,dna);
        System.out.println(getMostCommonCodon());
        printCodonCounts(7,7);
}
}
