import junit.framework.TestCase;

public class TestMarkers extends TestCase{

	
	public TestMarkers(String name) {super(name);}
	
	public void testPutMarker() throws Exception
	{
		Yinsh y = new Yinsh();
		
		//On place cet anneau selon le schema
		y.putRing(new Coordinates('D', 2), Yinsh.Color.BLACK);
		
		y.putMarker(new Coordinates('D', 2), Yinsh.Color.BLACK);
		assertTrue(y.m_plateauMarker[3][1] == Yinsh.Color.BLACK);
	}
	
	
	public void testMoveRing() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.putRing(new Coordinates('D', 2), Yinsh.Color.BLACK);
		y.putMarker(new Coordinates('D', 2), Yinsh.Color.BLACK);
		y.moveRing(new Coordinates('D', 2), new Coordinates('D', 5), Yinsh.Color.BLACK);
		assertTrue(y.m_plateau[3][4] == Yinsh.Color.BLACK);
	}
	
	public void testMauvaisMarqueur()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.putRing(new Coordinates('D', 2), Yinsh.Color.BLACK);
			y.putMarker(new Coordinates('D', 2), Yinsh.Color.WHITE);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
		try{
			
			y.putMarker(new Coordinates('D', 3), Yinsh.Color.BLACK);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	
	public void testDeplacement()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.putRing(new Coordinates('D', 2), Yinsh.Color.BLACK);
			y.putMarker(new Coordinates('D', 2), Yinsh.Color.WHITE);
			y.moveRing(new Coordinates('D', 2), new Coordinates('D', 6), Yinsh.Color.BLACK);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	public void testDeplacementHorsZone()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.putRing(new Coordinates('D', 2), Yinsh.Color.BLACK);
			y.putMarker(new Coordinates('D', 2), Yinsh.Color.WHITE);
			y.moveRing(new Coordinates('D', 2), new Coordinates('I', 7), Yinsh.Color.BLACK);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	

}
