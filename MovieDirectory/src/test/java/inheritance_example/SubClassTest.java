package inheritance_example;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;


public class SubClassTest extends BaseClassTest {


    protected static int calls=0;

    @BeforeClass
    public void resetGlobalInheritedBaseCounter() {
        BaseClassTest.restoreBeforeClassCal(0);
        BaseClassTest.restoreCounter(0);
        calls=1;
    }

    @Test
    public void initialBaseBeforeClassMethod() {
        System.out.println("BaseClassMethod  with BeforeClass annotation was called ");
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        System.out.println("From: " + trace[1].getMethodName());

    }

    @Test
    public void testExpectingBeforeMethodValueCalls() {
        System.out.println("Method BeforeMethod was inherited : " + BaseClassTest.getCounter() + "times ");
        Assert.assertEquals(3, (int) BaseClassTest.getCounter());

        // BaseClass :BeforeClass is called before SubClass :BeforeClass annotation
        // so the value will be restored to 0 
        Assert.assertEquals(java.util.Optional.ofNullable(BaseClassTest.getBeforeClassCalls()), Optional.of(0));
    }

}
