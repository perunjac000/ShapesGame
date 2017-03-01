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
public class Game extends JPanel implements ActionListener, KeyListener {

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
        setPreferredSize(new Dimension(1300, 800));
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
        if (Stats.levelOne()) {
            collisions();
            entities.get(0).playerMove();
            for (int i = 1; i < entities.size(); i++) {
                entities.get(i).move();
            }
        }
        if( Stats.levelTwo()){
            collisions();
            entities.get(0).playerMove();
            for (int i = 1; i < entities.size(); i++) {
                entities.get(i).move();
            }
        }
        if( Stats.levelThree()){
            collisions();
            entities.get(0).playerMove();
            for (int i = 1; i < entities.size(); i++) {
                entities.get(i).move();
            }
        }
        repaint();

    }

    public void init() {
        if(Stats.levelOne()) {
            entities = new ArrayList<Entity>();
            entities.add(new Circle(Color.red, getWidth() / 2, getHeight() / 2, 20, this));
            for (int i = 0; i < 15; i++) {
                entities.add(new Food(Color.green, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 20, 30, this));
            }
            for (int i = 0; i < 3; i++) {
                entities.add(new Circle(Color.blue, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 30, this));
            }
            for (int i = 0; i < 7; i++) {
                entities.add(new Obstacles(Color.white, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 30, 20, this));
            }
        }
        else if(Stats.levelTwo()){
            entities = new ArrayList<Entity>();

            entities.add(new Circle(Color.cyan, getWidth() / 2, getHeight() / 2, 20, this));

            for (int i = 0; i < 30; i++) {
                entities.add(new Food(Color.green, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 20, 30, this));
            }
            for (int i = 0; i < 5; i++) {
                entities.add(new Circle(Color.blue, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 30, this));
            }
            for (int i = 0; i < (int) (Math.random() + 3) * 3; i++) {
                entities.add(new Obstacles(Color.white, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 30, 20, this));

            }
        }
        else if(Stats.levelThree()){
            entities = new ArrayList<Entity>();

            entities.add(new Circle(Color.black, getWidth() / 2, getHeight() / 2, 20, this));

            for (int i = 0; i < 30; i++) {
                entities.add(new Food(Color.green, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 20, 30, this));
            }
            for (int i = 0; i < 10; i++) {
                entities.add(new Circle(Color.blue, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 30, this));
            }
            for (int i = 0; i < (int) (Math.random() + 3) * 3; i++) {
                entities.add(new Obstacles(Color.white, (int) (25 + (getWidth() - 100) * Math.random()),
                        (int) (25 + (getHeight() - 50) * Math.random()), 30, 20, this));
            }
        }
    }



    public void collisions() {

        for (int i = 1; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(0).collides(entities.get(i))) {
                    if (entities.get(i) instanceof Food) {
                        entities.remove(i);
                        Stats.updateFoodscore();
                       if (Stats.foodscore == 15) {
                                Stats.Win();
                        }
                    }
                    else if (entities.get(i) instanceof Circle) {
                        Stats.endGame();

                    }
                }
                if (entities.get(i).collides(entities.get(j))) {
                    if (entities.get(i) instanceof Food) {
                        if (entities.get(j) instanceof Obstacles) {
                            entities.get(i).bounce();

                        }
                    }
                    if (entities.get(i) instanceof Circle) {
                        if (entities.get(j) instanceof Obstacles) {
                            entities.get(i).speedUp();

                        }
                    }
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
        printSimpleString(String.valueOf("Food counter: " + Stats.foodscore), getWidth(), getX()/2, 25, g);
        if (Stats.levelOne()){
            Stats.startLevelOne();
        }
       else if (Stats.isMenu()) {

            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 32));
            printSimpleString("Dodge the Cedric's blue ball", getWidth(), 0, 200, g);
            printSimpleString("Press *SPACE* to start", getWidth(), 0, 400, g);
        }
        else if(Stats.isWin()){
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 32));
            printSimpleString("You Won!!!!!!!!!", getWidth(), 0, 200, g);
        }
        else if(Stats.isEnd()){
            setBackground(Color.red);
            g.setFont(new Font("Serif", Font.BOLD, 32));
            printSimpleString(String.valueOf("You lost try again?"), getWidth(), getX()/2, 300, g);
            printSimpleString(String.valueOf("Press Space to Restart"), getWidth(), getX()/2, 400, g);
        }
        else if(Stats.isPause()){
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 32));
            printSimpleString("PAUSE", getWidth(), 0, 200, g);
            printSimpleString("Press *P* to resume", getWidth(), 0, 400, g);

        }

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
@Override
    public void keyTyped(KeyEvent e) {
    }
@Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Stats.startLevelOne();
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            Stats.togglePlay();
            Stats.togglePause();
            Stats.toggleMenu();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
