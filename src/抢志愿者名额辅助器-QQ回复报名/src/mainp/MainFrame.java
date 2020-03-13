package mainp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;  
import java.awt.datatransfer.Clipboard;  
import java.awt.datatransfer.DataFlavor;  
import java.awt.datatransfer.StringSelection;  
import java.awt.datatransfer.Transferable;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import cpplink.Mouselistener;
import javax.swing.JButton;  
import javax.swing.JPanel;  
import javax.swing.JFrame;  
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;  
  
public class MainFrame extends JFrame {  
	public boolean brun=false,blrun=false;
	int fwidth = Toolkit.getDefaultToolkit().getScreenSize().width;//��ȡ�ֱ��ʿ�
    int fheight = Toolkit.getDefaultToolkit().getScreenSize().height;//��ȡ�ֱ��ʸ�
    Mouselistener ml;
 private JTextField textarea = null;  
 private JPanel panel = null;  
 private JButton button; 
 private JButton lbutton; 
 public MainFrame() {  
     super();
//////////////////////////////////////////
try    
{ // ʹ��Windows�Ľ�����  
UIManager.setLookAndFeel  
("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  
}  
catch (Exception e)    
{  
e.printStackTrace();  
} 
//////////////////////////////////////////////////////////////
     
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setBounds(fwidth-250,10,220,130);
     panel=(JPanel) getContentPane();
     panel.setLayout(new FlowLayout(1));
     panel.setBackground(Color.black);
     textarea=new JTextField(10);
     Font f=new Font("΢���ź�", 1,20);
     textarea.setFont(f);
     f=new Font("΢���ź�", 1,15);
     button=new JButton("����");
     button.setFont(f);
     lbutton=new JButton("��������");
     lbutton.setFont(f);
     panel.add(textarea);
     panel.add(button);
     panel.add(lbutton);
     button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(brun) {
				ml.name=textarea.getText();
			}else {
				new Thread() {
					public void run() {
						ml=new Mouselistener(textarea.getText());
						ml.run();
						brun=false;
						button.setText("����");
					}
				}.start();
				brun=true;
				button.setText("����");
			}
		}
	});
     lbutton.addActionListener(new ActionListener() {
 		
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			if(blrun) {
 				blrun=false;
 				brun=false;
 				ml.mend();//��ֹC++����
 				lbutton.setText("��������");
 				button.setText("����");
 			}else if(brun){
 				ml.mend();
 				blrun=true;
 				lrun();
 				lbutton.setText("ֹͣ");
 			}else {
 				blrun=true;
 				brun=true;
 				lrun();
 				lbutton.setText("ֹͣ");
 			    button.setText("����");
 			}
 		}
 	});
     setVisible(true);
 }
 public void lrun() {
	 new Thread() {
			public void run() {
				ml=new Mouselistener(textarea.getText());
				ml.run();
				if(blrun) {
					lrun();
				}
			}
		}.start();	
 }
}