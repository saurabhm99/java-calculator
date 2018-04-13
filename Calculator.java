import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
Provides interfaces and classes for dealing with different types of events fired by AWT components.
See the java.awt.AWTEvent class for details on the AWT event model.
Events are fired by event sources.
An event listener registers with an event source to receive notifications about the events of a particular type.
This package defines events and event listeners, as well as event listener adapters, which are convenience classes to make easier the process of writing event listeners.
 */
class Calculator extends JFrame {
	/**
	 * The javax.swing.JFrame class is a type of container which inherits the java.awt.Frame class.
	 *  JFrame works like the main window where components like labels, buttons, textfields are added to create a GUI.
	 */
	
	
    private final Font BIGGER_FONT = new Font("monspaced",Font.PLAIN, 30);
    private JTextField textfield;
    private boolean number = true;
    private String equalOp = "=";
    private CalculatorOp op = new CalculatorOp();
    
    public Calculator() {
        textfield = new JTextField("", 22);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setFont(BIGGER_FONT);
        ActionListener numberListener = new NumberListener();
        String buttonOrder = "1234567890 ";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5, 5, 5));
        for (int i = 0; i < buttonOrder.length(); i++) {
            String key = buttonOrder.substring(i, i+1);
            if (key.equals(" ")) {
                buttonPanel.add(new JLabel(""));
            } else {
                JButton button = new JButton(key);
                button.addActionListener(numberListener);
                button.setFont(BIGGER_FONT);
                buttonPanel.add(button);
            }
        }
        ActionListener operatorListener = new OperatorListener();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 5, 5));
        String[] opOrder = {"+", "-", "*", "/","=","C","sin","cos","log"};
        for (int i = 0; i < opOrder.length; i++) {
            JButton button = new JButton(opOrder[i]);
            button.addActionListener(operatorListener);
            button.setFont(BIGGER_FONT);
            panel.add(button);
        }
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout(4, 4));
        pan.add(textfield, BorderLayout.SOUTH );
        pan.add(buttonPanel , BorderLayout.CENTER);
        pan.add(panel , BorderLayout.EAST);
        this.setContentPane(pan);
        this.pack();
        this.setTitle("great calc");
        this.setResizable(true);
    }
    private void action() {
        number = true;
        textfield.setText("");
        equalOp = "=";
        op.setTotal("");
    }
    class OperatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String displayText = textfield.getText();
            if (e.getActionCommand().equals("sin"))
            {
                textfield.setText("" + Math.sin(Double.valueOf(displayText).doubleValue()));
                
            }else
            if (e.getActionCommand().equals("cos"))
            {
                textfield.setText("" + Math.cos(Double.valueOf(displayText).doubleValue()));
                
            }
            else
            if (e.getActionCommand().equals("log"))
            {
                textfield.setText("" + Math.log(Double.valueOf(displayText).doubleValue()));
                
            }
            else if (e.getActionCommand().equals("C"))
            {
                textfield.setText("");
            }
 
            else
            {
                if (number)
                {
                    
                    action();
                    textfield.setText("");
                    
                }
                else
                {
                    number = true;
                    if (equalOp.equals("="))
                    {
                        op.setTotal(displayText);
                    }else
                    if (equalOp.equals("+"))
                    {
                        op.add(displayText);
                    }
                    else if (equalOp.equals("-"))
                    {
                        op.subtract(displayText);
                    }
                    else if (equalOp.equals("*"))
                    {
                        op.multiply(displayText);
                    }
                    else if (equalOp.equals("/"))
                    {
                        op.divide(displayText);
                    }
                    
                    textfield.setText("" + op.getTotalString());
                    equalOp = e.getActionCommand();
                }
            }
        }
    }
    class NumberListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String digit = event.getActionCommand();
            if (number) {
                textfield.setText(digit);
                number = false;
            } else {
                textfield.setText(textfield.getText() + digit);
            }
        }
    }
    public class CalculatorOp {
        private int total;
        public CalculatorOp() {
            total = 0;
        }
        public String getTotalString() {
            return ""+total;
        }
        public void setTotal(String n) {
            total = convertToNumber(n);
        }
        public void add(String n) {
            total += convertToNumber(n);
        }
        public void subtract(String n) {
            total -= convertToNumber(n);
        }
        public void multiply(String n) {
            total *= convertToNumber(n);
        }
        public void divide(String n) {
            total /= convertToNumber(n);
        }
        private int convertToNumber(String n) {
            return Integer.parseInt(n);
        }
    }
}
class SwingCalculator {
    public static void main(String[] args) {
        JFrame frame = new Calculator();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
//it will an exception if you put first sin or cos button first 
//saurabh m
