/**
 * Created by asherfischbaum on 08/03/2016.
 */


/**
 * The LoadedJuliaSet class is used as a way to store the imginary number, real number that go into the julia set as
 * well as the user assigned name. This is used to put it in the array that the user can choose from using the
 * JComboBox
 */
public class LoadedJuliaSet {

    ComplexNumbers juliaSetLoaded;

    String name;

    double x;
    double y;

    public LoadedJuliaSet(ComplexNumbers cn, String setName){
        juliaSetLoaded = cn;
        name = setName;

        x = juliaSetLoaded.getReal();
        y = juliaSetLoaded.getComplex();
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
