import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalculatorGUI extends JFrame implements ActionListener{
    
    public static JFrame frame;
    public static JTextField workspace;

    public JButton b0;
    public JButton b1;
    public JButton b2;
    public JButton b3;
    public JButton b4;
    public JButton b5;
    public JButton b6;
    public JButton b7;
    public JButton b8;
    public JButton b9;
    
    public JButton clear;
    public JButton add;
    public JButton sub;
    public JButton div;
    public JButton mult;
    public JButton dec;
    public JButton equal;

    public double first;
    public double second;
    public String operator;
    public double answer;

    public CalculatorGUI()
    {
        // set up GUI Components
        frame = new JFrame("Calculator");
        frame.setSize(250, 380); // 10 border size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        workspace = new JTextField();
        workspace.setBounds(10, 10, 215, 50);
        workspace.setFont(new Font("Arial", Font.BOLD, 30));
        workspace.setBorder(new LineBorder(Color.BLACK, 5));
        frame.add(workspace);

        Color numberColor = new Color(207, 222, 189);
        Color operationColor = new Color(172, 186, 153); 

        b0 = new JButton("0");
        b0.setBounds(10, 285, 50, 50);
        b0.setBackground(numberColor);
        frame.add(b0);

        b1 = new JButton("1");
        b1.setBounds(10, 230, 50, 50);
        b1.setBackground(numberColor);
        frame.add(b1);

        b2 = new JButton("2");
        b2.setBounds(65, 230, 50, 50);
        b2.setBackground(numberColor);
        frame.add(b2);

        b3 = new JButton("3");
        b3.setBounds(120, 230, 50, 50);
        b3.setBackground(numberColor);
        frame.add(b3);

        b4 = new JButton("4");
        b4.setBounds(10, 175, 50, 50);
        b4.setBackground(numberColor);
        frame.add(b4);

        b5 = new JButton("5");
        b5.setBounds(65, 175, 50, 50);
        b5.setBackground(numberColor);
        frame.add(b5);

        b6 = new JButton("6");
        b6.setBounds(120, 175, 50, 50);
        b6.setBackground(numberColor);
        frame.add(b6);

        b7 = new JButton("7");
        b7.setBounds(10, 120, 50, 50);
        b7.setBackground(numberColor);
        frame.add(b7);

        b8 = new JButton("8");
        b8.setBounds(65, 120, 50, 50);
        b8.setBackground(numberColor);
        frame.add(b8);

        b9 = new JButton("9");
        b9.setBounds(120, 120, 50, 50);
        b9.setBackground(numberColor);
        frame.add(b9);

        dec = new JButton(".");
        dec.setBounds(65, 285, 50, 50);
        dec.setBackground(operationColor);
        frame.add(dec);

        add = new JButton("+");
        add.setBounds(175, 120, 50, 50);
        add.setBackground(operationColor);
        frame.add(add);

        sub = new JButton("-");
        sub.setBounds(175, 175, 50, 50);
        sub.setBackground(operationColor);
        frame.add(sub);

        mult = new JButton("*");
        mult.setBounds(175, 230, 50, 50);
        mult.setBackground(operationColor);
        frame.add(mult);

        div = new JButton("/");
        div.setBounds(175, 285, 50, 50);
        div.setBackground(operationColor);
        frame.add(div);

        equal = new JButton("=");
        equal.setBounds(120, 285, 50, 50);
        equal.setBackground(operationColor);
        frame.add(equal);

        clear = new JButton("AC");
        clear.setBounds(175, 65, 50, 50);
        clear.setBackground(operationColor);
        frame.add(clear);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

        first = 0;
        second = 0;
        operator = "";
    }

    public void actionPerformed(ActionEvent calcEvent)
    {
        // event
        String buttonClick = calcEvent.getActionCommand();
        //double first = 0;
        //double second = 0;
        //String operator = "";

        ArrayList<String> numbers = new ArrayList<String>();
        numbers.add("0");
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");

        if (buttonClick.equals("AC")) // handle CLEAR button
        {
            workspace.setText("");
            first = 0;
            second = 0;
            operator = "";
            answer = 0;
        }

        if (numbers.contains(buttonClick)) // handle numbers
        {
            workspace.setText(workspace.getText() + buttonClick);
        }
        
        if (buttonClick.equals(".")) // handle decimal point
        {
            workspace.setText(workspace.getText() + buttonClick);
        }
        
        if ((buttonClick.equals("+")) || (buttonClick.equals("*")) || (buttonClick.equals("/")) || (buttonClick.equals("-"))) // operand
        {
            first = Double.parseDouble(workspace.getText());
            operator = buttonClick;
            workspace.setText("");
        }
        
        if (buttonClick.equals("="))
        {
            second = Double.parseDouble(workspace.getText());
            
            switch (operator)
            {
                case "+":
                    answer = first + second;
                break;

                case "-":
                    answer = first - second;
                break;

                case "*":
                    answer = first * second;
                break;

                case "/":
                    if (second != 0)
                    {
                        answer = first / second;
                    } else {
                        workspace.setText("Undefined");
                    }
                break;
            }

            workspace.setText(String.valueOf(answer));
            first = answer;
        }

    }

    public static void main(String[] args)
    {

        CalculatorGUI newGui = new CalculatorGUI();

        newGui.b0.addActionListener(newGui);
        newGui.b1.addActionListener(newGui);
        newGui.b2.addActionListener(newGui);
        newGui.b3.addActionListener(newGui);
        newGui.b4.addActionListener(newGui);
        newGui.b5.addActionListener(newGui);
        newGui.b6.addActionListener(newGui);
        newGui.b7.addActionListener(newGui);
        newGui.b8.addActionListener(newGui);
        newGui.b9.addActionListener(newGui);

        newGui.clear.addActionListener(newGui);
        newGui.add.addActionListener(newGui);
        newGui.sub.addActionListener(newGui);
        newGui.div.addActionListener(newGui);
        newGui.mult.addActionListener(newGui);
        newGui.equal.addActionListener(newGui);
        newGui.dec.addActionListener(newGui);
    }
}
