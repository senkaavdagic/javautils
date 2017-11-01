import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Konverter1 extends JFrame {
	private JPanel myPanel;
	private JButton convBtn;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JTextField quantityTxt;
    private JTextField resultTxt;
	private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
	private GroupLayout layout;
	String[] xStrings = { "Fahrenheit", "Celzius" };
	
	// Inicialize GUI 
	public Konverter1 () {
		myPanel = new JPanel();
		layout = new GroupLayout(myPanel);
		myPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		convBtn = new JButton("GO!");
		convBtn.addActionListener(new ButtonListener());
		fromLabel = new JLabel("From:");
		toLabel = new JLabel("To:");
		quantityTxt = new JTextField("quantity");
		quantityTxt.setForeground(Color.GRAY);
		resultTxt = new JTextField("result");
		resultTxt.setForeground(Color.GRAY);
		quantityTxt.addMouseListener(new MyMouseAdapter1());
		resultTxt.addMouseListener(new MyMouseAdapter2());
		fromComboBox = new JComboBox(xStrings);
		fromComboBox.setForeground(Color.BLACK);
		toComboBox = new JComboBox(xStrings);
		toComboBox.setForeground(Color.BLACK);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addComponent(fromLabel)
			.addComponent(fromComboBox)
			.addComponent(quantityTxt)
			.addComponent(toLabel)
			.addComponent(toComboBox)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(resultTxt)
					.addComponent(convBtn)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(fromLabel)
					.addComponent(fromComboBox)
					.addComponent(quantityTxt)
					.addComponent(toLabel)
					.addComponent(toComboBox)
					.addComponent(resultTxt))
				.addComponent(convBtn));
		getContentPane().add(myPanel);
		setSize(600,120);
		setLocationRelativeTo(null);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setTitle("My tiny little conversion calculator");
		setIconImage(new ImageIcon("res/flower.png").getImage());
		setVisible(true);
		}
	
	class ButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			go(evt);
		}
	}
	
	class MyMouseAdapter1 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
		quantityTxt.setText("");
		}
		}
	
	class MyMouseAdapter2 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
		resultTxt.setText("");
		}
		}
	
	//implement conversion
	public void go (ActionEvent evt) { 
		if ((fromComboBox.getSelectedItem().toString()=="Fahrenheit")&&(toComboBox.getSelectedItem().toString()=="Celzius")) {
				int tempCel = (int)(((Double.parseDouble(quantityTxt.getText())) - 32)/1.8);
				resultTxt.setText(tempCel + " Celzius");
			}
		else if ((fromComboBox.getSelectedItem().toString()=="Celzius")&&(toComboBox.getSelectedItem().toString()=="Fahrenheit")){
				int tempFahr = (int)((Double.parseDouble(quantityTxt.getText())) * 1.8 + 32);
				resultTxt.setText(tempFahr + " Fahrenheit");
			}
		else {
			resultTxt.setText(quantityTxt.getText());
		} 
	}

	public static void main (String [] arg) {
		new Konverter1();
	
	}
}