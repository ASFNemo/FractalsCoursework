import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by asherfischbaum on 05/03/2016.
 */
public class JuliaMainFrame extends JFrame implements ActionListener{

    JuliaWindow juliaWindow;

    Container container;

    double x;
    double y;

    JLabel xPosition;
    JLabel xPosInput;

    JLabel yPosition;
    JLabel yPosInput;

    JButton save;
    //
    public JuliaMainFrame(ComplexNumbers complexNumber, double x, double y){
        super();

        setSize(600, 600);

        this.x = x;
        this.y = y;

        //juliaWindow = new JuliaWindow(complexNumber, x, y);

        //this.add(mandelbrotWindow);
        //this.add(new MandelbrotPanel());
        setResizable(false);
        frameItem();
    }

    public JuliaMainFrame(double x, double y){
        super();

        setSize(400, 600);

        this.x = x;
        this.y = y;

        //juliaWindow = new JuliaWindow(complexNumber, x, y);

        juliaWindow = new JuliaWindow(x, y);

        //this.add(mandelbrotWindow);
        //this.add(new MandelbrotPanel());
        setResizable(false);
        frameItem();
    }

    public void frameItem(){

        container = getContentPane();
        container.setLayout(null);

        xPosition = new JLabel("X position: ");
        xPosInput = new JLabel(""+ x);
        //realInput.setText("" + juliaWindow.getFractalY());

        yPosition = new JLabel("Y position");
        yPosInput = new JLabel("" + y);
        //imaginaryInput.setText("" + juliaWindow.getFractalY());
//
        save = new JButton("Save this Julia set");
        save.addActionListener(this);

        container.add(juliaWindow);
        container.add(xPosition);
        container.add(xPosInput);
        container.add(yPosition);
        container.add(yPosInput);
        container.add(save);
//
        juliaWindow.setBounds(0, 0, 400, 400);
        xPosition.setBounds(15, 400, 75, 30);
        xPosInput.setBounds(100, 400, 180, 30);
        yPosition.setBounds(15, 440, 75, 30);
        yPosInput.setBounds(100, 440, 180, 30);
        save.setBounds(260, 405, 120, 70);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save){
//            JFrame frame = new SaveJuliaSet(Double.parseDouble(realInput.getText()), Double.parseDouble(imaginaryInput.getText()));
//            frame.setVisible(true);
        }
    }
}
