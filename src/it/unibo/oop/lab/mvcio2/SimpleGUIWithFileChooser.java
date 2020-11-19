package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame("My second java graphical user interface");

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private SimpleGUIWithFileChooser(final Controller ctrl) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * First panel
         */
        final JPanel panel1 = new JPanel(new BorderLayout());
        final JTextArea text = new JTextArea();
        final JButton save = new JButton("Save");
        panel1.add(text, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);
        /*
         * Second panel that goes on top
         */
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JTextField text2 = new JTextField();
        final JButton browse = new JButton("Browse...");
        text2.setEditable(false);
        text2.setText(ctrl.getFilePath());
        panel2.add(text2, BorderLayout.CENTER);
        panel2.add(browse, BorderLayout.LINE_END);
        // Add the second panel to the first one
        panel1.add(panel2, BorderLayout.NORTH);
        /*
         * 
         */
        frame.setContentPane(panel1);
        /*
         * Handlers
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    ctrl.save(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
                final JFileChooser fc = new JFileChooser("Choose where to save.");
                fc.setSelectedFile(ctrl.getFile());
                final int result = fc.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File newDest = fc.getSelectedFile();
                    ctrl.setFile(newDest);
                    text2.setText(newDest.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        /*
         * Size the frame considering screen res
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }
    /*
     * Display
     */
    private void display() {
        frame.setVisible(true);
    }
    /**
     * @param args ignored
     */
    public static void main(final String... args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
