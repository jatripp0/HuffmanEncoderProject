package edu.frostburg.COSC310.TrippJohnathan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Driver class for the Huffman Encoding Tree project
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
public class Driver {

    //map used for storing the character/code pairs that are generated using the tree
    public static Map<Character, String> lookupTable = new HashMap<>();
    
    /**
     * Main method for the Driver class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f1;
        BufferedReader br;
        //Stores the set of characters present in the file
        ArrayList<Character> characterSet = new ArrayList<>();
        //Used to store the Huffman Tree nodes as a queue
        LinkedList<BinaryTree<Character>> freqTable;
        //Stores the set of character/frequency pairs
        Map<Character, Integer> frequency = new HashMap<>();
        try {
            if(args.length > 0){
                //attempts to retrieve the input file from the method arguments
                f1 = new File(args[0]);
            } else { //throws exception if arguments are improperly formatted
                throw new IOException("File Not Found: Incorrect parameters. Please try again.");
            }
            br = new BufferedReader(new FileReader(f1));
        
            while(br.ready()){ //adds the characters of the file to a list without duplicates
                char c = (char)br.read();
                if(!characterSet.contains(c)){
                    characterSet.add(c);
                }
            }
            freqTable = new LinkedList<>();
            for(char c : characterSet){
                //creates a Huffman tree node for storing the character and its frequency
                BinaryTree bt = new BinaryTree();
                bt.addRoot(c, 0);
                int count = 0;
                br = new BufferedReader(new FileReader(f1));
                while(br.ready()){ //counts frequency of the character
                    char ch = (char)br.read();
                    if(c == ch)
                        count++;
                }
                bt.root.setFrequency(count);
                freqTable.add(bt);
                frequency.put(c, count);
            }
            Collections.sort(freqTable, new FreqComp()); //sorts the frequency table from least to most frequently occurring 
            BinaryTree<Character> huffmanTree;
            while(freqTable.size() > 1){
                //removes the two least frequent character nodes and joins them together
                BinaryTree<Character> b1 = freqTable.removeFirst();
                BinaryTree<Character> b2 = freqTable.removeFirst();
                BinaryTree<Character> newRoot = new BinaryTree<>();
                newRoot.addRoot('#', b1.root.getFrequency() + b2.root.getFrequency());
                newRoot.attach(newRoot.root, b1, b2);
                //re-inserts the newly formed tree back into the queue
                freqTable.addFirst(newRoot);
                //queue is re-sorted
                Collections.sort(freqTable, new FreqComp());
            }
            huffmanTree = freqTable.get(0); //stores the final huffman tree
            
            //generates the code pairings for the characters using a recursive method 
            lookupTable(huffmanTree.root, "");
            
            //Prints the results of the encoding
            br = new BufferedReader(new FileReader(f1));
            String text = "";
            while(br.ready()){
                text += br.readLine();
            }
            System.out.println("Frequency Table\n---------------");
            System.out.println("Char\t|Code\t\t|Frequency");
            for(char c : characterSet){
                System.out.print(c+"\t|"+lookupTable.get(c)+"\t\t|"+frequency.get(c)+"\n");
                text = text.replaceAll(Character.toString(c), lookupTable.get(c));
            }
            System.out.print("\nEncoded Text:\n------------\n");
            System.out.println(text);
            
            br.close();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Method to generate the encoded values of the Huffman tree leaf nodes
     * @param n the node to begin with
     * @param code the binary code for the character
     */
    public static void lookupTable(Node<Character> n, String code){
        if(n.isExternal(n)){ //if node is a leaf node, its value and code are placed in the table
            lookupTable.put(n.getElement(), code);
        } else { //if node is internal, recursively call the method to perform left and right traversals, building the code
            lookupTable(n.getLeft(), code + "0"); //add 0 to the code for left traversals
            lookupTable(n.getRight(), code + "1"); //add 1 to the code for right traversals
        }
    }
}

/**
 * Simple class to implement comparator for sorting the frequency table
 * @author Johnathan Tripp (╯°□°）╯︵ ┻━┻
 */
class FreqComp implements Comparator<BinaryTree<Character>>{
 
    /**
     * Compares two Huffman tree nodes based on their frequency
     * @param t1 the first Huffman tree node
     * @param t2 the second Huffman tree node
     * @return 1 if the first frequency is greater than the second, -1 otherwise
     */
    @Override
    public int compare(BinaryTree<Character> t1, BinaryTree<Character> t2) {
        if(t1.root.getFrequency() > t2.root.getFrequency()){
            return 1;
        } else {
            return -1;
        }
    }
}
