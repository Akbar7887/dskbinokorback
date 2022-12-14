package uz.dsk.binokorback.models.jobs;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.dsk.binokorback.models.Active;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;
    private String vacancy;

    @OneToMany(mappedBy = "job",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemJob> joblist = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;


    public void addItem(ItemJob itemJob) {
        if (!this.joblist.contains(itemJob)) {
            this.joblist.add(itemJob);
            itemJob.setJob(this);
        }
    }
    public void removeItem(ItemJob itemJob) {
        if (this.joblist.contains(itemJob)) {
            this.joblist.remove(itemJob);
            itemJob.setJob(null);
        }
    }
}
