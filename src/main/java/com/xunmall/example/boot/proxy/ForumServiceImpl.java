package com.xunmall.example.boot.proxy;

/**
 * Created by Gimgoog on 2018/2/11.
 */
public class ForumServiceImpl implements ForumService {

    @Override
    public void removeTopic(String topicId) {
        System.out.println("模拟删除topicId:" + topicId);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeForum(String forumId) {
        System.out.println("模拟删除forumId:" + forumId);
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
