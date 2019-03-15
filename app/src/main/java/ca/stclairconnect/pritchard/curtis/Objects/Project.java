package ca.stclairconnect.pritchard.curtis.Objects;

import java.util.ArrayList;

public class Project {
    private int id;
    private int image;
    private String name;
    private String description;
    private int[] gallery;
    private ArrayList<ListItem> listItems;

    public Project(int id, int image, String name, String description, int[] gallery, ArrayList<ListItem> listItems) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.gallery = gallery;
        this.listItems = listItems;
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


    public class ListItem {
        private String name;
        private boolean active;
        private String[] url;


        public ListItem(String name, boolean active, String[] url) {
            this.name = name;
            this.active = active;
            this.url = url;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String[] getUrl() {
            return url;
        }

        public void setUrl(String[] url) {
            this.url = url;
        }


    }
}
