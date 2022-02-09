package com.xunmall.example.boot.el;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class CompilerSample {

    public static void main(String[] args) {
        User user = new User();
        //创建解析器配置
        SpelParserConfiguration configuration = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, CompilerSample.class.getClassLoader());
        //创建解析器
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser(configuration);
        //创建取值上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext(user);
        //表达式
        String expression = "isVipMember('tom') && isVipMember('jesse')";
        //解析表达式
        SpelExpression spelExpression = spelExpressionParser.parseRaw(expression);

        System.out.println(spelExpression.getValue(evaluationContext));
        System.out.println(spelExpression.getValue(evaluationContext));

    }

    static class User {
        private String userName;
        private Integer credits;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getCredits() {
            return credits;
        }

        public void setCredits(Integer credits) {
            this.credits = credits;
        }

        public boolean isVipMember(String name) {
            if ("tom".equals(name) || "jeremy".equals(name)) {
                return true;
            }
            return false;
        }
    }

}
