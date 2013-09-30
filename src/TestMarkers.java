import junit.framework.TestCase;

public class TestMarkers extends TestCase{

	
	public TestMarkers(String name) {super(name);}
	
	public void testPutMarker() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.put_marker('D',2,Yinsh.color.BLACK);
		assertTrue(y.plateauMarker[3][1] == Yinsh.color.BLACK);
	}
	
	
	public void testMoveRing() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.put_ring('D', 2, Yinsh.color.BLACK);
		y.put_marker('D',2,Yinsh.color.BLACK);
		y.move_ring('D',2,'D',5);
		assertTrue(y.plateau[3][4] == Yinsh.color.BLACK);
	}
	
	public void testMauvaisMarqueur()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.put_ring('D', 2, Yinsh.color.BLACK);
			y.put_marker('D',2,Yinsh.color.WHITE);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
		try{
			
			y.put_marker('D', 3, Yinsh.color.BLACK);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	
	public void testDeplacement()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.put_ring('D', 2, Yinsh.color.BLACK);
			y.put_marker('D',2,Yinsh.color.WHITE);
			y.move_ring('D',2,'D',6);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	public void testDeplacementHorsZone()
	{
		
		Yinsh y = new Yinsh();
		
		try{
			
			y.put_ring('D', 2, Yinsh.color.BLACK);
			y.put_marker('D',2,Yinsh.color.WHITE);
			y.move_ring('D',2,'I',7);
			assertTrue(false);
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}
	
	

}
