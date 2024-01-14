import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Terrain {

    private int hauteur, largeur;
    private Case[][] carte;

    /* Initialisation d'un terrain Ã  partir de la description donnÃ©e par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la premiÃ¨re ligne
       - puis dessin du terrain (un caractÃ¨re == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new CaseIntraversable(l, c); break;
                        case ' ': cc = new CaseLibre(l, c); break;
                        case 'o': cc = new Sortie(l, c); break;
                        case '@': cc = new CaseLibre(l, c, new Obstacle()); break;
                        case '^': case '>': case 'v': case '<':
                            cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
                            break;
                        case 'm': case '»': case 'w': case '«':
                            cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
                            break;
                        case 'H': cc = new CaseLibre(l, c, new Joueur()); break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public int getHauteur() { return this.hauteur; }
    public int getLargeur() { return this.largeur; }


    public ArrayList<Case> mur() {
        ArrayList<Case> mur = new ArrayList<>();
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if (carte[l][c] instanceof CaseIntraversable) {
                    mur.add(carte[l][c]);
                }
            }
        }
        return mur;
    }
    public ArrayList<Case> obstacle() {
        ArrayList<Case> o = new ArrayList<>();
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if ((carte[l][c] instanceof CaseTraversable) && ((CaseTraversable)carte[l][c]).getContenu() instanceof Obstacle) {
                    o.add(carte[l][c]);
                }
            }
        }
        return o;
    }
    public Case sortie() {
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if (carte[l][c] instanceof CaseTraversable && carte[l][c] instanceof Sortie) {
                    return carte[l][c];
                }
            }
        }
        return null;
    }
    public ArrayList<Case> monstre() {
        ArrayList<Case> m = new ArrayList<>();
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if ((carte[l][c] instanceof CaseTraversable) && (((CaseTraversable)carte[l][c]).getContenu()) instanceof Monstre) {
                    m.add(carte[l][c]);
                }
            }
        }
        return m;
    }
    public ArrayList<Case> perso() {
        ArrayList<Case> p = new ArrayList<>();
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if ((carte[l][c] instanceof CaseTraversable) && (((CaseTraversable)carte[l][c]).getContenu()) instanceof Personnage) {
                    p.add(carte[l][c]);
                }
            }
        }
        return p;
    }
    public Case Joueur() {
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if (carte[l][c] instanceof CaseTraversable && (((CaseTraversable)carte[l][c]).getContenu()) instanceof Joueur) {
                    return carte[l][c];
                }
            }
        }
        return null;
    }


    // Méthode qui vérifie si la case c contient une entité mobile.
    public boolean contientMob(Case c) {
        if ((c instanceof CaseTraversable) && ((CaseTraversable)c).getContenu() instanceof EntiteMobile) {
            return true;
        }
        else {
            return false;
        }
    }
    // Méthode qui renvoie une liste de toutes les cases qui contiennent des entités mobiles.
    public ArrayList<Case> mob() {
        ArrayList<Case> mob = new ArrayList<>();
        for (int l=0; l<hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                if (contientMob(carte[l][c])) {
                    mob.add(carte[l][c]);
                }
            }
        }
        return mob;
    }
    // Méthode qui trouve la case indiquée par la direction d à partir de la case courante.
    public Case trouveCible(Case c1, Direction d) {
        switch (d) {
            case nord:
                return carte[c1.getLig() - 1][c1.getCol()];
            case sud:
                return carte[c1.getLig() + 1][c1.getCol()];
            case est:
                return carte[c1.getLig()][c1.getCol() + 1];
            case ouest:
                return carte[c1.getLig()][c1.getCol() - 1];
            default:
                return c1;
        }
    }
    /* Méthode qui déplace toutes les entités mobiles du terrain. A partir de la liste des entités
       mobiles du terrain, tire au hazard un élément de cette liste et le fait agir, puis le retire
       de la liste. Répète cela jusque quand la liste est vide et que tous ses éléments aient effectué
       une action.
     */
    public void deplaceMob(ArrayList<Case> mob) {
        while (mob.size() != 0) {
            Random rnd = new Random();
            int i = rnd.nextInt(mob.size());
            Entite E = ((CaseTraversable)mob.get(i)).getContenu();
            ((EntiteMobile)E).action(mob.get(i), trouveCible(mob.get(i), ((EntiteMobile)E).getDirection()));
            mob.remove(i);
        }
    }
    public void deplaceJoueur(String action) {
        Case courante = Joueur();
        Joueur j = ((Joueur)((CaseTraversable)courante).getContenu());
        switch (action) {
            case "up" : j.avance(courante, carte[courante.getLig()-1][courante.getCol()]); break;
            case "down" : j.avance(courante, carte[courante.getLig()+1][courante.getCol()]); break;
            case "right" : j.avance(courante, carte[courante.getLig()][courante.getCol()+1]); break;
            case "left" : j.avance(courante, carte[courante.getLig()][courante.getCol()-1]); break;
            case "attaque" : j.casseObstacle(trouveCible(courante, j.getDirection())); break;
            case "sortir" : j.sortir(courante); break;
        }
    }

    // Méthode d'affichage du terrain
    public void print() {
            for (Case[] c : carte)  {
                System.out.println();
                for (Case t : c) {
                    System.out.print(t);
                }
            }
        }
    }
