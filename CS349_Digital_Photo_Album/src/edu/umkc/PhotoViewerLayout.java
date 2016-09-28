package edu.umkc;

import java.awt.*;
import java.util.ResourceBundle;

import javax.swing.*;

public class PhotoViewerLayout extends JFrame {

    static JButton nextButton;
    static JButton prevButton;
    static JTextField pictureNumberTextField;
    static ImageIcon imageIcon;
    static JLabel imageLabel;
    static JLabel descriptionText;
    static JTextArea descriptionTextArea;
    static JButton addButton;
    static JButton saveButton;
    static JButton deleteButton;
    static JLabel pictureCountLabel;
    static JLabel dateLabel;
    static JTextField dateTextField;

    public PhotoViewerLayout() {
        Container contentPane = getContentPane();


        imageLabel = new JLabel("", SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.PAGE_AXIS));

        JPanel descriptionPane = new JPanel();
        descriptionPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        descriptionText = new JLabel("Description: ");
        descriptionTextArea = new JTextArea(4,20);
        descriptionPane.add(descriptionText);
        descriptionPane.add(descriptionTextArea);
        descriptionTextArea.setText(PhotoViewerModel.getDescription());

        JPanel datePane = new JPanel();
//		datePane.setLayout(new FlowLayout(FlowLayout.LEFT));
//		datePane.setLayout(new BoxLayout(datePane, BoxLayout.LINE_AXIS));

        dateLabel = new JLabel("Date: ");
        dateLabel.setPreferredSize(new Dimension(descriptionText.getPreferredSize().width,dateLabel.getPreferredSize().height));
        dateTextField = new JTextField("mm/dd/yyyy");
        datePane.add(dateLabel);
        datePane.add(dateTextField);

        //datePane.add(Box.createHorizontalGlue());
        JPanel buttonPane = new JPanel();

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> PhotoViewerModel.delete());

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> PhotoViewerModel.save());

        addButton = new JButton("Add Photo");
        addButton.addActionListener(e -> PhotoViewerModel.add());

        buttonPane.add(deleteButton); buttonPane.add(saveButton); buttonPane.add(addButton);

        JPanel leftRightPane = new JPanel();
        leftRightPane.setLayout(new BorderLayout());
        leftRightPane.add(datePane,BorderLayout.WEST);
        leftRightPane.add(buttonPane,BorderLayout.EAST);


        JPanel southButtonPanel = new JPanel();
        pictureNumberTextField = new JTextField(PhotoViewerModel.getCurrentPhotoNumber(),4);
        pictureNumberTextField.addActionListener(e -> PhotoViewerModel.search());
        pictureCountLabel = new JLabel(" of " + PhotoViewerModel.getPhotoCount());
        prevButton = new JButton(ResourceBundle.getBundle("PhotoAlbumStrings").getString("Previous"));
        prevButton.addActionListener(e -> PhotoViewerModel.prevButton());
        prevButton.setEnabled(false);
        nextButton = new JButton(ResourceBundle.getBundle("PhotoAlbumStrings").getString("Next"));
        nextButton.addActionListener(e -> PhotoViewerModel.nextButton());
        nextButton.setEnabled(false);

        southButtonPanel.add(pictureNumberTextField);
        southButtonPanel.add(pictureCountLabel);
        southButtonPanel.add(prevButton);
        southButtonPanel.add(nextButton);
        FlowLayout flowLayout = (FlowLayout) southButtonPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);


        controlPane.add(descriptionPane);
        controlPane.add(leftRightPane);
        controlPane.add(southButtonPanel);

        contentPane.add(controlPane, BorderLayout.SOUTH); // Or PAGE_END
    }

    public static void createAndShowGUI() {
        JFrame frame = new PhotoViewerLayout();
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame frame = new PhotoViewerLayout();
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}