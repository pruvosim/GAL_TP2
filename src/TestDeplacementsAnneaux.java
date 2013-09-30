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

}
