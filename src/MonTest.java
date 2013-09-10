import junit.framework.*;

public class MonTest extends TestCase{

	
	public MonTest(String name) {super(name);}


	public void test1()
	{
		Yinsh y = new Yinsh();
		assertTrue((y.current_color()) == Yinsh.color.WHITE);
	}



}
