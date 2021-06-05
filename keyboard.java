import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class keyboard extends JFrame implements ActionListener,KeyListener{
    
    private JLabel instructions;
    private JTextField text;
    private final String TITLE = "Pinyin Keyboard";
    private final String INSTRUCTIONS = "";
    private backend back = new backend();
    private boolean syllableFinished;
    private String syllable;

    public keyboard() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,300);
        this.setTitle(TITLE);
        
        this.instructions = new JLabel(INSTRUCTIONS);
        this.text = new JTextField(10);
        this.text.addKeyListener(this);
        this.syllable = "";
        this.syllableFinished = false;

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Set the overall layout of the GUI for the keyboard
        //
        // Set the vertical group for the GUI
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(text)
            .addComponent(instructions)
        );
        // Set the horizontal group for the GUI
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(text)
            .addComponent(instructions)
        );

    }

    // We want to detect when a number has been entered into the text field. Then, we know it's time to parse a syllable. However, we need to filter
    //  out bad input. Think about things like typos of the form "n3i". That should be left as it, and we should resume parsing as normal after we see
    //  either:
    //      1) A space separating syllables
    //      2) 


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {
        // We want to handle the case where we detect a number or whitespace, then we parse what we've seen.
        // so as soon as we hit either one of those, we know we've got a new syllable to parse.
        // But for this to work, we need to keep track of the characters we've seen so far
        switch(e.getKeyChar()) {
            case '1':
            case '2':
            case '3':
            case '4':
                // First we convert
                this.syllable += e.getKeyChar();
                String converted = back.convertSyllable(this.syllable);
                try{
                    System.setOut(new PrintStream(System.out, true, "UTF-8"));
                    System.out.printf("%s --> %s\n", this.syllable, converted);
                }catch (UnsupportedEncodingException err) { err.printStackTrace(); }
                // Second we replace
                String current = this.text.getText();
                current = current.replace(this.syllable,converted);
                this.text.setText(current);
                this.syllable = "";
                break;
            case ' ':
            case '\n':
            case '\t':
            case '\r':
                // This is the case where we have the neutral tone, so no conversion is necessary,
                //  thus we just leave it alone and more on to the next syllable.
                this.syllable = "";
                break;
            default:
                this.syllable += e.getKeyChar();
        }
    }

    public void run() {
        this.setVisible(true);
    }
}
