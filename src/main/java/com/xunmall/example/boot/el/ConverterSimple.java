package com.xunmall.example.boot.el;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class ConverterSimple {

    @Test
    public void test() {
        Simple simple = new Simple();

        simple.booleanList.add(true);

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(simple);

        ExpressionParser expressionParser = new SpelExpressionParser();

        expressionParser.parseExpression("booleanList[0]").setValue(standardEvaluationContext, "false");

        Boolean b = simple.booleanList.get(0);

        System.out.println(b);
    }

    class Simple {
        public List<Boolean> booleanList = new ArrayList<>();
    }
}
