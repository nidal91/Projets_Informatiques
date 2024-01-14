import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Donjon {
    public static void main(String[] args) {
        int tempo = 10;
        AtomicInteger tourCounter = new AtomicInteger();
        Jeu jeu = new Jeu("res\\maps\\laby2.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            if (tourCounter.get() > 2) {
                jeu.tour();
                tourCounter.set(0);
            }
            if (jeu.success()) { graphic.ecranFinal(jeu.sortis); }
            tourCounter.getAndIncrement();
            graphic.update();
            graphic.repaint();
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}
