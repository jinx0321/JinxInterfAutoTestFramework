package AutoTest.Report.SpecialReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtils {
	 public static void write(VelocityContext context, String outputDirectory,String vmname,String reportname) {
		   outputDirectory=System.getProperties().get("user.dir")+"\\report";
		   if(!new File(outputDirectory).exists()) {
			   new File(outputDirectory).mkdir()	;
		   }
		   
	       try {
	           //写文件
	           VelocityEngine ve = new VelocityEngine();
	           Properties p = new Properties();
	           p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, outputDirectory);
	           p.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
	           p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
	           p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
	           ve.init(p);


	           Template t = ve.getTemplate(vmname);
	           OutputStream out = new FileOutputStream(new File(outputDirectory+"/"+reportname));
	           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
	           // 转换输出
	           t.merge(context, writer);
	           writer.flush();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }
}
