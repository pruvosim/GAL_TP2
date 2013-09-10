import junit.framework.*;

public class MonTest extends TestCase{

	
	public MonTest(String name) {super(name);}


	public void test1()
	{
		Yinsh y = new Yinsh();
		Yinsh.color col = y.current_color();
		assertTrue((col == Yinsh.color.WHITE) || (col == Yinsh.color.BLACK));
	}
	
	public void testVerifAnneauPlace()
	{
		//put_ring(String col, int line, Yinsh.color color)
		Yinsh y = new Yinsh();
		y.put_ring("A",1,Yinsh.color.WHITE);
		assertTrue(plateau[0][1] == Yinsh.color.WHITE);
	}
	
	public void testNombreAnneaux()
	{
		//On créé une nouvelle instance
		Yinsh y = new Yinsh();
		//On crée une variable qui va compter le nombre d'anneaux
		int nb_anneaux=0;
		
		//On parcourt le tableau et on compte le nombre d'anneaux
		for (int i = 0; i < y.plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				if(y.plateau[i][j] != null) nb_anneaux++;
			}
		}
		
		//On test si après l'initialisation il n'y a pas d'anneau placé
		assertTrue(nb_anneaux == 0);
		
		//On place un anneau
		y.put_ring("A",1,Yinsh.color.WHITE);
		
		assertTrue(y.plateau[0][1] == Yinsh.color.WHITE);
	}
	
	
	public void testExceptionMauvaisesCoordonnees()
	{
		Yinsh y = new Yinsh();
		
		try {
			
			y.put_ring("C",3,Yinsh.color.WHITE);
			
		} catch (Exception e) {
			
			assertTrue(false);
			
		}
		
	}
	
	
	public void testExceptionAnneauxMemeCouleurConsecutifs()
	{
		Yinsh y = new Yinsh();
		
		try {
			
			y.put_ring("A",1,Yinsh.color.WHITE);
			y.put_ring("A",2,Yinsh.color.WHITE);
			assertTrue(false);
			
		} catch (Exception e) {
		
			assertTrue(true);
			
		}
		
	}



}
