import java.text.SimpleDateFormat;
import java.util.Date;

public class dateLoop {
	public static void main(String[] args) throws Exception
	{
	    String Startstr = "Jun 13 2003";
	    String Endstr = "Sep 13 2003";
	    SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
	    Date date = df.parse(Startstr);
	    Date date1 = df.parse(Endstr);
	    long epoch = date.getTime();
	    
	    long epoch1 = date1.getTime();
	    
	   
	    while(epoch1> epoch) {
	    	epoch1 = epoch1-86400000;
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    	System.out.println(sdf.format(new Date(epoch1)));	
	    }
	}

}
