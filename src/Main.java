import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Main {

    static String tree = "";
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
        //System.out.println(textFile);
		Map<Character, Integer> freqMap = buildCharacterToFrequencyMap(textFile);
        HuffmanNode headNode = buildPQTTree(freqMap);
        assignBinary(headNode, "");
        Map<Character, String> bMap = buildCharacterToBinaryMap(headNode);
        String result = createEncodedString(textFile, bMap);
       // System.out.println("DONE: comprcessed string is " + result);
        System.out.println("tree representation: " + tree);
        System.out.println("\nSize of original string is " + textFile.length() + " bytes");
        System.out.println("size of tree is " + tree.length()  + " bytes");
        System.out.println("Size of binary string is " + result.length() / 8 + " bytes");
        System.out.println("\n% Compression = " + ((double) result.length() / 8) / textFile.length() * 100);


        String decoded = decode(result, bMap);
        System.out.println("decoded = \n" + decoded);
	}

    private static String decode(String result, Map<Character, String> binaryMap) {
        StringBuilder  stringBuilder = new StringBuilder();
        int counter = 0;
        String temp = "";
        while( counter < result.length()) {
            temp = temp + result.charAt(counter);
            String character = getKeyByValue(binaryMap, temp);
            if(character == null) {
                counter++;
            } else {
                stringBuilder.append(character);
                temp = "";
                counter++;
            }
        }
        return stringBuilder.toString();
    }

    private static String getKeyByValue(Map<Character, String> binaryMap, String temp) {
        for (Entry<Character, String> entry : binaryMap.entrySet()) {
            if (temp.equals(entry.getValue())) {
                return entry.getKey().toString();
            }
        }
        return null;
    }

    /**
	 * Convert a txt file into a String - DONE
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
	//helper method to compare which node has higher frequency
	private static class HuffManComparator implements Comparator<HuffmanNode> { 
		public int compare(HuffmanNode node1, HuffmanNode node2) {
			return node1.frequency - node2.frequency;
		}
	}

	// helper method for adding to the priority queue of HuffmanNodes
	private static Queue<HuffmanNode> createNodeQueue(Map<Character, Integer> map) {
		final Queue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(10000, new HuffManComparator());
		Main hi = new Main(); //because you're trying to create an instance of an inner class -_-
		for (Entry<Character, Integer> entry : map.entrySet()) {
			pq.add(hi.new HuffmanNode(entry.getKey(), entry.getValue(), null, null, null));
		}
		return pq;
	}

	//building the PQTree
	private static HuffmanNode buildPQTTree(Map<Character, Integer> freqMap) {
		final Queue<HuffmanNode> nodeQueue = createNodeQueue(freqMap);
		Main hi = new Main(); //because you're trying to create an instance of an inner class -_-
		while (nodeQueue.size() > 1) {
			final HuffmanNode node1 = nodeQueue.remove();
			final HuffmanNode node2 = nodeQueue.remove();
			HuffmanNode node = hi.new HuffmanNode('\0', node1.frequency + node2.frequency, node1, node2, null);
			nodeQueue.add(node);
		}
		// remove it to prevent object leak.
		return nodeQueue.remove();
	}


    /**
     * Step 3.5: assign binary code to each node
     */
    private static void assignBinary(HuffmanNode node, String code) {
        if(node.leftNode == null && node.rightNode == null){
            node.binaryCode = code;
           // return;
        } else {
            assignBinary(node.leftNode, code + "0");
            assignBinary(node.rightNode, code + "1");
        }
    }

	/**
	 * Step 4: Use the PQTree to build another map that maps each character to a binary representation - DONE
	 */
	private static Map<Character, String> buildCharacterToBinaryMap(HuffmanNode node){
		HashMap<Character, String> bMap = new HashMap<Character, String>();
		iterateTree(node, bMap);

		return bMap;
	}

    private static void iterateTree(HuffmanNode node, HashMap<Character, String> bMap) {
        if(node.leftNode == null && node.rightNode == null){
            bMap.put(node.c, node.binaryCode);
            System.out.print(node.c + ": "+ node.frequency);
            tree = tree + "1" + Integer.toBinaryString((int) node.c);
            return;
        } else {
            tree = tree + "0";
            iterateTree(node.leftNode, bMap);
            iterateTree(node.rightNode, bMap);
        }
    }

	/**
	 * Step 5: Generate a String with the characters encoded to Binary - DONE
	 */
	private static String createEncodedString(String original, Map<Character, String> binaryMap){
		StringBuilder  stringBuilder = new StringBuilder();
		for (int i = 0; i < original.length(); i++) {
			char c = original.charAt(i);
			String bCode = binaryMap.get(c);
			stringBuilder.append(bCode);
		}
		return stringBuilder.toString();
	}
}
