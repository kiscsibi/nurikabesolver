import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class TFileHandler {

    
    TLevelStruct readLevel(File file) {

	TLevelStruct level = null;

	try{
	     // Open the file that is the first 
	     // command line parameter
	     FileInputStream fstream = new FileInputStream(file);
	     // Get the object of DataInputStream
	     DataInputStream in = new DataInputStream(fstream);
	     BufferedReader br = new BufferedReader(new InputStreamReader(in));
	     
	     String strLine;
	     boolean gotdim = false;    
	     
	     //Read File Line By Line
	     while ((strLine = br.readLine()) != null)   {
		 if(!(strLine.length() == 0 || strLine.startsWith("#"))) {
		     if(!gotdim) {
			 if(strLine.matches("[0-9]*x[0-9]*")) {
			     String[] tmp = strLine.split("x");
			     level = new TLevelStruct(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
			     gotdim = true;
			 }
		     }
		     else {
			 if(strLine.matches("[0-9]*,[0-9]*,[0-9]*")) {
			     String[] tmp = strLine.split(",");
			     level.addWallInfo(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
			 }
		     }
		 }
	     }
		 	
	     //Close the input stream
	     in.close();
	     
	 }catch (Exception e){//Catch exception if any
	     System.err.println("Error: " + e.getMessage());
	 }
	 return level;
    }
}
