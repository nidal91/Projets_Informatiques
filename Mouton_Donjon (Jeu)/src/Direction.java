import java.util.Random;

/* Directions cardinales */
public enum Direction {
    nord,
    sud,
    est,
    ouest;

    /* Lie le symbole reprÃ©sentant une crÃ©ature et sa direction */
    public static Direction ofChar(Character d) {
        switch (d) {
            case '^': case 'm': return Direction.nord;
            case 'v': case 'w': return Direction.sud;
            case '>': case '»': return Direction.est;
            case '<': case '«': return Direction.ouest;
        }
        return null;
    }

    /* Tirage d'une direction alÃ©atoire */
    public static Direction random() {
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        switch (r) {
            case 0:  return Direction.nord;
            case 1:  return Direction.sud;
            case 2:  return Direction.est;
            default: return Direction.ouest;
        }
    }
}