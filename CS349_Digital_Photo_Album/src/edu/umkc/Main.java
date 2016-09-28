package edu.umkc;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        //create and show GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PhotoViewerLayout.createAndShowGUI();
            }
        });
    }
}
