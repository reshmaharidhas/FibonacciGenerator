package fibonaccigenerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Reshma
 */
public class FibonacciGenerator implements ActionListener{
    JFrame jframe;
    JTextField tf;
    JLabel label;
    JTextArea result;
    JButton buttonGenerate;
    JScrollPane scroll;
    FibonacciGenerator(){
        jframe = new JFrame();
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Documents\\NetBeansProjects\\FibonacciGenerator\\src\\icons\\fibgenicon.png");       
        Color c = new Color(204,255,255);
        jframe.getContentPane().setBackground(c);
        label = new JLabel("Enter number to generate");
        label.setFont(new Font(Font.SERIF,Font.PLAIN,19));
        label.setBounds(210,50,250,30);
        tf = new JTextField();
        tf.setToolTipText("Enter number between 1 to 150000");
        tf.setBounds(250,100,100,30);
        buttonGenerate = new JButton("Generate");
        buttonGenerate.setBounds(240,150,120,45);
        buttonGenerate.setBackground(Color.BLACK);
        buttonGenerate.setForeground(Color.white);
        buttonGenerate.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        buttonGenerate.addActionListener(this);
        result = new JTextArea(10,20);
        result.setAlignmentX(SwingConstants.CENTER);
        result.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        result.setLineWrap(true);
        result.setEditable(false);
        scroll = new JScrollPane(result,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(60,240,480,250);
        jframe.add(scroll);
        jframe.add(tf);
        jframe.add(label);
        jframe.add(buttonGenerate);
        jframe.setSize(600,620);
        jframe.setTitle("Fibonacci Generator");
        jframe.setIconImage(icon);
        jframe.setLayout(null);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
    }
    public BigInteger generateFib(int n){     
        if(n==1){
            return BigInteger.ZERO;
        }
        if(n==2){
            return BigInteger.ONE;
        }
        BigInteger fnum1=BigInteger.ZERO;
        BigInteger fnum2=BigInteger.ONE;
        BigInteger[] fibonaccinums=new BigInteger[n];
        fibonaccinums[0] = fnum1;
        fibonaccinums[1] = fnum2;
        if(n>=2 && n<=150000){
            for(int i=2;i<n;i++){
                BigInteger num1 = fibonaccinums[i-1];
                BigInteger num2 = fibonaccinums[i-2];
                BigInteger num = num1.add(num2);
                fibonaccinums[i] = num;
            }
        }
        return fibonaccinums[(int)n-1];
    }
    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } 
        catch (Exception e) { 
            System.out.println("Look and Feel not set"); 
        }
        FibonacciGenerator fibonaccigen = new FibonacciGenerator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String text = tf.getText();
            if(!text.equals("")){
                int index = Integer.parseInt(text);
                if(index<=0){
                    JOptionPane.showMessageDialog(jframe,"Enter positive number");
                    result.setText("");
                }
                else{
                    BigInteger fib = generateFib(index);
                    result.setText(fib.toString());
                }
            }
        } catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(jframe,"Enter only numbers");
        }
    }   
}
