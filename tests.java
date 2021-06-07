import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class tests {
    
    backend back = new backend();


    // PASSED
    public void testGetTone() {
        // Should print "1, 4, 3, 2, 0" and then throw an error
        String tests[] = {"zhong1", "hua4", "ni3", "shi2", "zhe", "n3i"};
        for (String test : tests) {
            System.out.printf("%d ", back.getIntendedTone(test));
        }
    }

    // PASSED
    public void testSingleConversions() {
        // Should print:
        /**
         * zhōng
         * huà
         * zhe
         * āi
         * nuǎn
         * ér
         * b3i
         */
        String tests[] = {"zhong1", "hua4", "zhe", "ai1", "nuan3", "er2", "b3i"};
        for (String test : tests) {
            System.out.printf("%s\n", back.convertSyllable(test));
        }
    }

    //
    public void testConvert() {
        String output = back.convert("tai2wan1 shi4 di4 yi1 ge");
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.out.printf("%s\n", output);
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }
}
