import java.util.Random;


public class Yinsh {
	
	public enum color{
		BLACK,
		WHITE;
	}
	
	public color[][] plateau;
	public color[][] plateauMarker;
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
		
		plateauMarker = new color[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				plateauMarker[i][j] = null;
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
		
		if(plateau[column][line-1] != null) throw new Exception();
		
		if(color == derniere_couleur_jouee) throw new Exception();
		
		plateau[column][line-1] = color;
		derniere_couleur_jouee = color;
		
	}
	
	
	public boolean isInitialized()
	{
		int nb_anneaux_blancs = 0, nb_anneaux_noirs = 0;
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if(plateau[i][j] == color.BLACK) nb_anneaux_noirs++;
				if(plateau[i][j] == color.WHITE) nb_anneaux_blancs++;
			}
		}
		
		if((nb_anneaux_blancs == 5) && (nb_anneaux_noirs == 5)) return true;
		else return false;
	}
	
	public void put_marker(char col, int line, color color) throws Exception
	{
		int column;
		column = col - 65;

		
		if(plateau[column][line-1] == null) throw new Exception();
		if(plateau[column][line-1] != color) throw new Exception();
		plateauMarker[column][line-1] = color;
		
	}
	
	public void move_ring(char col_depart, int line_depart, char col_arrivee, int line_arrivee)
	{
		int column_depart;
		column_depart = col_depart - 65;
		int column_arrivee;
		column_arrivee = col_arrivee - 65;
		
		int nb_anneaux_a_retourner = 0;
		
		if(plateauMarker[column_depart][line_depart-1] == plateau[column_depart][line_depart-1])
		{
			if(plateau[column_arrivee][line_arrivee-1] == null)
			{
				plateau[column_arrivee][line_arrivee-1] = plateau[column_depart][line_depart-1];
				plateau[column_depart][line_depart-1] = null;
				
				if(column_depart == column_arrivee) nb_anneaux_a_retourner = line_arrivee - line_depart;
				
				for (int i = 0; i < nb_anneaux_a_retourner; i++) {
					if(plateauMarker[column_depart][line_depart + i] == Yinsh.color.WHITE) plateauMarker[column_depart][line_depart + i] = Yinsh.color.BLACK;
					else plateauMarker[column_depart][line_depart + i] = Yinsh.color.WHITE ;
				}
			}
			
			
		}
	}

}
