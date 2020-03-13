package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Show extends JFrame {
    private Timer timer;

    private JButton buttonStart,buttonSuspend,buttonStop;
    private JPanel panel,jPanel1,jPanel2;
    private Choice chour,cminute, csecond;
    private JLabel jLabel;
    private CardLayout cardLayout;
    private int state=0;//0已停止，1已开始，2已暂停
    int fwidth,fheight;
    public Show(){
        super("计时器");
        try{ // 使用Windows的界面风格
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception e){
            e.printStackTrace();
        }

        fwidth = Toolkit.getDefaultToolkit().getScreenSize().width;//获取分辨率宽
        fheight = Toolkit.getDefaultToolkit().getScreenSize().height;//获取分辨率高
        setBounds(fwidth*6/7,fheight*24/25-fwidth/18,fwidth/7,fwidth/18);
        setVisible(true);

        panel = (JPanel) this.getContentPane();
        jPanel1=new JPanel(  );
        jPanel2=new JPanel(  );
        chour=new Choice();
        cminute=new Choice();
        csecond=new Choice();
        buttonStart=new JButton("开始");
        buttonSuspend=new JButton( "暂停" );
        buttonStop=new JButton( "停止" );
        jLabel=new JLabel( "00:00" ,JLabel.CENTER);

        Font f=new Font("微软雅黑", 1,fwidth/80);
        buttonStart.setFont(f);
        chour.setFont(f);
        cminute.setFont(f);
        csecond.setFont(f);
        Font f2=new Font("微软雅黑", 1,fwidth/100);
        buttonSuspend.setFont(f2);
        buttonStop.setFont(f2);
        jLabel.setFont( new Font( "微软雅黑", 1,fwidth/50 ) );

        chour.add("时");
        cminute.add("分");
        csecond.add("秒");
        for(int i=0;i<24;i++) {
            chour.add(""+i);
        }
        for(int i=0;i<60;i++) {
            cminute.add(""+i);
            csecond.add(""+i);
        }
        chour.select( 2 );
        cminute.select( 1 );
        csecond.select( 1 );

        panel.setLayout( cardLayout=new CardLayout(  ) );
        jPanel1.add(chour);
        jPanel1.add(cminute);
        jPanel1.add(csecond);
        jPanel1.add(buttonStart);
        jPanel1.setLayout(new FlowLayout(1));
        panel.add(jPanel1,"0");

        jPanel2.setLayout( null );
        jLabel.setBounds( 0,0,panel.getWidth()*2/3,panel.getHeight() );
        jPanel2.add(jLabel);
        buttonSuspend.setBounds( panel.getWidth()*2/3,0,panel.getWidth()/3,panel.getHeight()/2 );
        jPanel2.add(buttonSuspend);
        buttonStop.setBounds( panel.getWidth()*2/3,panel.getHeight()/2,panel.getWidth()/3,panel.getHeight()/2 );
        jPanel2.add(buttonStop);
        panel.add(jPanel2,"1");

        addMonitor();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);//始终在屏幕顶层
        setVisible(true);
    }

    private void addMonitor(){
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int hour,minute,second;
                try {
                    hour=Integer.parseInt(chour.getSelectedItem());
                    minute=Integer.parseInt(cminute.getSelectedItem());
                    second=Integer.parseInt(csecond.getSelectedItem());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    return;
                }
                timer.start( hour*3600+minute*60+second,jLabel,Show.this );
                cardLayout.show(panel ,"1" );
                state=1;
            }
        });
        buttonSuspend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                timer.suspend();
                if(state==2) {
                    state=1;
                    buttonSuspend.setText("暂停");
                }else{
                    state=2;
                    buttonSuspend.setText("继续");
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                timer.stop();
                buttonSuspend.setText("暂停");
                cardLayout.show(panel ,"0" );
                setBounds(fwidth*6/7,fheight*24/25-fwidth/18,fwidth/7,fwidth/18);
                state=0;
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                timer.stop();
            }
        });
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void warning(){
        double rate=1.01;
        int w=(int)(getWidth()*rate);
        int h=(int)(getHeight()*rate);
        if(h==getWidth())//防止比率过小，没有增加
            h+=1;
        if(h==getHeight())
            h+=1;
        int unHeight=fheight*24/25;
        if(fwidth<w){
            w=fwidth;
        }
        if(unHeight<h){
            h=unHeight;
        }
        setBounds(fwidth-w,unHeight-h,w,h);
//        if(fwidth>w||unHeight>h){
//            if(fwidth<w){
//                w=fwidth;
//            }
//            if(unHeight<h){
//                h=unHeight;
//            }
//            setBounds(fwidth-w,unHeight-h,w,h);
//        }
    }
}
