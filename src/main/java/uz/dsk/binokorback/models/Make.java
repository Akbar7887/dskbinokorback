package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Make {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "make",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Catalog> catalogs = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;


    private String imagepath;

    public List<Catalog> getCatalogs() {
        catalogs.removeIf(catalog -> catalog.getActive() == Active.NOACTIVE);
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public void addCatalog(Catalog catalog){
        if(!this.catalogs.contains(catalog)){
            this.catalogs.add(catalog);
            catalog.setMake(this);
        }
    }

    public void removeCatalog(Catalog catalog){
        if(this.catalogs.contains(catalog)){
            this.catalogs.remove(catalog);
            catalog.setMake(null);
        }
    }
}
