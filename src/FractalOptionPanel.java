////import com.sun.deploy.panel.JavaPanel;
////
////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////
/////**
//// * Created by asherfischbaum on 16/03/2016.
//// */
////public class FractalOptionPanel extends JPanel implements ActionListener{
////
////    ButtonGroup setOption;
////    JRadioButton mandelbrot;
////    JRadioButton burningShip;
////    JRadioButton z8;
////    JRadioButton BOP;
////    JRadioButton BSV;
////
////    JPanel panel;
////
////    public FractalOptionPanel(){
////        panel = new JPanel();
////        items();
////    }
////
////    public void items(){
////
////        panel.setLayout(null);
////        panel.setSize(400, 75);
////
////        setOption = new ButtonGroup();
////        mandelbrot = new JRadioButton("Mandelbrot");
////        mandelbrot.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                changeFractal("Mandelbrot");
////            }
////        });
////        burningShip = new JRadioButton("Burning Ship");
////        burningShip.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                changeFractal("BurningShip");
////            }
////        });
////        mandelbrot.setSelected(true);
////        z8 = new JRadioButton("Z^8");
////        z8.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                changeFractal("z^8");
////            }
////        });
////        BOP = new JRadioButton("Bird of Prey");
////        BOP.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                changeFractal("BirdOFPrey");
////            }
////        });
////        BSV = new JRadioButton("Burning Ship Variant");
////        BSV.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                changeFractal("bsv");
////            }
////        });
//
//
//        setOption.add(mandelbrot);
//        setOption.add(burningShip);
//        setOption.add(z8);
//        setOption.add(BOP);
//        setOption.add(BSV);
//
//        panel.add(mandelbrot);
//        panel.add(burningShip);
//        panel.add(z8);
//        panel.add(BOP);
//        panel.add(BSV);
//
//        mandelbrot.setBounds();
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
