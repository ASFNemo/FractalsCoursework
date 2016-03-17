//import javax.swing.*;
//
///**
// * Created by asherfischbaum on 16/03/2016.
// */
//public class FractalInfoPanel {
//
//    JLabel iterationsText;
//    JTextField inputIterations;
//
//    JLabel realLabel;
//    JTextField realInput;
//
//    JLabel imaginaryLabel;
//    JTextField imaginaryInput;
//
//    JLabel xAxisMin;
//    JTextField xAxisMinInput;
//    JLabel xAxisMax;
//    JTextField xAxisMaxInput;
//
//    JLabel yAxisMin;
//    JTextField yAxisMinInput;
//    JLabel yAxisMax;
//    JTextField yAxisMaxInput;
//
//    public FractalInfoPanel(){
//        items();
//    }
//
//    public void items(){
//        iterationsText =  new JLabel("Amount of iterations: ");
//        inputIterations = new JTextField("" + mandelbrotWindow.getIterationsToComplete(), 30);
//
//        realLabel = new JLabel("real: ");
//        realInput = new JTextField("0", 30);
//        realInput.setText("" + mandelbrotWindow.getFractalY());
//
//        imaginaryLabel = new JLabel("Imaginary: ");
//        imaginaryInput = new JTextField("0", 30);
//        imaginaryInput.setText("" + mandelbrotWindow.getFractalY());
//
//        xAxisMin = new JLabel("xMin: ");
//        xAxisMinInput = new JTextField("" + mandelbrotWindow.getxMin());
//        xAxisMax = new JLabel("xMax");
//        xAxisMaxInput = new JTextField("" + mandelbrotWindow.getxMax());
//
//        yAxisMin = new JLabel("yMin: ");
//        yAxisMinInput = new JTextField("" +  mandelbrotWindow.getyMin());
//        yAxisMax = new JLabel("yMax");
//        yAxisMaxInput = new JTextField("" + mandelbrotWindow.getyMax());
//    }
//
//}
