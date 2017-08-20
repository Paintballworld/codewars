package kyu2;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import com.sun.xml.internal.fastinfoset.sax.SystemIdResolver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MathEvaluator {

    public double calculate(String expression)  {
        System.out.println(expression);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        try {
            Object res = engine.eval(expression);
            if (res instanceof Integer)
                return (int) res * 1.0d;
            return (double) res;
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return Double.MIN_NORMAL;
    }

    public void testAddition() {
        assertEquals(new MathEvaluator().calculate("1+1"), 2d, 0.01);

        assertEquals(new MathEvaluator().calculate("1 - 1"), 0d, 0.01);

        assertEquals(new MathEvaluator().calculate("1* 1"), 1d, 0.01);

        assertEquals(new MathEvaluator().calculate("1 /1"), 1d, 0.01);

        assertEquals(new MathEvaluator().calculate("-123"), -123d, 0.01);

        assertEquals(new MathEvaluator().calculate("123"), 123d, 0.01);

        assertEquals(new MathEvaluator().calculate("2 /2+3 * 4.75- -6"), 21.25, 0.01);

        assertEquals(new MathEvaluator().calculate("12* 123"), 1476d, 0.01);

        assertEquals(new MathEvaluator().calculate("2 / (2 + 3) * 4.33 - -6"), 7.732, 0.01);
    }

    private static void assertEquals(double actual, double expected, double threshold) {
        System.out.println("\nActual :" + actual + "\n Expect :" + expected);
        if (Double.compare(actual, expected) != 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }

    public static void main(String[] args) {
        new MathEvaluator().testAddition();
    }


}
