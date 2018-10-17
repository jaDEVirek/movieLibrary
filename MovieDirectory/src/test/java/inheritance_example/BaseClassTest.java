package inheritance_example;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseClassTest {
    private static Integer counter = 0;
    private static Integer beforeClassCalls = 0;

    /**
     * Method for checking counting in SubClass   {@link SubClassTest}
     *
     * @return count of beforeMethod annotation calls
     */
    public static Integer getCounter() {
        return counter;
    }

    protected static void restoreBeforeClassCal(int i) {
        beforeClassCalls = i;
    }

    public void setCounter(Integer rc) {
        counter = rc;
    }

    @BeforeClass
    public void publicBeforeClassMethod() {
        System.out.println("Action from BaseClass : " + this.getClass());
        System.out.println("Performed publicBeforeClassMethod \n");
        beforeClassCalls++;
    }

    @BeforeMethod
    public void publicBeforeMethod() {
        counter += 1;
        System.out.println("Action class: " + this.getClass() + " from beforeMethod");
        System.out.println("Generated times: " + counter + "\n");

    }

    @Test
    public void shouldReturnFalseByPriority() {
        Assert.assertNotEquals(2, (int) counter);
    }

    @Test(priority = 1)
    public void testBaseClassAnnotations() {
        System.out.println("Called first by priority 1 ");
        System.out.println("Starting");
        System.out.println("Before class calls : " + beforeClassCalls + "\n");
    }

    @Test(priority = 2)
    public void testAssertionsTruth() throws InterruptedException {
        counter--;
        System.out.println("Counter in Assertion:" + counter);
        // for Base class test
        if (SubClassTest.calls != 0) {
            // counter will be increment by method inheritance in SubClass
            Assert.assertSame(counter, 4);
        } else {
            Assert.assertSame(counter, 2);
        }

    }

    public static Integer getBeforeClassCalls() {
        return beforeClassCalls;
    }

    protected static void restoreCounter(Integer i) {
        counter = i;
    }
}
