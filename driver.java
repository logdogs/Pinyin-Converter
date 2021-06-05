import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
public class driver {

    public static void main(String[] args) {
        keyboard k = new keyboard();
        k.run();
        // try {
        //     System.setOut(new PrintStream(System.out, true, "UTF-8"));
        //     System.out.printf("你");
        //     tests tester = new tests();
        //     tester.testConversions();
        // } catch(UnsupportedEncodingException e) { e.printStackTrace(); }
    }    
}
