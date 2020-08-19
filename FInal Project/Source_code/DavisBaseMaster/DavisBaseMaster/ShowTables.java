import java.io.RandomAccessFile;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;


public class ShowTables{

public static void showTables() {
		String table = "davisbase_tables";
		String[] attrs = {"table_name"};
		String[] userarg = new String[0];
		select(table, attrs, userarg); 
	}

public static void select(String table, String[] attrs, String[] userargs){     
	try{
		
		RandomAccessFile file = new RandomAccessFile("data/"+table+".tbl", "rw");
		String[] column = Table.getColName(table);
		String[] dataType = Table.getDataType(table);
		
		Buffer buffer = new Buffer();
		
		Table.filter(file, userargs, column, dataType, buffer);
		buffer.display(attrs);
		file.close();
	}catch(Exception e){
		System.out.println(e);
	}
}

}
