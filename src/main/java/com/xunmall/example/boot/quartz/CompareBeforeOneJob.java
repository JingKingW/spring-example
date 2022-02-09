package com.xunmall.example.boot.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompareBeforeOneJob {
    private static final Logger logger = LoggerFactory.getLogger(CompareBeforeOneJob.class);

    public void process() {
        logger.info("*************************比对前一天数据开始*******************************");
        try {
            System.out.println("定时任务执行！");
        } catch (Exception e) {
            logger.error("比对前一天数据异常:" + e);
        }
        logger.info("*************************比对前一天数据结束*******************************");
    }
}
