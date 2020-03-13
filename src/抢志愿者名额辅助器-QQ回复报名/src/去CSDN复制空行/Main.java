package 去CSDN复制空行;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Calendar;

public class Main {
	public static void main(String[] args) throws AWTException {
        try{ // 使用Windows的界面风格
            UIManager.setLookAndFeel
                    ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        setText();
        JOptionPane.showMessageDialog(null, "成功", "OK!", JOptionPane.INFORMATION_MESSAGE );
	}

	private static void setText( ) {
        String str=getString();
        assert str != null;
        String[] strs=str.split( "\n" );
        StringBuilder re= new StringBuilder();
        for(String str0:strs){
            if(!str0.equals( "" )){
                re.append( str0 );
                re.append( "\n" );
            }
        }
        setClipboardText( re.toString() );
    }
    private static void setClipboardText(String writeMe) {
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        sysClip.setContents(tText, null);
    }
    private static String getString(){
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
	    Transferable clipTf =sysClip.getContents(null);
	    if(clipTf.isDataFlavorSupported( DataFlavor.stringFlavor)){
	        try{
                return (String) clipTf.getTransferData(DataFlavor.stringFlavor);
 			}catch(Exception e){
 			    e.printStackTrace();
 			}
	    }
	    return null ;
	}
}