import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;


/**
 * Created by perunjac000 on 2/7/2017.
 */
public class Game extends JPanel implements ActionListener {

    Timer timer;
    int positionX, positionY;
    int x, y, width;

    ArrayList<Entity> entities;
    int dy;
    int dx;
    int choose;
    public Game() {

        x = 0;
        y = 0;
        //Sets up JFrame properties
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Shapes");
        setPreferredSize(new Dimension(1580, 820));
        setBackground(Color.black);
        Stats.foodscore = 0;


        ImageIcon Pic = new ImageIcon("./src/Icon.png");
        Image icon = Pic.getImage();
        frame.setIconImage(icon);


        frame.add(this);

        addMouseMotionListener(new MouseMotionAdapter() {


            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                positionX = e.getX();
                positionY = e.getY();

            }

        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                        new Point(0, 0), null));
            }
        });


        frame.pack();
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* for(int i = 0; i < entities.size; i++{
        entities.get(i).move();
         */

        collisions();
        entities.get(0).playerMove();
        for (int i = 1; i < entities.size(); i++) {

            entities.get(i).move();

        }
        repaint();

    }

    public void init() {

        entities = new ArrayList<Entity>();

        entities.add(new Circle(Color.red, getWidth() / 2, getHeight() / 2, 20, this));

        for (int i = 0; i < 30; i++) {
            entities.add(new Food(Color.green, (int) (25 + (getWidth() - 100) * Math.random()),
                    (int) (25 + (getHeight() - 50) * Math.random()), 20, 30, this));
        }
        for (int i = 0; i < 1; i++) {
            entities.add(new Circle(Color.blue, (int) (25 + (getWidth() - 100) * Math.random()),
                    (int) (25 + (getHeight() - 50) * Math.random()), 30, this));
        }
        for (int i = 0; i < (int) (Math.random() + 3) * 3; i++) {
            entities.add(new Obstacles(Color.white, (int) (25 + (getWidth() - 100) * Math.random()),
                    (int) (25 + (getHeight() - 50) * Math.random()), 30, 20, this));


        }

    }

    public void bounce() {
        for (int i = 1; i < entities.size() - 1; i++) {
            if (getBounds().intersects(entities.get(i).getBounds())) {
                if (x < width / 2) {
                    for (int j = x; getBounds().intersects(entities.get(i).getBounds()); x--) {
                    }
                    dx *= -1;
                }
            }
        }
    }

    public void collisions() {

        for (int i = 1; i < entities.size(); i++) {

            if (entities.get(0).collides(entities.get(i))) {
                if (entities.get(i) instanceof Food) {
                    entities.remove(i);
                    Stats.updateFoodscore();


                } else if (entities.get(i) instanceof Circle) {
                    choose =JOptionPane.showConfirmDialog(null,"You Suck, Wanna try again?","Dodge the Cedric's Blue Ball" ,JOptionPane.YES_NO_OPTION);
                    if(choose == YES_OPTION){
                        new Game().restart();
                    }
                    if(choose == NO_OPTION){
                        System.exit(0);
                    }

                }
            }

            if (entities.get(i).collides(entities.get(i))) {
                if (entities.get(i) instanceof Obstacles) {
                    bounce();
                    //entities.get(i).speedUp();
                }

            }


        }
    }

    public void run() {
        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    public void paint(Graphics g) {

        super.paint(g);

        for (Entity obj : entities) {
            obj.paint(g);
        }

        g.setFont(new Font("Serif", Font.BOLD, 32));
        printSimpleString(String.valueOf("Food counter: "+ Stats.foodscore), getWidth() / 6, 0, 25, g);
    }
    public void restart() {
        Game game = new Game();
        game.init();
        game.run();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }


    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d) {

        int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth() + 20;
        int start = width / 2 - stringLen / 2;
        g2d.drawString(s, start + XPos, YPos);


    }
}
