import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    // these variables are for the zoom
    int zoomStartX;
    int zoomStartY;

    MandelbrotWidnow mandelbrotWindow;
    //JuliaWindow juliaWindow;

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
    JComboBox<JuliaWindow> juliaOptions;

    int i;

    double mandelbrotClickedX;
    double mandelbrotClickedY;

    BufferedReader saveFile;

    ArrayList<JuliaWindow> savedJuliaSets = new ArrayList<>();

    public MandelbrotMainFrame(){
        super();

        try {
            saveFile = new BufferedReader(new FileReader("savedJuliaSets.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        readFavourites();
        addItem();
        setSize(1000, 600);
        mandelbrotWindow = new MandelbrotWidnow();
        //this.add(mandelbrotWindow);
        //this.add(new MandelbrotWidnow());
        setResizable(false);
        i = 0;






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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
                System.out.println(mandelbrotWindow.getIterationsToComplete());
                //System.out.println(mandelbrotWindow.getIterationsToComplete());
                mandelbrotWindow.invalidate();
                //mandelbrotWindow.repaint();
                container.revalidate();
                //mandelbrotWindow.revalidate();
                //mandelbrotWindow.repaint();
                container.repaint();
            }
        });
        //mandelbrotWindow.repaint();

        juliaOptions = new JComboBox<>();
        for (JuliaWindow juliaSet : savedJuliaSets){
            juliaOptions.add(juliaSet);
        }

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
        container.add(juliaOptions);

        mandelbrotWindow.setBounds(0, 0, 600, 600);
        iterationsText.setBounds(602, 0, 150, 30);
        inputIterations.setBounds(750, 0, 180, 30);
        xPosition.setBounds(602, 30, 150, 30);
        xPosInput.setBounds(750, 30, 180, 30);
        yPosition.setBounds(602, 60, 150, 30);
        yPosInput.setBounds(750, 60, 180, 30);
        CNtext.setBounds(602, 90, 150, 30);
        CNLabel.setBounds(756, 90, 400, 30);
        startButton.setBounds(680, 120, 230, 30);
        juliaOptions.setBounds(680, 150, 230, 30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        JuliaMainFrame jmf= new JuliaMainFrame(x, y);
        jmf.setVisible(true);

        /*
        in the worst case I should do container.remove (current window) and tehn container.add(new window)
         */

//        if (i > 0){
//            container.remove(jw);
//        }
        JuliaWindow jw = new JuliaWindow(x, y);
        container.add(jw);
        jw.setVisible(true);
        jw.setBounds(600, 180, 400, 400);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        zoomStartX = e.getX();
        zoomStartY = e.getY();
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


    public void readFavourites(){

        String[] juliaSet;

        String line;

        try {
            while ((line = saveFile.readLine()) != null){
                juliaSet = new String[3];
                juliaSet = line.split(":");

                savedJuliaSets.add(new JuliaWindow(juliaSet[0], Double.parseDouble(juliaSet[1]), Double.parseDouble(juliaSet[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
