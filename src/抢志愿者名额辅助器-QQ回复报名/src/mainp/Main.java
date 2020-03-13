package mainp;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Main {
//	static Clipboard sysClip;
	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		new MainFrame();
		
	}
	

	/*
	  1. 从指定的剪切板中获取文本内容  
  59.   
  60. protected static String getClipboardText(Clipboard clip) throws Exception{  
  61.    // 获取剪切板中的内容  
  62.    Transferable clipT = clip.getContents(null);  
  63.    if (clipT != null) {  
  64.     // 检查内容是否是文本类型  
  65.     if (clipT.isDataFlavorSupported(DataFlavor.stringFlavor))  
  66.      return (String)clipT.getTransferData(DataFlavor.stringFlavor);   
  67.    }  
  68.    return null;  
  69. }   
  73.   
  74. protected static void setClipboardText(Clipboard clip, String writeMe) {  
  75.    Transferable tText = new StringSelection(writeMe);  
  76.    clip.setContents(tText, null);  
  77. }  
  Calendar cld;
			cld = Calendar.getInstance();
			int dhour = cld.get(Calendar.HOUR_OF_DAY);
			int dminute = cld.get(Calendar.MINUTE);
			int dsecond = cld.get(Calendar.SECOND);
		System.out.println(second);
		try {                 
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c = Calendar.getInstance();
		System.out.println(c.get(Calendar.SECOND));
	 */

}