class Monstre extends EntiteMobile{

    public Monstre(Direction d) {
        super(d);
    }

    /* Méthode d'action des entités mobiles Monstre.
       Le Monstre peut agir des manières suivantes :
        - Le Monstre se trouve en face d'une case libre et avance.
        - Le personnage se trouve en face d'un obstacle ou d'un personnage
          et l'attaque. La résistance de l'entité baisse de 1. Si la
          résistance de l'entité tombe à 0, celle-ci disparait.
        Au tour suivant l'entité prend une nouvelle direction de manière
        aléatoire.
     */
    public void action(Case courante, Case cible) {
        ((EntiteMobile)((CaseTraversable)courante).getContenu()).changeDirection();
        if (cible.estLibre()) {
            ((CaseTraversable)cible).entree(((CaseTraversable)courante).getContenu());
            ((CaseTraversable)courante).vide();
        }
        else if (cible instanceof CaseTraversable && !(((CaseTraversable)cible).getContenu() instanceof Monstre)) {
            ((CaseTraversable) cible).getContenu().attaque();
            if (((CaseTraversable) cible).getContenu().getResistance() <= 0) {
                if (((CaseTraversable)cible).getContenu() instanceof Joueur) {
                    Jeu.sortis /= 2;
                }
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
            case nord : return res[0] + "m" + res[2];
            case sud : return res[0] + "w" + res[2];
            case est : return res[0] + "»" + res[2];
            case ouest : return res[0] + "«" + res[2];
            default: return background;
        }
    }
}
