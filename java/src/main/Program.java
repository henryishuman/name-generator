package main;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

public class Program {
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Name Generator");
		f.setContentPane(new GuiPane());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	public static void logError(String message){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String logfile = System.getProperty("user.dir") + "\\" + "Log - " + dateFormat.format(date);
		
		try(Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(logfile), "utf-8"))) {
				writer.write(message);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
