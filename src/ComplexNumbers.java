/**
 * Created by asherfischbaum on 02/03/2016.
 */
public class ComplexNumbers {

    private double real; // the real number in the complex number. i.e the first digit
    private double complex; // the complex number, i.e the number next to the i

    // this is used when the user wants to specify the real and complex number.

    /**
     * this is creates a custom complex number
     * @param real the double that represents the real number in this complex numbe
     * @param complex this represents the imaginary number in the complex number
     */
    public ComplexNumbers(double real, double complex) {
        this.real = real;
        this.complex = complex;
    }

    /**
     * this is for creating a 0 + 0I complex number used as the base equation for some fractals
     */
    public ComplexNumbers(){
        this.real = 0;
        this.complex = 0;
    }



    // when we want to iterate so we take the real number of the output + the real number of c, then we take the complex
    // number of the output and the complex number of c to give us the next z(n) that we should be multiplying.


    // returns the real we are using

    // to add complex numbers, you addd the reals and the complex and that gives us the new complex number. That is what
    // we are doing here and returning a new complex number.

    /**
     * this is for adding the complex numbers. we first add the real number to itself and the imaginary number to itself
     * @param complexNumber This is the complex number we want to add to the complex number this function is being
     *                      called on.
     */
    public void add(ComplexNumbers complexNumber){
        this.real += complexNumber.getReal();
        this.complex += complexNumber.getComplex();

        //return new ComplexNumbers(addReal, addComplex);
    }

    /**
     * this squares the complex number this method is called on. we times the real numbre by itself and we multiply the
     * imaginary number by itself. we then multiply the real number by the imaginary number. the final number is the
     * square of the real number minus the square of the imaginary number. for the imaginary value you we do two times
     * the multiple of the real and the imaginary number.
     */
    public void square(){
        double squareReal = getReal() * getReal();
        double squareComplex = getComplex() * getComplex();


        double compvaltimesrealval = getReal() * getComplex();

        this.real = (squareReal - squareComplex) ;
        this.complex = (2*(compvaltimesrealval));
    }

    /**
     * this cubes the complex number that it is being called on.
     */
    public void cube(){
        double squareReal = getReal() * getReal();
        double squareComplex = getComplex() * getComplex();

        this.real = (getReal()*(squareReal - (3*squareComplex)));
        this.complex = (getComplex() * ((3*squareReal) - squareComplex));
    }

    /**
     * this makes the complex number positive
     */
    public void makePositive(){
        this.real = Math.abs(this.real);
        this.complex = Math.abs(this.complex);
        this.complex = -this.complex; //change this as i think it is flipping the fractal everytime we zoom
    }



    /*

     */

    /**
     * @return this returns the magnitud of the point, this means that it returns the distance from the center point.
     */
    public double modulusSquared(){
        double modSquaredReal = getReal() * getReal();
        double modSquaredComplex = getComplex() * getComplex();
        double total = modSquaredReal + modSquaredComplex;

        return total;
    }

    public void divide(){

    }

    public void multiply(ComplexNumbers complexNumber){
        this.real = ((getReal()*complexNumber.getReal()) - (getComplex() * complexNumber.getComplex()));
        this.complex =  ((getReal()*complexNumber.getReal()) + (getComplex() * complexNumber.getComplex()));

        //return new ComplexNumbers(multReal, multComplex);
    }

    // this is a test to see if it prints out the complex number;




    public double getReal() {
        return real;
    }


    // gets the complex number
    public double getComplex() {
        return complex;
    }

}
