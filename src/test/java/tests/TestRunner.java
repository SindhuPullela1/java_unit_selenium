package tests;

import org.testng.TestNG;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();

        // Add test classes to be executed
        testng.setTestClasses(new Class[] {
                AlertTest.class,
                BasicAuthTest.class,
                DataTableTests.class,
                DynamicControlsTest.class,
                FileDownloadTest.class,
                FileUploadTest.class,
                FramesTest.class
        });

        testng.run();
    }
}
