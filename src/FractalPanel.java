import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by asherfischbaum on 02/03/2016.
 */
public class FractalPanel extends JPanel {

    int windowSiza;
    int iterationsToComplete;

    String fractalToShow;
    String typeToShow;
    String coloringAlgorithm;

    double xMax;
    double yMin;
    double xMin;
    double yMax;

    boolean draw = false;

    int loops;


    //String FractalToDraw

    double fractalX;
    double fractalY;


    public FractalPanel() {
        windowSiza = 600; // check if we can make the same
        iterationsToComplete = 100; // check if we can make the same

        fractalToShow = "Mandelbrot";
        coloringAlgorithm = "green";
        typeToShow = "orbitTrap";
        //complexNumberSet = new ComplexNumbers[windowSiza][windowSiza];

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;
    }

    public void paintComponent(Graphics g) {
        System.out.println("I am here");
        super.paintComponent(g);


            for (int i = 0; i < windowSiza; i++) {
                for (int j = 0; j < windowSiza; j++) {


//                    double[] infoArray =
                    int totalIterations = amountOfMAndelbrotIterations(new ComplexNumbers(getX(i), getY(j)));

                    switch (coloringAlgorithm){
                        case ("green"):
                            g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new
                                    Color(180 / (2 * totalIterations +1), (totalIterations * 2) % 254,
                                    180/ (2 * totalIterations +1)));
                            break;
                        case ("random"):
                            /// CHANGE NAME TO BLACK AND WHITE
//                            g.setColor(new Color(
//                                    (new Random()).nextInt(255/(iterationsToComplete / totalIterations)),
//                                    (new Random()).nextInt(255/(iterationsToComplete / totalIterations)),
//                                    (new Random()).nextInt(255/(iterationsToComplete / totalIterations))
//                            ));
//                            g.setColor((totalIterations ==getIterationsToComplete()) ? Color.BLACK :new Color((2*255)/(totalIterations + 3), (2*255)/(totalIterations + 3), (2*255)/(totalIterations + 3)));

                            g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new
                                    Color(180 / (totalIterations+1), 220/ (totalIterations+1), 180/ (5 * (iterationsToComplete/totalIterations+1))));
                            break;
                        case ("red"):
                            float saturation = 1f;
                            float brightness = totalIterations < iterationsToComplete ? 1f : 0;
                            float hue =  (totalIterations%256)/255.0f;
                            Color color = Color.getHSBColor((float) hue, saturation, brightness);
                            g.setColor(color);
                            break;
                        case ("pink"):
                            g.setColor((totalIterations ==getIterationsToComplete()) ? Color.BLACK :
                                    new Color(2*totalIterations/((totalIterations%2 + 1)), totalIterations/5,
                                            4*totalIterations/((totalIterations%4) + 4)));
                            break;
                        case ("blue"):
                            g.setColor((totalIterations ==getIterationsToComplete()) ? Color.BLACK :
                                    new Color((2*255)/(totalIterations + 3), (3*255)/(totalIterations + 4),
                                            (24*255)/(totalIterations+32)));
                            break;
                    }
  //Cool green                   // change this to do the colors more simply
//                    g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new
//                            Color(180 / (totalIterations), 220/ (totalIterations), 180/ (5 * (iterationsToComplete/totalIterations))));






//                    g.setColor((totalIterations ==getIterationsToComplete()) ? Color.BLACK :new Color((2*255)/(totalIterations + 3), (2*255)/(totalIterations + 3), (2*255)/(totalIterations + 3)));
                    g.drawLine(i, j, i, j);
                }
            }

        }



    protected int amountOfMAndelbrotIterations(ComplexNumbers complexNumber) {
        ComplexNumbers cNumber = new ComplexNumbers();
        int totalIterations = 0;
        double[] infoArray = new  double[2];
        while ((totalIterations < getIterationsToComplete()) && cNumber.modulusSquared() < 4) {
            totalIterations++;

            switch (fractalToShow){
                case "Mandelbrot":
                    cNumber.square();
                    cNumber.add(complexNumber);
                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }

                    break;
                case "BurningShip":
                    cNumber.square();
                    cNumber.add(complexNumber);
                    cNumber.makePositive();

                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }
                    break;
                case "NewtonsFractal":
                    cNumber.add(cNumber);
                    cNumber.add(new ComplexNumbers(1, 0));
                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }
                    break;
                case "BirdOFPrey":
                    cNumber.cube();
                    cNumber.add(complexNumber);
                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }
                    break;
                // Rename this as porbit trap
                case "z^4":

                    cNumber.square();
                    cNumber.square();
                    cNumber.add(complexNumber);

                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }
//                    if (Math.abs(cNumber.getReal()) < 0.01) {
//                        return totalIterations;
//
//                    }
//
//                    if (Math.abs(cNumber.getComplex()) < 0.01) {
//                        return totalIterations;
//
//                    }

//                    cNumber.square();
//                    cNumber.square();
//                    //cNumber.square();
//
//                    cNumber.add(complexNumber);
                    break;
                // Region Split - RENAME THIS!!
                case "bsv":
                    cNumber.cube();
                    cNumber.makePositive();
                    cNumber.add(complexNumber);

                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }


//                    if (Math.abs(cNumber.getReal()) < 0.01) {
//                        return (totalIterations%5)*(iterationsToComplete/5);
//
//                    }
//
//                    if (Math.abs(cNumber.getComplex()) < 0.01) {
//                        return (totalIterations%5)*(iterationsToComplete/5);
//
//                    }

                    break;
                case "RandomMultibrot":
                    for (int i = 0; i < loops; i++){
                        cNumber.square();
                    }
                    cNumber.add(complexNumber);
                    switch (typeToShow){
                        case "fractal":
                            break;
                        case "orbitTrap":
                            if (Math.abs(cNumber.getReal()) < 0.02) {
                                return totalIterations;
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.02) {
                                return totalIterations;
                            }
                            break;
                        case "regionSplit":
                            if (Math.abs(cNumber.getReal()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            if (Math.abs(cNumber.getComplex()) < 0.01) {
                                return (totalIterations%5)*(iterationsToComplete/5);
                            }
                            break;
                    }
                    break;
            }


        }



        return totalIterations;
    }



    public void setIterationsToComplete(int iterationsToComplete) {
        this.iterationsToComplete = iterationsToComplete;
    }
    public int getIterationsToComplete() {
        return iterationsToComplete;
    }

    public double getY(double realY){
        double y = (((double) realY)/(this.getHeight()));
        y = y * (yMax - yMin);
        y = y + yMin;
        return -y;
    }


    public double getX(double realX){

        double x = (realX)/(this.getWidth());
        x = x * (xMax - xMin);
        x = x + xMin;

        return x;
    }

    public ComplexNumbers getComplexNumber(double real, double complex){
        double realNum = 2* xMin *real/ windowSiza + xMax;
        double complexNum = 2* yMax *complex/ windowSiza + yMin;

        return new ComplexNumbers(realNum, complexNum);

    }

    public double getFractalY() {
        return fractalY;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getxMin() {
        return xMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setFractalToShow(String fractalToShow) {
        this.fractalToShow = fractalToShow;
    }

    public void setColoringAlgorithm(String coloringAlgorithm) {
        this.coloringAlgorithm = coloringAlgorithm;
    }

    public void setLoops(){
        Random rn = new Random();
        this.loops = rn.nextInt(8) + 1;
        System.out.println("amount of loops: " + this.loops);
    }

    public String getTypeToShow() {
        return typeToShow;
    }

    public void setTypeToShow(String typeToShow) {
        this.typeToShow = typeToShow;
    }
}
