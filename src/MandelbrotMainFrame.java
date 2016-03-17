import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 03/03/2016.
 */
public class MandelbrotMainFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {



    MandelbrotPanel mandelbrotWindow;
    ZoomPanel zoomPanel;

    Container container;

    ButtonGroup setOption;
    JRadioButton mandelbrot;
    JRadioButton burningShip;
    JRadioButton z8;
    JRadioButton BOP;
    JRadioButton BSV;
    JRadioButton randomMultibrot;


    ButtonGroup colorOptions;
    JRadioButton green;
    JRadioButton random;
    JRadioButton fieryRed;
    JRadioButton buriningPink;
    JRadioButton blue;

    ButtonGroup juliaColorOptions;
    JRadioButton jgreen;
    JRadioButton jrandom;
    JRadioButton jfieryRed;
    JRadioButton jburiningPink;
    JRadioButton jblue;

    boolean zPressed;

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

    double xMinDist;
    double xMaxDist;
    double yminDist;
    double yMaxDist;

    JLabel CNtext;

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

    double moveX;
    double moveY;

    double zoomX;
    double zoomY;


    JuliaWindow jw;

    BufferedReader saveFile;

    ArrayList<LoadedJuliaSet> savedJuliaSets = new ArrayList<>();

    double xPressed;
    double xReleased;
    double yPressed;
    double yReleased;


    public MandelbrotMainFrame(){
        super();

        // makes sure there is  a file to read
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
        moveX = 0.0;
        moveY = 0.0;
        zoomX = 0.2;
        zoomY = 0.16;
        zoomAndMove();

    }

    public void addItem(){
        container = getContentPane();
        container.setLayout(null); // change this later to add the bar at the bottom to add the menu  bar

        zPressed = false;
        mandelbrotWindow = new MandelbrotPanel();
        mandelbrotWindow.setBackground(Color.WHITE); // may want to make this black/grey at a later point, but try things out
        mandelbrotWindow.addMouseListener((MouseListener) this);
        mandelbrotWindow.addMouseMotionListener(this);
        mandelbrotWindow.setFocusable(true);
        mandelbrotWindow.requestFocus();
        mandelbrotWindow.addKeyListener(this);
        jw = new JuliaWindow();
        zoomPanel = new ZoomPanel();
        zoomPanel.setOpaque(false);

        fractalOptions();
        fractalInfo();
        fractalButtons();
        CNtext = new JLabel("This Complex Number:");

        juliaSetStuff();

        colorOptions();
        juliaColorOptions();
        elementsToAdd();
        elemetnLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        double x = mandelbrotWindow.getX(e.getX());
        double y = mandelbrotWindow.getY(e.getY());

        drawJulia(x, y);

    }

    /**
     * when the mouse is pressed we first save the location where the mouse is pressed for use in other methods such as
     * the mouse dragged method. we then set the boolean toggele in zoomPanel to true to allow the start of a zoom operation
     * @param e the evet that is taking place, in this case the mouse being pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        xPressed = e.getX();
        yPressed = e.getY();

        zoomPanel.setButtonDown(true);
        zoomPanel.setOldx(xPressed);
        zoomPanel.setOldy(yPressed);

    }

    /**
     * In this method we first assign the location where the mouse was released to a variable. we then make sure that
     * the zoom space is larger than two pixels to make sure the user wasn't trying to bring up a julia set. If it is
     * greater than two pixels we then check in which direction the zoom square has been drawn and adjust the fractal
     * accordincly. we call the updateFractal() method to paint the new view and finaly switch the boolean togle back to
     * it's original state where it iwll not draw zoom box.
     * @param e the event here is the mouse being released/
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        xReleased = e.getX();
        yReleased = e.getY();


        if ((xPressed - xReleased > 2 || xReleased - xPressed > 2) && (yPressed - yReleased > 2 || yReleased - yPressed > 2)) {

            if (xReleased > xPressed) {
                xMinDist = mandelbrotWindow.getxMin() - xPressed;
                xMaxDist = mandelbrotWindow.getxMax() - xReleased;
                xAxisMinInput.setText("" + mandelbrotWindow.getX(xPressed));
                xAxisMaxInput.setText("" + mandelbrotWindow.getX(xReleased));
                System.out.println("in line 1");
            } else {
                xMinDist = mandelbrotWindow.getxMin() - xReleased;
                xMaxDist = mandelbrotWindow.getxMax() - xPressed;
                xAxisMinInput.setText("" + mandelbrotWindow.getX(xReleased));
                xAxisMaxInput.setText("" + mandelbrotWindow.getX(xPressed));
                System.out.println("in if 2");
            }

            if (yReleased > yPressed) {
                yAxisMinInput.setText("" + mandelbrotWindow.getY(yReleased));
                yAxisMaxInput.setText("" + mandelbrotWindow.getY(yPressed));
                System.out.println("in if 3");
            } else {
                yminDist = mandelbrotWindow.getyMin() - xReleased;
                yMaxDist = mandelbrotWindow.getyMax() - xPressed;
                yAxisMinInput.setText("" + mandelbrotWindow.getY(yPressed));
                yAxisMaxInput.setText("" + mandelbrotWindow.getY(yReleased));
                System.out.println("in if 4");
            }

            updateFractal();



            zoomAndMove();

            zoomPanel.setButtonDown(false);
        }


    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    /**
     *
     * @param e the event here is the mouse being dragged.
     */
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
        double x = mandelbrotWindow.getX(e.getX());
        double y = mandelbrotWindow.getY(e.getY());
        if (zPressed) {
            drawJulia(x, y);
        }
    }

    public void drawJulia(double x, double y){
        realInput.setText("" + x);
        imaginaryInput.setText("" + y);
        jSetXPosInput.setText("" + x);
        jSetYPosInput.setText("" + y);

        jw.setMandelbrotx(x);
        jw.setMandelbrotY(y);
        jw.repaint();
    }

    public void readFavourites(){

        String[] juliaSet;

        String line;

        try {
            while ((line = saveFile.readLine()) != null){
                juliaSet = new String[3];
                juliaSet = line.split(":");

                if (juliaSet.length > 0){
                savedJuliaSets.add(new LoadedJuliaSet(new ComplexNumbers(Double.parseDouble(juliaSet[1]), Double.parseDouble(juliaSet[2])), juliaSet[0]));
            }}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean savePressed(){
        boolean saved = false;
        String saveName = setNameTextField.getText();
        if (!saveName.contains(":")) {
            writeToFile(saveName);
            saved = true;
        } else{
            warning.setText("please do not use a colon (:)");
        }

        return saved;
    }

    public void writeToFile(String saveAs){



        try {
            saveSetInfo = new PrintStream(new FileOutputStream("savedJuliaSets.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        saveSetInfo.println("");
        saveSetInfo.print(saveAs + ":" + jSetXPosInput.getText() + ":" + jSetYPosInput.getText());

        savedJuliaset = new LoadedJuliaSet(new ComplexNumbers(Double.parseDouble(jSetXPosInput.getText()),
                Double.parseDouble(jSetYPosInput.getText())), saveAs);
    }

    public void changeFractal(String fractal){
        mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
        xAxisMinInput.setText("" + -2);
        xAxisMaxInput.setText("" + 2);
        yAxisMinInput.setText("" + -1.6);
        yAxisMaxInput.setText("" + 1.6);
        mandelbrotWindow.setxMin(-2);
        mandelbrotWindow.setxMax(2);
        mandelbrotWindow.setyMin(-1.6);
        mandelbrotWindow.setyMax(1.6);
        mandelbrotWindow.setFractalToShow(fractal);
        mandelbrotWindow.repaint();
    }

    public void zoomAndMove(){
        double ixm= Double.parseDouble(xAxisMinInput.getText());
        double ixMa = Double.parseDouble(xAxisMaxInput.getText());
        double iym= Double.parseDouble(yAxisMinInput.getText());
        double iyMa = Double.parseDouble(yAxisMaxInput.getText());

        if (ixm > -2 && ixMa < 2){
            double avDistX = ((ixm - (-2)) + (2 -ixMa))/2;
            double avDistY = ((iym - (-1.6)) + (1.6 - iyMa))/2;

            moveX = avDistX/20;
            moveY = avDistY/20;
        }

    }

    public void moveLeft() {
        double newInput = (Double.parseDouble(xAxisMinInput.getText()) - moveX);
        if (newInput >= -2) {
            xAxisMinInput.setText("" + newInput);
            xAxisMaxInput.setText("" + (Double.parseDouble(xAxisMaxInput.getText()) - moveX));
            setAxes();
            mandelbrotWindow.repaint();
        } else{
            // work out that it works the distancce to the edge bfore making the move and only move the max side
        }

    }
    public void moveRight(){
        double newInput = (Double.parseDouble(xAxisMaxInput.getText()) + moveX);
        if (newInput <= 2) {
            xAxisMaxInput.setText("" + newInput);
            xAxisMinInput.setText("" + (Double.parseDouble(xAxisMinInput.getText()) + moveX));
            setAxes();
            mandelbrotWindow.repaint();
        }

    }
    public void moveUp(){
        double newInput = (Double.parseDouble(yAxisMaxInput.getText()) + moveY);
        if (newInput <= 1.6) {
            yAxisMaxInput.setText("" + newInput);
            yAxisMinInput.setText("" + (Double.parseDouble(yAxisMinInput.getText()) + moveY));
            setAxes();
            mandelbrotWindow.repaint();
        }
    }
    public void moveDown() {
        double newInput = (Double.parseDouble(yAxisMinInput.getText()) - moveY);
        if (newInput >= -1.6) {
            yAxisMinInput.setText("" + newInput);
            yAxisMaxInput.setText("" + (Double.parseDouble(yAxisMaxInput.getText()) - moveY));
            setAxes();
            mandelbrotWindow.repaint();
        }
    }

    public void zoomIn(){
        System.out.println("yaaawww");

        double newXMin = (Double.parseDouble(xAxisMinInput.getText()) + zoomX);
        double newXMax = (Double.parseDouble(xAxisMaxInput.getText()) - zoomX);
        double newYMin = (Double.parseDouble(yAxisMinInput.getText()) + zoomY);
        double newYMax = (Double.parseDouble(yAxisMaxInput.getText()) - zoomY);

        if (newXMax - newXMin > 0 && newYMax - newYMin > 0) {
            zoom(newXMin, newXMax, newYMin, newYMax);
        }
    }

    public void zoomOut(){
        System.out.println("boooha");

        double newXMin = (Double.parseDouble(xAxisMinInput.getText()) - zoomX);
        double newXMax = (Double.parseDouble(xAxisMaxInput.getText()) + zoomX);
        double newYMin = (Double.parseDouble(yAxisMinInput.getText()) - zoomY);
        double newYMax = (Double.parseDouble(yAxisMaxInput.getText()) + zoomY);

        if (newXMax + newXMin < 4 && newYMax + newYMin < 3.2) {
            zoom(newXMin, newXMax, newYMin, newYMax);
        }
    }

    public void zoom(double xMin, double xMax, double yMin, double yMax){
        xAxisMinInput.setText("" + xMin);
        System.out.println(xAxisMinInput.getText());
        xAxisMaxInput.setText("" + xMax);
        yAxisMinInput.setText("" + yMin);
        yAxisMaxInput.setText("" + yMax);
        setAxes();
        mandelbrotWindow.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_Z:
                System.out.println("z is pressed");
                zPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("I  SAID LEF6T GOD-DAMMIT");
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("SO NOW YOU WANT TO GO RIGHT");
                moveRight();
                break;
            case KeyEvent.VK_UP:
                System.out.println("UP WE GO!");
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("everything that goes up must come down :(");
                moveDown();
                break;
            case KeyEvent.VK_EQUALS:
                System.out.println("We are going in");
                zoomIn();
                break;
            case KeyEvent.VK_MINUS:
                zoomOut();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z){
            System.out.println("z is released");
            zPressed = false;
        }
    }

    public void setAxes(){
        mandelbrotWindow.setxMin(Double.parseDouble(xAxisMinInput.getText()));
        mandelbrotWindow.setxMax(Double.parseDouble(xAxisMaxInput.getText()));
        mandelbrotWindow.setyMin(Double.parseDouble(yAxisMinInput.getText()));
        mandelbrotWindow.setyMax(Double.parseDouble(yAxisMaxInput.getText()));
    }

    public void redrawFractal(){
        mandelbrotWindow.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
        xAxisMinInput.setText("" + -2);
        xAxisMaxInput.setText("" + 2);
        yAxisMinInput.setText("" + -1.6);
        yAxisMaxInput.setText("" + 1.6);
        mandelbrotWindow.setxMin(-2);
        mandelbrotWindow.setxMax(2);
        mandelbrotWindow.setyMin(-1.6);
        mandelbrotWindow.setyMax(1.6);
        mandelbrotWindow.repaint();
    }

    public void updateFractal(){
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

    public void elementsToAdd(){
        container.add(zoomPanel);
        container.add(mandelbrotWindow);
        container.add(mandelbrot);
        container.add(burningShip);
        container.add(z8);
        container.add(BOP);
        container.add(randomMultibrot);
        container.add(BSV);
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
        container.add(green);
        container.add(random);
        container.add(fieryRed);
        container.add(buriningPink);
        container.add(blue);
        container.add(jgreen);
        container.add(jrandom);
        container.add(jfieryRed);
        container.add(jburiningPink);
        container.add(jblue);
        container.add(mandelbrotWindow);
    }

    public void elemetnLayout(){
        mandelbrotWindow.setBounds(0, 0, 600, 600);
        zoomPanel.setBounds(0, 0, 600, 600);
        mandelbrot.setBounds(0, 600, 200, 25);
        burningShip.setBounds(0, 625, 200, 25);
        z8.setBounds(0, 650, 200, 25);
        BOP.setBounds(200, 600, 200, 25);
        BSV.setBounds(200, 625, 200, 25);
        randomMultibrot.setBounds(200, 650, 200, 25);
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
        redrawMandelbrot.setBounds(380, 675, 200, 25);
        updateMandelbrot.setBounds(620, 675, 200, 25);
        jw.setBounds(600, 0, 400, 400);
        juliaOptions.setBounds(600, 400, 150, 25);
        showJulia.setBounds(750, 400, 140, 25);
        jSetXPosition.setBounds(610, 425, 100, 25);
        jSetXPosInput.setBounds(710, 425, 180, 25);
        jSetYPosition.setBounds(610, 450, 100, 25);
        jSetYPosInput.setBounds(710, 450, 180, 25);
        setName.setBounds(610, 475, 100, 25);
        setNameTextField.setBounds(705,475, 180, 25);
        save.setBounds(890,420, 100, 80);
        warning.setBounds(700, 500, 200, 25);
        green.setBounds(600, 525, 120, 25);
        random.setBounds(600, 550, 120, 25);
        fieryRed.setBounds(600, 575, 120, 25);
        buriningPink.setBounds(720, 525, 120, 25);
        blue.setBounds(720, 550, 120, 25);
        //save.setBounds(675, 525, 250, 25);
    }

    public void fractalOptions(){
        setOption = new ButtonGroup();
        mandelbrot = new JRadioButton("Mandelbrot");
        mandelbrot.addActionListener(e -> changeFractal("Mandelbrot"));
        burningShip = new JRadioButton("Burning Ship");
        burningShip.addActionListener(e -> changeFractal("BurningShip"));
        mandelbrot.setSelected(true);
        z8 = new JRadioButton("Z^8");
        z8.addActionListener(e -> changeFractal("z^8"));
        BOP = new JRadioButton("Bird of Prey");
        BOP.addActionListener(e -> changeFractal("BirdOFPrey"));
        BSV = new JRadioButton("Burning Ship Variant");
        BSV.addActionListener(e -> changeFractal("bsv"));
        randomMultibrot = new JRadioButton("Random Multibrot");
        randomMultibrot.addActionListener( e -> changeFractal("RandomMultibrot"));


        setOption.add(mandelbrot);
        setOption.add(burningShip);
        setOption.add(z8);
        setOption.add(BOP);
        setOption.add(BSV);
        setOption.add(randomMultibrot);

    }

    public void colorOptions(){
        colorOptions = new ButtonGroup();
        green = new JRadioButton("Green");
        green.addActionListener(e -> changeColoring("green"));
        random = new JRadioButton("Random");
        random.addActionListener(e -> changeColoring("random"));
        fieryRed = new JRadioButton("Fiery Red");
        fieryRed.addActionListener(e -> changeColoring("red"));
        buriningPink = new JRadioButton("Burning Pink");
        buriningPink.addActionListener(e -> changeColoring("pink"));
        blue = new JRadioButton("blue");
        blue.addActionListener(e -> changeColoring("blue"));

        colorOptions.add(green);
        colorOptions.add(random);
        colorOptions.add(fieryRed);
        colorOptions.add(buriningPink);
        colorOptions.add(blue);
    }

    public void changeColoring(String coloring){
        mandelbrotWindow.setColoringAlgorithm(coloring);
        mandelbrotWindow.repaint();
    }


    public void fractalInfo(){

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
    }

    public void fractalButtons(){
        redrawMandelbrot = new JButton("redraw Mandelbrot");
        redrawMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redrawFractal();
            }
        });

        updateMandelbrot = new JButton("update Mandelbrot");
        updateMandelbrot.addActionListener(e -> updateFractal());
    }

    public void juliaSetStuff(){
        juliaOptions = new JComboBox<String>();
        for (LoadedJuliaSet juliaSet : savedJuliaSets){
            juliaOptions.addItem(juliaSet.toString());
        }
        showJulia = new JButton("Show Julia  set");
        showJulia.addActionListener(e -> {
            jw.setMandelbrotx(savedJuliaSets.get(juliaOptions.getSelectedIndex()).getX());
            jw.setMandelbrotY(savedJuliaSets.get(juliaOptions.getSelectedIndex()).getY());
            jw.repaint();
        });

        jSetXPosition = new JLabel("X position: ");
        jSetXPosInput = new JLabel("0");

        jSetYPosition = new JLabel("Y position");
        jSetYPosInput = new JLabel("0");

        setName = new JLabel("Name: ");
        setNameTextField = new JTextField();

        warning = new JLabel();
        warning.setForeground(Color.red);


        save = new JButton("Save Julia set");
        save.addActionListener(e -> {
            boolean save = savePressed();
            if (save){
                savedJuliaSets.add(savedJuliaset);
                juliaOptions.addItem(savedJuliaset.toString());
                setNameTextField.setText("");
            }

        });
    }

    public void changeJuliaColoring(String coloring){
        mandelbrotWindow.setColoringAlgorithm(coloring);
        mandelbrotWindow.repaint();
    }

    public void juliaColorOptions(){
        juliaColorOptions = new ButtonGroup();
        jgreen = new JRadioButton("Green");
        jgreen.addActionListener(e -> changeColoring("green"));
        jrandom = new JRadioButton("Random");
        jrandom.addActionListener(e -> changeColoring("random"));
        jfieryRed = new JRadioButton("Fiery Red");
        jfieryRed.addActionListener(e -> changeColoring("red"));
        jburiningPink = new JRadioButton("Burning Pink");
        jburiningPink.addActionListener(e -> changeColoring("pink"));
        jblue = new JRadioButton("blue");
        jblue.addActionListener(e -> changeColoring("blue"));

        juliaColorOptions.add(green);
        juliaColorOptions.add(random);
        juliaColorOptions.add(fieryRed);
        juliaColorOptions.add(buriningPink);
        juliaColorOptions.add(blue);
    }
}
