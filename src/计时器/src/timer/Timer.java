package timer;

import javax.swing.*;
import java.awt.*;

public class Timer {
    private int presetTime;//预设时间，秒数
    private long suspendTime,startTime;//暂停时毫秒时间戳,开始时间戳
    private boolean isSuspend;//是否已暂停
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
