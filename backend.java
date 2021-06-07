import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class backend {
    
    // Dictionaries for quick look-ups to determine where the tone marker should go on a pinyin syllable
    // For example, zhong would go in firstVowelFinals because the tone marker goes over the 'o'
    private ArrayList<ArrayList<String>> firstVowelFinalList;
    private ArrayList<ArrayList<String>> secondVowelFinalList;

    // For ease of access, these are intuitive arrays where you enter the tone number, and it gives you the proper character
    private char[] aTones = {'a', 'ā', 'á', 'ǎ', 'à'};
    private char[] eTones = {'e', 'ē', 'é', 'ě', 'è'};
    private char[] iTones = {'i', 'ī', 'í', 'ǐ', 'ì'};
    private char[] oTones = {'o', 'ō', 'ó', 'ǒ', 'ò'};
    private char[] uTones = {'u', 'ū', 'ú', 'ǔ', 'ù'};
    private char[] vTones = {'ü', 'ǖ', 'ǘ', 'ǚ', 'ǜ'}; // For the u with an umlaut since this is input with 'v' since 'v' doesn't exist in Chinese


    public backend() {
        String[] fvfInit4 = {"a", "e", "i", "o", "u", "v"};
        String[] fvfInit3 = {"ai", "an", "ao", "ei", "en", "er", "yi", "ya", "ye", "in", "ou", "wu", "wa", "un", "wo", "yu"};
        String[] fvfInit2 = {"ang", "eng", "yan", "yao", "yin", "ing", "you", "ong", "wai", "wan", "wei", "wen", "yun"};
        String[] fvfInit1 = {"yang", "ying", "yong", "wang", "weng"};
        String[] svfInit4 = {};
        String[] svfInit3 = {"ia", "ie", "iu", "ua", "ui", "uo", "ve", "ue"};
        String[] svfInit2 = {"ian", "iao", "uai", "uan", "uan", "yue"};
        String[] svfInit1 = {"iang", "iong", "uang"};
        
        firstVowelFinalList = new ArrayList<ArrayList<String>>();
        firstVowelFinalList.add(new ArrayList<String>(Arrays.asList(fvfInit1)));
        firstVowelFinalList.add(new ArrayList<String>(Arrays.asList(fvfInit2)));
        firstVowelFinalList.add(new ArrayList<String>(Arrays.asList(fvfInit3)));
        firstVowelFinalList.add(new ArrayList<String>(Arrays.asList(fvfInit4)));

        secondVowelFinalList = new ArrayList<ArrayList<String>>();
        secondVowelFinalList.add(new ArrayList<String>(Arrays.asList(svfInit1)));
        secondVowelFinalList.add(new ArrayList<String>(Arrays.asList(svfInit2)));
        secondVowelFinalList.add(new ArrayList<String>(Arrays.asList(svfInit3)));
        secondVowelFinalList.add(new ArrayList<String>(Arrays.asList(svfInit4)));

    }

    // Methods for conversion

    // First, find out what tone is intended from a single syllable
    public int getIntendedTone(String syllable) {
        // Need to do some error checking as to not give bad output from bad input
        if (syllable.contains("1") || syllable.contains("2") || syllable.contains("3") || syllable.contains("4")) {
            // First check to make sure that the number indicating the tone is the last character in the string (i.e. it's good input)
            int lastCharIndex = syllable.length()-1;
            switch(syllable.charAt(lastCharIndex)) {
                case '1':
                    return 1;
                case '2':
                    return 2;
                case '3':
                    return 3;
                case '4':
                    return 4;
                default:
                    System.out.printf("Error in getIntendedTone(), given a bad input string. Detected a digit in [1,4] but it wasn't the last character.\n");
                    System.exit(0);
                    return -1;
            }
        } else {
            return 0;
        }
    }

    // Chop the tone number
    public String chopToneNumber(String syllable) {
        if (syllable.contains("1") || syllable.contains("2") || syllable.contains("3") || syllable.contains("4")) {
            return syllable.substring(0,syllable.length()-1);
        } else {
            return syllable;
        }
    }

    // Find the first vowel in a string and replace it with the appropriate tone-marked equivalent
    public String replaceFirstVowel(String syllable, int tone) {
        for (int i = 0; i < syllable.length(); i++) {
            switch(syllable.charAt(i)) {
                case 'a':
                    return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(aTones[tone]));
                case 'e':
                    return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(eTones[tone]));
                case 'i':
                    return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(iTones[tone]));                
                case 'o':
                    return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(oTones[tone]));
                case 'u':
                    return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(uTones[tone]));
                case 'v':
                    return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(vTones[tone]));
            }
        }
        return syllable;
    }
    // Equivalent method but for the second vowel
    public String replaceSecondVowel(String syllable, int tone) {
        boolean firstVowelFound = false;
        ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'v'));
        for (int i = 0; i < syllable.length(); i++) {
            if (!firstVowelFound) {
                if (vowels.contains(syllable.charAt(i))) {
                    firstVowelFound = true;    
                }
            } else {
                switch(syllable.charAt(i)) {
                    case 'a':
                        return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(aTones[tone]));
                    case 'e':
                        return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(eTones[tone]));
                    case 'i':
                        return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(iTones[tone]));
                    case 'o':
                        return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(oTones[tone]));
                    case 'u':
                        return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(uTones[tone]));
                    case 'v':
                        return syllable.replaceFirst(Character.toString(syllable.charAt(i)), Character.toString(vTones[tone]));
                }
            }
        }        
        return syllable;
    }

    // Need to now replace the appropriate letter of the original string with the same letter and a tone mark (or the u with umlaut in the case of v)
    public String convertSyllable(String syllable) {
        String retval = "";
        // Retreive the tone of the syllable
        int tone = getIntendedTone(syllable);

        // Chop the tone number if there is one
        String chopped = chopToneNumber(syllable);
        
        for (int i = 0; i < firstVowelFinalList.size(); i++) {
            for (int j = 0; j < firstVowelFinalList.get(i).size(); j++) {
                if (chopped.contains(firstVowelFinalList.get(i).get(j))) {
                    retval = replaceFirstVowel(chopped,tone);
                }
            }
        }
        for (int i = 0; i < secondVowelFinalList.size(); i++) {
            for (int j = 0; j < secondVowelFinalList.get(i).size(); j++) {
                if (chopped.contains(secondVowelFinalList.get(i).get(j))) {
                    retval = replaceSecondVowel(chopped,tone);
                }
            }
        }

        return retval;
    }

    // Now time for the big one, converting an entire text!
    // The main idea here is as follows:
    //      1) Clean the input (trim leading and trailing whitespace)
    //      2) Split up the input into blocks
    //          BLOCK: One or more syllables internally separated by numbers, externally separated by whitespace
    //      3) Split blocks into syllables, parse the syllables, and recombine
    //      4) Recombine the split blocks into the converted text
    public String convert(String input) {
        String cleaned = input.trim();
        String[] blocks = cleaned.split("\\s");
        ArrayList<String> convertedBlocks = new ArrayList<String>(blocks.length);    
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            for (String block : blocks) {
                String convertedBlock = "";
                // We need to split but inclusively; that is we want to split but include the delimiters
                String[] syllables = block.split("((?<=1)|(?<=2)|(?<=3)|(?<=4))");
                for (int i = 0; i < syllables.length; i++) {
                    // System.out.printf("Syllable %d of block '%s' == %s\n", i, block, syllables[i]);
                    convertedBlock += convertSyllable(syllables[i]);
                }
                convertedBlocks.add(convertedBlock);
            }
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }

        String output = "";
        for (int i = 0; i < convertedBlocks.size(); i++) {
            output += convertedBlocks.get(i);
            if (i < convertedBlocks.size()-1) {
                output += " ";
            }
        }

        return output;
    }

}
