package com.xunmall.example.boot.quartz;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yanjing_wang on 2017/4/28.
 */
public class TimerTaskDemo {

    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new TimerTaskImpl(),new Date(),1000*5);
    }

    static class TimerTaskImpl extends TimerTask {
        @Override
        public void run() {
            System.out.println(new Date());
        }
    }
}
