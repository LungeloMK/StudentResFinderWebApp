package model;

import java.io.Serializable;

public class Admin implements Serializable {
    private Long id;
    private Long userId;
    private String department;

    public Admin() {
    }

    public Admin(Long id, Long userId, String department) {
        this.id = id;
        this.userId = userId;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
