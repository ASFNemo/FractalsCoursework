import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 03/03/2016.
 */
public class MandelbrotMainFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

    //


    int currentx;
    int currenty;

    MandelbrotPanel mandelbrotWindow;
    ZoomPanel zoomPanel;
    JuliaWindow juliaWindow;

    Container container;

    JLabel iterationsText;
    JTextField inputIterations;

    JLabel realLabel;
    JTextField realInput;

    JLabel imaginaryLabel;
    JTextField imaginaryInput;

    JLabel xAxisMin;
    JTextField xAxisMinInput;
    JLabel xAxisMax;
    JTextField xAxisMaxInput;

    JLabel yAxisMin;
    JTextField yAxisMinInput;
    JLabel yAxisMax;
    JTextField yAxisMaxInput;


    JLabel CNtext;
//    JLabel CNLabel;

    JButton updateMandelbrot;
    JButton redrawMandelbrot;

    JComboBox<String> juliaOptions;
    JButton showJulia;

    JLabel jSetXPosition;
    JLabel jSetXPosInput;

    JLabel jSetYPosition;
    JLabel jSetYPosInput;

    JButton save;
    LoadedJuliaSet savedJuliaset;

    JLabel setName;
    JTextField setNameTextField;

    JLabel warning;
    PrintStream saveSetInfo;

    int i;


    JuliaWindow jw;

    BufferedReader saveFile;

    ArrayList<LoadedJuliaSet> savedJuliaSets = new ArrayList<>();

    double xPressed;
    double xReleased;
    double yPressed;
    double yReleased;


    public MandelbrotMainFrame(){
        super();

        try {
            saveFile = new BufferedReader(new FileReader("savedJuliaSets.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        readFavourites();
        addItem();
        setSize(1000, 720);
        //mandelbrotWindow = new MandelbrotPanel();

        //this.add(mandelbrotWindow);
        //this.add(new MandelbrotPanel());
        setResizable(false);
        i = 0;

    }

    public void addItem(){
        container = getContentPane();
        container.setLayout(null); // change this later to add the bar at the bottom to add the menu  bar

        mandelbrotWindow = new MandelbrotPanel();
        mandelbrotWindow.setBackground(Color.WHITE); // may want to make this black/grey at a later point, but try things out
        mandelbrotWindow.addMouseListener((MouseListener) this);
        mandelbrotWindow.addMouseMotionListener(this);

        // this is the zoom panel, it should be see-through
        zoomPanel = new ZoomPanel();
        zoomPanel.setOpaque(false);

        iterationsText =  new JLabel("Amount of iterations: ");
        inputIterations = new JTextField("" + mandelbrotWindow.getIterationsToComplete(), 30);

        realLabel = new JLabel("real: ");
        realInput = new JTextField("0", 30);
        realInput.setText("" + mandelbrotWindow.getFractalY());

        imaginaryLabel = new JLabel("Imaginary: ");
        imaginaryInput = new JTextField("0", 30);
        imaginaryInput.setText("" + mandelbrotWindow.getFractalY());

        xAxisMin = new JLabel("xMin: ");
        xAxisMinInput = new JTextField("" + mandelbrotWindow.getxMin());
        xAxisMax = new JLabel("xMax");
        xAxisMaxInput = new JTextField("" + mandelbrotWindow.getxMax());

        yAxisMin = new JLabel("yMin: ");
        yAxisMinInput = new JTextField("" +  mandelbrotWindow.getyMin());
        yAxisMax = new JLabel("yMax");
        yAxisMaxInput = new JTextField("" + mandelbrotWindow.getyMax());

        CNtext = new JLabel("This Complex Number:");
//        CNLabel = new JLabel("0 + 0i");

        redrawMandelbrot = new JButton("redraw Mandelbrot");
        redrawMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
                mandelbrotWindow.setxMin(-2);
                mandelbrotWindow.setxMax(2);
                mandelbrotWindow.setyMin(-1.6);
                mandelbrotWindow.setyMax(1.6);
                mandelbrotWindow.repaint();
            }
        });

        updateMandelbrot = new JButton("update Mandelbrot");
        updateMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
                mandelbrotWindow.setxMin(Double.parseDouble(xAxisMinInput.getText()));
                mandelbrotWindow.setxMax(Double.parseDouble(xAxisMaxInput.getText()));
                mandelbrotWindow.setyMin(Double.parseDouble(yAxisMinInput.getText()));
                mandelbrotWindow.setyMax(Double.parseDouble(yAxisMaxInput.getText()));
                jw.setMandelbrotx(Double.parseDouble(realInput.getText()));
                jw.setMandelbrotY(Double.parseDouble(imaginaryInput.getText()));
                mandelbrotWindow.repaint();
                jw.repaint();
            }
        });
        //mandelbrotWindow.repaint();

        jw = new JuliaWindow();

        juliaOptions = new JComboBox<String>();
        for (LoadedJuliaSet juliaSet : savedJuliaSets){
            juliaOptions.addItem(juliaSet.toString());
        }
        showJulia = new JButton("Show Julia  set");
        showJulia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jw.setMandelbrotx(savedJuliaSets.get(juliaOptions.getSelectedIndex()).getX());
                jw.setMandelbrotY(savedJuliaSets.get(juliaOptions.getSelectedIndex()).getY());
                jw.repaint();
            }
        });

        jSetXPosition = new JLabel("X position: ");
        jSetXPosInput = new JLabel("0");
        //realInput.setText("" + juliaWindow.getFractalY());

        jSetYPosition = new JLabel("Y position");
        jSetYPosInput = new JLabel("0");

        setName = new JLabel("Name: ");
        setNameTextField = new JTextField();

        warning = new JLabel();
        warning.setForeground(Color.red);

        save = new JButton("Save this Julia set");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePressed();
                savedJuliaSets.add(savedJuliaset);
                juliaOptions.addItem(savedJuliaset.toString());
                setNameTextField.setText("");
            }
        });

        container.add(zoomPanel);
        container.add(mandelbrotWindow);
        container.add(redrawMandelbrot);
        container.add(updateMandelbrot);
        container.add(iterationsText);
        container.add(inputIterations);
        container.add(realLabel);
        container.add(realInput);
        container.add(imaginaryLabel);
        container.add(imaginaryInput);
        container.add(xAxisMin);
        container.add(xAxisMinInput);
        container.add(xAxisMax);
        container.add(xAxisMaxInput);
        container.add(yAxisMin);
        container.add(yAxisMinInput);
        container.add(yAxisMax);
        container.add(yAxisMaxInput);
        container.add(CNtext);
        //container.add(CNLabel);
        container.add(jw);
        container.add(juliaOptions);
        container.add(showJulia);
        container.add(jSetXPosition);
        container.add(jSetXPosInput);
        container.add(jSetYPosition);
        container.add(jSetYPosInput);
        container.add(setName);
        container.add(setNameTextField);
        container.add(warning);
        container.add(save);



        mandelbrotWindow.setBounds(0, 0, 600, 600);
        zoomPanel.setBounds(0, 0, 600, 600);
        // for teh position of the julia set look in teh action listner below
        iterationsText.setBounds(375, 600, 150, 25);
        inputIterations.setBounds(525, 600, 50, 25);
        xAxisMin.setBounds(375, 625, 50, 25);
        xAxisMinInput.setBounds(425, 625, 75, 25);
        xAxisMax.setBounds(500, 625, 50, 25);
        xAxisMaxInput.setBounds(550, 625, 75, 25);
        yAxisMin.setBounds(375, 650, 50, 25);
        yAxisMinInput.setBounds(425, 650, 75, 25);
        yAxisMax.setBounds(500, 650, 50, 25);
        yAxisMaxInput.setBounds(550, 650, 75, 25);
        CNtext.setBounds(775, 600, 200, 25);
        realLabel.setBounds(625, 625, 150, 25);
        realInput.setBounds(775, 625, 200, 25);
        imaginaryLabel.setBounds(625, 650, 150, 25);
        imaginaryInput.setBounds(775, 650, 200, 25);
//        CNLabel.setBounds(290, 625, 280, 25);
        redrawMandelbrot.setBounds(380, 675, 200, 25);
        updateMandelbrot.setBounds(620, 675, 200, 25);
        jw.setBounds(600, 0, 400, 400);
        juliaOptions.setBounds(600, 400, 230, 25);
        showJulia.setBounds(850, 400, 140, 25);
        jSetXPosition.setBounds(610, 425, 150, 25);
        jSetXPosInput.setBounds(760, 425, 180, 25);
        jSetYPosition.setBounds(610, 450, 150, 25);
        jSetYPosInput.setBounds(760, 450, 180, 25);
        setName.setBounds(610, 475, 150, 25);
        setNameTextField.setBounds(760,475, 180, 25);
        warning.setBounds(700, 500, 200, 25);
        save.setBounds(675, 525, 250, 25);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
                /*
                    here we need to get the position on our graph and the complex number and change the labels that will be placed at the bottom
                 */


        double x = mandelbrotWindow.getX(e.getX());
        double y = mandelbrotWindow.getY(e.getY());
        //System.out.println("x axis: " + x + " y axis: " + y);

        ComplexNumbers cn = new ComplexNumbers(x, y);

        realInput.setText("" + x);
        imaginaryInput.setText("" + y);
        jSetXPosInput.setText(""+x);
        jSetYPosInput.setText(""+y);

        // input the getReal and getImaginary with an i after the getImaginary.
        //CNLabel.setText(cn.getReal() + " + " + y + "i");

        jw.setMandelbrotx(x);
        jw.setMandelbrotY(y);
        jw.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         *THE ZOOM IS GOING IN THE WRONG PLACE, LOOK HOW TO SORT THAT.
         */
        xPressed = e.getX();
        yPressed = e.getY();

        //mandelbrotWindow.setDrawZoomRectangle(true);

        zoomPanel.setOldx(xPressed);
        zoomPanel.setOldy(yPressed);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        /**
         * MOVE THIS STUFF TO TEH MOUSE DRAGGED AREA
         */

        xReleased = e.getX();
        yReleased = e.getY();

        System.out.println("ZOOM: x axis pressed: " + mandelbrotWindow.getX(xPressed) + " x axis released: " + mandelbrotWindow.getX(xReleased));
        System.out.println("ZOOM: y axis pressed: " + mandelbrotWindow.getY(yPressed) + " y axis released: " + mandelbrotWindow.getY(yReleased));

        //mandelbrotWindow.setDrawZoomRectangle(false);
        //System.out.println("xpressed: " + xPressed + " yPressed: " + yPressed);
        //System.out.println("xreleased: " + xPressed + " yreleased: " + yReleased);

        if ((xPressed - xReleased > 5 || xReleased - xPressed > 5) && (yPressed - yReleased > 5 || yReleased - yPressed  >5)) {
            if (xReleased > xPressed) {
                xAxisMinInput.setText("" + mandelbrotWindow.getX(xPressed));
                xAxisMaxInput.setText("" + mandelbrotWindow.getX(xReleased));
                System.out.println("in line 1");
            } else {
                xAxisMinInput.setText("" + mandelbrotWindow.getX(xReleased));
                xAxisMaxInput.setText("" + mandelbrotWindow.getX(xPressed));
                System.out.println("in if 2");
            }

            if (yReleased > yPressed) {
                yAxisMinInput.setText("" + mandelbrotWindow.getY(yPressed));
                yAxisMaxInput.setText("" + mandelbrotWindow.getY(yReleased));
                System.out.println("in if 3");
            } else {
                yAxisMinInput.setText("" + mandelbrotWindow.getY(yReleased));
                yAxisMaxInput.setText("" + mandelbrotWindow.getY(yPressed));
                System.out.println("in if 4");
            }

            mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
            mandelbrotWindow.setxMin(Double.parseDouble(xAxisMinInput.getText()));
            System.out.print("xmin: " + Double.parseDouble(xAxisMinInput.getText()));
            mandelbrotWindow.setxMax(Double.parseDouble(xAxisMaxInput.getText()));
            System.out.println("c");
            mandelbrotWindow.setyMin(Double.parseDouble(yAxisMinInput.getText()));
            mandelbrotWindow.setyMax(Double.parseDouble(yAxisMaxInput.getText()));
            mandelbrotWindow.repaint();
        }



    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {

        if ((xPressed - xReleased > 2 || xReleased - xPressed > 2) && (yPressed - yReleased > 2 || yReleased - yPressed  >2)) {
            zoomPanel.setNewx(e.getX());
            zoomPanel.setNewy(e.getY());
            zoomPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void readFavourites(){

        String[] juliaSet;

        String line;

        try {
            while ((line = saveFile.readLine()) != null){
                juliaSet = new String[3];
                juliaSet = line.split(":");

                //savedJuliaSets.add(new JuliaWindow(juliaSet[0], Double.parseDouble(juliaSet[1]), Double.parseDouble(juliaSet[2])));
                if (juliaSet.length > 0){
                savedJuliaSets.add(new LoadedJuliaSet(new ComplexNumbers(Double.parseDouble(juliaSet[1]), Double.parseDouble(juliaSet[2])), juliaSet[0]));
            }}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePressed(){
        String saveName = setNameTextField.getText();
        if (!saveName.contains(":")) {
            writeToFile(saveName);
        } else{
            warning.setText("please do not use a colon (:)");
        }
    }

    public void writeToFile(String saveAs){



        try {
            saveSetInfo = new PrintStream(new FileOutputStream("savedJuliaSets.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //saveSetInfo.println();
        saveSetInfo.println("");
        saveSetInfo.print(saveAs + ":" + jSetXPosInput.getText() + ":" + jSetYPosInput.getText());

        savedJuliaset = new LoadedJuliaSet(new ComplexNumbers(Double.parseDouble(jSetXPosInput.getText()),
                Double.parseDouble(jSetYPosInput.getText())), saveAs);
    }

    public int getCurrentx() {
        return currentx;
    }

    public void setCurrentx(int currentx) {
        this.currentx = currentx;
    }

    public int getCurrenty() {
        return currenty;
    }

    public void setCurrenty(int currenty) {
        this.currenty = currenty;
    }

}
