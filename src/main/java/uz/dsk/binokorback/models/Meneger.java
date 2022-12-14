package uz.dsk.binokorback.models;

import javax.persistence.*;

@Entity
@Table
public class Meneger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;
    private String post;
    private String email;
    private String phone;
    private String imagepath;

    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;

    public Meneger() {
    }

    public Meneger(Long id, String name, String post, String email, String phone, String imagepath, Active active) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.imagepath = imagepath;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
