//import javax.swing.*;
//import java.awt.*;
//
///**
// * Created by asherfischbaum on 13/03/2016.
// */
//public class BurningShipPanel extends JPanel{
//    int windowSiza;
//    int iterationsToComplete;
////    int iterationsCompleted;
////
////    ComplexNumbers complexNumberSet;
//
//    boolean drawFractal = true;
//
//    double xMax;
//    double yMin;
//    double xMin;
//    double yMax;
//
//    double fractalX;
//    double fractalY;
//
//    public BurningShipPanel() {
//        windowSiza = 600; // check if we can make the same
//        iterationsToComplete = 100; // check if we can make the same
//
//        //complexNumberSet = new ComplexNumbers[windowSiza][windowSiza];
//
//        xMax = 2;
//        yMin = -1.6;
//        xMin = -2;
//        yMax = 1.6;
//    }
//
//    public void paintComponent(Graphics g) {
//        System.out.println("I am here");
//        super.paintComponent(g);
//
//
//        if (getIterationsToComplete() > 10000) {
//            System.out.println("this is fucking huge");
//        }
//        for (int i = 0; i < windowSiza; i++) {
//            for (int j = 0; j < windowSiza; j++) {
//
//
//                //double[] infoArray = amountOfIterations(new ComplexNumbers(getY(i), getX(j)));
//                int totalIterations = amountOfIterations(new ComplexNumbers((getY(i)), getX(j)));
//                g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new
//                        Color(180 / (2 * totalIterations), (totalIterations * 2) % 254, 180 / (2 * totalIterations))); // change this to do the colors more simply
//
//                if (i == 343 && j == 174) {
//                    System.out.println(" real: " + getY(i) + "Complex" + getX(j));
//                }
//
////                    g.setColor(new Color(
////                            (new Random()).nextInt(255),
////                            (new Random()).nextInt(255),
////                            (new Random()).nextInt(255)
////                    ));
//
//                g.drawLine(i, j, i, j);
//            }
//        }
//        //}
//    }
//
//
////    protected ComplexNumbers amountOfIterations(ComplexNumbers complexNumberPassedIn) {
////        ComplexNumbers cNumber = new ComplexNumbers();
////        int totalIterations = 0;
////        while ((totalIterations < iterationsToComplete) && cNumber.modulusSquared() < 4) {
////            totalIterations++;
////
////            //System.out.println("foo");
////            //cNumber = cNumber.multiply(cNumber).add(complexNumberPassedIn);
////
////            cNumber.square();
////            cNumber.add(complexNumberPassedIn);
////
////            if (totalIterations % 10 == 0) {
////
////                System.out.println("real: " + complexNumberPassedIn.getReal() + " complex: " + complexNumberPassedIn.getComplex()
////                        + " Total iterations: " + totalIterations + " magnitude: " + cNumber.modulusSquared());
////            }
////        }
////
////
////
////        return cNumber;
////        //return totalIterations;
////    }
//
//
//    protected int amountOfIterations(ComplexNumbers complexNumber) {
//        ComplexNumbers cNumber = new ComplexNumbers();
//        int totalIterations = 0;
//        int info;
//        while ((totalIterations < getIterationsToComplete()) && cNumber.modulusSquared() < 4) {
//            totalIterations++;
//
//            cNumber.square();
//            cNumber.add(complexNumber);
//
//            if (totalIterations % 10 == 0) {
//
//            }
//        }
//
//
//        info= totalIterations;
//
//
//        return info;
//        //return totalIterations;
//    }
//
//
//
//    public void setIterationsToComplete(int iterationsToComplete) {
//        this.iterationsToComplete = iterationsToComplete;
//    }
//    public int getIterationsToComplete() {
//        return iterationsToComplete;
//    }
//
//    public double getY(double realY){
//        double y = (((double) realY)/(this.getHeight()));
//        y = y * (yMax - yMin);
//        y = y + yMin;
//        //setFractalY(y);
//        return y;
//        //return (3.2*realY)/windowSiza;
//    }
//
////    public double getdY(double realY){
////        double y = (((double) realY)/(600));
////        y = y * (yMax - yMin);
////        y = y + yMin;
////        return y;
////    }
//
//
//    public double getX(double realX){
//
//        double x = (realX)/(this.getWidth());
//        x = x * (xMax - xMin);
//        x = x + xMin;
//
//        return x;
//    }
//
////
////    public double getdX(double realX){
////
////        double x = (realX)/(600);
////        x = x * (xMax - xMin);
////        x = x + xMin;
////
////        return x;
////    }
//
//    public ComplexNumbers getComplexNumber(double real, double complex){
//        double realNum = 2* xMin *real/ windowSiza + xMax;
//        double complexNum = 2* yMax *complex/ windowSiza + yMin;
//
//        return new ComplexNumbers(realNum, complexNum);
//
//    }
//
//    public double getFractalX() {
//        return fractalX;
//    }
//
//    public void setFractalX(double fractalX) {
//        this.fractalX = fractalX;
//    }
//
//    public double getFractalY() {
//        return fractalY;
//    }
//
//    public void setFractalY(double fractalY) {
//        this.fractalY = fractalY;
//    }
//
//
//    public int getWindowSiza() {
//        return windowSiza;
//    }
//
//    public void setWindowSiza(int windowSiza) {
//        this.windowSiza = windowSiza;
//    }
//
//    public void setxMax(double xMax) {
//        this.xMax = xMax;
//    }
//
//    public void setyMin(double yMin) {
//        this.yMin = yMin;
//    }
//
//    public void setxMin(double xMin) {
//        this.xMin = xMin;
//    }
//
//    public void setyMax(double yMax) {
//        this.yMax = yMax;
//    }
//
//    public double getxMax() {
//        return xMax;
//    }
//
//    public double getyMin() {
//        return yMin;
//    }
//
//    public double getxMin() {
//        return xMin;
//    }
//
//    public double getyMax() {
//        return yMax;
//    }
//}
//
