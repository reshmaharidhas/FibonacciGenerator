package fibonaccigenerator;

import java.awt.*;
import java.math.BigInteger;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import javax.swing.ImageIcon;
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
 * @author Reshma, ChlorinePentoxide
 */
public class FibonacciGenerator implements ActionListener{
	
    private JFrame jframe;
    private JTextField tf;
    private JLabel label;
    private JTextArea result;
    private JButton buttonGenerate;
    private JScrollPane scroll;
	
    public FibonacciGenerator(){
        jframe = new JFrame();

        Image icon = new ImageIcon(this.getClass().getResource("/icons/fibgenicon.png")).getImage();      
      
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
	
    public BigInteger fibon(int n){
        if(n==1){
            return BigInteger.ZERO;
        }
        if(n==2){
            return BigInteger.ONE;
        }
        BigInteger fnum1=BigInteger.ZERO;
        BigInteger fnum2=BigInteger.ONE;
        BigInteger num=BigInteger.ZERO;
        if(n>=2 && n<=150000){
            for(int i=2;i<n;i++){
                num = fnum1.add(fnum2);
                fnum1 = fnum2;
                fnum2 = num;
            }
        }
        return num;
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
                int inputnum = Integer.parseInt(text);
                if(inputnum<=0){
                    JOptionPane.showMessageDialog(jframe,"Enter positive number");
                    result.setText("");
                }
		else if(inputnum>150000){
                    JOptionPane.showMessageDialog(jframe,"Enter number within range");
                    result.setText("");
                }
                else{
                    BigInteger fib = fibon(inputnum);
                    result.setText(fib.toString());
                }
            }
        } catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(jframe,"Enter only numbers");
        }
    }   
}
