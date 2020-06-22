import edu.duke.*;
import java.util.*;

public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	private ArrayList<String> seenList;
	private int subst = 0;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		timeList = readIt(source+"/verb.txt");
		timeList = readIt(source+"/fruit.txt");
		seenList = new ArrayList<String>();
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
		        String random = randomFrom(countryList);
			return random;                        
		}
		if (label.equals("color")){
			String random = randomFrom(colorList);
			return random;
		}
		if (label.equals("noun")){
			String random = randomFrom(nounList);
			return random;
		}
		if (label.equals("name")){
			String random = randomFrom(nameList);
			return random;
		}
		if (label.equals("adjective")){
			String random = randomFrom(adjectiveList);
			return random;
		}
		if (label.equals("animal")){
			String random = randomFrom(animalList);
			return random;
		}
		if (label.equals("timeframe")){
			String random = randomFrom(timeList);
			return random;
		}
		if (label.equals("number")){
			String random =  ""+myRandom.nextInt(50)+5;
			return random;
		}
		if (label.equals("verb")){
			String random =  ""+myRandom.nextInt(50)+5;
			return random;
		}
		if (label.equals("fruit")){
			String random =  ""+myRandom.nextInt(50)+5;
			return random;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		while (true) {
		if (seenList.indexOf(sub) == -1) {
		seenList.add(sub);
		break;
                }
                else {sub = getSubstitute(w.substring(first+1,last));}
               }
               subst ++;
               return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    seenList.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
            System.out.println("Substitutes: " + subst);
	}
	


}
