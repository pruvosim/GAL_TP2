import junit.framework.*;

public class MonTest extends TestCase{

	
	public MonTest(String name) {super(name);}


	public void test1()
	{
		Yinsh y = new Yinsh();
		Yinsh.color col = y.current_color();
		assertTrue((col == Yinsh.color.WHITE) || (col == Yinsh.color.BLACK));
	}
	
	public void test2()
	{
		
	}



}
