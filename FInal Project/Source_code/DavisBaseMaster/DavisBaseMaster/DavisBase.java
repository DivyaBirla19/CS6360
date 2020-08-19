import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class DavisBase {

	
	static String prompt = "davisql> ";
	static boolean isExit = false;
		
	public static int pageSize = 512;
	
	static Scanner scanner = new Scanner(System.in).useDelimiter(";");
	
	
    public static void main(String[] args) {
    	Catalog.catalog();
		
		splashScreen();

		
		String userCommand = ""; 

		while(!isExit) {
			System.out.print(prompt);
			userCommand = scanner.next().replace("\n", " ").replace("\r", "").trim().toLowerCase();
			parseUserCommand(userCommand);
		}
		System.out.println();


	}
	
    public static void splashScreen() {
		System.out.println(line("-",80));
       		System.out.println("Welcome to DavisBase");
		System.out.println(line("-",80));
	}
	

	
	public static String line(String s,int num) {
		String a = "";
		for(int i=0;i<num;i++) {
			a += s;
		}
		return a;
	}

	
	public static boolean tableExists(String tablename){
		tablename = tablename+".tbl";
		
		try {
			File dataDir = new File("data");
			String[] oldTableFiles;
			oldTableFiles = dataDir.list();
			for (int i=0; i<oldTableFiles.length; i++) {
				if(oldTableFiles[i].equals(tablename))
					return true;
			}
		}
		catch (SecurityException se) {
			System.out.println("Unable to create data container directory");
			System.out.println(se);
		}

		return false;
	}


	public static String[] LogicalEquation(String log_symb){
		String log_operator[] = new String[3];
		String temp[] = new String[2];
		if(log_symb.contains("=")) {
			temp = log_symb.split("=");
			log_operator[0] = temp[0].trim();
			log_operator[1] = "=";
			log_operator[2] = temp[1].trim();
		}

		if(log_symb.contains("<")) {
			temp = log_symb.split("<");
			log_operator[0] = temp[0].trim();
			log_operator[1] = "<";
			log_operator[2] = temp[1].trim();
	
		}
		
		if(log_symb.contains(">")) {
			temp = log_symb.split(">");
			log_operator[0] = temp[0].trim();
			log_operator[1] = ">";
			log_operator[2] = temp[1].trim();
		}
		
		if(log_symb.contains("<=")) {
			temp = log_symb.split("<=");
			log_operator[0] = temp[0].trim();
			log_operator[1] = "<=";
			log_operator[2] = temp[1].trim();
		}

		if(log_symb.contains(">=")) {
			temp = log_symb.split(">=");
			log_operator[0] = temp[0].trim();
			log_operator[1] = ">=";
			log_operator[2] = temp[1].trim();
		}
		
		return log_operator;
	}

	public static void help() {
		System.out.println();
		System.out.println("\tSHOW TABLES;                                                 Display all the tables in the database.");
		System.out.println("\tCREATE TABLE table_name (<column_name datatype>);            Create a new table in the database.");
		System.out.println("\tINSERT INTO table_name VALUES (value1,value2,..);            Insert a new record into the table.");
		System.out.println("\tSELECT * FROM table_name;                                    Display all records in the table.");
		System.out.println("\tSELECT * FROM table_name WHERE column_name operator value;   Display records in the table where the given condition is satisfied.");
		System.out.println("\tDROP TABLE table_name;                                       Remove table data and its schema.");
		System.out.println("\tHELP;                                                        Show this help information.");
		System.out.println("\tEXIT;                                                        Exit DavisBase.");
		System.out.println();
	}

		
	public static void parseUserCommand (String Query) {
		
		ArrayList<String> commandTokens = new ArrayList<String>(Arrays.asList(Query.split(" ")));

		switch (commandTokens.get(0)) {

		    case "show":
			    ShowTables.showTables();
			    break;
			
		    case "create":
			    CreateTable.parseCreateString(Query);
			    break;

			case "insert":
				Insert.parseInsertString(Query);
				break;
				
			case "select":
				parseQueryString(Query);
				break;

			case "drop":
				DropTable.dropTable(Query);
				break;	

			case "help":
				help();
				break;

			case "exit":
				isExit=true;
				break;
	
			default:
				System.out.println("Invalid command!! \"" + Query + "\"");
				System.out.println();
				break;
		}
	} 
    public static void parseQueryString(String queryString) {
		
		String[] log_op;
		String[] column;
		String[] temp = queryString.split("where");
		if(temp.length > 1){
			String tmp = temp[1].trim();
			log_op = LogicalEquation(tmp);
		}
		else{
			log_op = new String[0];
		}
		String[] select = temp[0].split("from");
		String tableName = select[1].trim();
		String cols = select[0].replace("select", "").trim();
		if(cols.contains("*")){
			column = new String[1];
			column[0] = "*";
		}
		else{
			column = cols.split(",");
			for(int i = 0; i < column.length; i++)
				column[i] = column[i].trim();
		}
		
		if(!tableExists(tableName)){
			System.out.println("Table "+tableName+" does not exist !! Please crosscheck..");
		}
		else
		{
		    ShowTables.select(tableName, column, log_op);
		}
	}
	
	}
		


