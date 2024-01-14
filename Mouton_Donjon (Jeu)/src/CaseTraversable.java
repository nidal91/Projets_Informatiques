abstract class CaseTraversable extends Case {
    Entite contenu;

    public CaseTraversable(int lig, int col, Entite contenu) {
        super(lig, col);
        this.contenu = contenu;
    }
    //
    //Guetteur contenu
    public Entite getContenu() { return contenu; }
    //Setteur contenu (vide la case de son contenu)
    public void vide() { contenu = null; }
    //Setteur contenu (modifie le contenu en ajoutant l'entité e sur la case)
    public void entree(Entite e) { contenu = e; }
    public boolean estLibre() {
        return contenu == null;
    }
}
