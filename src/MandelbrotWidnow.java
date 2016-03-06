import java.awt.*;

/**
 * Created by asherfischbaum on 02/03/2016.
 */
public class MandelbrotWidnow extends Canvas {

    int size;
    int iterationsToComplete;
//    int iterationsCompleted;
//
//    ComplexNumbers complexNumberSet;

    double xMax;
    double yMin;
    double xMin;
    double yMax;

    double fractalX;
    double fractalY;

    public MandelbrotWidnow() {
        //toDraw = false;

        size = 600; // check if we can make the same
        iterationsToComplete = 500; // check if we can make the same

        //complexNumberSet = new ComplexNumbers[size][size];

        xMax = 2;
        yMin = -1.6;
        xMin = -2;
        yMax = 1.6;

        //double xStep = (xMin * xMax) / size;
        //double yStep = (yMax * yMin) / size;

//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                //complexNumberSet[i][j] = new ComplexNumbers(2*xMin*i/size +xMax, 2*yMax*j/size +yMin);
//
//                complexNumberSet = getComplexNumber(i, j);
//            }
 //       }
    }

    public void paint(Graphics g) {
        System.out.println("I am painting");
        //if (toDraw) {

        if (getIterationsToComplete() > 10000){
            System.out.println("this is fucking huge");
        }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                     //int totalIterations = amountOfIterations(complexNumberSet[i][j]); // convert the pixel to a complex number!!!
                    //int totalIterations = amountOfIterations(new ComplexNumbers(getY(j), getX(i)));
                    //int totalIterations = amountOfIterations(complexNumberSet);
                    //g.setColor((totalIterations == iterationsToComplete) ? Color.BLACK : new Color(180, (totalIterations*2)%254, 0)); // change this to do the colors more simply
                    //g.setColor(new Color(Math.round(225/(totalIterations+1)), Math.round(225/(totalIterations+1)),
                      //      Math.round(225/(totalIterations+1))));
                    //g.fillRect(i, j, 1, 1);
                    //int color = iterationsToComplete - totalIterations;
//                    //g.setColor(new Color(color, color, color));
//                    if (amountOfIterations(new ComplexNumbers(getY(i), getX(j))).modulusSquared() <= 4){
//                        g.setColor(Color.BLACK);
//                    } else {
//                        g.setColor(Color.WHITE);
//                    }

                    double[] infoArray = amountOfIterations(new ComplexNumbers(getY(i), getX(j)));
                    int totalIterations = (int) infoArray[1];
                    g.setColor((totalIterations == getIterationsToComplete()) ? Color.BLACK : new Color(180, (totalIterations*2)%254, 0)); // change this to do the colors more simply
//                    g.setColor(new Color(Math.round(2*(totalIterations+1)/225), Math.round(2*(totalIterations+1)/225),
//                    Math.round(2*(totalIterations+1)/225)));

                    if (i == 343 && j==174){
                        System.out.println(" real: " + getY(i) + "Complex" + getX(j));
                    }

//                    g.setColor(new Color(
//                            (new Random()).nextInt(255),
//                            (new Random()).nextInt(255),
//                            (new Random()).nextInt(255)
//                    ));

                    g.drawLine(i, j, i, j);
                }
            }
        //}
    }

//    protected ComplexNumbers amountOfIterations(ComplexNumbers complexNumberPassedIn) {
//        ComplexNumbers cNumber = new ComplexNumbers();
//        int totalIterations = 0;
//        while ((totalIterations < iterationsToComplete) && cNumber.modulusSquared() < 4) {
//            totalIterations++;
//
//            //System.out.println("foo");
//            //cNumber = cNumber.multiply(cNumber).add(complexNumberPassedIn);
//
//            cNumber.square();
//            cNumber.add(complexNumberPassedIn);
//
//            if (totalIterations % 10 == 0) {
//
//                System.out.println("real: " + complexNumberPassedIn.getReal() + " complex: " + complexNumberPassedIn.getComplex()
//                        + " Total iterations: " + totalIterations + " magnitude: " + cNumber.modulusSquared());
//            }
//        }
//
//
//
//        return cNumber;
//        //return totalIterations;
//    }

    protected double[] amountOfIterations(ComplexNumbers complexNumber) {
        ComplexNumbers cNumber = new ComplexNumbers();
        double totalIterations = 0;
        double[] infoArray = new  double[2];
        while ((totalIterations < getIterationsToComplete()) && cNumber.modulusSquared() < 4) {
            totalIterations++;

            //System.out.println("foo");
            //cNumber = cNumber.multiply(cNumber).add(complexNumberPassedIn);

            cNumber.square();
            cNumber.add(complexNumber);

            if (totalIterations % 10 == 0) {

//                System.out.println("real: " + complexNumberPassedIn.getReal() + " complex: " + complexNumberPassedIn.getComplex()
//                        + " Total iterations: " + totalIterations + " magnitude: " + cNumber.modulusSquared());
            }
        }

        infoArray[0] = cNumber.modulusSquared();
        infoArray[1] = totalIterations;


        return infoArray;
        //return totalIterations;
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
        //return (3.2*realY)/size;
    }

    public double getdY(double realY){
        double y = (((double) realY)/(600));
        y = y * (yMax - yMin);
        y = y + yMin;
        //setFractalY(y);
        return y;
        //return (3.2*realY)/size;
    }


    public double getX(double realX){

        double x = (realX)/(this.getWidth());
        x = x * (xMax - xMin);
        x = x + xMin;

        //setFractalX(x);

        return x;
        //return (4*realX)/size;
    }


    public double getdX(double realX){

        double x = (realX)/(600);
        x = x * (xMax - xMin);
        x = x + xMin;

        //setFractalX(x);

        return x;
        //return (4*realX)/size;
    }

    public ComplexNumbers getComplexNumber(double real, double complex){
//        double realNum = 2*xMin*real/size +xMax;
//        double complexNum = 2*yMax*complex/size +yMin;

        double realNum = 2* xMin *real/size + xMax;
        double complexNum = 2* yMax *complex/size + yMin;

        return new ComplexNumbers(realNum, complexNum);

    }

    public double getFractalX() {
        return fractalX;
    }

    public void setFractalX(double fractalX) {
        this.fractalX = fractalX;
    }

    public double getFractalY() {
        return fractalY;
    }

    public void setFractalY(double fractalY) {
        this.fractalY = fractalY;
    }


}
