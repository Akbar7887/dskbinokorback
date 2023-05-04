package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imagepath;

    @Temporal(TemporalType.DATE)
    private Date datacreate;

    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;

    private boolean showmain = false;

    @OneToMany(mappedBy = "news",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ImageNews> imageNewsList = new ArrayList<>();

    private String videopath;

    public void addImage(ImageNews imageNews) {
        if (!imageNewsList.contains(imageNews)) {
            this.imageNewsList.add(imageNews);
            imageNews.setNews(this);
        }
    }

    public void removeImage(ImageNews imageNews) {
        if (imageNewsList.contains(imageNews)) {
            this.imageNewsList.remove(imageNews);
            imageNews.setNews(null);
        }
    }

}
