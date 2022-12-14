package uz.dsk.binokorback.models.jobs;

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
public class ItemJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean title;

    @Column(columnDefinition = "TEXT")
    private  String description;

    @ManyToOne()
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @JsonBackReference
    private Job job;


}
