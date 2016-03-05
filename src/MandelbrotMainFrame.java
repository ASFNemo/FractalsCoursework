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



    JButton startButton;

    public MandelbrotMainFrame(){
        super();
        addItem();
        setSize(1000, 800);
        mandelbrotWindow = new MandelbrotWidnow();
        this.add(mandelbrotWindow);
        this.add(new MandelbrotWidnow());
        setResizable(false);


    }

    public void addItem(){
        container = getContentPane();
        container.setLayout(null); // change this later to add the bar at the bottom to add the menu  bar

        mandelbrotWindow = new MandelbrotWidnow();
        mandelbrotWindow.setBackground(Color.WHITE); // may want to make this black/grey at a later point, but try things out
        mandelbrotWindow.addMouseListener((MouseListener) this);


        iterationsText =  new JLabel("Amount of iterations: ");
        inputIterations = new JTextField("100", 30);

        xPosition = new JLabel("X position: ");
        xPosInput = new JTextField();

        startButton = new JButton("Start Mandelbrot");
        //startButton.addActionListener(this);
        mandelbrotWindow.draw();
        mandelbrotWindow.repaint();

        container.add(mandelbrotWindow);
        container.add(startButton);
        container.add(iterationsText);
        container.add(inputIterations);
        mandelbrotWindow.setBounds(0, 0, 600, 600);// change the different boundas to try things out. also try a layout manager
        iterationsText.setBounds(25, 610, 150, 30);
        inputIterations.setBounds(165, 610, 100, 30);
        startButton.setBounds(450, 610, 150, 50);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            //mandelbrotWindow.draw();
            mandelbrotWindow.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
                /*
                    here we need to get the position on our graph and the complex number and change the labels that will be placed at the bottom
                 */
        System.out.println(" the mouse was clicked at: " + e.getX() + "," + e.getY());

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
