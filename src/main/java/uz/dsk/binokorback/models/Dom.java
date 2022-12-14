package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "dom")
@Table(name = "dom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dom {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "kompleks_id",
            referencedColumnName = "id")
    @JsonBackReference
    private Kompleks kompleks;

    @OneToMany(mappedBy = "dom")
    @JsonManagedReference
    private Set<ImageData> imageDataList = new HashSet<>();


    public void addImageData(ImageData imageData){
        if(!imageDataList.contains(imageData)){
            imageDataList.add(imageData);
            imageData.setDom(this);
        }
    }

    public void removeImageData(ImageData imageData){
        if(imageDataList.contains(imageData)){
            imageDataList.remove(imageData);
            imageData.setDom(null);
        }
    }

}
