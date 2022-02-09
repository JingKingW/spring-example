package com.xunmall.example.boot.el;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class LiteralExprSample {

    public static void main(String[] args) throws Exception{

        ExpressionParser expressionParser = new SpelExpressionParser();

        String hellWorld = (String) expressionParser.parseExpression("\"Hello World \"").getValue();
        System.out.println(hellWorld);

        double doubleNumber = (double) expressionParser.parseExpression("6.12312312312+23").getValue();
        System.out.println(doubleNumber);


    }







}
