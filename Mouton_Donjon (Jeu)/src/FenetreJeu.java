import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FenetreJeu extends JPanel implements KeyListener, MouseListener {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;
    private BufferedImage imgBack, imgFront, imgRight, imgLeft, imgOb3, imgOb2, imgOb1, imgExit, imgPlayerB1, imgPlayerB2, imgPlayerF1, imgPlayerF2, imgPlayerR1, imgPlayerR2, imgPlayerL1, imgPlayerL2;
    private boolean keyPressed;
    private int spriteNum;

    int spriteCounter;
    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        this.keyPressed = false;
        this.spriteNum = 1;
        this.spriteCounter = 0;

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setVisible(true);
    }

    //charge l'image depuis les ressources du jeux


    //méthode qui dessine les sprites des objets sur leur case courante
    //m Liste de tous les monstres sur le terrain à affiche
    public void getImageMonstre() {
        try {
            imgBack = ImageIO.read(new File("res\\images\\loupBack.png"));
            imgFront = ImageIO.read(new File("res\\images\\loupFront.png"));
            imgRight = ImageIO.read(new File("res\\images\\loupRight.png"));
            imgLeft = ImageIO.read(new File("res\\images\\loupLeft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getImagePerso() {
        try {
            imgBack = ImageIO.read(new File("res\\images\\sheepBack.png"));
            imgFront = ImageIO.read(new File("res\\images\\sheepFront.png"));
            imgRight = ImageIO.read(new File("res\\images\\sheepRight.png"));
            imgLeft = ImageIO.read(new File("res\\images\\sheepLeft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getImageObstacle() {
        try {
            imgOb3 = ImageIO.read(new File("res\\images\\tonneaux3.png"));
            imgOb2 = ImageIO.read(new File("res\\images\\tonneaux2.png"));
            imgOb1 = ImageIO.read(new File("res\\images\\tonneaux1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getImageSortie() {
        try {
            imgExit = ImageIO.read(new File("res\\images\\sortieO.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getImagePlayer() {
        try {
            imgPlayerB1 = ImageIO.read(new File("res\\images\\playerBack1.png"));
            imgPlayerB2 = ImageIO.read(new File("res\\images\\playerBack2.png"));
            imgPlayerF1 = ImageIO.read(new File("res\\images\\playerFront1.png"));
            imgPlayerF2 = ImageIO.read(new File("res\\images\\playerFront2.png"));
            imgPlayerR1 = ImageIO.read(new File("res\\images\\playerRight1.png"));
            imgPlayerR2 = ImageIO.read(new File("res\\images\\playerRight2.png"));
            imgPlayerL1 = ImageIO.read(new File("res\\images\\playerLeft1.png"));
            imgPlayerL2 = ImageIO.read(new File("res\\images\\playerLeft2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMob(Graphics2D g2, Case c) {
        BufferedImage img = null;
        if (c instanceof CaseTraversable && ((CaseTraversable)c).getContenu() instanceof EntiteMobile) {
            EntiteMobile E = ((EntiteMobile) ((CaseTraversable)c).getContenu());
            switch (E.getDirection()) {
                case nord : img = imgBack;
                break;
                case sud : img = imgFront;
                break;
                case est : img = imgRight;
                break;
                case ouest : img = imgLeft;
                break;
            }
        }
        g2.drawImage(img, c.getCol()*tailleCase, c.getLig()*tailleCase, tailleCase, tailleCase, null);
    }
    public void drawObstacle(Graphics2D g2, Case c) {
        BufferedImage img = null;
        if (c instanceof CaseTraversable && ((CaseTraversable)c).getContenu() instanceof Obstacle) {
            Obstacle O = ((Obstacle) ((CaseTraversable)c).getContenu());
            switch (O.getResistance()) {
                case 3 : img = imgOb3; break;
                case 2 : img = imgOb2; break;
                case 1 : img = imgOb1; break;
            }

        }
        g2.drawImage(img, c.getCol()*tailleCase, c.getLig()*tailleCase, tailleCase, tailleCase, null);
    }
    public void drawSortie(Graphics2D g2, Case c) {
        BufferedImage img = null;
        if (c instanceof Sortie) {
            img = imgExit;
        }
        g2.drawImage(img, c.getCol()*tailleCase, c.getLig()*tailleCase, tailleCase, tailleCase, null);
    }
    public void drawClef(Graphics2D g2, Case c) {
        BufferedImage img = null;
        if ((c instanceof CaseLibre) && (((CaseLibre)c).getObjet()) instanceof Clef) {
            img = imgClef;
        }
        g2.drawImage(img, c.getCol()*tailleCase, c.getLig()*tailleCase, tailleCase, tailleCase, null);
    }
    public void drawPlayer(Graphics2D g2, Case c) {
        BufferedImage img = null;
        if (c != null) {
            Joueur J = ((Joueur) ((CaseTraversable) c).getContenu());
            if (spriteNum == 2) {
                switch (J.getDirection()) {
                    case nord:
                        img = imgPlayerB1;
                        break;
                    case sud:
                        img = imgPlayerF1;
                        break;
                    case est:
                        img = imgPlayerR1;
                        break;
                    case ouest:
                        img = imgPlayerL1;
                        break;
                }
            } else {
                switch (J.getDirection()) {
                    case nord:
                        img = imgPlayerB2;
                        break;
                    case sud:
                        img = imgPlayerF2;
                        break;
                    case est:
                        img = imgPlayerR2;
                        break;
                    case ouest:
                        img = imgPlayerL2;
                        break;
                }
            }
            g2.drawImage(img, c.getCol()*tailleCase, c.getLig()*tailleCase, tailleCase, tailleCase, null);
        }
    }

    public void update() {
        if (keyPressed) {
            spriteCounter++;
            if (spriteCounter > 1) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (Case c : terrain.mur()) {
            g.fillRect(c.getCol()*tailleCase, c.getLig()*tailleCase, tailleCase, tailleCase);
        }
        Graphics2D g2 = (Graphics2D)g;
        getImageSortie();
        drawSortie(g2, terrain.sortie());
        for (Case c : terrain.obstacle()) {
            getImageObstacle();
            drawObstacle(g2, c);
        }
        for (Case c : terrain.monstre()) {
            getImageMonstre();
            drawMob(g2, c);
        }
        for (Case c : terrain.perso()) {
            getImagePerso();
            drawMob(g2, c);
        }
        getImagePlayer();
        drawPlayer(g2, terrain.Joueur());
    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (terrain.Joueur() != null) {
            Joueur J = ((Joueur) ((CaseTraversable) terrain.Joueur()).getContenu());
            switch (e.getButton()) {
                case 1:
                    terrain.deplaceJoueur("attaque");
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            System.out.println("You released mouse code: " + e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (terrain.Joueur() != null) {
            Joueur J = ((Joueur) ((CaseTraversable) terrain.Joueur()).getContenu());
            switch (e.getKeyCode()) {
                case 38:
                    terrain.deplaceJoueur("up");
                    J.setDirection(Direction.nord);
                    keyPressed = true;
                    break;
                case 40:
                    terrain.deplaceJoueur("down");
                    J.setDirection(Direction.sud);
                    keyPressed = true;
                    break;
                case 39:
                    terrain.deplaceJoueur("right");
                    J.setDirection(Direction.est);
                    keyPressed = true;
                    break;
                case 37:
                    terrain.deplaceJoueur("left");
                    J.setDirection(Direction.ouest);
                    keyPressed = true;
                    break;
                case 32:
                    terrain.deplaceJoueur("sortir");
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key code: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case 38:
            case 40:
            case 39:
            case 37:
                keyPressed = false;
                break;
        }
    }
}
