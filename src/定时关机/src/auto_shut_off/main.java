package auto_shut_off;

import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class main {
	static boolean bstart=false;
	static int threads=0;
	static JButton button=new JButton("ȷ��");
	static Choice chour=new Choice(),
	    		  cminute=new Choice(),
	    		  csecond=new Choice();
	public static void main(String[] args) throws IOException {
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
		
		JFrame jf=new JFrame("��ʱ�ػ�");
		JPanel panel = panel=(JPanel) jf.getContentPane();
	    panel.setLayout(new FlowLayout(1));
	    
	   
	    Font f=new Font("΢���ź�", 1,16);
	    button.setFont(f);
	    chour.setFont(f);
	    cminute.setFont(f);
	    csecond.setFont(f);
	    int[] h=new int[60];
	    chour.add("ʱ");
	    cminute.add("��");
	    csecond.add("��");
	    for(int i=0;i<24;i++) {
	    	chour.add(""+i);
	    }
	    for(int i=0;i<60;i++) {
	    	cminute.add(""+i);
	    	csecond.add(""+i);
	    }
		int fwidth = Toolkit.getDefaultToolkit().getScreenSize().width;//��ȡ�ֱ��ʿ�
	    int fheight = Toolkit.getDefaultToolkit().getScreenSize().height;//��ȡ�ֱ��ʸ�
	    button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(bstart) {
					bstart=false;
					button.setText("ȷ��");
				}else {
					
					bstart=true;
					new Thread() {
						public void run() {
							threads++;
							int hour,minute,second;
							try {                         
								hour=Integer.parseInt(chour.getSelectedItem());
								minute=Integer.parseInt(cminute.getSelectedItem());
								second=Integer.parseInt(csecond.getSelectedItem());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								button.setText("ȷ��");
								return;
							}
							Calendar cld;
							while(bstart&&threads<2) {
								cld = Calendar.getInstance();
								int dhour = cld.get(Calendar.HOUR_OF_DAY);
								int dminute = cld.get(Calendar.MINUTE);
								int dsecond = cld.get(Calendar.SECOND);
								if(dhour==hour&&dminute==minute&&(second-dsecond)>-1&&(second-dsecond)<11) {
									try {
										Runtime.getRuntime().exec("shutdown -s -t "+(second-dsecond));
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									System.exit(0);
								}
								try {
									TimeUnit.MILLISECONDS.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							threads--;
						}
					}.start();
					button.setText("ȡ��");
					
				}
			}
		});
	    
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setBounds(fwidth-350,10,320,80);
	    panel.add(chour);
	    panel.add(cminute);
	    panel.add(csecond);
	    panel.add(button);
	    jf.setVisible(true);
		
	}

}
