package base;

import com.beust.jcommander.IStringConverter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public abstract class TestBase {

    @BeforeClass
    public void setUpClass() {
        PrintDetails("setUpClass");
    }

    @AfterClass
    public void tearDownClass() {
        PrintDetails("tearDownClass");
    }

    @BeforeSuite
    public void setUpSuite() {
        PrintDetails("setUpSuite");
    }

    @AfterSuite
    public void tearDownSuite() {
        PrintDetails("tearDownSuite");
    }

    private void PrintDetails(String methodName){
        System.out.printf("%s::%s @ %s\n", this.getClass().getName(), methodName, Thread.currentThread().getName());
    }
}
