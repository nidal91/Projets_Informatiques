abstract class Case {
    private final int lig;
    private final int col;

//Premier fichier.
    /* Initialisation des Cases possédant une coordonnée fixe sur la carte.
       Il y a 2 types de cases:
        - les cases intraversables.
        - les cases travesables (cases libres et sorties).
     */
    public Case(int lig, int col) {
        this.lig = lig;
        this.col = col;
    }

    //Guetteur lig
    public int getLig() { return lig; }
    //Guetteur Col
    public int getCol() { return col; }
    /* Méthode qui vérifie si une case est libre. Si elle n'est pas
       intraversable ou si elle n'est pas occupée.
     */
    public abstract boolean estLibre();
}
