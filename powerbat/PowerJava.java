import java.io.*;

public class PowerJava {
	Runtime r;
	Process p;
	public PowerJava () {}
	public PowerJava (String scripta, String argument) {
		try {
			r = Runtime.getRuntime();
			if (scripta == "Inactive for 60 days") {
				p = r.exec("script//power.bat " + argument);
			} else p = r.exec("script//power90.bat " + argument);
			
			BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = reader.readLine())!= null) { 
			sb.append(line );
			System.out.println (sb);
			}
			reader.close();
			p.destroy();
		} catch (Exception ex) {
		System.out.println (ex.getMessage());
		ex.	printStackTrace();
		}
}
}
