import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Van Vu 2230 on 7/21/2015.
 * Boyer Moore searching algorithm
 */
public class BoyerMoore {
    private HashMap<Character, Integer> mapPattern; //last table
    private int count;
    private ArrayList<Integer> index;
    public BoyerMoore() {
        mapPattern = new HashMap<>();
        count = 0;
        index = new ArrayList<>();
    }
    public HashMap<Character, Integer> getMapPattern() {
        return mapPattern;
    } // last table
    public int getCount() {
        return count;
    }
    public ArrayList<Integer> getIndex() {
        return index;
    }
    /*
    * map each char to its index 
    * @param pattern
     */
    private void makeTable(String pattern) { 
        int value;
        char key;
        for (int i = 0; i < pattern.length(); i++) {
            key = pattern.charAt(i);
            value = pattern.lastIndexOf(key);
            mapPattern.put(key, value);
            System.out.println(mapPattern.get(key));

        }
    }
    /*
    Boyer Moor searching
    @param text is the text searching on
    @param pattern look up in the text
    @return index of pattern occur in the text
    A - 0
    T - 5
    L - 2
    A - 6
    N - 4
    T - 5
    A - 6

     */
    public ArrayList<Integer> boyerSearch(String text, String pattern) {
        // finish searching, return list of matched indexes
        if (pattern.length() == 0 || text.length() < pattern.length()) {
            index.add(-1);
            return index;
        }
        // Hashtable of searching pattern (last table)
        makeTable(pattern); 
        int indexPattern = pattern.length() - 1; //6
        int indexText = pattern.length() - 1; //6
        int length = pattern.length() - 1; //6
        int getIndex; 
        boolean check;
        while (indexPattern > 0 && indexText < text.length()) {
            if (text.charAt(indexText) == (pattern.charAt(indexPattern))) {
                indexText--;
                indexPattern--;
                if (indexPattern == 0 && (indexPattern + pattern.length()) < text.length() ) {
                //still have text in string, keep searching
                    index.add(indexText);
                    indexText = indexText + pattern.length();
                    indexPattern = pattern.length() - 1;
                    count++;
                } else if (indexPattern == 0 && (indexPattern + pattern.length()) > text.length()) {
                    // found a match, add to output
                    index.add(indexText);
                }
            } else { // not match -> shift follow Bad character Boyer Moore rule
                check = mapPattern.containsKey(text.charAt(indexText));
                 // check if the key is in the map
                if (check) { //case the key is in the map
                    getIndex = mapPattern.get(text.charAt(indexText)); // get the index
                    System.out.println("get index is" + getIndex);
                    indexText = indexText + (length - getIndex); // shift amount of (pattern length - index)
                } else { // case key is not in the map, shift all the way pass that letter
                    indexText = indexText + pattern.length();
                }
                //if (indexPattern != (pattern.length() -1)) {
                indexPattern = pattern.length() - 1;
                // reset index of pattern after shifting
            }
        }
        if (indexPattern != length) {
            return index; // all strings are matched
        }
        index.add((-1));
        return index; // can not find the pattern
    }

    /*
    print out index where matching found
     */
    public void printIndex() {
        System.out.println("There are " + getCount() + " matching(s) found");
        if (getCount() != 0) {
            System.out.println("Matching(s) are found at index: ");
            for (int e = 0; e < getIndex().size() - 1; e++) {
                System.out.println(index.get(e));
            }
        }
    }
    /*
    Read the text file
    @param filename
     */
    public String inputFile(String filename) throws IOException {
        BufferedReader searchDoc = new BufferedReader(new FileReader(filename));
        try {
            String searchLine = searchDoc.readLine();
            StringBuilder searchText = new StringBuilder();
            while (searchLine != null) {
                searchText.append(searchLine);
                searchLine = searchDoc.readLine();
            }
            String output = searchText.toString();
            return output;
        } finally {
            searchDoc.close();
        }
    }
}

