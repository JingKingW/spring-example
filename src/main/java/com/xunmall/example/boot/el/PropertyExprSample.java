package com.xunmall.example.boot.el;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class PropertyExprSample {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("tom");
        user.setLastVisit(new Date());
        user.setCredits(100);
        user.setPlaceOfBirth(new PlaceOfBirth("中国", "上海"));

        ExpressionParser expressionParser = new SpelExpressionParser();
        EvaluationContext evaluationContext = new StandardEvaluationContext(user);

        String userName = (String) expressionParser.parseExpression("userName").getValue(evaluationContext);
        System.out.println(userName);

        String country = (String) expressionParser.parseExpression("placeOfBirth.country").getValue(evaluationContext);
        System.out.println(country);
    }

    static class User {
        private String userName;
        private Date lastVisit;
        private Integer credits;
        private PlaceOfBirth placeOfBirth;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Date getLastVisit() {
            return lastVisit;
        }

        public void setLastVisit(Date lastVisit) {
            this.lastVisit = lastVisit;
        }

        public Integer getCredits() {
            return credits;
        }

        public void setCredits(Integer credits) {
            this.credits = credits;
        }

        public PlaceOfBirth getPlaceOfBirth() {
            return placeOfBirth;
        }

        public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
        }
    }

}
