import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 03/03/2016.
 */
public class FractalMainFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {



    FractalPanel fractalPanel;
    ZoomPanel zoomPanel;
    OrbitTrap orbitTrapPanel;

    Container container;

    ButtonGroup panelOption;

    ButtonGroup setOption;
    JRadioButton mandelbrot;
    JRadioButton burningShip;
    JRadioButton z4;
    JRadioButton BOP;
    JRadioButton BSV;
    JRadioButton randomMultibrot;

    ButtonGroup orbitTraps;
    JRadioButton OTMandelbrot;
    JRadioButton OTburningShip;
    JRadioButton OTz4;
    JRadioButton OTBOP;
    JRadioButton OTBSV;
    JRadioButton OTrandomMultibrot;

    ButtonGroup regionSplit;
    JRadioButton RSmandelbrot;
    JRadioButton RSburningShip;
    JRadioButton RSz4;
    JRadioButton RSBOP;
    JRadioButton RSBSV;
    JRadioButton RSrandomMultibrot;

    ButtonGroup colorOptions;
    JRadioButton green;
    JRadioButton random;
    JRadioButton fieryRed;
    JRadioButton buriningPink;
    JRadioButton blue;



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


    public FractalMainFrame(){
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
        //fractalPanel = new FractalPanel();

        //this.add(fractalPanel);
        //this.add(new FractalPanel());
        setResizable(false);
        i = 0;
        moveX = 0.0;
        moveY = 0.0;
        zoomX = 0.2;
        zoomY = 0.16;
        zoomAndMove();

    }

    /**
     * In this method we are adding everything we need to the main window. I have split everthing needed into different
     * methods to make it easier to locate anthing that needs to be vied, ammended or deleted.
     */
    public void addItem(){
        container = getContentPane();
        container.setLayout(null); // change this later to add the bar at the bottom to add the menu  bar

        windowStuff();
        fractalOptions();
        regionSplitOptions();
        orbitTrapsOptions();
        fractalInfo();
        fractalButtons();
        juliaSetStuff();
        colorOptions();
        elementsToAdd();
        elemetnLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * this is what happens when someone clicks on the main fractal panel we take the positon of the click, set the
     * amount of iteration the programme should do and then we draw the relevant julia set.
     * @param e the event here is the click of the mouse on the main fractla panel.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        double x = fractalPanel.getX(e.getX());
        double y = fractalPanel.getY(e.getY());

        jw.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
        drawJulia(x, y);
        fractalPanel.requestFocus();

        // check if you can make the focus on the panel
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
                xMinDist = fractalPanel.getxMin() - xPressed;
                xMaxDist = fractalPanel.getxMax() - xReleased;
                xAxisMinInput.setText("" + fractalPanel.getX(xPressed));
                xAxisMaxInput.setText("" + fractalPanel.getX(xReleased));
                System.out.println("in line 1");
            } else {
                xMinDist = fractalPanel.getxMin() - xReleased;
                xMaxDist = fractalPanel.getxMax() - xPressed;
                xAxisMinInput.setText("" + fractalPanel.getX(xReleased));
                xAxisMaxInput.setText("" + fractalPanel.getX(xPressed));
                System.out.println("in if 2");
            }

            if (yReleased > yPressed) {
                yAxisMinInput.setText("" + fractalPanel.getY(yPressed));
                yAxisMaxInput.setText("" + fractalPanel.getY(yReleased));
                System.out.println("in if 3");
            } else {
                yminDist = fractalPanel.getyMin() - yReleased;
                yMaxDist = fractalPanel.getyMax() - yPressed;
                yAxisMinInput.setText("" + fractalPanel.getY(yReleased));
                yAxisMaxInput.setText("" + fractalPanel.getY(yPressed));
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
     * This happens when the user has pressed the left buton on the mouse but has not released the button and is dragging
     * the mouse across the main fractal panel. We only draw the box and zoom when they have dragged the mouse more than
     * two pixels to make sure we are not actually just registering a click to bring up the julia set.
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

    /**
     * this method is used to bring up a live update of the julia set should the user have z pressed. this first checks
     * if z is pressed. it then registers where the mouse is hovering over, sets the amount of iterations and then draws
     * the julia set for that point.
     * @param e the event here is mouse movement over the main fractal panel
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (zPressed) {
            double x = fractalPanel.getX(e.getX());
            double y = fractalPanel.getY(e.getY());
            jw.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
            drawJulia(x, y);
        }
    }

    /**
     * this method draws the calls the repaint method in the julia set class. but first it sets the text the mandelbrot
     * values so the user knows the position the julia set they are looking at represents
     * @param x the real number of the complex number this julia set is representing
     * @param y the imaginary number of the complex numberthis julia set is representing.
     */
    public void drawJulia(double x, double y){
        realInput.setText("" + x);
        imaginaryInput.setText("" + y);
        jSetXPosInput.setText("" + x);
        jSetYPosInput.setText("" + y);

        jw.setMandelbrotx(x);
        jw.setMandelbrotY(y);
        jw.repaint();
    }

    /**
     * when the programme starts this is called to read through the file that has the saved julia sets. if the file does
     * does not exist then there is nothing to read and the programe jumps over this step. if there is then the program
     * goes throught the file line by line splitting it into an array of 3 parts. part one is the assigned name of the
     * julia set, part to the real number and part 3 the complex number.
     */
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

    /**
     * when save is pressed this method is called to add the julia set being saved into a file. If the name contains a
     * colon ':' then it is not saved and an error message is shown to the user as I use a colon to seperate betwen
     * the three parts being saved out so it is easier to read back in.
     * @return this returns a true or false depending on whether the julia set was successfly saved or not.
     */
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

    /**
     * this method actually writes out to the file and is called by the savePressed method. thsi puts a colon ':'
     * between the name, the real number and the imaginary number and saves it out to the file. We also assign this julia
     * set to savedJulia set so it can be added to the JCombBox.
     * @param saveAs this is the name we want to save the julia set as.
     */
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

    /**
     * this is called whenever a user changes there choice of fracrals with the JRadioButton. thsi resets the real (x)
     * and imaginary (complex) axes. sets the fractal to show and repaints the panel.
     * @param fractal the name of the fractal that the user wants to draw.
     */
    public void changeFractal(String fractal){
        fractalPanel.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
        xAxisMinInput.setText("" + -2);
        xAxisMaxInput.setText("" + 2);
        yAxisMinInput.setText("" + -1.6);
        yAxisMaxInput.setText("" + 1.6);
        fractalPanel.setxMin(-2);
        fractalPanel.setxMax(2);
        fractalPanel.setyMin(-1.6);
        fractalPanel.setyMax(1.6);
        fractalPanel.setFractalToShow(fractal);
        fractalPanel.repaint();
    }


    /**
     * this is used as a base method for  moving. firstly it gets the
     * parameters of the real and complex axes, then only if it is withing the original bounds than do we allow the user
     * to move around
     */
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

    /**
     * we check that the user has not arrived at the left hand edge of the original fractal. if they have not we
     * allow them to move left
     */
    public void moveLeft() {
        double newInput = (Double.parseDouble(xAxisMinInput.getText()) - moveX);
        if (newInput >= -2) {
            xAxisMinInput.setText("" + newInput);
            xAxisMaxInput.setText("" + (Double.parseDouble(xAxisMaxInput.getText()) - moveX));
            setAxes();
            fractalPanel.repaint();
        }

    }

    /**
     * we check that the user has not arrived at the right hand edge of the original fractal. if they have not we
     * allow them to move left
     */
    public void moveRight(){
        double newInput = (Double.parseDouble(xAxisMaxInput.getText()) + moveX);
        if (newInput <= 2) {
            xAxisMaxInput.setText("" + newInput);
            xAxisMinInput.setText("" + (Double.parseDouble(xAxisMinInput.getText()) + moveX));
            setAxes();
            fractalPanel.repaint();
        }

    }

    /**
     * we check that the user has not arrived at the rupperedge of the original fractal. if they have not we
     * allow them to move up.
     */
    public void moveUp(){
        double newInput = (Double.parseDouble(yAxisMaxInput.getText()) + moveY);
        if (newInput <= 1.6) {
            yAxisMaxInput.setText("" + newInput);
            yAxisMinInput.setText("" + (Double.parseDouble(yAxisMinInput.getText()) + moveY));
            setAxes();
            fractalPanel.repaint();
        }
    }


    /**
     * we check that the user has not arrived at the lower edge of the original fractal. if they have not we
     * allow them to move down
     */
    public void moveDown() {
        double newInput = (Double.parseDouble(yAxisMinInput.getText()) - moveY);
        if (newInput >= -1.6) {
            yAxisMinInput.setText("" + newInput);
            yAxisMaxInput.setText("" + (Double.parseDouble(yAxisMaxInput.getText()) - moveY));
            setAxes();
            fractalPanel.repaint();
        }
    }

    /**
     * this method does the zooming in. It does this by focusing on a smaller set of axes for the fractal thus making
     * everything in the panel larger. we do however stop the user from zooming in when it would get to the point where
     * the fractal would invert and it would start expanding outwards the wrong way round.
     */
    public void zoomIn(){

        double newXMin = (Double.parseDouble(xAxisMinInput.getText()) + zoomX);
        double newXMax = (Double.parseDouble(xAxisMaxInput.getText()) - zoomX);
        double newYMin = (Double.parseDouble(yAxisMinInput.getText()) + zoomY);
        double newYMax = (Double.parseDouble(yAxisMaxInput.getText()) - zoomY);

        if (newXMax - newXMin > 0 && newYMax - newYMin > 0) {
            zoom(newXMin, newXMax, newYMin, newYMax);
        }
    }

    /**
     * this method does the zooming out. It It does this by focusing on a larger set of axes for the fractal thus making
     * everything in the panel smaller (simulating a zoom out). we do however stop the user from zooming out when they
     * get back to the original size.
     */
    public void zoomOut(){

        double newXMin = (Double.parseDouble(xAxisMinInput.getText()) - zoomX);
        double newXMax = (Double.parseDouble(xAxisMaxInput.getText()) + zoomX);
        double newYMin = (Double.parseDouble(yAxisMinInput.getText()) - zoomY);
        double newYMax = (Double.parseDouble(yAxisMaxInput.getText()) + zoomY);

        if (newXMax + newXMin < 4 && newYMax + newYMin < 3.2) {
            zoom(newXMin, newXMax, newYMin, newYMax);
        }
    }

    /**
     * this method sets the axes for the zoom to occur
     * @param xMin the minimum number for the real number axis
     * @param xMax the maximum number for the real number axis
     * @param yMin the minimum number for the imaginary number axis
     * @param yMax the maximum number for the imaginary number axis
     */
    public void zoom(double xMin, double xMax, double yMin, double yMax){
        xAxisMinInput.setText("" + xMin);
        System.out.println(xAxisMinInput.getText());
        xAxisMaxInput.setText("" + xMax);
        yAxisMinInput.setText("" + yMin);
        yAxisMaxInput.setText("" + yMax);
        setAxes();
        fractalPanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * when a key that does something in our program is pressed then here we send them to the correct place. if it is
     * 'z' being pressed then we toggle the zPressed boolean to true. if it is a moving or zooming key then we send the
     * program to the correct method
     * @param e this is the key that
     */
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

    /**
     * this is for when the 'z' key is released to turn zPressed back to false so the programme knows to no-longer show
     * live julia updates.
     * @param e the key that has been released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z){
            System.out.println("z is released");
            zPressed = false;
        }
    }

    /**
     * this is adding he information about the panels in this window
     */
    public void windowStuff(){
        zPressed = false;
        fractalPanel = new FractalPanel();
        fractalPanel.setBackground(Color.WHITE); // may want to make this black/grey at a later point, but try things out
        fractalPanel.addMouseListener((MouseListener) this);
        fractalPanel.addMouseMotionListener(this);
        fractalPanel.setFocusable(true);
        fractalPanel.requestFocus();
        fractalPanel.addKeyListener(this);
        jw = new JuliaWindow();
        zoomPanel = new ZoomPanel();
        zoomPanel.setOpaque(false);
    }

    /**
     * this is setting the axes of the complex and real numbers
     */
    public void setAxes(){
        fractalPanel.setxMin(Double.parseDouble(xAxisMinInput.getText()));
        fractalPanel.setxMax(Double.parseDouble(xAxisMaxInput.getText()));
        fractalPanel.setyMin(Double.parseDouble(yAxisMinInput.getText()));
        fractalPanel.setyMax(Double.parseDouble(yAxisMaxInput.getText()));
    }

    /**
     * this calls the fractal repaint method after resetting the axes to there oriiginal
     */
    public void redrawFractal(){
        fractalPanel.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
        xAxisMinInput.setText("" + -2);
        xAxisMaxInput.setText("" + 2);
        yAxisMinInput.setText("" + -1.6);
        yAxisMaxInput.setText("" + 1.6);
        fractalPanel.setxMin(-2);
        fractalPanel.setxMax(2);
        fractalPanel.setyMin(-1.6);
        fractalPanel.setyMax(1.6);
        fractalPanel.repaint();
    }

    /**
     * this updatest he fractals based on the real and complex axes that the user has inputed.
     */
    public void updateFractal(){
        fractalPanel.setIterationsToComplete(Integer.parseInt(inputIterations.getText()));
        fractalPanel.setxMin(Double.parseDouble(xAxisMinInput.getText()));
        fractalPanel.setxMax(Double.parseDouble(xAxisMaxInput.getText()));
        fractalPanel.setyMin(Double.parseDouble(yAxisMinInput.getText()));
        fractalPanel.setyMax(Double.parseDouble(yAxisMaxInput.getText()));
        jw.setMandelbrotx(Double.parseDouble(realInput.getText()));
        jw.setMandelbrotY(Double.parseDouble(imaginaryInput.getText()));
        fractalPanel.repaint();
        jw.repaint();
    }

    /**
     * this method simply adds all the elemetns we use in the main window to the window so they are visible to the user.
     */
    public void elementsToAdd(){
        container.add(zoomPanel);
        container.add(fractalPanel);
        container.add(mandelbrot);
        container.add(burningShip);
        container.add(z4);
        container.add(BOP);
        container.add(randomMultibrot);
        container.add(BSV);
        container.add(OTMandelbrot);
        container.add(OTburningShip);
        container.add(OTz4);
        container.add(OTBOP);
        container.add(OTrandomMultibrot);
        container.add(OTBSV);
        container.add(RSmandelbrot);
        container.add(RSburningShip);
        container.add(RSz4);
        container.add(RSBOP);
        container.add(RSrandomMultibrot);
        container.add(RSBSV);
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
        container.add(fractalPanel);
    }

    /**
     * this sets the bounds of all the elements. I decided to use a null layout so i could specify exactly where i want
     * ecerything ot go. to make sure that it does not get mucked up i set resizable to false.
     */
    public void elemetnLayout(){
        fractalPanel.setBounds(0, 0, 600, 600);
        zoomPanel.setBounds(0, 0, 600, 600);
        mandelbrot.setBounds(50, 600, 150, 25);
        burningShip.setBounds(50, 625, 150, 25);
        z4.setBounds(50, 650, 175, 25);
        BOP.setBounds(250, 600, 175, 25);
        BSV.setBounds(250, 625, 175, 25);
        randomMultibrot.setBounds(250, 650, 150, 25);
        OTMandelbrot.setBounds(0, 600, 25, 25);
        OTburningShip.setBounds(0, 625, 25, 25);
        OTz4.setBounds(0, 650, 25, 25);
        OTBOP.setBounds(200, 600, 25, 25);
        OTBSV.setBounds(200, 625, 25, 25);
        OTrandomMultibrot.setBounds(200, 650, 25, 25);
        RSmandelbrot.setBounds(25, 600, 25, 25);
        RSburningShip.setBounds(25, 625, 25, 25);
        RSz4.setBounds(25, 650, 25, 25);
        RSBOP.setBounds(225, 600, 25, 25);
        RSBSV.setBounds(225, 625, 25, 25);
        RSrandomMultibrot.setBounds(225, 650, 25, 25);
        iterationsText.setBounds(425, 600, 150, 25);
        inputIterations.setBounds(575, 600, 50, 25);
        xAxisMin.setBounds(425, 625, 50, 25);
        xAxisMinInput.setBounds(475, 625, 75, 25);
        xAxisMax.setBounds(550, 625, 50, 25);
        xAxisMaxInput.setBounds(600, 625, 75, 25);
        yAxisMin.setBounds(425, 650, 50, 25);
        yAxisMinInput.setBounds(475, 650, 75, 25);
        yAxisMax.setBounds(550, 650, 50, 25);
        yAxisMaxInput.setBounds(600, 650, 75, 25);
        CNtext.setBounds(775, 600, 200, 25);
        realLabel.setBounds(675, 625, 100, 25);
        realInput.setBounds(775, 625, 200, 25);
        imaginaryLabel.setBounds(675, 650, 100, 25);
        imaginaryInput.setBounds(775, 650, 200, 25);
        redrawMandelbrot.setBounds(505, 675, 200, 25);
        updateMandelbrot.setBounds(695, 675, 200, 25);
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
    }

    /**
     * this method creates the JradioButtons for every fractal we allow the user to check. we add a ActionListner to the
     * options so we draw the new fractal whenever it is clicked. we also wadd them to a button group so only one can be
     * selected at a time
     */
    public void fractalOptions(){
        setOption = new ButtonGroup();
        mandelbrot = new JRadioButton("Mandelbrot");
        mandelbrot.addActionListener(e -> {
            fractalPanel.setTypeToShow("fractal");
            changeFractal("Mandelbrot");
        });
        burningShip = new JRadioButton("Burning Ship");
        burningShip.addActionListener(e -> {
            fractalPanel.setTypeToShow("fractal");
            changeFractal("BurningShip");
        });
        mandelbrot.setSelected(true);
        z4 = new JRadioButton("Quadrabrot");
        z4.addActionListener(e -> {
            fractalPanel.setTypeToShow("fractal");
            changeFractal("z^4");
        });
        BOP = new JRadioButton("Bird of Prey");
        BOP.addActionListener(e -> {
            fractalPanel.setTypeToShow("fractal");
            changeFractal("BirdOFPrey");
        });
        BSV = new JRadioButton("Burning Ship Variant");
        BSV.addActionListener(e -> {
            fractalPanel.setTypeToShow("fractal");
            changeFractal("bsv");
        });
        randomMultibrot = new JRadioButton("Random Multibrot");
        randomMultibrot.addActionListener(e -> {
            fractalPanel.setTypeToShow("fractal");
            fractalPanel.setLoops();
            changeFractal("RandomMultibrot");
        });


        setOption.add(mandelbrot);
        setOption.add(burningShip);
        setOption.add(z4);
        setOption.add(BOP);
        setOption.add(BSV);
        setOption.add(randomMultibrot);

    }

    public void regionSplitOptions(){

        regionSplit = new ButtonGroup();
        RSmandelbrot = new JRadioButton("Mandelbrot");
        RSmandelbrot.addActionListener(e -> {
            fractalPanel.setTypeToShow("regionSplit");
            changeFractal("Mandelbrot");
        });
        RSburningShip = new JRadioButton("Burning Ship");
        RSburningShip.addActionListener(e -> {
            fractalPanel.setTypeToShow("regionSplit");
            changeFractal("BurningShip");
        });
        RSz4 = new JRadioButton("Quadrabrot");
        RSz4.addActionListener(e -> {
            fractalPanel.setTypeToShow("regionSplit");
            changeFractal("z^4");
        });
        RSBOP = new JRadioButton("Bird of Prey");
        RSBOP.addActionListener(e -> {
            fractalPanel.setTypeToShow("regionSplit");
            changeFractal("BirdOFPrey");
        });
        RSBSV = new JRadioButton("Burning Ship Variant");
        RSBSV.addActionListener(e -> {
            fractalPanel.setTypeToShow("regionSplit");
            changeFractal("bsv");
        });
        RSrandomMultibrot = new JRadioButton("Random Multibrot");
        RSrandomMultibrot.addActionListener(e -> {
            fractalPanel.setTypeToShow("regionSplit");
            fractalPanel.setLoops();
            changeFractal("RandomMultibrot");
        });


        regionSplit.add(RSmandelbrot);
        regionSplit.add(RSburningShip);
        regionSplit.add(RSz4);
        regionSplit.add(RSBOP);
        regionSplit.add(RSBSV);
        regionSplit.add(RSrandomMultibrot);

    }

    public void orbitTrapsOptions(){
        orbitTraps = new ButtonGroup();
        OTMandelbrot = new JRadioButton("Mandelbrot");
        OTMandelbrot.addActionListener(e -> {
            fractalPanel.setTypeToShow("orbitTrap");
            changeFractal("Mandelbrot");
        });
        OTburningShip = new JRadioButton("Burning Ship");
        OTburningShip.addActionListener(e -> {
            fractalPanel.setTypeToShow("orbitTrap");
            changeFractal("BurningShip");
        });
        OTz4 = new JRadioButton("Quadrabrot");
        OTz4.addActionListener(e -> {
            fractalPanel.setTypeToShow("orbitTrap");
            changeFractal("z^4");
        });
        OTBOP = new JRadioButton("Bird of Prey");
        OTBOP.addActionListener(e -> {
            fractalPanel.setTypeToShow("orbitTrap");
            changeFractal("BirdOFPrey");
        });
        OTBSV = new JRadioButton("Burning Ship Variant");
        OTBSV.addActionListener(e -> {
            fractalPanel.setTypeToShow("orbitTrap");
            changeFractal("bsv");
        });
        OTrandomMultibrot = new JRadioButton("Random Multibrot");
        OTrandomMultibrot.addActionListener(e -> {
            fractalPanel.setTypeToShow("orbitTrap");
            fractalPanel.setLoops();
            changeFractal("RandomMultibrot");
        });


        orbitTraps.add(OTMandelbrot);
        orbitTraps.add(OTburningShip);
        orbitTraps.add(OTz4);
        orbitTraps.add(OTBOP);
        orbitTraps.add(OTBSV);
        orbitTraps.add(OTrandomMultibrot);


        panelOption = new ButtonGroup();

        panelOption.add(OTMandelbrot);
        panelOption.add(OTburningShip);
        panelOption.add(OTz4);
        panelOption.add(OTBOP);
        panelOption.add(OTBSV);
        panelOption.add(OTrandomMultibrot);
        panelOption.add(RSmandelbrot);
        panelOption.add(RSburningShip);
        panelOption.add(RSz4);
        panelOption.add(RSBOP);
        panelOption.add(RSBSV);
        panelOption.add(RSrandomMultibrot);
        panelOption.add(mandelbrot);
        panelOption.add(burningShip);
        panelOption.add(z4);
        panelOption.add(BOP);
        panelOption.add(BSV);
        panelOption.add(randomMultibrot);
    }

    /**
     * this method creates the JradioButtons for every coloring algorithm we allow the user to use. we add a ActionListner
     * to the options so we draw the fractals in the new colors whenever it is clicked. we also wadd them to a button
     * group so only one can be selected at a time
     */
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

    /**
     * sets the new coloring algorithm and repaints the fractal
     * @param coloring the name of the coloring algorithm we want to use
     */
    public void changeColoring(String coloring){
        fractalPanel.setColoringAlgorithm(coloring);
        fractalPanel.repaint();
    }


    /**
     * this is all the information of the fractal currently being shown including the real and imaginary axes (min and
     * max) and the the amount of iterations
     */
    public void fractalInfo(){

        iterationsText =  new JLabel("Amount of iterations: ");
        inputIterations = new JTextField("" + fractalPanel.getIterationsToComplete(), 30);

        realLabel = new JLabel("real: ");
        realInput = new JTextField("0", 30);
        realInput.setText("" + fractalPanel.getFractalY());

        imaginaryLabel = new JLabel("Imaginary: ");
        imaginaryInput = new JTextField("0", 30);
        imaginaryInput.setText("" + fractalPanel.getFractalY());

        xAxisMin = new JLabel("xMin: ");
        xAxisMinInput = new JTextField("" + fractalPanel.getxMin());
        xAxisMax = new JLabel("xMax");
        xAxisMaxInput = new JTextField("" + fractalPanel.getxMax());

        yAxisMin = new JLabel("yMin: ");
        yAxisMinInput = new JTextField("" +  fractalPanel.getyMin());
        yAxisMax = new JLabel("yMax");
        yAxisMaxInput = new JTextField("" + fractalPanel.getyMax());
        CNtext = new JLabel("This Complex Number:");
    }

    /**
     * This creates the buttons 'redraw fractal' and 'update fractal' and adds an action listners to them.
     */
    public void fractalButtons(){
        redrawMandelbrot = new JButton("redraw fractal");
        redrawMandelbrot.addActionListener(e -> redrawFractal());

        updateMandelbrot = new JButton("update fractal");
        updateMandelbrot.addActionListener(e -> updateFractal());
    }

    /**
     * this method sets out all that is needed for the julia sets. we add the JComboBox that has all the saved julia sets,
     * the ability to save it with the fractal position of the julia set and and the name that the user wnts to save it
     * save it as. we also have the show julia set that allows the user to see any set that is in the JComboBox (any set
     * they have saved or that was pre-loaded.
     */
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



}
