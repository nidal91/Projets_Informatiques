class Sortie extends CaseTraversable {

    public Sortie(int lig, int col) {
        super(lig, col, null);
    }

    public Sortie(int lig, int col, Entite contenu) {
        super(lig, col, contenu);
    }

    @Override
    public String toString() {
        if (contenu == null) {
            return "( )";
        }
        else {
            return contenu.toString("( )");
        }
    }
}
