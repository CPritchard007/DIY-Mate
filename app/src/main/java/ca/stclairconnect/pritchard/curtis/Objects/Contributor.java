package ca.stclairconnect.pritchard.curtis.Objects;

public class Contributor {
    private int id;
    private String name;
    private String description;
    private int image;
    private String position;
    private int project_id;

    public Contributor(String name, String description, int image, String position, int project_id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.position = position;
        this.project_id = project_id;
    }
    public Contributor(int id,String name, String description, int image, String position, int project_id) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.position = position;
        this.project_id = project_id;
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

    public int getProjectId() {
        return project_id;
    }

    public void setProjectId(int project_id) {
        this.project_id = project_id;
    }
}
