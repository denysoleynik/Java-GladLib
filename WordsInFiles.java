import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
private HashMap<String,ArrayList<String>> map;

public WordsInFiles() {
 map = new HashMap<String,ArrayList<String>>() ;
}

public void addWordsFromFile(File f) {
    FileResource fr = new FileResource(f);
    for (String word : fr.words()){
        if (!map.containsKey(word)) {
            ArrayList<String> sourceF = new ArrayList<String>();
            sourceF.add(f.getName());
            map.put(word,sourceF);
        }
        else {
            ArrayList<String> sourceF = new ArrayList<String>();
            sourceF = map.get(word);
            if (!sourceF.contains(f.getName())) {
              sourceF.add(f.getName());  
            }
        }
    }
}

public void buildWordFileMap() {
    DirectoryResource dr = new DirectoryResource();
    map.clear();
    for (File f : dr.selectedFiles()) {
        addWordsFromFile(f);
    }
    
}

public int maxNumber() {
    int maxsize = 0;
    for (ArrayList word : map.values()) {
        if (word.size() > maxsize) {
            maxsize = word.size();
        }
    }
   
    return maxsize;
}

public ArrayList wordsInNumFiles (int number) {
    ArrayList<String> words = new ArrayList<String>();
    int counting = 0;
    for (String word : map.keySet()) {
        int counts = map.get(word).size();
        if (counts == number) {
            words.add(word);
            counting ++;
        }
    }
    System.out.println("total of words repeated for " + number + " is times: " + counting);
    return words;
}

public void printFilesIn(String word) {
    for (String hashwords : map.keySet()) {
        if (hashwords.equals(word)) {
                ArrayList<String> words = map.get(hashwords);
                for (int i=0; i<words.size(); i++) {
                System.out.println("For word " + word + " there are files" + words.get(i)); 
                }
                
        }
    }
    
    
}

public void tester() {
    buildWordFileMap();
    ArrayList wordsInNumFiles = wordsInNumFiles(4);
    System.out.println("\nMaximum number of words in all the files given = " +maxNumber());
        printFilesIn("tree");
        System.out.println("\n");
        //for (String s : map.keySet() ){
            //System.out.println(s + map.get(s) );
       // }
       
    }
}






