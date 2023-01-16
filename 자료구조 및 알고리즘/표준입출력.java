import java.util.*;
import java.io.*;

class Main{
    
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
             
            String[] input = br.readLine().split(" ");
            
           
            bw.write("\n");
	          bw.flush();
	          bw.close();
            br.close();
            
            
        } catch(Exception e){
            
        }
                   
        
    }
    
}
