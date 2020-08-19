import java.util.HashMap;

class Buffer{
	
	public int table_rec; 
	public HashMap<Integer, String[]> record;
	public String[] attr; 
	public int[] Qtype; 
	
	public Buffer(){
		table_rec = 0;
		record = new HashMap<Integer, String[]>();
	}

	public void insert_records(int rowid, String[] val){
		record.put(rowid, val);
		table_rec = table_rec + 1;
	}

	public String table_struc(int len, String s) {
		return String.format("%-"+(len+3)+"s", s);
	}


	public void display(String[] attribute){
		
		if(table_rec == 0){
			System.out.println("No match found!!");
		}
		else{
			for(int i = 0; i < Qtype.length; i++)
				Qtype[i] = attr[i].length();
			for(String[] i : record.values())
				for(int j = 0; j < i.length; j++)
					if(Qtype[j] < i[j].length())
						Qtype[j] = i[j].length();
			
			if(attribute[0].equals("*")){
				
				for(int l: Qtype)
					System.out.print(DavisBase.line("-", l+3));
				
				System.out.println();
				
				for(int i = 0; i< attr.length; i++)
					System.out.print(table_struc(Qtype[i], attr[i])+"|");
				
				System.out.println();
				
				for(int l: Qtype)
					System.out.print(DavisBase.line("-", l+3));
				
				System.out.println();

				for(String[] i : record.values()){
					for(int j = 0; j < i.length; j++)
						System.out.print(table_struc(Qtype[j], i[j])+"|");
					System.out.println();
				}
			
			}
			else{
				int[] seeks = new int[attribute.length];
				for(int j = 0; j < attribute.length; j++)
					for(int i = 0; i < attr.length; i++)
						if(attribute[j].equals(attr[i]))
							seeks[j] = i;

				for(int j = 0; j < seeks.length; j++)
					System.out.print(DavisBase.line("-", Qtype[seeks[j]]+3));
				
				System.out.println();
				
				for(int j = 0; j < seeks.length; j++)
					System.out.print(table_struc(Qtype[seeks[j]], attr[seeks[j]])+"|");
				
				System.out.println();
				
				for(int j = 0; j < seeks.length; j++)
					System.out.print(DavisBase.line("-", Qtype[seeks[j]]+3));
				
				System.out.println();
				
				for(String[] i : record.values()){
					for(int j = 0; j < seeks.length; j++)
						System.out.print(table_struc(Qtype[seeks[j]], i[seeks[j]])+"|");
					System.out.println();
				}
				System.out.println();
			}
		}
	}
}