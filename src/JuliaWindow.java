import java.awt.*;
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

    public JuliaWindow(ComplexNumbers complexNumber){
        size = 600;
        iterationsToComplete = 500;

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;

        this.complexJuliaNumber = complexNumber;
    }

    public JuliaWindow(){
        size = 600;
        iterationsToComplete = 500;

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;

    }

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

//                g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new Color(180,
//                        (totalIterations*2)%254, 0));

                g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new
                        Color(200/(2*totalIterations +1), (totalIterations*2)%180, 180/(2*totalIterations +1)));
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
        //setFractalY(y);
        return y;
        //return (3.2*realY)/windowSiza;
    }

    public double getdY(double realY){
        double y = (((double) realY)/(600));
        y = y * (yMax - yMin);
        y = y + yMin;
        //setFractalY(y);
        return y;
        //return (3.2*realY)/windowSiza;
    }


    public double getX(double realX){

        double x = (realX)/(this.getWidth());
        x = x * (xMax - xMin);
        x = x + xMin;

        //setFractalX(x);

        return x;
        //return (4*realX)/windowSiza;
    }


    public double getdX(double realX){

        double x = (realX)/(600);
        x = x * (xMax - xMin);
        x = x + xMin;

        return x;
    }

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












