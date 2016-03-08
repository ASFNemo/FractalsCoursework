/**
 * Created by asherfischbaum on 08/03/2016.
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
}
