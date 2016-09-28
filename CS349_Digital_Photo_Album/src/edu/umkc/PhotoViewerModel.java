package edu.umkc;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static edu.umkc.PhotoViewerLayout.*;

public class PhotoViewerModel {

    private static int photoCount = 0;
    private static int currentPhotoNumber = 0;
    private static ArrayList<Photo> photos = new ArrayList<>();
    private static JFileChooser fileChooser = new JFileChooser();

    public static void delete() {
        photos.remove(currentPhotoNumber - 1);
        photoCount--;
        prevButton();
    }

    public static void add() {
        int returnVal = fileChooser.showOpenDialog(addButton);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                photos.add(new Photo(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        photoCount++;
        nextButton();
    }

    public static void save() {
        photos.get(currentPhotoNumber - 1).setDate(dateTextField.getText());
        photos.get(currentPhotoNumber - 1).setDescription(descriptionTextArea.getText());
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(
                    photos.get(currentPhotoNumber - 1).getName()  + ".txt"));
            oos.writeObject(photos.get(currentPhotoNumber - 1));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void prevButton() {
        currentPhotoNumber--;
        if (currentPhotoNumber < photoCount) {
            // Show previous photo
            nextButton.setEnabled(true);
        }
        if (currentPhotoNumber <= 1) {
            prevButton.setEnabled(false);
        }
        if (photoCount <= 1){
            nextButton.setEnabled(false);
        }
        updateUI();
    }

    public static void nextButton() {
        if (currentPhotoNumber < photoCount) {
            // Show next photo
            currentPhotoNumber++;
        }
        if (currentPhotoNumber > 1){
            prevButton.setEnabled(true);
        }
        if (currentPhotoNumber == photoCount) {
            nextButton.setEnabled(false);
        }
        updateUI();
    }

    public static String getPhotoCount() {
        return Integer.toString(photoCount);
    }

    public static String getCurrentPhotoNumber() {
        return Integer.toString(currentPhotoNumber);
    }

    public static String getDescription() {
        if(photoCount <= 0){
            return "Add a Photo to View";
        }
        else {
            return photos.get(currentPhotoNumber - 1).getDescription();
        }
    }

    public static String getPhotoLocation() {
        if(photoCount <= 0){
            return "";
        }
        else {
            return photos.get(currentPhotoNumber - 1).getLocation();
        }
    }

    public static String getDate() {
        return photos.get(currentPhotoNumber - 1).getDate().toString();
    }

    private static void updateUI(){
        if (photoCount == 0) {
            imageIcon = new ImageIcon("");
            pictureCountLabel.setText(" of " + PhotoViewerModel.getPhotoCount());
            pictureNumberTextField.setText(Integer.toString(currentPhotoNumber));
            descriptionTextArea.setText(getDescription());
            dateTextField.setText("mm/dd/yyyy");
        }
        else {
            imageIcon = photos.get(currentPhotoNumber - 1).getPhoto();
            imageLabel.setIcon(imageIcon);
            pictureCountLabel.setText(" of " + PhotoViewerModel.getPhotoCount());
            pictureNumberTextField.setText(Integer.toString(currentPhotoNumber));
            if (photos.get(currentPhotoNumber - 1).getDate() == null) {
                dateTextField.setText("mm/dd/yyyy");
            } else {
                dateTextField.setText(photos.get(currentPhotoNumber - 1).getDate());
            }
            descriptionTextArea.setText(photos.get(currentPhotoNumber - 1).getDescription());
        }
    }

    public static void search() {
        int search = Integer.parseInt(pictureNumberTextField.getText());
        if (search <= photoCount && search > 0){
            currentPhotoNumber = search;
        }
        updateUI();
    }
}
