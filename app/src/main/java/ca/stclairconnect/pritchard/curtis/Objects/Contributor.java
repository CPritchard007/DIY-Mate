package ca.stclairconnect.pritchard.curtis.Objects;

public class Contributor {
    private String name;
    private String description;
    private int image;
    private String position;

    public Contributor(String name, String description, int image, String position) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.position = position;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
