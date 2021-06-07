import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;

public class keyboard extends JFrame implements ActionListener,KeyListener{
    
    private JLabel instructions;
    private JButton convert;
    private JTextField input, output;
    private final String TITLE = "Pinyin Keyboard";
    private final String INSTRUCTIONS = "";
    private final String BUTTON_LABEL = "Convert";
    private backend back = new backend();
    // private String syllable;

    public keyboard() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,300);
        this.setTitle(TITLE);
        
        this.instructions = new JLabel(INSTRUCTIONS);
        this.input = new JTextField(10);
        this.output = new JTextField();
        this.output.setEditable(false);
        this.input.addKeyListener(this);
        this.convert = new JButton(BUTTON_LABEL);
        this.convert.addActionListener(this);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Set the overall layout of the GUI for the keyboard

        // Set the vertical group for the GUI
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(input)
                .addComponent(output)
                .addComponent(convert)
            )
            .addComponent(instructions)
        );
        // Set the horizontal group for the GUI
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(input)
                .addComponent(output)
                .addComponent(convert)
            )
            .addComponent(instructions)
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.convert) {
            String converted = back.convert(this.input.getText());
            this.output.setText(converted);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            String converted = back.convert(this.input.getText());
            this.output.setText(converted);
        }
    }

    public void run() {
        this.setVisible(true);
    }
}
