package cpplink;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;

import mainp.MainFrame;
public class Mouselistener {
	public String name;
	Robot robot; 
	public Mouselistener(String str) {
		name=str;
		try {
			robot = new Robot(); 
		}catch(AWTException e) {
     		
     	}	
	}
	 static{  
         System.loadLibrary("CJLink3");  
     }    
//     public static void main(String args[]){  
//	     Mouselistener hw = new Mouselistener("123");  
//         hw.run();  
//     }
     public native boolean mrun();
     public native void mend();
     
     Clipboard sysClip;
 	private static final long serialVersionUID = 1L;
 	
 	public void run() {
 		if(mrun()) {
 			run2();
 			
 		}
 	}
 	public void run2() {
 		robot.delay(50);//不暂停时间来不及更改粘贴板内容
 		keyPressWithCtrl(robot,KeyEvent.VK_V);
 		robot.delay(10);
//     	String str=getString();
//     	str+=name;
 		keyPress(robot,KeyEvent.VK_BACK_SPACE);
 		
 		String[] str=name.split("\\(|\\)|（|）");
 		int[] ul=new int[str.length];
 		for(int i=0;i<str.length;i++) {
 			if(!str[i].equals("")) {
 				try {
 					ul[i]=Integer.parseInt(str[i]);
 				}catch(Exception e) {
 					ul[i]=-1;
 				}
 			}else {
 				ul[i]=-2;
 			}
 		}
 		
// 		int sl=name.length();
// 		int[] ul=new int[sl];
// 		if(sl>0) {
// 			str[0]="";
// 		}
// 		int ls=0,qc=-1,jn=-1;
// 		boolean bbf=false;
// 		for(int i=0;i<sl;i++) {
// 			int n=name.charAt(i)-'0';
// 			if(bbf) {
// 				if(n>=0&&n<10) {
// 					if(qc>=0&&qc<10) {
// 				    	jn=jn*10+n;
// 				    }else {
// 				    	ls++;
// 				    	str[ls]="";
// 				    	jn=n;
// 				    }
// 			    }else {
// 				    if(qc>=0&&qc<10) {
// 				    	ul[ls-1]=jn;
// 				    	jn=-1;	
// 				    }
// 				   str[ls]+=name.charAt(i);
// 			    }
// 				qc=n;
// 			}else {
// 				if(n<0||n>9) {
// 					bbf=true;
// 				}
// 				str[ls]+=name.charAt(i);
// 			}
// 		}
// 		if(jn>=0) {
// 			str[ls-1]+=jn;
// 		}else {
// 			ls++;
// 		}
 		for(int i=0;i<str.length;i++) {
 			if(ul[i]!=-2) {
 				if(ul[i]<0) {
 					setClipboardText(str[i]);
 	 		       	robot.delay(10);
 	 		        keyPressWithCtrl(robot,KeyEvent.VK_V);
 				}else {
 					for(int j=0;j<ul[i];j++) {
 	 	         		robot.delay(10);
 	 	         		keyPress(robot,KeyEvent.VK_UP);
 	 	         	}
 				}
 			}
        }
 		keyPressWithCtrl(robot,KeyEvent.VK_ENTER);
 	}
 	    // 按ctrl+ 键
 		public void keyPressWithCtrl(Robot r, int key) {
 			                r.keyPress(KeyEvent.VK_CONTROL);
 			                r.keyPress(key);
 			                r.keyRelease(key);
 			                r.keyRelease(KeyEvent.VK_CONTROL);
 			                //r.delay(100);
 			        }
 		//单个按键
 		public void keyPress(Robot r,int key){
 			                r.keyPress(key);
 			                r.keyRelease(key);
 			               // r.delay(100);
 			        }
 		///////////////////////////////////////////////////
// 		得到粘贴板内容（仅文字）
// 		public  String getString(){
// 			 
// 			sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
// 			Transferable clipTf =sysClip.getContents(null);
// 			if(clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)){
// 			try{
// 			 
// 			String ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
// 			return ret ;
// 			}
// 			catch(Exception e){
// 			e.printStackTrace();
// 			}
// 			}
// 			return null ;
// 		}
 		protected  void setClipboardText(String writeMe) {  
 			sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
 			Transferable tText = new StringSelection(writeMe);  
 			sysClip.setContents(tText, null);  
 	    } 
}
/*
 * 
 */
