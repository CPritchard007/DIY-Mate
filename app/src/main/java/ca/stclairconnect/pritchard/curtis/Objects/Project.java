package ca.stclairconnect.pritchard.curtis.Objects;

import java.util.ArrayList;

public class Project {
    private int id;
    private String image;
    private String name;
    private String description;
    private int[] gallery;
    private ArrayList<ListItem> listItems;

    public Project(int id, String name, String image, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;

    }
    public Project(String name, String image,  String description) {
        this.image = image;
        this.name = name;
        this.description = description;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getGallery() {
        return gallery;
    }

    public void setGallery(int[] gallery) {
        this.gallery = gallery;
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
