public class Application {

    private static void putRing(Yinsh yinsh)
    {
        // chaque joueur place ses anneaux
        yinsh.putRing(new Coordinates('B', 1), Yinsh.Color.BLACK);
        yinsh.putRing(new Coordinates('B', 2), Yinsh.Color.WHITE);
        yinsh.putRing(new Coordinates('D', 2), Yinsh.Color.BLACK);
        yinsh.putRing(new Coordinates('D', 6), Yinsh.Color.WHITE);
        yinsh.putRing(new Coordinates('F', 7), Yinsh.Color.BLACK);
        yinsh.putRing(new Coordinates('G', 8), Yinsh.Color.WHITE);
        yinsh.putRing(new Coordinates('G', 9), Yinsh.Color.BLACK);
        yinsh.putRing(new Coordinates('H', 6), Yinsh.Color.WHITE);
        yinsh.putRing(new Coordinates('J', 8), Yinsh.Color.BLACK);
        yinsh.putRing(new Coordinates('C', 2), Yinsh.Color.WHITE);
    }

    private static void putMarkerAndMoveRing(Yinsh yinsh)
    {
        // le joueur noir place un marqueur  et déplace un anneau
        yinsh.putMarker(new Coordinates('J', 8), Yinsh.Color.BLACK);
        yinsh.moveRing(new Coordinates('J', 8), new Coordinates('I', 8), Yinsh.Color.BLACK);

        // le joueur blanc place un marqueur et déplace un anneau
        yinsh.putMarker(new Coordinates('G', 8), Yinsh.Color.WHITE);
        yinsh.moveRing(new Coordinates('G', 8), new Coordinates('H', 9), Yinsh.Color.WHITE);

        // le joueur noir place un marqueur  et déplace un anneau
        yinsh.putMarker(new Coordinates('I', 8), Yinsh.Color.BLACK);
        yinsh.moveRing(new Coordinates('I', 8), new Coordinates('H', 8), Yinsh.Color.BLACK);

        // le joueur blanc place un marqueur et déplace un anneau
        yinsh.putMarker(new Coordinates('H', 6), Yinsh.Color.WHITE);
        yinsh.moveRing(new Coordinates('H', 6), new Coordinates('I', 7), Yinsh.Color.WHITE);

        // le joueur noir place un marqueur  et déplace un anneau
        yinsh.putMarker(new Coordinates('H', 8), Yinsh.Color.BLACK);
        yinsh.moveRing(new Coordinates('H', 8), new Coordinates('F', 8), Yinsh.Color.BLACK);

        // le joueur blanc place un marqueur et déplace un anneau
        yinsh.putMarker(new Coordinates('B', 2), Yinsh.Color.WHITE);
        yinsh.moveRing(new Coordinates('B', 2), new Coordinates('B', 3), Yinsh.Color.WHITE);

        // le joueur noir place un marqueur  et déplace un anneau
        yinsh.putMarker(new Coordinates('F', 8), Yinsh.Color.BLACK);
        yinsh.moveRing(new Coordinates('F', 8), new Coordinates('E', 8), Yinsh.Color.BLACK);
    }

    private static void removeRowAndRemoveRing(Yinsh yinsh)
    {
        yinsh.removeRow(new Coordinates('F', 8), new Coordinates('J', 8), Yinsh.Color.BLACK);
        yinsh.removeRing(new Coordinates('B', 1), Yinsh.Color.BLACK);
    }

    public static void main(String[] args)
    {
        // le joueur noir commence
        Yinsh yinsh = new Yinsh(Yinsh.Color.BLACK, Yinsh.Type.BLITZ);

        putRing(yinsh);
        putMarkerAndMoveRing(yinsh);
        removeRowAndRemoveRing(yinsh);
        if (yinsh.winnerIs() == Yinsh.Color.BLACK) {
            System.out.println("Le joueur noir a gagné !");
        } else {
            System.out.println("L'API Yinsh n'est pas conforme");
        }
    }

}
