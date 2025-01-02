package models;

public class Studio {
    private Integer id;
    private Integer capacity;
    private String studioName;

    public Studio(Integer id, Integer capacity, String studioName) {
        this.id = id;
        this.capacity = capacity;
        this.studioName = studioName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getStudioName() {
        return studioName;
    }
}
