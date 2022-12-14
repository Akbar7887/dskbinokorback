package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagepath;

    @ManyToOne()
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    @JsonBackReference
    private News news;

}
