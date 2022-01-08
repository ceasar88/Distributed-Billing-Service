//import java.util.Scanner;
import java.net.*;  
import java.io.*; 
import java.util.*; 
//import com.opencsv.CSVWriter;/\
//import java.io.FileReader; 
//import java.util.List; 
import com.opencsv.*; 
import org.apache.commons.lang3.*;

/*
How to run : 

compile with 'javac -cp .:opencsv-5.1.jar:commons-lang3-3.1.jar HadoopServer.java'
run with 'java -cp .:opencsv-5.1.jar:commons-lang3-3.1.jar HadoopServer'
*/

class HadoopServer
{
    public static void main(String args[])
    {
        
        //Scanner in = new Scanner(System.in);
       // System.out.println("Name: ");
        //String name = in.nextLine();
        //String name = args[0];
       // System.out.println("payment");
        //float payment = in.nextFloat();
         //String name = args[1];
        //System.out.println("Name: "+name+"\n payment:"+payment);

        //server socket input
        
        try
        {  
            ServerSocket servSoc = new ServerSocket(8888);  
            System.out.println("Socket open and listenin....");
            Socket soc = servSoc.accept();  
            DataInputStream dataInput = new DataInputStream(soc.getInputStream());  
            DataOutputStream dataOutput = new DataOutputStream(soc.getOutputStream());  
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
            String str="",str2="";  
         
           // while(!str.equals("stop")){  
            str=dataInput.readUTF();  
            System.out.println("client info: "+str); 
            String name = str.split(",")[0]; 
            float payment = Float.parseFloat(str.split(",")[1]); 
             //str2=br.readLine();  
            if(findStudent(name, payment))
            {
                System.out.println("Found and updated");  
                str2 = "Found and updated";
            }
            else
            {
                System.out.println("Student Not Found");  
                str2 = "Student Not Found";
            }
            dataOutput.writeUTF(str2);  
            dataOutput.flush();  
            //}  
            dataInput.close();  
            soc.close();  
            servSoc.close();  
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 

       /* if(findStudent(name, payment))
        {
            System.out.println("Found and updated");  
        }
        else
        {
            System.out.println("Student Not Found");  
        }*/
    }

// Java code to illustrate reading a 
// CSV file line by line 
public static boolean findStudent(String name, Float payment) 
{ 

	try {  
            // Create an object of file reader 
            // class with CSV file as a parameter.
            String file = "CS230_DATA.csv"; 
            FileReader filereader = new FileReader(file); 
  
            // create csvReader object and skip first Line 
            CSVReader csvReader = new CSVReaderBuilder(filereader) 
                                  .build(); 
            List<String[]> allData = csvReader.readAll(); 
  
            // print Data 
            for (int row = 0; row < allData.size()-1; row++) { 
                //for (int row = 0; col < allData.get(row)[0].length-1; col++) { 
                if(allData.get(row)[0].startsWith(name)){
                    System.out.println("old payment:"+allData.get(row)[1]);

                    allData.get(row)[1] = Float.toString(payment - Float.parseFloat(allData.get(row)[1]));
                    csvReader.close();

                    // Write to CSV file which is open
                    CSVWriter writer = new CSVWriter(new FileWriter(file));
                    writer.writeAll(allData);
                    writer.flush();
                    writer.close();
                    return true;
                }
                //} 
                //System.out.println(); 
            } 
    } 
    catch (Exception e) { 
        e.printStackTrace(); 
    } 
return false;
} 

}
