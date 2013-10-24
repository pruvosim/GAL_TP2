import java.util.ArrayList;
import java.util.Random;


public class Yinsh {

	public enum Color {
		BLACK,
		WHITE
	}

	public Color[][] m_plateau;
	public Color[][] m_plateauMarker;
	public Color m_derniereCouleurJouee = null;

	public int m_nbPointNoir;
	public int m_nbPointBlanc;

    public enum Type{
        BLITZ,
        NORMAL
    }

    public Type mode;

	public Color m_joueurEnCours;

	public int m_whiteRingsRemoved = 0;
	public int m_blackRingsRemoved = 0;

	public int m_marqueursBlancs = 0;
	public int m_marqueursNoirs = 0;
	public int m_marqueursUtilises = 0;


	public Yinsh()
	{
		m_nbPointBlanc = 0 ;
		m_nbPointNoir = 0 ;

		//initialisation du m_plateau
		m_plateau = new Color[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				m_plateau[i][j] = null;
			}
		}

		m_plateauMarker = new Color[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				m_plateauMarker[i][j] = null;
			}
		}
	}



	public Color currentColor()
	{
		Random rand = new Random();
		int r = Math.abs(rand.nextInt(2));
		if(r==1) return Color.WHITE;
		else return Color.BLACK;
	}

	public void putRing(char col, int line, Color Color) throws Exception
	{
		int column;
		column = col - 65;

		if((column < 0) || (column > 11)) throw new Exception();

		//Vérification des voisins
		/*if((m_plateau[column][line] == Color) || (m_plateau[column][line -2] == Color) || (m_plateau[column -1][line -2] == Color) || (m_plateau[column -1][line] == Color) || (m_plateau[column +1][line -2] == Color) || (m_plateau[column +1][line] == Color))
		{
			throw new Exception();
		}*/

		if(m_plateau[column][line-1] != null) throw new Exception();

		if(Color == m_derniereCouleurJouee) throw new Exception();

		m_plateau[column][line-1] = Color;
		m_derniereCouleurJouee = Color;

	}


	public boolean isInitialized()
	{
		int nbAnneauxBlancs = 0, nbAnneauxNoirs = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if(m_plateau[i][j] == Color.BLACK) nbAnneauxNoirs++;
				if(m_plateau[i][j] == Color.WHITE) nbAnneauxBlancs++;
			}
		}
        return (nbAnneauxBlancs == 5) && (nbAnneauxNoirs == 5);
    }

	public void putMarker(char col, int line, Color Color) throws Exception
	{
		int column;
		column = col - 65;


		if(m_plateau[column][line-1] == null) throw new Exception();
		if(m_plateau[column][line-1] != Color) throw new Exception();
		m_plateauMarker[column][line-1] = Color;
		
		m_marqueursUtilises++;

	}

	public void moveRing(char colDepart, int lineDepart, char colArrivee, int lineArrivee) throws Exception
	{
		int columnDepart;
		columnDepart = colDepart - 65;
		int columnArrivee;
		columnArrivee = colArrivee - 65;

		int nbIterations;
		int anneauDifferentChemin = 0;
		int nbAnneauxRetourner = 0;

		if(m_plateauMarker[columnDepart][lineDepart-1] == m_plateau[columnDepart][lineDepart-1])
		{

			if(columnArrivee == columnDepart) nbIterations = lineArrivee - lineDepart + 1;
			else nbIterations = columnArrivee - columnDepart + 1;

			for (int i = 0; i < nbIterations; i++) {
				if(m_plateau[columnDepart + i][lineDepart -1 + i] != m_plateau[columnDepart][lineDepart -1] && m_plateau[columnDepart + i][lineDepart -1 + i] != null) anneauDifferentChemin = 1;
			}

			if(anneauDifferentChemin == 1) throw new Exception();

			if(m_plateau[columnArrivee][lineArrivee-1] == null)
			{
				m_plateau[columnArrivee][lineArrivee-1] = m_plateau[columnDepart][lineDepart-1];
				m_plateau[columnDepart][lineDepart-1] = null;

				if(columnDepart == columnArrivee) nbAnneauxRetourner = lineArrivee - lineDepart;

				for (int i = 0; i < nbAnneauxRetourner; i++) {
					if(m_plateauMarker[columnDepart][lineDepart + i] == Color.WHITE) m_plateauMarker[columnDepart][lineDepart + i] = Color.BLACK;
					else m_plateauMarker[columnDepart][lineDepart + i] = Color.WHITE ;
				}
			}


		}
	}

	public void removeRow(char colDepart, int lineDepart, char colArrivee, int lineArrivee) throws Exception
	{
		int columnDepart;
		columnDepart = colDepart - 65;
		int columnArrivee;
		columnArrivee = colArrivee - 65;

		int nbIterations;
		int nbMarqueurs = 0;

		if(columnArrivee == columnDepart) nbIterations = lineArrivee - lineDepart + 1;
		else nbIterations = columnArrivee - columnDepart + 1;

		if (nbIterations > 5){
			System.out.println("Il y a un alignement de plus de 5 marqueurs. Veuillez pr�ciser quels marqueurs sont � enlever!");
			throw new Exception();
		}

		for (int i = 0; i < nbIterations; i++) {
			if(m_plateauMarker[columnDepart + i][lineDepart -1 + i] != null) nbMarqueurs ++;
		}

		if(nbMarqueurs == nbIterations)
		{
			for (int i = 0; i < nbIterations; i++) {
				m_plateauMarker[columnDepart + i][lineDepart -1 + i] = null;
			}
			m_nbPointNoir += 1 ;
		}
	}

	public void removeRing(char col, int line)
	{
		int column;
		column = col - 65;

		m_plateau[column][line -1] = null;

		if(m_joueurEnCours == Color.BLACK) m_blackRingsRemoved += 1;
		else m_whiteRingsRemoved += 1 ;
	}

	public String[] deplacements(char column, int line){

		int col = column - 65;
		int intLigne = line - 1;
		int i;
		int marque;
		int fin;

		ArrayList<Integer> colonne = new ArrayList<Integer>();
		ArrayList<Integer> ligne = new ArrayList<Integer>();

		i = 1;
		marque = 0;
		fin = 0;
		while( (intLigne-i > -1) && (m_plateau[col][intLigne-i] == null) && (fin == 0)){
			if(m_plateauMarker[col][intLigne-i] == null){
				colonne.add(col);
				ligne.add(intLigne-i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(m_plateauMarker[col][intLigne-i] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (intLigne+i < 11) && (m_plateau[col][intLigne+i] == null)  && (fin == 0)){
			if(m_plateauMarker[col][intLigne+i] == null){
				colonne.add(col);
				ligne.add(intLigne+i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(m_plateauMarker[col][intLigne+i] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (col-i > -1) && (m_plateau[col-i][intLigne] == null)  && (fin == 0)){
			if(m_plateauMarker[col-i][intLigne] == null){
				colonne.add(col-i);
				ligne.add(intLigne);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(m_plateauMarker[col-i][intLigne] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (col+i < 11) && (m_plateau[col+i][intLigne] == null)  && (fin == 0)){
			if(m_plateauMarker[col+i][intLigne] == null){
				colonne.add(col+i);
				ligne.add(intLigne);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(m_plateauMarker[col+i][intLigne] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (intLigne-i > -1) && (col-i > -1) && (m_plateau[col-i][intLigne-i] == null)  && (fin == 0)){
			if(m_plateauMarker[col-i][intLigne-i] == null){
				colonne.add(col-i);
				ligne.add(intLigne-i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(m_plateauMarker[col-i][intLigne-i] == null){
					fin = 1;
				}
			}
			i++;
		}

		i = 1;
		marque = 0;
		fin = 0;
		while( (intLigne+i < 11) && (col+i < 11) && (m_plateau[col+i][intLigne+i] == null)  && (fin == 0)){
			if(m_plateauMarker[col+i][intLigne+i] == null){
				colonne.add(col+i);
				ligne.add(intLigne+i);
			}
			else{
				marque = 1;
			}
			if(marque == 1){
				if(m_plateauMarker[col+i][intLigne+i] == null){
					fin = 1;

				}
			}
			i++;

		}

		int[] colonneTab = listeTableau(colonne);
		int[] ligneTab = listeTableau(ligne);
		String[] solution = concatenationTab(colonneTab, ligneTab);

		for(i = 0; i < solution.length; i++){
			System.out.println(solution[i]);
		}

		return solution;

	}

	public int[] listeTableau(ArrayList<Integer> a){

		int i;
		int[] tab = new int[a.size()];
		for(i = 0; i < a.size(); i++){
			tab[i] = a.get(i);
		}

		return tab;
	}

	public char convertLettre(int character){
        return Character.toChars(character + 65)[0];
	}

	public String convertIndice(int character){
        return Integer.toString(character+1);
	}

	public String[] concatenationTab(int[] col, int[] ligne){

		String[] tab = new String[col.length];
		char c;
		String ligneEcrit;
		int i;
		for(i = 0; i < col.length; i++){
			c = convertLettre(col[i]);
			ligneEcrit = convertIndice(ligne[i]);
			tab[i] = c + ligneEcrit;
		}
		return tab;
	}

	public Color winnerIs(){
		if (mode == Type.BLITZ){
			if(m_nbPointNoir > 0){
				return Color.BLACK;
			}
			else if(m_nbPointBlanc > 0){
				return Color.WHITE;
			}
		}

	}

	public int alignementsPossibles(char col, int line)
	{

		int colonne;
		colonne = col - 65;

		int ligne;
		ligne = line - 1;

		//4 directions possibles pour l'alignement
		int vertical = 0;
		int horizontal = 0;
		int diagonaleGauche = 0;
		int diagonaleDroite = 0;

		if((m_plateauMarker[colonne + 1][ligne] == m_plateauMarker[colonne][ligne]) || (m_plateauMarker[colonne - 1][ligne] == m_plateauMarker[colonne][ligne])) horizontal = 1;
		if((m_plateauMarker[colonne][ligne + 1] == m_plateauMarker[colonne][ligne]) || (m_plateauMarker[colonne][ligne - 1] == m_plateauMarker[colonne][ligne])) vertical = 1;
		if((m_plateauMarker[colonne - 1][ligne + 1] == m_plateauMarker[colonne][ligne]) || (m_plateauMarker[colonne + 1][ligne - 1] == m_plateauMarker[colonne][ligne])) diagonaleDroite = 1;
		if((m_plateauMarker[colonne + 1][ligne + 1] == m_plateauMarker[colonne][ligne]) || (m_plateauMarker[colonne - 1][ligne - 1] == m_plateauMarker[colonne][ligne])) diagonaleGauche = 1;

		//Recherche d'alignements

		boolean retour;
		int sameColorMarker = 0;
		int i;
		String alignementPossible = "";

		///////////////////////////////////////////////////////////////////////VERTICAL////////////////////////////////////////////////////////////////////////
		if(vertical ==1)
		{
			//sameColorMarker = 0;
			i = 1;
			alignementPossible = "Vertical : ";

			while((m_plateauMarker[colonne][ligne + i] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne][ligne + i] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + colonne + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			while((m_plateauMarker[colonne][ligne - i] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne][ligne - i] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + colonne + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			//if(sameColorMarker >= 5) System.out.println(alignementPossible); retour = true;
		}

		///////////////////////////////////////////////////////////////////////HORIZONTAL////////////////////////////////////////////////////////////////////////
		if(horizontal ==1)
		{
			//sameColorMarker = 0;
			i = 1;
			alignementPossible = "Horizontal : ";

			while((m_plateauMarker[colonne + i][ligne] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne + i][ligne] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + (colonne + i) + "][" + ligne + "] "  ;
				}

				i++;
			}

			while((m_plateauMarker[colonne - i][ligne] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne - i][ligne] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + (colonne - i) + "][" + ligne + "] "  ;
				}

				i++;
			}

			//if(sameColorMarker >= 5) System.out.println(alignementPossible); retour = true;
		}

		///////////////////////////////////////////////////////////////////////DIAGONALE GAUCHE////////////////////////////////////////////////////////////////////////
		if(diagonaleGauche == 1)
		{
			//sameColorMarker = 0;
			i = 1;
			alignementPossible = "Diagonale Gauche : ";

			while((m_plateauMarker[colonne + i][ligne + i] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne + i][ligne + i] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + (colonne + i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			while((m_plateauMarker[colonne - i][ligne - i] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne - i][ligne - i] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + (colonne + i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			//if(sameColorMarker >= 5) System.out.println(alignementPossible); retour = true;
		}

		///////////////////////////////////////////////////////////////////////DIAGONALE DROITE////////////////////////////////////////////////////////////////////////
		if(diagonaleDroite ==1)
		{
			//sameColorMarker = 0;
			i = 1;
			alignementPossible = "Diagonale Droite : ";

			while((m_plateauMarker[colonne - i][ligne + i] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne - i][ligne + i] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + (colonne - i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}

			while((m_plateauMarker[colonne + i][ligne - i] == m_plateauMarker[colonne][ligne]))
			{
				if((m_plateauMarker[colonne + i][ligne - i] == m_plateauMarker[colonne][ligne]))
				{
					sameColorMarker ++;
					alignementPossible += "[" + (colonne + i) + "][" + (ligne + i) + "] "  ;
				}

				i++;
			}


		}

		System.out.println("Same Color Marker : " + sameColorMarker);
		if(sameColorMarker >= 5) System.out.println(alignementPossible); retour = true;
		if(retour) return 1;

		return 0;
	}

	public boolean markerLimitWinnerIs()
	{
		
		if(m_marqueursUtilises == 51)
		{
			if(m_marqueursBlancs > m_marqueursNoirs){
				System.out.println("Le joueur blanc a gagn�, il a plus d'anneaux");
				return true;
			}
			else if(m_marqueursNoirs > m_marqueursBlancs){
				System.out.println("Le joueur noir a gagn�, il a plus d'anneaux");
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
