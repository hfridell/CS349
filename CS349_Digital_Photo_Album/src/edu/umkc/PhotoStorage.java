package edu.umkc;

import javax.swing.*;
import java.io.Serializable;

public class PhotoStorage implements Serializable {

    private static final long serialVersionUID = 123L;
    private String description;
    private String name;
    private String date = null;
    private String location;
    private ImageIcon photo;

    public PhotoStorage(Photo photo){
        this.description = photo.getDescription();
        this.name = photo.getName();
        this.date = photo.getDate();
        this.location = photo.getLocation();
        this.photo = photo.getPhoto();
    }

    public String getInstanceSecret() {
        return name + date;
    }

    public String getComputedSecret() {
        return System.getProperty("user.dir");
    }
}
