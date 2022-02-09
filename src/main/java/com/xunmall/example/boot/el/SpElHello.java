package com.xunmall.example.boot.el;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class SpElHello {

    @Test
    public void test() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello' + 'World'");
        String message = (String) expression.getValue();
        System.out.println(message);
    }

}
