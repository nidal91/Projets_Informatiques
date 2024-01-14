abstract class EntiteMobile extends Entite{
    private Direction d;

    /* Initialisation des entités mobiles ayant une direction
       vers laquelle elles vont agir.
     */
    public EntiteMobile(Direction d) {
        this.d = d;
    }

    //Guetteur direction
    public Direction getDirection() {
        return this.d;
    }
    //Setteur direction
    public void changeDirection() { this.d = Direction.random(); }
    /* Méthode d'action des entités mobiles.
       Prend en @param la case courante, où ce trouve l'entités, et une case cible, la case
       dans sa direction.
     */
    public abstract void action(Case courante, Case cible);
}
