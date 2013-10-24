import junit.framework.TestCase;

public class TestMarkers extends TestCase{

	
	public TestMarkers(String name) {super(name);}
	
	public void testPutMarker() throws Exception
	{
		Yinsh y = new Yinsh();
		
		//On place cet anneau selon le schema
		y.putRing('D', 2, Yinsh.Color.BLACK);
		
		y.putMarker('D', 2, Yinsh.Color.BLACK);
		assertTrue(y.m_plateauMarker[3][1] == Yinsh.Color.BLACK);
	}
	
	
	public void testMoveRing() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.putRing('D', 2, Yinsh.Color.BLACK);
		y.putMarker('D', 2, Yinsh.Color.BLACK);
		y.moveRing('D', 2, 'D', 5);
		assertTrue(y.m_plateau[3][4] == Yinsh.Color.BLACK);
	}
	
	public void testMauvaisMarqueur()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.putRing('D', 2, Yinsh.Color.BLACK);
			y.putMarker('D', 2, Yinsh.Color.WHITE);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
		try{
			
			y.putMarker('D', 3, Yinsh.Color.BLACK);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	
	public void testDeplacement()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.putRing('D', 2, Yinsh.Color.BLACK);
			y.putMarker('D', 2, Yinsh.Color.WHITE);
			y.moveRing('D', 2, 'D', 6);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	public void testDeplacementHorsZone()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.putRing('D', 2, Yinsh.Color.BLACK);
			y.putMarker('D', 2, Yinsh.Color.WHITE);
			y.moveRing('D', 2, 'I', 7);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	

}
