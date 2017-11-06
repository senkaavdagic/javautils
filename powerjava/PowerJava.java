import java.io.*;

public class PowerJava {
	public static void main(String[] args) throws Exception {

      Runtime r = Runtime.getRuntime();
      Process p = r.exec("list.bat");
	  BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
	  StringBuffer sb = new StringBuffer();
      String line = "";
      while ((line = reader.readLine())!= null) { 
		sb.append(line );
		System.out.println (sb);
	}
	reader.close();
    p.destroy();
   }
}
