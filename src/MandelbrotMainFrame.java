import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by asherfischbaum on 03/03/2016.
 */
public class MandelbrotMainFrame extends JFrame implements ActionListener, MouseListener{
//    public MandelbrotMainFrame(){
//        super();
//        this.setSize(600, 600);
//        this.add(new MandelbrotWidnow());
//
//    }

    MandelbrotWidnow mandelbrotWindow;

    Container container;

    JLabel iterationsText;
    JTextField inputIterations;

    JLabel xPosition;
    JTextField xPosInput;

    JLabel yPosition;
    JTextField yPosInput;

    JLabel CNtext;
    JLabel CNLabel;

    JButton startButton;

    public MandelbrotMainFrame(){
        super();
        addItem();
        setSize(600, 800);
        mandelbrotWindow = new MandelbrotWidnow();
        //this.add(mandelbrotWindow);
        //this.add(new MandelbrotWidnow());
        setResizable(false);


    }

    public void addItem(){
        container = getContentPane();
        container.setLayout(null); // change this later to add the bar at the bottom to add the menu  bar

        mandelbrotWindow = new MandelbrotWidnow();
        mandelbrotWindow.setBackground(Color.WHITE); // may want to make this black/grey at a later point, but try things out
        mandelbrotWindow.addMouseListener((MouseListener) this);


        iterationsText =  new JLabel("Amount of iterations: ");
        inputIterations = new JTextField("" + mandelbrotWindow.getIterationsToComplete(), 30);

        xPosition = new JLabel("X position: ");
        xPosInput = new JTextField("0", 30);
        xPosInput.setText("" + mandelbrotWindow.getFractalY());

        yPosition = new JLabel("Y position");
        yPosInput = new JTextField("0", 30);
        yPosInput.setText("" + mandelbrotWindow.getFractalY());

        CNtext = new JLabel("This Complex Number:");
        CNLabel = new JLabel("0 + 0i");

        startButton = new JButton("Redraw Mandelbrot");
        startButton.addActionListener(this);
        //mandelbrotWindow.repaint();

        container.add(mandelbrotWindow);
        container.add(startButton);
        container.add(iterationsText);
        container.add(inputIterations);
        container.add(xPosition);
        container.add(xPosInput);
        container.add(yPosition);
        container.add(yPosInput);
        container.add(CNtext);
        container.add(CNLabel);

        mandelbrotWindow.setBounds(0, 0, 600, 600);
        iterationsText.setBounds(15, 600, 150, 30);
        inputIterations.setBounds(180, 600, 180, 30);
        xPosition.setBounds(15, 630, 150, 30);
        xPosInput.setBounds(180, 630, 180, 30);
        yPosition.setBounds(15, 660, 150, 30);
        yPosInput.setBounds(180, 660, 180, 30);
        CNtext.setBounds(15, 690, 150, 30);
        CNLabel.setBounds(183, 690, 400, 30);
        startButton.setBounds(5, 730, 230, 30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            //mandelbrotWindow.draw();
            mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
            //System.out.println(mandelbrotWindow.getIterationsToComplete());
            //mandelbrotWindow.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
                /*
                    here we need to get the position on our graph and the complex number and change the labels that will be placed at the bottom
                 */
        System.out.println(" the mouse was clicked at: " + e.getX() + "," + e.getY());

        double x = mandelbrotWindow.getdX(e.getX());
        double y = mandelbrotWindow.getdY(e.getY());

        ComplexNumbers cn = new ComplexNumbers(x, y);

        xPosInput.setText("" + x);
        yPosInput.setText("" + y);

        // if we need to return the value of the complex number simply reverse engineer the getx and get y methods in madelbrot window.
        //xPosInput.setText("" + e.getX());
        //yPosInput.setText("" + e.getY());

        // input the getReal and getImaginary with an i after the getImaginary.
        CNLabel.setText(cn.getReal() + " + " + y + "i");

        //JuliaMainFrame jmf = new JuliaMainFrame(new ComplexNumbers(x, y), x, y);
        JuliaMainFrame jmf= new JuliaMainFrame();
        jmf.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
