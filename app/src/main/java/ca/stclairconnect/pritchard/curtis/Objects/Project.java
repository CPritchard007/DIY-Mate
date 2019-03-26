package ca.stclairconnect.pritchard.curtis.Objects;

import java.util.ArrayList;

public class Project {
    private int id;
    private int image;
    private String name;
    private Profile user;
    private String description;
    private int[] gallery;
    private ArrayList<ListItem> listItems;

    public Project(int id, String name, Profile user, int image, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.user = user;
        this.description = description;

    }
    public Project(String name, Profile user, int image,  String description) {
        this.image = image;
        this.name = name;
        this.user = user;
        this.description = description;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public Profile getUser() {
        return user;
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
