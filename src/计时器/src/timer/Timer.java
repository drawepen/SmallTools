package timer;

import javax.swing.*;
import java.awt.*;

public class Timer {
    private int presetTime;//Ԥ��ʱ�䣬����
    private long suspendTime,startTime;//��ͣʱ����ʱ���,��ʼʱ���
    private boolean isSuspend;//�Ƿ�����ͣ
    private boolean isRun;

    public void start(int presetTime,JLabel jLabel,Show show){
        int oneTime=200;
        this.presetTime=presetTime;
        startTime=System.currentTimeMillis();
        isSuspend=false;
        jLabel.setForeground( Color.black );
        new Thread(  ){
            public void run(){
                isRun=true;
                while (isRun){
                    if(!isSuspend){
                        int time=presetTime-(int)((System.currentTimeMillis()-startTime)/1000);
                        jLabel.setText( getTimeStr(time) );
                        if(time<=0){
                            jLabel.setForeground( Color.red );
                            show.warning();
                        }
                    }
                    try {
                        Thread.sleep( oneTime );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();;
    }

    public void suspend(){
        if(isSuspend){
            startTime+=(System.currentTimeMillis()-suspendTime);
        }else{
            suspendTime=System.currentTimeMillis();
        }
        isSuspend=(!isSuspend);
    }

    public void stop(){
        isRun=false;
    }

    private String getTimeStr(int seconds){
        String re="";
        if(seconds<0){
            re+="-";
            seconds=-seconds;
        }
        int hous=seconds/3600;
        int min=seconds%3600/60;
        int sec=seconds%60;

        if(hous>0){
            re+=hous+":";
        }
        if(min<10){
            re+="0";
        }
        re+=min+":";
        if(sec<10){
            re+="0";
        }
        re+=sec;
        return re;
    }

}
