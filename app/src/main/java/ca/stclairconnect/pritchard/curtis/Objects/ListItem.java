package ca.stclairconnect.pritchard.curtis.Objects;

public class ListItem {
    private int id;
    private String name;
    private boolean active;
    private String url;
    private int project_id;



    public ListItem(String name, boolean active) {
        this.name = name;
        this.active = active;


    }
    public ListItem(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;


    }
    public ListItem(int id, String name,String url, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;


    }
    public ListItem(String name, String url, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;

    }

    public ListItem(String name, boolean active, int project_id) {
        this.name = name;
        this.active = active;
        this.project_id = project_id;

    }
    public ListItem(int id, String name, boolean active, int project_id) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.project_id = project_id;

    }
    public ListItem(int id, String name,String url, boolean active, int project_id) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.project_id = project_id;


    }
    public ListItem(String name, String url, boolean active, int project_id) {
        this.id = id;
        this.name = name;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProjectId() {
        return project_id;
    }

    public void setProjectId(int project_id) {
        this.project_id = project_id;
    }
}