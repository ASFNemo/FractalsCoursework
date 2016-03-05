import javax.swing.*;

/**
 * Created by asherfischbaum on 03/03/2016.
 */
public class StartMandelbrot {

    public static void main(String[] args) {
        JFrame frame = new MandelbrotMainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
