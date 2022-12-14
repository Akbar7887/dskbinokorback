package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class ImageCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String imagepath;

    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    @JsonBackReference
    private Catalog catalog;

    public ImageCatalog() {
    }

    public ImageCatalog(String description) {
        this.description = description;
    }

    public ImageCatalog(Long id, String description, String imagepath, Catalog catalog) {
        this.id = id;
        this.description = description;
        this.imagepath = imagepath;
        this.catalog = catalog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }



}
