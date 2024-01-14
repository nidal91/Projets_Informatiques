class Personnage extends EntiteMobile{
    public Personnage(Direction d) {
        super(d);
    }

    /* Méthode d'action des entités mobiles Personnage.
       Le personnage peut agir des manières suivantes :
        - Le personnage se trouve sur une case sortie et sort du jeu.
          Le score est alors implémenté de 1.
        - Le personnage se trouve en face d'une case libre et avance.
        - Le personnage se trouve en face d'un obstacle et l'attaque.
          La résistance de l'obsatacle baisse de 1. Si la
          résistance de l'entité tombe à 0, celle-ci disparait.
        Au tour suivant l'entité prend une nouvelle direction de manière
        aléatoire.
     */
    public void action(Case courante, Case cible) {
        ((EntiteMobile)((CaseTraversable)courante).getContenu()).changeDirection();
        if (courante instanceof Sortie) {
            Jeu.sortis += 1;
            ((CaseTraversable)courante).vide();
        }
        else if (cible.estLibre()) {
            ((CaseTraversable) cible).entree(((CaseTraversable) courante).getContenu());
            ((CaseTraversable) courante).vide();
        }
        else if (cible instanceof CaseTraversable && ((CaseTraversable) cible).getContenu() instanceof Obstacle) {
            ((CaseTraversable) cible).getContenu().attaque();
            if (((CaseTraversable) cible).getContenu().getResistance() <= 0) {
                ((CaseTraversable) cible).entree(((CaseTraversable) courante).getContenu());
                ((CaseTraversable) courante).vide();
            }
        }
    }

    @Override
    public String toString(String background) {
        String[] res = background.split("");
        Direction d = this.getDirection();
        switch(d) {
            case nord : return res[0] + "^" + res[2];
            case sud : return res[0] + "v" + res[2];
            case est : return res[0] + ">" + res[2];
            case ouest : return res[0] + "<" + res[2];
            default: return background;
        }
    }
}
