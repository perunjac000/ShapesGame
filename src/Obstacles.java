import java.awt.*;
/**
 * Created by perunjac000 on 2/13/2017.
 */
public class Obstacles extends Entity {

    public Obstacles(Color color, int x, int y, int width, int height, Game game) {

        super(color, x, y, width, height, game);
    }
        public void paint(Graphics g){
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());

    }
}


