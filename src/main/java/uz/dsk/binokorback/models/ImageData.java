package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "image_data")
@Table(name = "image_data")
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imagepath;

    private boolean web = false;

    private boolean layout = false;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date datacreate;

    @ManyToOne
    @JoinColumn(name = "dom_id", referencedColumnName = "id")
    @JsonBackReference
    private Dom dom;


    public ImageData() {
    }

    public ImageData(Long id, String name, String imagepath, boolean web, boolean layout, Date datacreate, Dom dom) {
        this.id = id;
        this.name = name;
        this.imagepath = imagepath;
        this.web = web;
        this.layout = layout;
        this.datacreate = datacreate;
        this.dom = dom;
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

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public boolean isLayout() {
        return layout;
    }

    public void setLayout(boolean layout) {
        this.layout = layout;
    }

    public Date getDatacreate() {
        return datacreate;
    }

    public void setDatacreate(Date datacreate) {
        this.datacreate = datacreate;
    }

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }
}
