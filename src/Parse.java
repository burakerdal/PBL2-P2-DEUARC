import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parse {
	private ArrayList<String> dataTable = new ArrayList<>();
	private ArrayList<String> instructionTable = new ArrayList<>();
	private ArrayList<String> labelTable = new ArrayList<>();
	private String empty_memory = "00000000000";
	private int empty_rows = 0; 
	private ArrayList<String[]> assembly_variables = new ArrayList<String[]>();
	public ArrayList<String> getDataTable() {
		return dataTable;
	}
	public void setDataTable(ArrayList<String> dataTable) {
		this.dataTable = dataTable;
	}
	public ArrayList<String> getInstructionTable() {
		return instructionTable;
	}
	public void setInstructionTable(ArrayList<String> instructionTable) {
		this.instructionTable = instructionTable;
	}
	public String[] readFile(String filename) throws IOException{
		ArrayList <String> inst = new ArrayList<String>();    //Ýnstruction array
		String line = null;
		try{
			FileReader freader = new FileReader(filename);
			BufferedReader bfreader = new BufferedReader(freader);
			
			while((line = bfreader.readLine()) != null){
				inst.add(line);
			}
			bfreader.close();
		} catch(FileNotFoundException ex){
			System.out.println("File not found!");
		}
		return parseFile(inst);
		
	}
	public String[] parseFile(ArrayList<String> arr){
		String[] assembly = new String[arr.size()];
		
		for(int i = 0;i < arr.size();i++){
			System.out.println(arr.get(i));
			for(int j = 0;j < arr.get(i).length();j++){
				if(arr.get(i).charAt(j) == '%'){
					break;
				} else{
					assembly[i] += arr.get(i).substring(j, j + 1);
				}
			}
		}
		for (int i = 0; i < assembly.length; i++)
		{
			String temp = assembly[i].substring(4);
			assembly[i] = temp;
		}
			
		for(int k = 0;k < assembly.length;k++){
			if (assembly[k].charAt(assembly[k].length() - 1) == ' ')
				assembly[k] = assembly[k].substring(0, assembly[k].length() - 1);
		}
		FillTables(assembly);
		return assembly;
	}
	public String[][] getInstruction()
	{
		String[][] instTable = new String[16][2];
		instTable[0][0] = "ADD";
		instTable[1][0] = "INC";
		instTable[2][0] = "DBL";
		instTable[3][0] = "DBT";
		instTable[4][0] = "NOT";
		instTable[5][0] = "AND";
		instTable[6][0] = "LD";
		instTable[7][0] = "ST";
		instTable[8][0] = "HLT";
		instTable[9][0] = "TSF";
		instTable[10][0] = "CAL";
		instTable[11][0] = "RET";
		instTable[12][0] = "JMP";
		instTable[13][0] = "JMR";
		instTable[14][0] = "PSH";
		instTable[15][0] = "POP";
		for (int i = 0; i < instTable.length; i++)
		{
			String binary = "";
			for (int j = 0; j < 4 - Integer.toBinaryString(i).length(); j++)
			{
				binary += "0";
			}
			binary += Integer.toBinaryString(i);
			instTable[i][1] = binary;
		}
		return instTable;
	}
	public void FillTables(String[] commands)
	{ 
		String[][] opcode = getInstruction();
		boolean datafilled = false;
		boolean inst_before = false;
		for (int i = 0; i < commands.length; i++) {
			String[] parsed_commands = commands[i].split(" ");
			if (parsed_commands.length == 3)
			{
				if (parsed_commands[0].equals("ORG")&& parsed_commands[1].equals("D"))
				{
					int datacount = Integer.parseInt(parsed_commands[2]);
					for (int j = 0; j < datacount; j++) {
						dataTable.add("0000");
					}
					int index = i + 1;
					while (!commands[index].equals("END") && !commands[index].substring(0, 3).equals("ORG"))
					{
						String[] parsed_commands2 = commands[index].split(" ");
						String[] temparr = new String[2];
						temparr[1] = "";
						temparr[0] = parsed_commands2[0].substring(0, parsed_commands2.length - 2);
						for (int j = 0; j < 4 - Integer.toBinaryString(dataTable.size()).length(); j++) {
							temparr[1] += "0";
						}
						temparr[1] += Integer.toBinaryString(dataTable.size());
						assembly_variables.add(temparr);
						String label = "";
						label += parsed_commands2[0].substring(0, parsed_commands2.length - 2);
						label += " ";
						label += Integer.toString(dataTable.size());
						label += " ";
						label += parsed_commands2[2];
						labelTable.add(label);
						String data = "";
						int value = Integer.parseInt(parsed_commands2[2]);
						for (int j = 0; j < 4 - Integer.toBinaryString(value).length(); j++)
						{
							data += "0";
						}
						data += Integer.toBinaryString(value);
						dataTable.add(data);
						index++;
					}
					if (inst_before)
						i = -1;
					datafilled = true;
				}
				else if (parsed_commands[0].equals("ORG")&& parsed_commands[1].equals("C"))
				{
					
					if (datafilled)
					{
						int instcount = Integer.parseInt(parsed_commands[2]);
						for (int j = 0; j < instcount; j++) {
							instructionTable.add(empty_memory);
						}
						empty_rows = instcount;
						int index = i + 1;
						while (!commands[index].equals("END") && !commands[index].substring(0, 3).equals("ORG"))
						{
							String[] parsed_commands2;
							if (commands[index].contains(":"))
							{
								int first_space = 0;
								for (int j = 0; j < commands[index].length(); j++) {
									if (commands[index].substring(j, j + 1).equals(" "))
									{
										first_space = j;
										break;
									}
								}
								String label = "";
								label += commands[index].substring(0, first_space);
								label += " ";
								label += instructionTable.size();
								labelTable.add(label);
								System.out.println("lbl: " + commands[index].substring(first_space, commands[index].length()));
								parsed_commands2 = commands[index].substring(first_space + 1, commands[index].length()).split(" ");
							}
							else
							{
								parsed_commands2 = commands[index].split(" ");
							}
							System.out.println("com: " + parsed_commands2[0]);
							String instruction = "";
							if (commands[index].contains("#"))
								instruction += "1";
							else
								instruction += "0";
							for (int j = 0; j < opcode.length; j++) {
								if (opcode[j][0].equals(parsed_commands2[0]))
									instruction += opcode[j][1];
							}
							if (commands[index].substring(0, 3).equals("CAL"))
							{
								instruction += "1010";
							}
							else if (parsed_commands2.length > 1)
							{
								System.out.println("pc2: " + parsed_commands2[1]);
								String[] registers_and_adressses = parsed_commands2[1].split(",");
								for (int j = 0; j < registers_and_adressses.length; j++) {
									char charat = registers_and_adressses[j].charAt(0);
									if (charat == '#')
									{
										int value = Integer.parseInt(registers_and_adressses[j].substring(1, registers_and_adressses[j].length()));
										for (int k = 0; k < 4 - Integer.toBinaryString(value).length(); k++) {
											instruction += "0";
										}
										instruction += Integer.toBinaryString(value);
									}
									else if (charat == '@')
									{
										String address = registers_and_adressses[j].substring(1, registers_and_adressses[j].length());
										System.out.println("add: " + address);
										for (int k = 0; k < assembly_variables.size(); k++) {
											if (address.equals(assembly_variables.get(k)[0]))
											{
												System.out.println("bins:" + assembly_variables.get(k)[1]);
												instruction += assembly_variables.get(k)[1];
												break;
											}
										}
									}
									else
									{
										if (registers_and_adressses[j].contains("NPR"))
										{
											instruction += "11";
										}
										else
										{
											System.out.println("aar: " + registers_and_adressses[j]);
											int value = Integer.parseInt(registers_and_adressses[j].substring(1, 2));
											for (int k = 0; k < 2 - Integer.toBinaryString(value).length(); k++) {
												instruction += "0";
											}
											instruction += Integer.toBinaryString(value);
										}
										
									}
								}
							}
							for (int j = instruction.length(); j < 11; j++) {
								instruction += "0";
							}
							instructionTable.add(instruction);
							index++;
						}

						break;
					}
					else
						inst_before = true;
				}
			}
		}
		System.out.println("data: " + dataTable.size());
		for (int i = 0; i < dataTable.size(); i++) {
			System.out.println(dataTable.get(i));
		}
		System.out.println("inst: " + instructionTable.size());
		for (int i = 0; i < instructionTable.size(); i++) {
			System.out.println(instructionTable.get(i));
		}
		System.out.println("label: " + labelTable.size());
		for (int i = 0; i < labelTable.size(); i++) {
			System.out.println(labelTable.get(i));
		}
	}
	public ArrayList<String> getLabelTable() {
		return labelTable;
	}
	public void setLabelTable(ArrayList<String> labelTable) {
		this.labelTable = labelTable;
	}
	public int getEmpty_rows() {
		return empty_rows;
	}
	public void setEmpty_rows(int empty_rows) {
		this.empty_rows = empty_rows;
	}
}

