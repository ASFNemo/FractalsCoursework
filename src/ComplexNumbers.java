/**
 * Created by asherfischbaum on 02/03/2016.
 */
public class ComplexNumbers {

    private double real; // the real number in the complex number. i.e the first digit
    private double complex; // the complex number, i.e the number next to the i

    // this is used when the user wants to specify the real and complex number.
    public ComplexNumbers(double real, double complex) {
        this.real = real;
        this.complex = complex;
    }

    // used when we want to initialise th complex number to 0.
    public ComplexNumbers(double real){
        this.real = real;
        this.complex = 0;
    }

    public ComplexNumbers(){
        this.real = 0;
        this.complex = 0;
    }



    // when we want to iterate so we take the real number of the output + the real number of c, then we take the complex
    // number of the output and the complex number of c to give us the next z(n) that we should be multiplying.


    // returns the real we are using

    // to add complex numbers, you addd the reals and the complex and that gives us the new complex number. That is what
    // we are doing here and returning a new complex number.
    public void add(ComplexNumbers complexNumber){
        this.real += complexNumber.getReal();
        this.complex += complexNumber.getComplex();

        //return new ComplexNumbers(addReal, addComplex);
    }

    public void square(){
        double squareReal = getReal() * getReal();
        double squareComplex = getComplex() * getComplex();

        //double compvaltimesrealval = squareReal * squareComplex;

        double compvaltimesrealval = getReal() * getComplex();

        this.real = (squareReal - squareComplex) ;
        this.complex = (2*(compvaltimesrealval));
        //return total;
    }



    /*
        this returns the magnitud of the point, this means that it returns the distance from the center point.
     */
    public double modulusSquared(){
        double modSquaredReal = getReal() * getReal();
        double modSquaredComplex = getComplex() * getComplex();
        double total = modSquaredReal + modSquaredComplex;

        return total;
    }

    public ComplexNumbers multiply(ComplexNumbers complexNumber){
        double multReal = ((getReal()*complexNumber.getReal()) - (getComplex() * complexNumber.getComplex()));
        double multComplex =  ((getReal()*complexNumber.getReal()) + (getComplex() * complexNumber.getComplex()));

        return new ComplexNumbers(multReal, multComplex);
    }

    // this is a test to see if it prints out the complex number;
    public void printComplexNumber(){
        System.out.println(getReal() + "+" + getComplex() + "i");
    }

    public String returnComplexNumber(){
        return (getReal() + "+" + getComplex() + "i");
    }



    public double getReal() {
        return real;
    }


    // gets the complex number
    public double getComplex() {
        return complex;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setComplex(double complex) {
        this.complex = complex;
    }
}
