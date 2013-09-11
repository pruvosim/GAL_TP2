import java.util.Random;


public class Yinsh {
	
	public enum color{
		BLACK,
		WHITE;
	}
	
	public color[][] plateau;
	public color derniere_couleur_jouee = null;
	
	public Yinsh()
	{
		//initialisation du plateau
		plateau = new color[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				plateau[i][j] = null;
			}
		}
	}
	
	public color current_color()
	{
		Random rand = new Random();
		int r = Math.abs(rand.nextInt(2));
		if(r==1) return color.WHITE;
		else return color.BLACK;
	}
	
	public void put_ring(char col, int line, color color) throws Exception
	{
		int column;
		column = col - 65;
		
		if((column < 0) || (column > 11)) throw new Exception();
		
		//Vérification des voisins
		/*if((plateau[column][line] == color) || (plateau[column][line -2] == color) || (plateau[column -1][line -2] == color) || (plateau[column -1][line] == color) || (plateau[column +1][line -2] == color) || (plateau[column +1][line] == color))
		{
			throw new Exception();
		}*/
		
		if(color == derniere_couleur_jouee) throw new Exception();
		
		plateau[column][line-1] = color;
		derniere_couleur_jouee = color;
		
	}

}
