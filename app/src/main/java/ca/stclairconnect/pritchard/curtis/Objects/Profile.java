package ca.stclairconnect.pritchard.curtis.Objects;

public class Profile {
    private int id;
    private String name;
    private int image;
    private String[] tags;
    private String description;

    /**
     * Class of profiles to be linked to the Database
     * @author Curtis Pritchard
     *
     * @param id
     * @param name
     * @param tags
     * @param description
     */
    public Profile(int id, String name, int image, String[] tags, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tags = tags;
        this.description = description;
    }
    public Profile( String name, int image, String[] tags, String description) {
        this.name = name;
        this.image = image;
        this.tags = tags;
        this.description = description;
    }
    public Profile(int id, String name, int image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tags = null;
        this.description = description;
    }

    public Profile(String name, int image, String description) {

        this.name = name;
        this.image = image;
        this.tags = null;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
