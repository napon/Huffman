import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

	/**
	 * Steps:
	 * 1. Parse text file into a Map<Character, Integer> (character to frequency).
	 * 2. Create a HuffmanNode class where each node contains char:character, int:frequency, HuffmanNode:leftNode, HuffmanNode:rightNode
	 * 3. Build a min priority queue using the Map we just created and fill it with HuffmanNodes
	 * 4. Build another map but this time we map each character to its binary representation using the priorityQueue
	 * 5. Generate a new String with binary text (compressed)
	 */
	public static void main(String[] args) throws IOException {

		String textFile = readFile("src/alice30.txt");
		System.out.println(textFile);
		buildCharacterToFrequencyMap(textFile);

	}

	/**
	 * Convert a txt file into a String
	 * @param file
	 * @return
	 * @throws IOException
	 */
	
	private static String readFile( String file ) throws IOException {
		System.out.println("ReadFile");
		BufferedReader reader = new BufferedReader( new FileReader(file));
		String         line;
		StringBuilder  stringBuilder = new StringBuilder();
		String         ls = System.getProperty("line.separator");

		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( ls );
		}
		return stringBuilder.toString();
	}

	/**
	 * Step 1: Create a Map<Character, Integer> (character to frequency) from the input String - DONE
	 */
	private static Map<Character, Integer> buildCharacterToFrequencyMap(String text) {
		System.out.println("BuildCharacterToFrequency");
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		for (int i = 0; i < text.length(); i++) { //iterating through all the chars in text
			char c = text.charAt(i);
			Integer val = map.get(new Character(c));
			if(val != null){
				map.put(c, new Integer(val + 1)); //increment counter if the char has been used before
			} else{
				map.put(c,1); //else create a new map entry
			}
		}
		System.out.println(map);
		return map;
	}

	/**
	 * Step 2: HuffmanNode class to represent a node in the priorityQueue tree - DONE
	 */
	private class HuffmanNode {
		public char c;
		public int frequency;
		public HuffmanNode leftNode;
		public HuffmanNode rightNode;
		public String binaryCode;

		public HuffmanNode(char c, int frequency, HuffmanNode leftNode, HuffmanNode rightNode, String binaryCode){
			this.c = c;
			this.frequency = frequency;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.binaryCode = binaryCode;
		}
		
	}

	/**
	 * Step 3: Build a min priorityQueue tree of HuffmanNodes from an input map - DOING
	 */
	private PriorityQueue<HuffmanNode> buildPQTree(Map<Character, Integer> freqMap) {
	     Set setOfEntries = freqMap.entrySet(); //get a set of the map's entries
	     Iterator i = setOfEntries.iterator(); //making a hashmap iterator
	     while (i.hasNext()) {
	    	 Map.Entry pairs = (Map.Entry)i.next();
	    	 
	    	 i.remove();
	     }
	     
		
		return null;
	}

	/**
	 * Step 4: Use the PQTree to build another map that maps each character to a binary representation
	 */
	private Map<Character, String> buildCharacterToBinaryMap(PriorityQueue<HuffmanNode> priorityQueue){

		return null;
	}


	/**
	 * Step 5: Generate a String with the characters encoded to Binary
	 */
	private String createEncodedString(String original, Map<Character, String> binaryMap){

		return null;
	}
}
