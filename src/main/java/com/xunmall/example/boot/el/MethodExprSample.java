package com.xunmall.example.boot.el;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class MethodExprSample {

    public static void main(String[] args) {
        User user = new User();
        ExpressionParser expressionParser = new SpelExpressionParser();
        EvaluationContext evaluationContext = new StandardEvaluationContext(user);

        //String 方法
        String substring = expressionParser.parseExpression("'Spring SpEL'.substring(7)").getValue(String.class);
        System.out.println(substring);
        Integer index = expressionParser.parseExpression("'Spring SpEL'.indexOf('SpEL')").getValue(Integer.class);
        System.out.println(index);
        //实例 方法
        boolean flag = expressionParser.parseExpression("validatePassword('123426')").getValue(evaluationContext,Boolean.class);
        System.out.println(flag);
    }

    static class User {
        private String name;
        private String password;

        public boolean validatePassword(String password) {
            if ("123456".equals(password)) {
                return true;
            }
            return false;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
