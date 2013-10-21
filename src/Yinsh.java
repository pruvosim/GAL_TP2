import java.util.ArrayList;
import java.util.Random;


public class Yinsh {

	public enum color{
		BLACK,
		WHITE;
	}

	public color[][] plateau;
	public color[][] plateauMarker;
	public color derniere_couleur_jouee = null;

	public int nb_point_noir;
	public int nb_point_blanc;

	public static int BLITZ = 1;
	public static int NORMAL = 0;

	public int mode;

	public color joueur_en_cours;

	public int white_rings_removed = 0;
	public int black_rings_removed = 0;

	public int marqueurs_blancs = 0;
	public int marqueurs_noirs = 0;
	public int marqueurs_utilises = 0;


	public Yinsh()
	{
		nb_point_blanc = 0 ;
		nb_point_noir = 0 ;

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

		//VÃ©rification des voisins
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
		
		marqueurs_utilises ++;

	}

	public void move_ring(char col_depart, int line_depart, char col_arrivee, int line_arrivee) throws Exception
	{
		int column_depart;
		column_depart = col_depart - 65;
		int column_arrivee;
		column_arrivee = col_arrivee - 65;

		int nb_iterations = 0;
		int anneau_different_sur_le_chemin = 0;
		int nb_anneaux_a_retourner = 0;

		if(plateauMarker[column_depart][line_depart-1] == plateau[column_depart][line_depart-1])
		{

			if(column_arrivee == column_depart) nb_iterations = line_arrivee - line_depart + 1;
			else nb_iterations = column_arrivee - column_depart + 1;

			for (int i = 0; i < nb_iterations; i++) {
				if(plateau[column_depart + i][line_depart -1 + i] != plateau[column_depart][line_depart -1] && plateau[column_depart + i][line_depart -1 + i] != null) anneau_different_sur_le_chemin = 1;
			}

			if(anneau_different_sur_le_chemin == 1) throw new Exception();

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

	public void remove_row(char col_depart, int line_depart, char col_arrivee, int line_arrivee) throws Exception
	{
		int column_depart;
		column_depart = col_depart - 65;
		int column_arrivee;
		column_arrivee = col_arrivee - 65;

		int nb_iterations = 0;
		int nb_marqueurs = 0;

		if(column_arrivee == column_depart) nb_iterations = line_arrivee - line_depart + 1;
		else nb_iterations = column_arrivee - column_depart + 1;

		if (nb_iterations > 5){
			System.out.println("Il y a un alignement de plus de 5 marqueurs. Veuillez préciser quels marqueurs sont à enlever!");
			throw new Exception();
		}

		for (int i = 0; i < nb_iterations; i++) {
			if(plateauMarker[column_depart + i][line_depart -1 + i] != null) nb_marqueurs ++;
		}

		if(nb_marqueurs == nb_iterations)
		{
			for (int i = 0; i < nb_iterations; i++) {
				plateauMarker[column_depart + i][line_depart -1 + i] = null;
			}
			nb_point_noir += 1 ;
		}
	}

	public void remove_ring(char col, int line)
	{
		int column;
		column = col - 65;

		plateau[column][line -1] = null;

		if(joueur_en_cours == Yinsh.color.BLACK) black_rings_removed += 1;
		else white_rings_removed += 1 ;
	}

	public String[] deplacements(char column, int line, color couleur){

		int col = column - 65;
		int l = line - 1;
		int i;
		int marque;
		int fin;

		ArrayList<Integer> colonne = new ArrayList<Integer>();
		ArrayList<Integer> ligne = new ArrayList<Integer>();

		i = 1;
		marque = 0;
		fin = 0;
		while( (l-i > -1) && (plateau[col][l-i] == null) && (fin == 0)){
			if(plateauMarker[col][l-i] == null){
				colonne.add(col);
				ligne.add(l-i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(plateauMarker[col][l-i] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (l+i < 11) && (plateau[col][l+i] == null)  && (fin == 0)){
			if(plateauMarker[col][l+i] == null){
				colonne.add(col);
				ligne.add(l+i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(plateauMarker[col][l+i] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (col-i > -1) && (plateau[col-i][l] == null)  && (fin == 0)){
			if(plateauMarker[col-i][l] == null){
				colonne.add(col-i);
				ligne.add(l);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(plateauMarker[col-i][l] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (col+i < 11) && (plateau[col+i][l] == null)  && (fin == 0)){
			if(plateauMarker[col+i][l] == null){
				colonne.add(col+i);
				ligne.add(l);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(plateauMarker[col+i][l] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (l-i > -1) && (col-i > -1) && (plateau[col-i][l-i] == null)  && (fin == 0)){
			if(plateauMarker[col-i][l-i] == null){
				colonne.add(col-i);
				ligne.add(l-i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(plateauMarker[col-i][l-i] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (l+i < 11) && (col+i < 11) && (plateau[col+i][l+i] == null)  && (fin == 0)){
			if(plateauMarker[col+i][l+i] == null){
				colonne.add(col+i);
				ligne.add(l+i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(plateauMarker[col+i][l+i] == null){
					fin = 1;

				}
			}
			i++;

		}

		int[] colonne_tab = conv_int_liste_tableau(colonne);
		int[] ligne_tab = conv_int_liste_tableau(ligne);
		String[] solution = concatenation_tab(colonne_tab, ligne_tab);

		for(i = 0; i < solution.length; i++){
			System.out.println(solution[i]);
		}

		return solution;

	}

	public int[] conv_int_liste_tableau(ArrayList<Integer> A){

		int i;
		int[] tab = new int[A.size()];
		for(i = 0; i < A.size(); i++){
			tab[i] = A.get(i);
		}

		return tab;
	}

	public char convert_lettre(int c){
		if(c == 0){
			return 'A';
		}
		if(c == 1){
			return 'B';
		}
		if(c == 2){
			return 'C';
		}
		if(c == 3){
			return 'D';
		}
		if(c == 4){
			return 'E';
		}
		if(c == 5){
			return 'F';
		}
		if(c == 6){
			return 'G';
		}
		if(c == 7){
			return 'H';
		}
		if(c == 8){
			return 'I';
		}
		if(c == 9){
			return 'J';
		}
		if(c == 10){
			return 'K';
		}
		return 0;

	}

	public String convert_indice(int c){
		if(c == 0){
			return "1";
		}
		if(c == 1){
			return "2";
		}
		if(c == 2){
			return "3";
		}
		if(c == 3){
			return "4";
		}
		if(c == 4){
			return "5";
		}
		if(c == 5){
			return "6";
		}
		if(c == 6){
			return "7";
		}
		if(c == 7){
			return "8";
		}
		if(c == 8){
			return "9";
		}
		if(c == 9){
			return "10";
		}
		if(c == 10){
			return "11";
		}
		return "";

	}

	public String[] concatenation_tab(int[] col, int[] ligne){

		String[] tab = new String[col.length];
		char c;
		String l;
		int i;
		for(i = 0; i < col.length; i++){
			c = convert_lettre(col[i]);
			l = convert_indice(ligne[i]);
			tab[i] = c + l;
		}
		return tab;
	}

	public boolean win(){
		if (mode == BLITZ){
			if(nb_point_noir > 0){
				System.out.println("Le joueur noir gagne la partie!");
				return true;
			}
			if(nb_point_blanc > 0){
				System.out.println("Le joueur blanc gagne la partie!");
				return true;
			}
		}
		return false;

	}

	public int alignementsPossibles(char col, int line, color color)
	{

		int colonne;
		colonne = col - 65;

		int ligne;
		ligne = line - 1;

		//4 directions possibles pour l'alignement
		int vertical = 0;
		int horizontal = 0;
		int diagonale_gauche = 0;
		int diagonale_droite = 0;

		if((plateauMarker[colonne + 1][ligne] == plateauMarker[colonne][ligne]) || (plateauMarker[colonne - 1][ligne] == plateauMarker[colonne][ligne])) horizontal = 1;
		if((plateauMarker[colonne][ligne + 1] == plateauMarker[colonne][ligne]) || (plateauMarker[colonne][ligne - 1] == plateauMarker[colonne][ligne])) vertical = 1;
		if((plateauMarker[colonne - 1][ligne + 1] == plateauMarker[colonne][ligne]) || (plateauMarker[colonne + 1][ligne - 1] == plateauMarker[colonne][ligne])) diagonale_droite = 1;
		if((plateauMarker[colonne + 1][ligne + 1] == plateauMarker[colonne][ligne]) || (plateauMarker[colonne - 1][ligne - 1] == plateauMarker[colonne][ligne])) diagonale_gauche = 1;

		//Recherche d'alignements

		boolean retour = false;
		int same_color_marker = 0;
		int i = 1;
		String alignement_possible = "";

		///////////////////////////////////////////////////////////////////////VERTICAL////////////////////////////////////////////////////////////////////////
		if(vertical ==1)
		{
			//same_color_marker = 0;
			i = 1;
			alignement_possible = "Vertical : ";

			while((plateauMarker[colonne][ligne + i] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne][ligne + i] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + colonne + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			while((plateauMarker[colonne][ligne - i] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne][ligne - i] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + colonne + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			//if(same_color_marker >= 5) System.out.println(alignement_possible); retour = true;
		}

		///////////////////////////////////////////////////////////////////////HORIZONTAL////////////////////////////////////////////////////////////////////////
		if(horizontal ==1)
		{
			//same_color_marker = 0;
			i = 1;
			alignement_possible = "Horizontal : ";

			while((plateauMarker[colonne + i][ligne] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne + i][ligne] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + (colonne + i) + "][" + ligne + "] "  ;
				}

				i++;
			}

			while((plateauMarker[colonne - i][ligne] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne - i][ligne] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + (colonne - i) + "][" + ligne + "] "  ;
				}

				i++;
			}

			//if(same_color_marker >= 5) System.out.println(alignement_possible); retour = true;
		}

		///////////////////////////////////////////////////////////////////////DIAGONALE GAUCHE////////////////////////////////////////////////////////////////////////
		if(diagonale_gauche == 1)
		{
			//same_color_marker = 0;
			i = 1;
			alignement_possible = "Diagonale Gauche : ";

			while((plateauMarker[colonne + i][ligne + i] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne + i][ligne + i] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + (colonne + i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			while((plateauMarker[colonne - i][ligne - i] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne - i][ligne - i] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + (colonne + i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			//if(same_color_marker >= 5) System.out.println(alignement_possible); retour = true;
		}

		///////////////////////////////////////////////////////////////////////DIAGONALE DROITE////////////////////////////////////////////////////////////////////////
		if(vertical ==1)
		{
			//same_color_marker = 0;
			i = 1;
			alignement_possible = "Diagonale Droite : ";

			while((plateauMarker[colonne - i][ligne + i] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne - i][ligne + i] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + (colonne - i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			while((plateauMarker[colonne + i][ligne - i] == plateauMarker[colonne][ligne]))
			{
				if((plateauMarker[colonne + i][ligne - i] == plateauMarker[colonne][ligne]))
				{
					same_color_marker ++;
					alignement_possible += "[" + (colonne + i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}


		}

		//System.out.println("Same color Marker : " + same_color_marker);
		if(same_color_marker >= 5) System.out.println(alignement_possible); retour = true;
		if(retour == true) return 1;

		return 0;
	}

	public boolean win_limite_anneaux()
	{
		
		if(marqueurs_utilises == 51)
		{
			if(marqueurs_blancs > marqueurs_noirs){
				System.out.println("Le joueur blanc a gagné, il a plus d'anneaux");
				return true;
			}
			else if(marqueurs_noirs > marqueurs_blancs){
				System.out.println("Le joueur noir a gagné, il a plus d'anneaux");
				return true;
			}
			else
			{
				System.out.println("Match nul");
				return true;
			}
		}
		return false;
	}

}
