package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String weigth;
    private String heigth;
    private String length;
    private String volume;
    private String mass;
    private String concrete;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "catalog", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ImageCatalog> imageCatalogs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "make_id", referencedColumnName = "id")
    @JsonBackReference
    private Make make;

    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;

    public void addImage(ImageCatalog imageCatalog){
        if (!this.imageCatalogs.contains(imageCatalog)) {
            this.imageCatalogs.add(imageCatalog);
            imageCatalog.setCatalog(this);
        }
    }
    public void removeImage(ImageCatalog imageCatalog){
        if (this.imageCatalogs.contains(imageCatalog)) {
            this.imageCatalogs.remove(imageCatalog);
            imageCatalog.setCatalog(null);
        }
    }
}
