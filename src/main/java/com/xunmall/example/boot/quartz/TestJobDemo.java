package com.xunmall.example.boot.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/12/27.
 */
public class TestJobDemo {
    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedFact = new StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        AnnualCalendar holidays = new AnnualCalendar();

        java.util.Calendar laborDay = new GregorianCalendar();
        laborDay.add(java.util.Calendar.MONDAY, 5);
        laborDay.add(java.util.Calendar.DATE, 1);

        java.util.Calendar nationalDay = new GregorianCalendar();
        nationalDay.add(java.util.Calendar.MONTH, 10);
        nationalDay.add(java.util.Calendar.DATE, 1);

        ArrayList<java.util.Calendar> list = new ArrayList<>();
        list.add(laborDay);
        list.add(nationalDay);
        holidays.setDaysExcluded(list);

        sched.addCalendar("holidays", holidays, false, false);


        JobDetail jobDetail = JobBuilder.newJob(JobDemo.class).withIdentity("helloJob", "group1").build();

//        org.quartz.Trigger trigger = TriggerUtils.makeHourlyTrigger(); // 每个小时触发
//
//        trigger.setStartTime(TriggerUtils.getEvenHourDate(new Date())); // 在下个小时开始
//
//        trigger.setName("myTrigger");

        long endTime = System.currentTimeMillis() + 10000L;

        SimpleTrigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                //每5s运行一次
                                .withIntervalInSeconds(5)
                                //重复运行3次
                                .withRepeatCount(3)
                ).build();

        Date date = sched.scheduleJob(jobDetail, trigger1);

        sched.start();

        System.out.println(date != null ? new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(date) : "任务没执行！");

    }
}


