import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    /**
     * Steps:
     * 1. Parse text file into a Map<Character, Integer> (character to frequency).
     * 2. Create a HuffmanNode class where each node contains char:character, int:frequency, HuffmanNode:leftNode, HuffmanNode:rightNode
     * 3. Build a priority queue using the Map we just created and fill it with HuffmanNodes
     * 4. Build another map but this time we map each character to its binary representation using the priorityQueue
     * 5. Generate a new text file with the encoded text
     */
    public static void main(String[] args) throws IOException {

        String textFile = readFile("src/alice30.txt");
        System.out.println(textFile);

    }

    /**
     * Convert a txt file into a String
     * @param file
     * @return
     * @throws IOException
     */
    private static String readFile( String file ) throws IOException {
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
     * Step 1: Create a Map<Character, Integer> (character to frequency) from the input String
     */
    private Map<Character, Integer> buildCharacterToFrequencyMap(String text) {

        return null;
    }

    /**
     * Step 2: HuffmanNode class to represent a node in the priorityQueue tree
     */
    private class HuffmanNode {
        public char c;
        public int frequency;
        public HuffmanNode leftNode;
        public HuffmanNode rightNode;

        public HuffmanNode(char c, int frequency, HuffmanNode leftNode, HuffmanNode rightNode){
            this.c = c;
            this.frequency = frequency;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    /**
     * Step 3: Build a priorityQueue tree of HuffmanNodes fram an input map
     */
    private PriorityQueue<HuffmanNode> buildPQTree(Map<Character, Integer> freqMap) {

        return null;
    }

}
