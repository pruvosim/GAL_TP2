import junit.framework.*;

public class MonTest extends TestCase{

	
	public MonTest(String name) {super(name);}


	public void test1()
	{
		Yinsh y = new Yinsh();
		Yinsh.Color col = y.currentColor();
		assertTrue((col == Yinsh.Color.WHITE) || (col == Yinsh.Color.BLACK));
	}
	
	public void testVerifAnneauPlace() throws Exception
	{
		//putRing(String col, int line, Yinsh.Color Color)
		Yinsh y = new Yinsh();
		y.putRing(new Coordinates('A', 1), Yinsh.Color.WHITE);
		assertTrue(y.m_plateau[0][0] == Yinsh.Color.WHITE);
	}
	
	public void testNombreAnneaux() throws Exception
	{
		//On créé une nouvelle instance
		Yinsh y = new Yinsh();
		//On crée une variable qui va compter le nombre d'anneaux
		int nbAnneaux=0;
		
		//On parcourt le tableau et on compte le nombre d'anneaux
		for (int i = 0; i < y.m_plateau.length; i++) {
			for (int j = 0; j < y.m_plateau[i].length; j++) {
				if(y.m_plateau[i][j] != null) nbAnneaux++;
			}
		}
		
		//On test si après l'initialisation il n'y a pas d'anneau placé
		assertTrue(nbAnneaux == 0);
		
		//On place un anneau
		y.putRing(new Coordinates('A', 1), Yinsh.Color.WHITE);
		
		assertTrue(y.m_plateau[0][0] == Yinsh.Color.WHITE);
	}
	
	
	public void testExceptionMauvaisesCoordonnees()
	{
		Yinsh y = new Yinsh();
		
		try {
			
			y.putRing(new Coordinates('C', 1), Yinsh.Color.WHITE);
			
		} catch (Exception e) {
			
			assertTrue(false);
			
		}
		
	}
	
	
	public void testExceptionAnneauxMemeCouleurConsecutifs()
	{
		Yinsh y = new Yinsh();
		
		try {
			
			y.putRing(new Coordinates('B', 3), Yinsh.Color.WHITE);
			y.putRing(new Coordinates('B', 4), Yinsh.Color.WHITE);
			assertTrue(false);
			
		} catch (Exception e) {
		
			assertTrue(true);
			
		}
		
	}
	
	public void testExceptionMemeIntersection()
	{
		Yinsh y = new Yinsh();
		
		try {
			
			y.putRing(new Coordinates('B', 3), Yinsh.Color.WHITE);
			y.putRing(new Coordinates('B', 3), Yinsh.Color.BLACK);
			assertTrue(false);
			
		} catch (Exception e) {
		
			assertTrue(true);
			
		}
		
	}
	
	public void testIsInitialized() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.putRing(new Coordinates('A', 3), Yinsh.Color.BLACK);
		y.putRing(new Coordinates('D', 1), Yinsh.Color.WHITE);
		y.putRing(new Coordinates('A', 7), Yinsh.Color.BLACK);
		y.putRing(new Coordinates('D', 3), Yinsh.Color.WHITE);
		y.putRing(new Coordinates('C', 1), Yinsh.Color.BLACK);
		y.putRing(new Coordinates('C', 3), Yinsh.Color.WHITE);
		y.putRing(new Coordinates('B', 4), Yinsh.Color.BLACK);
		y.putRing(new Coordinates('A', 5), Yinsh.Color.WHITE);
		y.putRing(new Coordinates('C', 2), Yinsh.Color.BLACK);
		y.putRing(new Coordinates('B', 3), Yinsh.Color.WHITE);
		
		assertTrue((y.isInitialized()));
		
	}



}
