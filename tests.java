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

    //
    public void testConversions() {
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
}
