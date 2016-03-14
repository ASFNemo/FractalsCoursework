//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//
///**
// * Created by asherfischbaum on 06/03/2016.
// */
//public class SaveJuliaSet extends JFrame implements ActionListener{
//
//    Container container;
//
//    double xToSave;
//    double yToSave;
//
//    JLabel realLabel;
//    JLabel realInput;
//
//    JLabel imaginaryLabel;
//    JLabel imaginaryInput;
//
//    JLabel setName;
//    JTextField setNameTextField;
//
//    JButton save;
//
//    JLabel warning;
//
//    PrintStream saveSetInfo;
//
//    public SaveJuliaSet(double x, double y){
//
//        super();
//
//        xToSave = x;
//        yToSave = y;
//
//        addItem();
//        setWindowSiza(250, 200);
//        setResizable(false);
//
//    }
//
//    public void addItem(){
//        container = getContentPane();
//        container.setLayout(null);
//
//        realLabel = new JLabel("X Position: ");
//        realInput = new JLabel("" + xToSave);
//        imaginaryLabel = new JLabel("Y Positon: ");
//        imaginaryInput = new JLabel("" + yToSave);
//
//        setName = new JLabel("Name: ");
//        setNameTextField = new JTextField();
//
//        save = new JButton("Save");
//        save.addActionListener(this);
//
//        warning = new JLabel();
//        warning.setForeground(Color.red);
//
//        container.add(realLabel);
//        container.add(realInput);
//        container.add(imaginaryLabel);
//        container.add(imaginaryInput);
//        container.add(setName);
//        container.add(setNameTextField);
//        container.add(save);
//        container.add(warning);
//
//        realLabel.setBounds(10, 10, 70, 30);
//        realInput.setBounds(90, 10, 150, 30);
//        imaginaryLabel.setBounds(10, 50, 70, 30);
//        imaginaryInput.setBounds(90, 50, 150, 30);
//        setName.setBounds(10, 90, 70, 30);
//        setNameTextField.setBounds(90, 90, 150, 30);
//        save.setBounds(90, 130, 70, 30);
//        warning.setBounds(30, 155, 190, 25);
//
//
//    }
//
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        if (e.getSource() == save){
////            savePressed();
////        }
////    }
//
////    public void savePressed(){
////        String saveName = setNameTextField.getText();
////        if (!saveName.contains(":")) {
////            boolean writeOut = writeToFile(saveName);
////            if (writeOut) {
////                setVisible(false);
////                dispose();
////            }
////        } else{
////            warning.setText("please do not use a colon (:)");
////        }
////    }
////
////    public boolean writeToFile(String saveAs){
////
////
////
////        try {
////            saveSetInfo = new PrintStream(new FileOutputStream("savedJuliaSets.txt", true));
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        }
////
////        saveSetInfo.println(saveAs + ":" + xToSave + ":" + yToSave);
////
////        return true;
////    }
//}
