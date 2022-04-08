package main;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiPane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private NameGenerator gen;
	private ArrayList<Name> names;
	
	private JButton btnGen;
	private JButton btnSave;
	
	private JComboBox cboLang;
	private JTextField txtCount;
	
	private JTextField txtFMin;
	private JTextField txtFMax;
	private JTextField txtSMin;
	private JTextField txtSMax;
	
	private JTextArea txtNames;
	
	public GuiPane(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		FlowLayout flow = new FlowLayout();
		JPanel line1 = new JPanel(flow);
		JPanel optionsA = new JPanel(flow);
		JPanel optionsB = new JPanel(flow);
		JPanel line3 = new JPanel(flow);
		JPanel line4 = new JPanel(flow);
		
		btnGen = new JButton("Generate");
		btnGen.setActionCommand("generate");
		btnGen.addActionListener(this);
		line1.add(btnGen);
		
		JLabel lblLanguage = new JLabel("Language: ");
		line1.add(lblLanguage);
		
		cboLang = new JComboBox();
		line1.add(cboLang);
		
		JLabel lblCount = new JLabel("Count: ");
		line1.add(lblCount);
		
		txtCount = new JTextField(4);
		line1.add(txtCount);
		
		JLabel lblFMin = new JLabel("Forename Min: ");
		JLabel lblFMax = new JLabel("Max: ");
		JLabel lblSMin = new JLabel("Surname Min: ");
		JLabel lblSMax = new JLabel("Max: ");
		
		txtFMin = new JTextField(4);
		txtFMin.setText("0");
		txtFMax = new JTextField(4);
		txtFMax.setText("0");
		txtSMin = new JTextField(4);
		txtSMin.setText("0");
		txtSMax = new JTextField(4);
		txtSMax.setText("0");
		
		optionsA.add(lblFMin);
		optionsA.add(txtFMin);
		optionsA.add(lblFMax);
		optionsA.add(txtFMax);
		optionsB.add(lblSMin);
		optionsB.add(txtSMin);
		optionsB.add(lblSMax);
		optionsB.add(txtSMax);
		
		txtNames = new JTextArea(10, 25);
		JScrollPane scroll = new JScrollPane(txtNames);
		scroll.getViewport().setView(txtNames);
		line3.add(scroll);
		
		btnSave = new JButton("Save");
		btnSave.setActionCommand("save");
		btnSave.addActionListener(this);
		line4.add(btnSave);
		
		this.add(line1);
		this.add(optionsA);
		this.add(optionsB);
		this.add(line3);
		this.add(line4);
		
		gen = new NameGenerator();
		names = new ArrayList<Name>();
		loadLanguages();
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "generate":
				try{
					int count = Integer.parseInt(txtCount.getText());
					int fmin, fmax, smin, smax;
					fmin = Integer.parseInt(txtFMin.getText());
					fmax = Integer.parseInt(txtFMax.getText());
					smin = Integer.parseInt(txtSMin.getText());
					smax = Integer.parseInt(txtSMax.getText());
					
					Language l = (Language)cboLang.getSelectedItem();
					
					gen.setLanguage(l.getName());
					gen.setForenameLimits(fmin, fmax);
					gen.setSurnameLimits(smin, smax);
					names = gen.generate(count);
					
					txtNames.setText("");
					for(Name n : names)
						txtNames.append(n.forename + " " + n.surname + "\n");
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error Code: " + ex.getMessage() + "\n\nBe sure you have filled out \neverything correctly.", "Generate Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "save":
				try{
					Language lan = (Language)cboLang.getSelectedItem();
					DateFormat dateFormat = new SimpleDateFormat("HHmmssddMMyyyy");
					Date date = new Date();
					saveNames(lan.getName() + "-" + dateFormat.format(date) + ".txt");
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error Code: " + ex.getMessage() + "\n\n Error saving names.\nPlease contact the developer.", "Save Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
		}
	}
	
	private void saveNames(String filename){
		File nameDir = new File("Names");
		if(!nameDir.exists())
			nameDir.mkdir();
		
		filename = System.getProperty("user.dir") + "\\Names\\" + filename;
		
		try(Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "utf-8"))) {
			for(int i = 0; i < names.size(); i++){
				Name n = names.get(i);
				writer.write(n.forename + " " + n.surname + "\n");
			}
		} catch (Exception ex) {

		}
	}
	
	private void loadLanguages(){
		gen.clear();
		
		String dirpath = System.getProperty("user.dir") + "\\Languages\\";
		File dir = new File(dirpath);
		File[] langfiles = dir.listFiles();
		
		for(File f : langfiles){
			try(BufferedReader br = new BufferedReader(new FileReader(f.getPath()))){
				Language l = new Language();
				
				l.setName(br.readLine());
				String sylls = br.readLine();		
				l.setSyllables(sylls.split(","));
				
				gen.addLanguage(l);
			} catch(Exception ex) {

			}
		}
		
		for(Language la : gen.getLanguages())
			cboLang.addItem(la);
	}
}
