class Obstacle extends Entite {

    public Obstacle() {
        super();
    }
    public Obstacle(int r) {
        super(r);
    }

    @Override
    public String toString(String background) {
        String[] res = background.split("");
        if (getResistance() >= 3) {
            return "@@@";
        }
        else if (getResistance() == 2) {
            return "@@" + res[2];
        }
        else if (getResistance() == 1) {
            return res[0] + "@" + res[2];
        }
        else {
            return background;
        }
    }
}
