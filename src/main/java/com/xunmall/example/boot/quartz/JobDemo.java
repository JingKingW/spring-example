package com.xunmall.example.boot.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/28.
 */
public class JobDemo implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date())+"  Hello World!");
    }
}
