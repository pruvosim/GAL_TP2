import junit.framework.TestCase;

public class TestDeplacementsAnneaux extends TestCase{


	public TestDeplacementsAnneaux(String name) {super(name);}

	public void testChangementCouleur()
	{
		Yinsh y = new Yinsh();

		try{

			y.put_ring('E', 4, Yinsh.color.BLACK);

			y.plateauMarker[4][3] = Yinsh.color.BLACK;
			y.plateauMarker[4][4] = Yinsh.color.BLACK;
			y.plateauMarker[4][5] = Yinsh.color.WHITE;
			y.plateauMarker[4][6] = Yinsh.color.WHITE;
			y.plateauMarker[4][7] = Yinsh.color.BLACK;
			y.plateauMarker[4][8] = Yinsh.color.WHITE;

			y.move_ring('E', 4, 'E', 10);

			assertTrue(y.plateauMarker[4][3] == Yinsh.color.BLACK && y.plateauMarker[4][4] == Yinsh.color.WHITE && y.plateauMarker[4][5] == Yinsh.color.BLACK && y.plateauMarker[4][6] == Yinsh.color.BLACK && y.plateauMarker[4][7] == Yinsh.color.WHITE && y.plateauMarker[4][8] == Yinsh.color.BLACK);

		}catch(Exception e){
			assertTrue(false);
		}
	}

	public void testSuppresion() throws Exception{

		Yinsh y = new Yinsh();

		y.plateau[7][9] = Yinsh.color.BLACK;
		y.plateauMarker[4][5] = Yinsh.color.BLACK;
		y.plateauMarker[5][6] = Yinsh.color.BLACK;
		y.plateauMarker[6][7] = Yinsh.color.BLACK;
		y.plateauMarker[7][8] = Yinsh.color.BLACK;
		y.plateauMarker[8][9] = Yinsh.color.BLACK;

		y.remove_row('E', 6, 'I', 10);
		y.remove_ring('H', 10);

		assertTrue(y.nb_point_noir == 1);

	}
	
	public void testDeplacementsPossibles()
	{
		Yinsh y = new Yinsh();
		y.plateau[3][2] = Yinsh.color.BLACK;
		y.plateau[1][3] = Yinsh.color.WHITE;
		y.plateauMarker[4][2] = Yinsh.color.WHITE;
		y.plateauMarker[4][8] = Yinsh.color.WHITE;
		y.plateauMarker[4][7] = Yinsh.color.BLACK;
		y.plateauMarker[4][6] = Yinsh.color.WHITE;
		y.plateauMarker[4][5] = Yinsh.color.WHITE;
		y.plateauMarker[4][4] = Yinsh.color.BLACK;
		y.plateauMarker[2][3] = Yinsh.color.BLACK;
		y.plateauMarker[7][6] = Yinsh.color.WHITE;
		y.plateauMarker[8][7] = Yinsh.color.WHITE;
		
		
		assertTrue(y.deplacements('E',4) != null);
	}
	
	public void testBlitz() throws Exception
	{
		Yinsh y = new Yinsh();
		y.mode = Yinsh.BLITZ;
		y.plateau[7][9] = Yinsh.color.BLACK;
		y.plateauMarker[4][5] = Yinsh.color.BLACK;
		y.plateauMarker[5][6] = Yinsh.color.BLACK;
		y.plateauMarker[6][7] = Yinsh.color.BLACK;
		y.plateauMarker[7][8] = Yinsh.color.BLACK;
		y.plateauMarker[8][9] = Yinsh.color.BLACK;
		y.remove_row('E', 6, 'I', 10);
		y.remove_ring('H', 10);
		
		assertTrue(y.win());
		
	}
	
	public void testSuppressionPlus(){
		
		Yinsh y = new Yinsh();
		
		try{
			y.plateauMarker[1][2] = Yinsh.color.BLACK;
			y.plateauMarker[2][3] = Yinsh.color.BLACK;
			y.plateauMarker[3][4] = Yinsh.color.BLACK;
			y.plateauMarker[4][5] = Yinsh.color.BLACK;
			y.plateauMarker[5][6] = Yinsh.color.BLACK;
			y.plateauMarker[6][7] = Yinsh.color.BLACK;
			y.plateauMarker[7][8] = Yinsh.color.BLACK;
			y.plateauMarker[8][9] = Yinsh.color.BLACK;
			
			y.remove_row('B', 3, 'I', 10);
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(true);
		}
	}
	
	public void testChoixAlignementARetirer() throws Exception
	{
		Yinsh y = new Yinsh();
		y.joueur_en_cours = Yinsh.color.BLACK;
		
		y.plateauMarker[1][2] = Yinsh.color.BLACK;
		y.plateauMarker[2][3] = Yinsh.color.BLACK;
		y.plateauMarker[3][4] = Yinsh.color.BLACK;
		y.plateauMarker[5][6] = Yinsh.color.BLACK;
		
		y.plateauMarker[3][6] = Yinsh.color.BLACK;
		y.plateauMarker[5][4] = Yinsh.color.BLACK;
		y.plateauMarker[6][3] = Yinsh.color.BLACK;
		y.plateauMarker[7][2] = Yinsh.color.BLACK;
		
		y.plateauMarker[4][4] = Yinsh.color.WHITE;
		y.plateauMarker[4][3] = Yinsh.color.BLACK;
		
		y.plateau[4][3] = Yinsh.color.BLACK;
		y.move_ring('D', 4, 'D', 6);
		
		y.plateauMarker[4][6] = Yinsh.color.BLACK;
		
		y.alignementsPossibles('D', 6);
		
		assertTrue(y.alignementsPossibles('D', 6) == 1);

	}
	
	public void testTroisAnneauxRetires()
	{
		Yinsh y = new Yinsh();
		
		y.joueur_en_cours = Yinsh.color.WHITE;
		
		y.plateau[4][5] = Yinsh.color.WHITE;
		y.plateau[3][4] = Yinsh.color.WHITE;
		y.plateau[2][3] = Yinsh.color.WHITE;
		
		y.remove_ring('E', 6);
		y.remove_ring('D', 5);
		y.remove_ring('C', 4);
		
		assertTrue(y.white_rings_removed == 3);
		
	}
	
	public void testTousLesAnneauxUtilises() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.marqueurs_blancs = 40;
		y.marqueurs_noirs = 9;
		y.marqueurs_utilises = 49;
		
		y.derniere_couleur_jouee = Yinsh.color.WHITE;
		
		y.put_ring('A', 5, Yinsh.color.BLACK);
		y.put_ring('B', 5, Yinsh.color.WHITE);
		y.put_ring('A', 1, Yinsh.color.BLACK);
		y.put_marker('A', 5, Yinsh.color.BLACK);
		y.put_marker('A', 1, Yinsh.color.BLACK);
		
		assertTrue(y.win_limite_anneaux());
	}
}