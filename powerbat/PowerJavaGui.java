import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*; //SimpleDateFormat
import java.util.*; //Date
 
public class PowerJavaGui{
	private JFrame myFrame;
	private JPanel myPanel;
	private JButton goBtn;
    private JLabel scriptLabel;
    private JLabel fileLabel;
    private JTextField fileTxt;
	private JComboBox<String> scriptComboBox;
    private GroupLayout layout;
	private BoxLayout frmLayout;
	private FlowLayout flLayout;
	static int counter;
	String[] xStrings = { "Inactive for 60 days",  "Inactive for 90 days"};

	public PowerJavaGui () { 
		myFrame = new JFrame();
		frmLayout = new BoxLayout (myFrame, BoxLayout.Y_AXIS);
		myPanel = new JPanel();
		layout = new GroupLayout(myPanel);
		myPanel.setLayout(layout);
		goBtn = new JButton ("Go! ");
		goBtn.addActionListener(new ButtonListener());
		scriptLabel = new JLabel ("Pick a script ->");
		fileLabel = new JLabel ("Set name of output file ->");
		fileTxt = new JTextField ("Name of file");
		fileTxt.setForeground(Color.GRAY);
		fileTxt.addMouseListener(new MyMouseAdapter1());
		scriptComboBox = new JComboBox (xStrings);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addComponent(scriptLabel)
			.addComponent(scriptComboBox)
			.addComponent(fileLabel)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(fileTxt)
					.addComponent(goBtn)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(scriptLabel)
					.addComponent(scriptComboBox)
					.addComponent(fileLabel)
					.addComponent(fileTxt))
				.addComponent(goBtn));
		myFrame.getContentPane().add(myPanel);
		myFrame.setSize(600,120);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("My tiny little csv maker");
		myFrame.setIconImage(new ImageIcon("res/flower.png").getImage());
		myFrame.setVisible(true);
	}
	
		class MyMouseAdapter1 extends MouseAdapter{
			public void mouseClicked(MouseEvent e) {
				fileTxt.setText("");
			}
		}
		
		class ButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent evt) {
				go(evt);
			}
	}
	
	public void go (ActionEvent evt) {
		/* Ovdje dodati kontrole ako je temp prazan, ili "Name of file" ili ima-nema extenziju csv */
		String temp = fileTxt.getText();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//	temp.toLowerCase().
		if (temp.isEmpty() || temp.toLowerCase().contains("name of file")) {
			counter++;
			temp = "output_" + timeStamp + ".csv";
		}
		
		if (!temp.toLowerCase().contains(".csv")) {
			temp=temp + ".csv";
		}
			
		String temp2 = scriptComboBox.getSelectedItem().toString();
		new PowerJava(temp2,temp);
	}

}