package ca.stclairconnect.pritchard.curtis.Objects;

public class Profile {
    private int id;
    private String name;
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
    public Profile(int id, String name, String[] tags, String description) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.description = description;
    }
    public Profile( String name, String[] tags, String description) {
        this.name = name;
        this.tags = tags;
        this.description = description;
    }
    public Profile(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.tags = null;
        this.description = description;
    }

    public Profile(String name, String description) {
        this.name = name;
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


}
