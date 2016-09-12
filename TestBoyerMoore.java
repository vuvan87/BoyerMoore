import java.io.IOException;

/**
 * Created by Van Vu  on 7/21/2015.
 */
public class TestBoyerMoore {
    public static void main(String[] args) throws IOException {
        BoyerMoore test1 = new BoyerMoore();
        String text = new String();
        try {
            text = test1.inputFile("test.txt");
        } catch (IOException e) {
            e.getMessage();
        }
        String pattern = "Atlanta";
        test1.boyerSearch(text, pattern);
        test1.printIndex();
        BoyerMoore test2 = new BoyerMoore();
        String text2 = "I liveain Atlanta city";
        String pattern2 = "Atlanta";
        test2.boyerSearch(text2, pattern2);
        test2.printIndex();
    }
}
