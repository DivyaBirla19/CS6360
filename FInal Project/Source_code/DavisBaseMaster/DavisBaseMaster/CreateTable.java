
import java.io.RandomAccessFile;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CreateTable {
public static void parseCreateString(String usercommand) {
		
		String[] tokens=usercommand.split(" ");
		
		if (tokens[1].compareTo("index")==0)
		{
		String attr = tokens[4];
		String attrName = attr.substring(1,attr.length()-1);
		Index.createIndex(tokens[3],attrName, "String");
		}
		else
		{
		
		if (tokens[1].compareTo("table")>0){
			System.out.println("Wrong syntax");
			
		}
		else{
				 
		String tablename = tokens[2];
		String[] temp = usercommand.split(tablename);
		String attrs = temp[1].trim();
		String[] create_attrs = attrs.substring(1, attrs.length()-1).split(",");
		
		for(int i = 0; i < create_attrs.length; i++)
			create_attrs[i] = create_attrs[i].trim();
		
		if(DavisBase.tableExists(tablename)){
			System.out.println("Table "+tablename+" already exists.");
		}
		else
			{
			createTable(tablename, create_attrs);		
			}
			}
		}
	}
public static void createTable(String table, String[] attr){                                     
	try{	
		
		RandomAccessFile file = new RandomAccessFile("data/"+table+".tbl", "rw");			
		file.setLength(Table.pageSize);											
		file.seek(0);				
		file.writeByte(0x0D);		
		file.close();
		
		file = new RandomAccessFile("data/davisbase_tables.tbl", "rw");
		
		int num_pages = Table.pages(file);
		int page=1;
		for(int p = 1; p <= num_pages; p++){
			int rm = Page.getRightMost(file, p);
			if(rm == 0)
				page = p;
		}
		
		int[] keys = Page.getKeyArray(file, page);
		int l = keys[0];
		for(int i = 0; i < keys.length; i++)
			if(keys[i]>l)
				l = keys[i];
		file.close();
		
		String[] record = {Integer.toString(l+1), table};
		insertInto("davisbase_tables", record);

		file = new RandomAccessFile("data/davisbase_columns.tbl", "rw");
		
		num_pages = Table.pages(file);
		page=1;
		for(int p = 1; p <= num_pages; p++){
			int rm = Page.getRightMost(file, p);
			if(rm == 0)
				page = p;
		}
		
		keys = Page.getKeyArray(file, page);
		l = keys[0];
		for(int i = 0; i < keys.length; i++)
			if(keys[i]>l)
				l = keys[i];
		file.close();

		for(int i = 0; i < attr.length; i++){
			l = l + 1;
			String[] token = attr[i].split(" ");
			String attr_name = token[0];
			String dt = token[1].toUpperCase();
			String pos = Integer.toString(i+1);
			String nullable;
			if(token.length > 2)
				nullable = "NO";
			else
				 nullable = "YES";
			String[] value = {Integer.toString(l), table, attr_name, dt, pos, nullable};
			insertInto("davisbase_columns", value);
		}

	}catch(Exception e){
		System.out.println(e);
	}
}
public static void insertInto(String table, String[] record){
	try{
		RandomAccessFile file = new RandomAccessFile("data/"+table+".tbl", "rw");
		insertInto(file, table, record);
		file.close();

	}catch(Exception e){
		System.out.println(e);
	}
}
public static void insertInto(RandomAccessFile file, String table, String[] record){
	String[] dtype = Table.getDataType(table);
	String[] nullable = Table.getNullable(table);

	for(int i = 0; i < nullable.length; i++)
		if(record[i].equals("null") && nullable[i].equals("NO")){
			System.out.println("Entity constraint violation");
			System.out.println();
			return;
		}

	int key = new Integer(record[0]);
	int page = Table.searchKeyPage(file, key);
	if(page != 0)
		if(Page.hasKey(file, page, key)){
			System.out.println("Key constraint violation");
			return;
		}
	if(page == 0)
		page = 1;


	byte[] stc = new byte[dtype.length-1];
	short plSize = (short) Table.calPayloadSize(table, record, stc);
	int cellSize = plSize + 6;
	int offset = Page.checkLeafSpace(file, page, cellSize);


	if(offset != -1){
		Page.insertLeafCell(file, page, offset, plSize, key, stc, record);
	}else{
		Page.splitLeaf(file, page);
		insertInto(file, table, record);
	}
}

}
