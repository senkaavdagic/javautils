//package fcdemo2;
/* Add log file append and rotate .... for all user actions in the format timestamp: user action 
*/
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;


public class BuildGui  {
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel openPanel;
	private JPanel regexPanel;
	private JPanel goPanel;
	private JPanel outputPanel;
	private JButton openButton;
	private JButton goButton;
	private JLabel pickLabel;
	private JLabel regexLabel;
	private JTextArea regexTA;
	private JTextArea outputTA;
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	private JScrollPane logScrollPane;
	private JFileChooser fc;
	private File file;
	private LineNumberReader r;
	
	public BuildGui () { // Constructor
		// Initialize all the panels:
		mainPanel = new JPanel ();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		openPanel = new JPanel ();
		regexPanel = new JPanel();
		goPanel = new JPanel();
		outputPanel = new JPanel(new BorderLayout());
		
		// Initialize components of openPanel
		pickLabel = new JLabel ("Pick a file for parsing: ");
		openButton = new JButton("Open ");
		openPanel.add(pickLabel);
		openPanel.add(openButton);
		
		// Initialize components of regexPanel
		regexLabel = new JLabel("Enter regex:");
		regexTA = new JTextArea ("Type it here...", 1,20);
		//regexTA = new JTextArea ("Type it here...");
		regexTA.setForeground(Color.GRAY);
		regexTA.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		regexPanel.add(regexLabel);
		regexPanel.add(regexTA);
		
		//Initialize components of goPanel
		goButton = new JButton ("Enter");
		goPanel.add(goButton);
		
		// Initialize components of outputPanel
		outputTA = new JTextArea(20,20);
		outputTA = new JTextArea();
		outputTA.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		logScrollPane = new JScrollPane(outputTA);
		logScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		outputTA.setMargin(new Insets(5,5,5,5));
		outputTA.setEditable(false);
		outputPanel.add(logScrollPane, BorderLayout.CENTER);
		//outputPanel.add(outputTA, BorderLayout.PAGE_END);
		
		// Make all up
		mainPanel.add(openPanel);
		mainPanel.add(regexPanel);
		mainPanel.add(goPanel);
		mainPanel.add(outputPanel);
		JFrame frame = new JFrame("FileChooserAndParserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
		frame.setIconImage(new ImageIcon("res/flower.png").getImage());
		frame.setTitle("My tiny little file parser");
		frame.setSize(600,620);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
		
		// add event listeners to buttons and text field/area
		openButton.addActionListener(new OpenButtonListener());
		regexTA.addMouseListener(new MyMouseAdapter()); // remove text when clicked
		goButton.addActionListener(new GoButtonListener());
	} 

		class MyMouseAdapter extends MouseAdapter{
			public void mouseClicked(MouseEvent e) {
				regexTA.setText("");
				regexTA.setForeground(Color.BLACK);
			}
		}
		
		class OpenButtonListener implements ActionListener {  // implements file picker/chooser
			public void actionPerformed (ActionEvent evt){
				fc = new JFileChooser("c:\\"); // opens root c:\ ---windows only
				int returnVal = fc.showOpenDialog(null); 
				if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
				System.out.println("Selected file: " + file.getAbsolutePath());
				
				} else {
				System.out.println("Open command cancelled by user.");
				} 
			}
		}
		
		
		void openFile (File f) {
			try {
			BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
			int newLine=0;
            	while ((readLine = b.readLine()) != null) {
					newLine++;
									
					if (regexTA.getText()!=null) {
						Pattern p = Pattern.compile (regexTA.getText());
						Matcher m = p.matcher(readLine);
						if (m.find()) {
						outputTA.append("Line " + + newLine + ": " +readLine + "\n");
						}
					}
				}
				} catch (IOException e) {
            e.printStackTrace();
			}
		}
						
		class GoButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent evt) {
				openFile (file) ;
			}
		}
		
		
} 