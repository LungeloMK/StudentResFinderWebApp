package model;

import java.io.Serializable;

public class Student implements Serializable {
    private Long id;
    private Long userId;
    private String campus;
    private String preferredLocation;

    public Student() {
    }

    public Student(Long id, Long userId, String campus, String preferredLocation) {
        this.id = id;
        this.userId = userId;
        this.campus = campus;
        this.preferredLocation = preferredLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }
}
