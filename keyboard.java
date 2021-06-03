import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;

public class keyboard extends JFrame implements ActionListener,KeyListener{
    
    private JLabel instructions;
    private JTextField text;
    private final String TITLE = "Pinyin Keyboard";
    private final String INSTRUCTIONS = "";

    public keyboard() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,300);
        this.setTitle(TITLE);
        
        this.instructions = new JLabel(INSTRUCTIONS);
        this.text = new JTextField(10);

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void run() {
        this.setVisible(true);
    }
}
