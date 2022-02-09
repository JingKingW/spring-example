package com.xunmall.example.boot.el;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class ScriptSample {

    @Test
    public void test() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        String scriptText = "function sum(a,b) {return a+b;}";
        scriptEngine.eval(scriptText);
        Invocable invocable = (Invocable) scriptEngine;
        Object result = invocable.invokeFunction("sum", 100, 200);
        System.out.println(result);
    }
}
