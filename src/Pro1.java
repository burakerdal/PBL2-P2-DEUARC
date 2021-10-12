import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Pro1 extends JFrame {

	private JPanel contentPane;
	private JTextField PC;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblInstructionRegister;
	private JTextField textField_3;
	private JLabel lblInputRegister;
	private JTextField textField_4;
	private JLabel lblOutputRegister;
	private JTextField textField_5;
	private JLabel lblR;
	private JTextField textField_6;
	private JLabel lblR_1;
	private JTextField textField_7;
	private JLabel lblR_2;
	private JTextField textField_8;
	private JLabel lblInstructionMemory;
	

	private Parse p = new Parse();
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private int t=-1;
	private String tx ="";
	private int first_inst_row = 0;
	String t0 = "T0 = IR <- IM[";
	String t1 = "T1 = PC <- PC+1";
	String t2 = "T2 = Q <- IR[10]\nD0..D15 <- IR[9..6]";
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pro1 frame = new Pro1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pro1() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		
		JFileChooser fileChooser = new JFileChooser();
		StringBuilder sb = new StringBuilder();
		
		setTitle("DEUARC Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1522, 750);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		JMenuItem mnitemExit = new JMenuItem("Exit");
		mnitemExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnitemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				int decide = JOptionPane.showConfirmDialog(null, "Do you want really exit?");
				if(decide==0)
				{
					System.exit(0);
				}							
			}
		});
		
		JRadioButtonMenuItem rdbtnmnNewRadioItem = new JRadioButtonMenuItem("Binary");
		mnFile.add(rdbtnmnNewRadioItem);
		
		JRadioButtonMenuItem rdbtnmnNewRadioItem_1 = new JRadioButtonMenuItem("Hexadecimal");
		mnFile.add(rdbtnmnNewRadioItem_1);
		
		JRadioButtonMenuItem rdbtnmnNewRadioItem_2 = new JRadioButtonMenuItem("Decimal");
		mnFile.add(rdbtnmnNewRadioItem_2);
		mnFile.add(mnitemExit);
		
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnHelp);
		
		JMenuItem AboutDeuarcSimulator = new JMenuItem("About DEUARC Simulator");
		AboutDeuarcSimulator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Desktop.isDesktopSupported()) {
		            try {
		                File myFile = new File( "DEUARC_Simulation_20170414.pdf");
		                Desktop.getDesktop().open(myFile);
		            } catch (IOException ex) {
		                
		            }
		        }
			}
		});
		AboutDeuarcSimulator.setIcon(new ImageIcon("logo2.png"));
		AboutDeuarcSimulator.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnHelp.add(AboutDeuarcSimulator);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProgramCounter = new JLabel("Program Counter");
		lblProgramCounter.setBounds(375, 78, 104, 14);
		contentPane.add(lblProgramCounter);
		
		PC = new JTextField();
		PC.setEditable(false);
		PC.setBounds(510, 75, 109, 20);
		contentPane.add(PC);
		PC.setColumns(10);
		
		JLabel lblStackPointer = new JLabel("Stack Pointer");
		lblStackPointer.setBounds(1025, 78, 90, 14);
		contentPane.add(lblStackPointer);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(840, 116, 105, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAddressRegister = new JLabel("Address Register");
		lblAddressRegister.setBounds(700, 78, 115, 14);
		contentPane.add(lblAddressRegister);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(1150, 75, 105, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblInstructionRegister = new JLabel("Instruction Register");
		lblInstructionRegister.setBounds(375, 119, 115, 14);
		contentPane.add(lblInstructionRegister);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(840, 75, 105, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblInputRegister = new JLabel("Input Register");
		lblInputRegister.setBounds(700, 119, 109, 14);
		contentPane.add(lblInputRegister);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(1150, 156, 105, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		lblOutputRegister = new JLabel("Output Register");
		lblOutputRegister.setBounds(1025, 119, 115, 14);
		contentPane.add(lblOutputRegister);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(1150, 116, 105, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		lblR = new JLabel("REGISTER 1");
		lblR.setBounds(375, 159, 115, 14);
		contentPane.add(lblR);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(510, 116, 109, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		lblR_1 = new JLabel("REGISTER 2");
		lblR_1.setBounds(700, 159, 115, 14);
		contentPane.add(lblR_1);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(510, 156, 109, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		lblR_2 = new JLabel("REGISTER 3");
		lblR_2.setBounds(1025, 159, 115, 14);
		contentPane.add(lblR_2);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(840, 156, 105, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		lblInstructionMemory = new JLabel("Instruction Memory");
		lblInstructionMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructionMemory.setBounds(50, 235, 300, 14);
		contentPane.add(lblInstructionMemory);
		
		JLabel lblDataMemory = new JLabel("Data Memory");
		lblDataMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataMemory.setBounds(375, 235, 300, 14);
		contentPane.add(lblDataMemory);
		    contentPane.setVisible(true);
		    
		
		JLabel lblNewLabel = new JLabel("Label Table");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(700, 235, 300, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(385, 428, 300, 200);
		contentPane.add(scrollPane);
		String data[][]={ 
				{"-","-","-"},    
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"},
                {"-","-","-"}};    
		
		String data2[][]={ 
				{"0","0","0000","000000"},    
	    		{"1","0","0000","000000"},
	    		{"2","0","0000","000000"},
	    		{"3","0","0000","000000"},
	    		{"4","0","0000","000000"},
	    		{"5","0","0000","000000"},
	    		{"6","0","0000","000000"},
	    		{"7","0","0000","000000"},
	    		{"8","0","0000","000000"},
	    		{"9","0","0000","000000"},
	    		{"10","0","0000","000000"},
	    		{"11","0","0000","000000"},
	    		{"12","0","0000","000000"},
	    		{"13","0","0000","000000"},
	    		{"14","0","0000","000000"},
	    		{"15","0","0000","000000"},
	    		{"16","0","0000","000000"},
	    		{"17","0","0000","000000"},
	    		{"18","0","0000","000000"},
	    		{"19","0","0000","000000"},
	    		{"20","0","0000","000000"},
	    		{"21","0","0000","000000"},
	    		{"22","0","0000","000000"},
	    		{"23","0","0000","000000"},
	    		{"24","0","0000","000000"},
	    		{"25","0","0000","000000"},
	    		{"26","0","0000","000000"},
	    		{"27","0","0000","000000"},
	    		{"28","0","0000","000000"},
	    		{"29","0","0000","000000"},
	    		{"30","0","0000","000000"},
	    		{"31","0","0000","000000"}
	    		};    
		
		String data3[][]={ 
				{"0","0000"},    
	    		{"1","0000"},
	    		{"2","0000"},
	    		{"3","0000"},
	    		{"4","0000"},
	    		{"5","0000"},
	    		{"6","0000"},
	    		{"7","0000"},
	    		{"8","0000"},
	    		{"9","0000"},
	    		{"10","0000"},
	    		{"11","0000"},
	    		{"12","0000"},
	    		{"13","0000"},
	    		{"14","0000"},
	    		{"15","0000"}};   
		
		String data4[][]={ 
				{"0","00000"},    
	    		{"1","00000"},
	    		{"2","00000"},
	    		{"3","00000"},
	    		{"4","00000"},
	    		{"5","00000"},
	    		{"6","00000"},
	    		{"7","00000"},
	    		{"8","00000"},
	    		{"9","00000"},
	    		{"10","00000"},
	    		{"11","00000"},
	    		{"12","00000"},
	    		{"13","00000"},
	    		{"14","00000"},
	    		{"15","00000"}};
		JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(756, 431, 282, 211);
	    contentPane.add(scrollPane_1);
	    
	    JTextPane textPane_3 = new JTextPane();
	    scrollPane_1.setViewportView(textPane_3);
		JTextPane txtpnAssemblyCode = new JTextPane();
	     txtpnAssemblyCode.setBounds(388, 431, 297, 197);
	     contentPane.add(txtpnAssemblyCode);
	    

		JButton btnNewButton = new JButton("Get Assembly");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
				{
					java.io.File file = fileChooser.getSelectedFile();
					
					try {
						String[] write = p.readFile(file.getAbsolutePath());
						for (int i = 0; i < write.length; i++)
						{
							txtpnAssemblyCode.setText(sb.append(write[i]).toString());
							sb.append("\n");
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i = 0; i < p.getInstructionTable().size(); i++) {
						String q = "";
				        String op = "";
				        String add = "";
						q += p.getInstructionTable().get(i).substring(0, 1);
						op += p.getInstructionTable().get(i).substring(1, 5);
						add += p.getInstructionTable().get(i).substring(5, 11);
						data2[i][1] = q;
						data2[i][2] = op;
						data2[i][3] = add;
					}
					String datatext = null;
					for (int i = 0; i < p.getDataTable().size(); i++) {
						datatext = "";
						datatext += p.getDataTable().get(i);
						datatext += "\n";
						data3[i][1] = datatext;
					}
					for (int j = 0; j < p.getLabelTable().size(); j++) {
						String[] label_fill = p.getLabelTable().get(j).split(" ");
						for (int i = 0; i < label_fill.length; i++) {
							data[j][i] = label_fill[i];
						}
					}
					first_inst_row = p.getEmpty_rows();
				}
				else
				{
					txtpnAssemblyCode.setText(sb.append("---------No file choosen!!!-------").toString());
				}
			}
		});
		btnNewButton.setBackground(new Color(135, 206, 235));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(400, 397, 142, 23);
		contentPane.add(btnNewButton);
		JButton btnDebug = new JButton("Debug");
		btnDebug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				t++;
				if(t==0)
				{
					t0 += Integer.toString(first_inst_row);
					t0 += "]";
					PC.setText(Integer.toString(first_inst_row));
					String inst_reg = "";
					for (int i = 1; i < data2[first_inst_row].length; i++) {
						inst_reg += data2[first_inst_row][i];
					}
					textField_6.setText(inst_reg);
					//textField_3.setText(data2[first_inst_row][3]);
					first_inst_row++;
					tx+=t0;
					textPane_3.setText(tx);
					tx+="\n";

					t0 = "T0 = IR <- IM[";
				}
				else if (t==1)
				{
					PC.setText(Integer.toString(first_inst_row));
					tx+=t1;
					textPane_3.setText(tx);
					tx+="\n";
				}
				else if (t==2)
				{
					if (data2[first_inst_row - 1][2].equals("0001") || 
							data2[first_inst_row - 1][2].equals("0010") || 
							data2[first_inst_row - 1][2].equals("0011") || 
							data2[first_inst_row - 1][2].equals("0100") || 
							data2[first_inst_row - 1][2].equals("0110") || 
							data2[first_inst_row - 1][2].equals("0111") || 
							data2[first_inst_row - 1][2].equals("1001"))
					{
						t2 +=  ",\nD <- IR[5..4],\n S1 <- IR[3..2]";
					}
					else if (data2[first_inst_row - 1][2].equals("0000") || 
							data2[first_inst_row - 1][2].equals("0101"))
					{
						t2 +=  ",\nD <- IR[5..4],\n S1 <- IR[3..2],\n S2 <- IR[1..0]";
					}
					else if (data2[first_inst_row - 1][2].equals("1000"))
					{
						//nothing
					}
					else
					{
						t2 += ",\nAddress <- IR[4..0]";
					}
					tx+=t2;
					textPane_3.setText(tx);
					tx+="\n";
					t2 = "T2 = Q <- IR[10],\nD0..D15 <- IR[9..6]";
					
				}
				else
				{
					t=-1;
				}
				// en sonda T tekrar -1 yapýlacak baþta -1 di
			}
		});
		btnDebug.setBackground(new Color(124, 252, 0));
		btnDebug.setBounds(400, 640, 90, 23);
		contentPane.add(btnDebug);
		
		
		
		JButton btnNextLine = new JButton("Next Line");
		btnNextLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// satýrda hangi iþlem varsa kaç T sürüyorsa komple yapacak
				tx=t0+"\n"+t1+"\n"+t2;
				textPane_3.setText(tx);
				tx+="\n";
				// en sonda T tekrar -1 yapýlacak baþta -1 di
			}
		});
		btnNextLine.setForeground(new Color(255, 255, 255));
		btnNextLine.setBackground(new Color(255, 0, 0));
		btnNextLine.setBounds(576, 640, 109, 23);
		contentPane.add(btnNextLine);
		
		JLabel lblStackMemory = new JLabel("Stack Memory");
		lblStackMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblStackMemory.setBounds(1034, 235, 300, 14);
		contentPane.add(lblStackMemory);
		
		
        String column[]={"LABEL","ADDRESS","VALUE"}; 
        
		table_3 = new JTable(data, column);
		table_3.setEnabled(false);
		table_3.setBounds(41, 21, 47, 192);
		contentPane.add(table_3);
		
		JScrollPane sp=new JScrollPane(table_3); 
		sp.setEnabled(false);
		sp.setBounds(710, 261, 300, 125);
	    contentPane.add(sp);              
	    contentPane.setVisible(true); 
	    
	    
        String column2[]={"i","Q","OPCODE","ADDRESSING"}; 
	    table_4 = new JTable(data2, column2);
	    table_4.setEnabled(false);
		table_4.setBounds(674, 443, 326, 200);
		table_4.getColumnModel().getColumn(0).setMaxWidth(20);
		table_4.getColumnModel().getColumn(1).setMaxWidth(20);
		contentPane.add(table_4);
		
		JScrollPane sp2=new JScrollPane(table_4);
		sp2.setEnabled(false);
		sp2.setBounds(50, 260, 300, 125);
	    contentPane.add(sp2);
	    contentPane.setVisible(true);
	    
	   
	     
	 
        String column3[]={"i","DATA"}; 
        
	    table_5 = new JTable(data3, column3);
	    table_5.setEnabled(false);
		table_5.setBounds(674, 443, 326, 200);
		table_5.getColumnModel().getColumn(0).setMaxWidth(20);
		contentPane.add(table_5);
		
		JScrollPane sp3=new JScrollPane(table_5);
		sp3.setEnabled(false);
		sp3.setBounds(385, 260, 300, 125);
	    contentPane.add(sp3);
	    contentPane.setVisible(true);
	    
        String column4[]={"i","PC"}; 
        
	    table_6 = new JTable(data4, column4);
	    table_6.setEnabled(false);
		table_6.setBounds(674, 443, 326, 200);
		table_6.getColumnModel().getColumn(0).setMaxWidth(20);
		contentPane.add(table_6);
		
		JScrollPane sp4=new JScrollPane(table_6);
		sp4.setEnabled(false);
		sp4.setBounds(1025, 260, 300, 125);
	    contentPane.add(sp4);
	    
	    JLabel lblMicrooperations = new JLabel("Micro-operations");
	    lblMicrooperations.setHorizontalAlignment(SwingConstants.CENTER);
	    lblMicrooperations.setBounds(720, 414, 300, 14);
	    contentPane.add(lblMicrooperations);
	    
	    
	    
	    

	}
}

