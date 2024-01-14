public class Jeu {

    Terrain terrain;
    static int sortis;

    /* Initialisation d'un jeu avec le terrain initial dÃ©crit dans
       le fichier [f] donnÃ© en paramÃ¨tre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        sortis = 0;
    }

    /* Méthode qui exécute un tour du jeu en faisant agir toutes
       les entités mobiles du terrain dans un ordre aléatoire.
     */
    public void tour() {
        terrain.deplaceMob(terrain.mob());
    }
    /* Méthode qui active le succès du jeu. Vérifie le nombre de
       personnage encore en jeu à partir de la liste de toutes les
       entités mobiles du terrain. Si il n'y a plus de personnage
       en Jeu la méthode renvoie true le jeu s'arrête.
     */
    public boolean success() {
        if (terrain.Joueur() == null) {
            return true;
        }
        else if (terrain.perso().size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        Jeu j = new Jeu("res\\maps\\laby2.txt");
        j.terrain.print();
        while (!j.success()) {
            j.tour();
            j.terrain.print();
        }
        System.out.println("\n" + sortis);
    }
}