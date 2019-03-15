package ca.stclairconnect.pritchard.curtis.Objects;

public class profile {
    private int id;
    private String name;
    private String[] tags;


    /**
     * Class of profiles to be linked to the Database
     */

    public profile(int id, String name, String[] tags, String description) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.description = description;
    }

    private String description;

}
