import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.io.*;
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
    JComboBox<String> juliaOptions;

    JLabel jSetXPosition;
    JLabel jSetXPosInput;

    JLabel jSetYPosition;
    JLabel jSetYPosInput;

    JButton save;

    JLabel setName;
    JTextField setNameTextField;

    JLabel warning;
    PrintStream saveSetInfo;

    int i;

    double mandelbrotClickedX;
    double mandelbrotClickedY;

    JuliaWindow jw;

    BufferedReader saveFile;

    ArrayList<LoadedJuliaSet> savedJuliaSets = new ArrayList<>();

    public MandelbrotMainFrame(){
        super();

        try {
            saveFile = new BufferedReader(new FileReader("savedJuliaSets.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        readFavourites();
        addItem();
        setSize(1000, 800);
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

        jw = new JuliaWindow();

        juliaOptions = new JComboBox<String>();
        for (LoadedJuliaSet juliaSet : savedJuliaSets){
            juliaOptions.addItem(juliaSet.toString());
        }

        jSetXPosition = new JLabel("X position: ");
        jSetXPosInput = new JLabel("");
        //xPosInput.setText("" + juliaWindow.getFractalY());

        jSetYPosition = new JLabel("Y position");
        jSetYPosInput = new JLabel("");

        setName = new JLabel("Name: ");
        setNameTextField = new JTextField();

        warning = new JLabel();
        warning.setForeground(Color.red);

        save = new JButton("Save this Julia set");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePressed();
            }
        });

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
        container.add(jw);
        container.add(juliaOptions);
        container.add(jSetXPosition);
        container.add(jSetXPosInput);
        container.add(jSetYPosition);
        container.add(jSetYPosInput);
        container.add(setName);
        container.add(setNameTextField);
        container.add(warning);
        container.add(save);


//        mandelbrotWindow.setBounds(0, 0, 600, 600);
//        iterationsText.setBounds(602, 0, 150, 30);
//        inputIterations.setBounds(750, 0, 180, 30);
//        xPosition.setBounds(602, 30, 150, 30);
//        xPosInput.setBounds(750, 30, 180, 30);
//        yPosition.setBounds(602, 60, 150, 30);
//        yPosInput.setBounds(750, 60, 180, 30);
//        CNtext.setBounds(602, 90, 150, 30);
//        CNLabel.setBounds(756, 90, 400, 30);
//        startButton.setBounds(680, 120, 230, 30);
//        juliaOptions.setBounds(680, 150, 230, 30);

        mandelbrotWindow.setBounds(0, 0, 600, 600);
        // for teh position of the julia set look in teh action listner below
        iterationsText.setBounds(0, 600, 150, 30);
        inputIterations.setBounds(150, 600, 180, 30);
        xPosition.setBounds(0, 630, 150, 30);
        xPosInput.setBounds(150, 630, 180, 30);
        yPosition.setBounds(0, 660, 150, 30);
        yPosInput.setBounds(150, 660, 180, 30);
        CNtext.setBounds(0, 690, 150, 30);
        CNLabel.setBounds(150, 690, 400, 30);
        startButton.setBounds(120, 720, 230, 30);
        jw.setBounds(600, 0, 400, 400);
        juliaOptions.setBounds(675, 420, 230, 30);
        jSetXPosition.setBounds(610, 475, 150, 30);
        jSetXPosInput.setBounds(760, 475, 180, 30);
        jSetYPosition.setBounds(610, 505, 150, 30);
        jSetYPosInput.setBounds(760, 505, 180, 30);
        setName.setBounds(610, 535, 150, 30);
        setNameTextField.setBounds(760,535, 180, 30);
        warning.setBounds(700, 565, 200, 30);
        save.setBounds(675, 720, 250, 30);

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
        jSetXPosInput.setText(""+x);
        jSetYPosInput.setText(""+y);

        // if we need to return the value of the complex number simply reverse engineer the getx and get y methods in madelbrot window.
        //xPosInput.setText("" + e.getX());
        //yPosInput.setText("" + e.getY());

        // input the getReal and getImaginary with an i after the getImaginary.
        CNLabel.setText(cn.getReal() + " + " + y + "i");

        //JuliaMainFrame jmf = new JuliaMainFrame(new ComplexNumbers(x, y), x, y);
//        JuliaMainFrame jmf= new JuliaMainFrame(x, y);
//        jmf.setVisible(true);

        /*
        in the worst case I should do container.remove (current window) and tehn container.add(new window)
         */

//        if (i > 0){
//            container.remove(jw);
//        }
//        JuliaWindow jw = new JuliaWindow(x, y);
//        JuliaWindow jw = new JuliaWindow(cn);
//        container.add(jw);

//        jw.setVisible(true);
        jw.setMandelbrotx(x);
        jw.setMandelbrotY(y);
//        jw.setBounds(600, 0, 400, 400);
        jw.repaint();

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

                //savedJuliaSets.add(new JuliaWindow(juliaSet[0], Double.parseDouble(juliaSet[1]), Double.parseDouble(juliaSet[2])));

                savedJuliaSets.add(new LoadedJuliaSet(new ComplexNumbers(Double.parseDouble(juliaSet[1]), Double.parseDouble(juliaSet[2])), juliaSet[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePressed(){
        String saveName = setNameTextField.getText();
        if (!saveName.contains(":")) {
            boolean writeOut = writeToFile(saveName);
            if (writeOut) {
                setVisible(false);
                dispose();
            }
        } else{
            warning.setText("please do not use a colon (:)");
        }
    }

    public boolean writeToFile(String saveAs){



        try {
            saveSetInfo = new PrintStream(new FileOutputStream("savedJuliaSets.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        saveSetInfo.println(saveAs + ":" + jSetXPosInput.getText() + ":" + jSetYPosInput.getText());

        return true;
    }
}
