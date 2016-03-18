import java.awt.*;
import java.util.Random;

/**
 * This class is created to do the window calculations of the Julia set and work out where to put each pixel and what
 * color to set it.
 *
 * as a base we use 100 itereations, this gives a good picture without taking to long to render.
 */

public class JuliaWindow extends Canvas{

    String juliaName;

    int size;
    int iterationsToComplete;

    double xMax;
    double yMin;
    double xMin;
    double yMax;

    double mandelbrotx;
    double mandelbrotY;

    ComplexNumbers complexJuliaNumber;

    /**
     * @param x this x represents the real number int the overall complex number
     * @param y this y represents the imaginary number in the overall complex number
     */
    public JuliaWindow(double x, double y){
        size = 600;
        iterationsToComplete = 100;

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;

        mandelbrotx = x;
        mandelbrotY = y;

    }

    /**
     * @param complexNumber ths complex number used to form the julia set
     */
    public JuliaWindow(ComplexNumbers complexNumber){
        size = 600;
        iterationsToComplete = 500;

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;

        this.complexJuliaNumber = complexNumber;


    }

    /**
     * just a generic julia set
     */
    public JuliaWindow(){
        size = 600;
        iterationsToComplete = 500;

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;


    }

    /**
     * this is here in case the programme is to be expanded and extra functionality is to be added.
     * @param jName
     * @param x
     * @param y
     */
    public JuliaWindow(String jName, double x, double y){
        size = 600;
        iterationsToComplete = 500;

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;

        mandelbrotx = x;
        mandelbrotY = y;

        juliaName = jName;
    }


    /**
     * In this part of the this method works out loops through every x,y coordinate on the screen going along the
     * columns. every x,y coordinate is passed into athe amount of iterations method that returns a doble array, most
     * importatn int tha array is the second element (in position 1) as this tells us how many iterations it took before
     * this complex number took to diverge. if it never diverges it is set to black, if it does it is colored differently
     * depending on the amount of itterations it took to diverge. finaly we draw a dot by drawing a 'line' that goes
     * from itself to itself.
     * @param g
     */
    public void paint(Graphics g){
        for (int i =0; i < size; i++){
            for (int j = 0; j<size; j++){
                double[] infoArray = amountOfIterations(new ComplexNumbers(getY(i), getX(j)));
                int totalIterations = (int) infoArray[1];

                float saturation = 1f;
                float brightness = totalIterations < iterationsToComplete ? 1f : 0;
                float hue =  (totalIterations%256)/255.0f;
                Color color = Color.getHSBColor((float) hue, saturation, brightness);
                g.setColor(color);

                g.drawLine(i, j, i, j);
            }
        }
    }

    /**
     * This method works out how many iterations are needed for the complex number to diverge up to a maximum of 100
     * iterations. every itteration the complex number is squared and we add the complex number from the julia set and
     * then increment the counter
     * @param complexNumber we passs in the complex number used for this julia set
     *
     *
     * @return
     */
    protected double[] amountOfIterations(ComplexNumbers complexNumber){
//        ComplexNumbers DCN = new ComplexNumbers(mandelbrotx, mandelbrotY);
        ComplexNumbers DCN = new ComplexNumbers(getMandelbrotx(), getMandelbrotY());
        ComplexNumbers cNumber =  complexNumber;
        double totalIterations = 0;
        double[] infoArray = new double[2];
        while ((totalIterations < getIterationsToComplete()) && cNumber.modulusSquared() < 4){

            cNumber.square();
            cNumber.add(DCN);

            totalIterations++;
        }

        infoArray[0] = cNumber.modulusSquared();
        infoArray[1] = totalIterations;

        return infoArray;

    }

    /**
     * this allows the user to set the amount of iteratiosn they want to complete for each point in the julia set to
     * check if it diverges
     * @param iterationsToComplete the number of iterations each coordinate should complete
     */
    public void setIterationsToComplete(int iterationsToComplete) {
        this.iterationsToComplete = iterationsToComplete;
    }

    /**
     * @return the number of iterations each coordinate of the julia set should complete.
     */
    public int getIterationsToComplete() {
        return iterationsToComplete;
    }

    /**
     * here we are trying to plot the y coordinate on the maginary plane so we take the real y and divide it by the
     * height of the panel. then we mutiply it by the maximum*minimum of the y plane (in our  case 1.6 - (-1.6))  and
     * finaly we add ymin to it.
     * @param realY the y coordinate on the real axis
     * @return the coordinates on the imaginary Axis on the complex plane
     */
    public double getY(double realY){
        double y = (((double) realY)/(this.getHeight()));
        y = y * (yMax - yMin);
        y = y + yMin;
        return y;
    }


    /**
     * here we are trying to plot the x coordinate on the real axis so we take the real x and divide it by the
     * width of the panel. then we mutiply it by the maximum*minimum of the x plane (in our  case 2 - (-2)  and
     * finaly we add xmin to it to it.
     * @param realX the x coordinate on teh real axis
     * @return the coordinates on the real axis of the complex plane
     */
    public double getX(double realX){

        double x = (realX)/(this.getWidth());
        x = x * (xMax - xMin);
        x = x + xMin;
        return x;
    }


    /**
     * @return the current mandelbrot set
     */
    public double getMandelbrotx() {
        return mandelbrotx;
    }

    public void setMandelbrotx(double mandelbrotx) {
        this.mandelbrotx = mandelbrotx;
    }

    public double getMandelbrotY() {
        return mandelbrotY;
    }

    public void setMandelbrotY(double mandelbrotY) {
        this.mandelbrotY = mandelbrotY;
    }

    public String getJuliaName() {
        return juliaName;
    }

}












