import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite()
	{
		TestSuite suite = new TestSuite("Mes tests");
		
		suite.addTest(new TestSuite(MonTest.class));
		suite.addTest(new TestSuite(TestMarkers.class));
		return suite;
	}
	
}
