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
	int fwidth = Toolkit.getDefaultToolkit().getScreenSize().width;//获取分辨率宽
    int fheight = Toolkit.getDefaultToolkit().getScreenSize().height;//获取分辨率高
    Mouselistener ml;
 private JTextField textarea = null;  
 private JPanel panel = null;  
 private JButton button; 
 private JButton lbutton; 
 public MainFrame() {  
     super();
//////////////////////////////////////////
try    
{ // 使用Windows的界面风格  
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
     Font f=new Font("微软雅黑", 1,20);
     textarea.setFont(f);
     f=new Font("微软雅黑", 1,15);
     button=new JButton("运行");
     button.setFont(f);
     lbutton=new JButton("连续运行");
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
						button.setText("运行");
					}
				}.start();
				brun=true;
				button.setText("改名");
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
 				ml.mend();//终止C++方法
 				lbutton.setText("连续运行");
 				button.setText("运行");
 			}else if(brun){
 				ml.mend();
 				blrun=true;
 				lrun();
 				lbutton.setText("停止");
 			}else {
 				blrun=true;
 				brun=true;
 				lrun();
 				lbutton.setText("停止");
 			    button.setText("改名");
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