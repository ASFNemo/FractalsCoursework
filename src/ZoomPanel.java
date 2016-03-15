import javax.swing.*;
import java.awt.*;

/**
 * Created by asherfischbaum on 14/03/2016.
 */
public class ZoomPanel extends JPanel {

    double oldx;
    double oldy;
    double newy;
    double newx;

    public ZoomPanel(){

    }

    public void paint(Graphics g){
        super.paint(g);

        // old x and y stays constant, the new one gets redrawn
        // draw a line from the old to new x/y posisition

        //g.drawRect((int) getOldx(), (int) getOldy(), (int) zoomRectangleWidth(), (int) zoomRectangleHeight());

        g.setColor(Color.white);

        // draw line 1 (old x, old y) -> (new x, old y)
        g.drawLine((int) getOldx(), (int) getOldy(), (int) getNewx(), (int) getOldy());

        // draw line 2 (old x, old y) -> (old x, new y)
        g.drawLine((int) getOldx(), (int) getOldy(), (int) getOldx(), (int) getNewy());
        // draw line 3 (old x, new y) -> (new x, new y)
        g.drawLine((int) getOldx(), (int) getNewy(), (int) getNewx(), (int) getNewy());
        // draw line 4 (new x, old y) -> (new x, new y)
        g.drawLine((int) getNewx(), (int) getOldy(), (int) getNewx(), (int) getNewy());

    }

    public double getOldx() {
        return oldx;
    }

    public void setOldx(double oldx) {
        this.oldx = oldx;
    }

    public double getOldy() {
        return oldy;
    }

    public void setOldy(double oldy) {
        this.oldy = oldy;
    }

    public double getNewy() {
        return newy;
    }

    public void setNewy(double newy) {
        this.newy = newy;
    }

    public double getNewx() {
        return newx;
    }

    public void setNewx(double newx) {
        this.newx = newx;
    }

}
