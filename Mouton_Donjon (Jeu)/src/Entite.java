abstract class Entite {
    private int resistance;

    /* Initialisation des entités. Objets occuppant des cases traversables.
       Ils possèdent une résistance initialisé à 3 à l'apparition. Si la
       résistance tombe à 0 l'entité disparrait.
     */
    public Entite() {
        this.resistance = 3;
    }
    public Entite(int resistance) {
        this.resistance = resistance;
    }

    //Guetteur resistance
    public int getResistance() {
        return this.resistance;
    }
    //Setteur resistance (si une entité subit une attaque la résistance baisse de 1)
    public void attaque() {
        this.resistance -= 1;
    }

    public abstract String toString(String background);
}
