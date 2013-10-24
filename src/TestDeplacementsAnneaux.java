import junit.framework.TestCase;

public class TestDeplacementsAnneaux extends TestCase{


	public TestDeplacementsAnneaux(String name) {super(name);}

	public void testChangementCouleur()
	{
		Yinsh y = new Yinsh();

		try{

			y.putRing('E', 4, Yinsh.Color.BLACK);

			y.m_plateauMarker[4][3] = Yinsh.Color.BLACK;
			y.m_plateauMarker[4][4] = Yinsh.Color.BLACK;
			y.m_plateauMarker[4][5] = Yinsh.Color.WHITE;
			y.m_plateauMarker[4][6] = Yinsh.Color.WHITE;
			y.m_plateauMarker[4][7] = Yinsh.Color.BLACK;
			y.m_plateauMarker[4][8] = Yinsh.Color.WHITE;

			y.moveRing('E', 4, 'E', 10);

			assertTrue(y.m_plateauMarker[4][3] == Yinsh.Color.BLACK && y.m_plateauMarker[4][4] == Yinsh.Color.WHITE && y.m_plateauMarker[4][5] == Yinsh.Color.BLACK && y.m_plateauMarker[4][6] == Yinsh.Color.BLACK && y.m_plateauMarker[4][7] == Yinsh.Color.WHITE && y.m_plateauMarker[4][8] == Yinsh.Color.BLACK);

		}catch(Exception e){
			assertTrue(false);
		}
	}

	public void testSuppresion() throws Exception{

		Yinsh y = new Yinsh();

		y.m_plateau[7][9] = Yinsh.Color.BLACK;
		y.m_plateauMarker[4][5] = Yinsh.Color.BLACK;
		y.m_plateauMarker[5][6] = Yinsh.Color.BLACK;
		y.m_plateauMarker[6][7] = Yinsh.Color.BLACK;
		y.m_plateauMarker[7][8] = Yinsh.Color.BLACK;
		y.m_plateauMarker[8][9] = Yinsh.Color.BLACK;

		y.removeRow('E', 6, 'I', 10);
		y.removeRing('H', 10);

		assertTrue(y.m_nbPointNoir == 1);

	}
	
	public void testDeplacementsPossibles()
	{
		Yinsh y = new Yinsh();
		y.m_plateau[3][2] = Yinsh.Color.BLACK;
		y.m_plateau[1][3] = Yinsh.Color.WHITE;
		y.m_plateauMarker[4][2] = Yinsh.Color.WHITE;
		y.m_plateauMarker[4][8] = Yinsh.Color.WHITE;
		y.m_plateauMarker[4][7] = Yinsh.Color.BLACK;
		y.m_plateauMarker[4][6] = Yinsh.Color.WHITE;
		y.m_plateauMarker[4][5] = Yinsh.Color.WHITE;
		y.m_plateauMarker[4][4] = Yinsh.Color.BLACK;
		y.m_plateauMarker[2][3] = Yinsh.Color.BLACK;
		y.m_plateauMarker[7][6] = Yinsh.Color.WHITE;
		y.m_plateauMarker[8][7] = Yinsh.Color.WHITE;
		
		
		assertTrue(y.deplacements('E',4) != null);
	}
	
	public void testBlitz() throws Exception
	{
		Yinsh y = new Yinsh();
		y.mode = Yinsh.Type.BLITZ;
		y.m_plateau[7][9] = Yinsh.Color.BLACK;
		y.m_plateauMarker[4][5] = Yinsh.Color.BLACK;
		y.m_plateauMarker[5][6] = Yinsh.Color.BLACK;
		y.m_plateauMarker[6][7] = Yinsh.Color.BLACK;
		y.m_plateauMarker[7][8] = Yinsh.Color.BLACK;
		y.m_plateauMarker[8][9] = Yinsh.Color.BLACK;
		y.removeRow('E', 6, 'I', 10);
		y.removeRing('H', 10);
		
		assertTrue(y.winnerIs());
		
	}
	
	public void testSuppressionPlus(){
		
		Yinsh y = new Yinsh();
		
		try{
			y.m_plateauMarker[1][2] = Yinsh.Color.BLACK;
			y.m_plateauMarker[2][3] = Yinsh.Color.BLACK;
			y.m_plateauMarker[3][4] = Yinsh.Color.BLACK;
			y.m_plateauMarker[4][5] = Yinsh.Color.BLACK;
			y.m_plateauMarker[5][6] = Yinsh.Color.BLACK;
			y.m_plateauMarker[6][7] = Yinsh.Color.BLACK;
			y.m_plateauMarker[7][8] = Yinsh.Color.BLACK;
			y.m_plateauMarker[8][9] = Yinsh.Color.BLACK;
			
			y.removeRow('B', 3, 'I', 10);
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(true);
		}
	}
	
	public void testChoixAlignementARetirer() throws Exception
	{
		Yinsh y = new Yinsh();
		y.m_joueurEnCours = Yinsh.Color.BLACK;
		
		y.m_plateauMarker[1][2] = Yinsh.Color.BLACK;
		y.m_plateauMarker[2][3] = Yinsh.Color.BLACK;
		y.m_plateauMarker[3][4] = Yinsh.Color.BLACK;
		y.m_plateauMarker[5][6] = Yinsh.Color.BLACK;
		
		y.m_plateauMarker[3][6] = Yinsh.Color.BLACK;
		y.m_plateauMarker[5][4] = Yinsh.Color.BLACK;
		y.m_plateauMarker[6][3] = Yinsh.Color.BLACK;
		y.m_plateauMarker[7][2] = Yinsh.Color.BLACK;
		
		y.m_plateauMarker[4][4] = Yinsh.Color.WHITE;
		y.m_plateauMarker[4][3] = Yinsh.Color.BLACK;
		
		y.m_plateau[4][3] = Yinsh.Color.BLACK;
		y.moveRing('D', 4, 'D', 6);
		
		y.m_plateauMarker[4][6] = Yinsh.Color.BLACK;
		
		y.alignementsPossibles('D', 6);
		
		assertTrue(y.alignementsPossibles('D', 6) == 1);

	}
	
	public void testTroisAnneauxRetires()
	{
		Yinsh y = new Yinsh();
		
		y.m_joueurEnCours = Yinsh.Color.WHITE;
		
		y.m_plateau[4][5] = Yinsh.Color.WHITE;
		y.m_plateau[3][4] = Yinsh.Color.WHITE;
		y.m_plateau[2][3] = Yinsh.Color.WHITE;
		
		y.removeRing('E', 6);
		y.removeRing('D', 5);
		y.removeRing('C', 4);
		
		assertTrue(y.m_whiteRingsRemoved == 3);
		
	}
	
	public void testTousLesAnneauxUtilises() throws Exception
	{
		Yinsh y = new Yinsh();
		
		y.m_marqueursBlancs = 40;
		y.m_marqueursNoirs = 9;
		y.m_marqueursUtilises = 49;
		
		y.m_derniereCouleurJouee = Yinsh.Color.WHITE;
		
		y.putRing('A', 5, Yinsh.Color.BLACK);
		y.putRing('B', 5, Yinsh.Color.WHITE);
		y.putRing('A', 1, Yinsh.Color.BLACK);
		y.putMarker('A', 5, Yinsh.Color.BLACK);
		y.putMarker('A', 1, Yinsh.Color.BLACK);
		
		assertTrue(y.markerLimitWinnerIs());
	}
}