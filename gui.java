package V1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.Font;

public class gui extends javax.swing.JFrame{


	private static final long serialVersionUID = 4019618764444747575L;
	
	private static Toolkit t;
	private int x=0,y=0,width=400,height=600;
	public static JLabel lblLngeDesPasswortes,lblNewLabel;
	public static JButton btnGenerieren, btnClose, btnCopy;
	public static JFormattedTextField textField;
	private static String str = "";;
	
	public gui(){
		JFrame window = new JFrame();
		
		t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		
		x = (int) (d.getWidth()-width)/2;
		y = (int) (d.getHeight()-height)/2;
		
		
		
		window.setBounds(x,y, width, height);
		window.getContentPane().setLayout(null);
		window.setTitle("Passwortgenerator");
		
		setup(window);
		
		ActionListener submitAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Textfeld darf nicht leer sein!");
				}else{
				gen(window);
				}
			}
			
		};
		textField.addActionListener(submitAction);
		btnGenerieren.addActionListener(submitAction);
		
		ActionListener close = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Wirklich beenden?", "Passwortgenerator",JOptionPane.YES_NO_OPTION);
				log("Schlieﬂen-Fenster erzeugt");
				if(dialogResult == 0){
					log("Generator beendet.");
					window.setVisible(false);
					window.dispose();
				}else{
					log("Generator nicht beendet.");
				}
				

				
			}
		};
		btnClose.addActionListener(close);
		
		
		ActionListener copy = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Clipboard clip = t.getSystemClipboard();
				StringSelection strSel = new StringSelection(str);
				clip.setContents(strSel, null);
				
				JOptionPane.showMessageDialog(null, "Passwort in Zwischenablage kopiert!");
				
				log("Passwort kopiert.");
			}
		};
		btnCopy.addActionListener(copy);
		
		
		
		window.setMinimumSize(window.getSize());
		window.setMaximumSize(window.getSize());		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public static void setup(JFrame window){
		Dimension d;
		
		JLabel lblPasswortgenerator = new JLabel("Passwortgenerator");
		lblPasswortgenerator.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswortgenerator.setFont(new Font("Tahoma", Font.PLAIN, 26));
		d = center(window,368,65);
		lblPasswortgenerator.setBounds((int) d.getSize().getWidth(), 11, 368, 65);
		window.getContentPane().add(lblPasswortgenerator);
		
		lblLngeDesPasswortes = new JLabel("L\u00E4nge des Passwortes");
		lblLngeDesPasswortes.setHorizontalAlignment(SwingConstants.LEFT);
		lblLngeDesPasswortes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		d = center(window,200,118);
		lblLngeDesPasswortes.setBounds((int)d.getSize().getWidth()-30, 118, 179, 35);
		window.getContentPane().add(lblLngeDesPasswortes);
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
		
		textField = new JFormattedTextField(formatter);
		d = center(window,10,121);
		textField.setBounds((int)d.getSize().getWidth()+60, 121, 65, 35);
		window.getContentPane().add(textField);
		textField.setColumns(1);
		
		btnGenerieren = new JButton("Generieren!");
		d = center(window,125,53);
		btnGenerieren.setBounds((int)d.getSize().getWidth(), 181, 125, 53);
		window.getContentPane().add(btnGenerieren);
		
		JLabel lblDeinNeuesPasswort = new JLabel("Dein neues Passwort lautet");
		lblDeinNeuesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDeinNeuesPasswort.setHorizontalAlignment(SwingConstants.CENTER);
		d = center(window,238,21);
		lblDeinNeuesPasswort.setBounds((int) d.getSize().getWidth(), 301, 238, 21);
		window.getContentPane().add(lblDeinNeuesPasswort);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		d = center(window,80,14);
		lblNewLabel.setBounds((int) d.getSize().getWidth(), 333, 80, 14);
		window.getContentPane().add(lblNewLabel);
		
		btnCopy = new JButton("Copy");
		d = center(window,75,30);
		btnCopy.setBounds((int) d.getSize().getWidth(), 360, 75, 30);
		window.getContentPane().add(btnCopy);
		
		btnClose = new JButton("Close");
		d = center(window,90,30);
		btnClose.setBounds((int) d.getSize().getWidth(), (int) window.getBounds().getHeight()-100, 90, 30);		
		window.getContentPane().add(btnClose);
		
		JLabel lblVersion = new JLabel("Version: 1.0");
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVersion.setBounds(10, (int) window.getBounds().getHeight()-65, 150, 11);
		window.getContentPane().add(lblVersion);
	}
	
	public void gen(JFrame window){
		lblNewLabel.setText("");
		str = "";
		int passwordLength = Integer.parseInt(textField.getText());
		Random r = new Random();
		char[] password = new char[passwordLength];
		log("Passwort generiert. " + passwordLength + " Zeichen");
		for(int i = 0; i < passwordLength; i++){
			int nr;
			do{
				nr = r.nextInt(80)+42;
				log((i+1) + ". Zeichen erzeugt");
			}while((nr >=58 && nr <= 64) || (nr >=91 && nr <= 96) || nr == 44 || nr == 46);
			
			char c = (char) nr;
			password[i] = c;
			
			if(str.isEmpty()){
				str = "" + c;
			}else{
				str = str+c;
			}
		}
		lblNewLabel.setText(str);
	}

	public static Dimension center(JFrame window, int w, int h){
		t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		int x,y;
		
		x = (int) (window.getWidth() - w)/2;
		y = (int) (window.getHeight() - h)/2;
		
		d.setSize(x, y);
		
		return d;
	}
	
	public static String time(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String uhrzeit = sdf.format(new Date());
		return uhrzeit;
	}
	
	public static void log(String message){
		System.out.println("<" + time() + "> " + message);
	}
	
	public static void main(String[] args) {
		new gui();
		log("Generator gestartet");

	}
}
