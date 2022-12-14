package uz.dsk.binokorback.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.dsk.binokorback.models.auth.AppUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "orderb")
@Table(name = "orderb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderb implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heigth;
    private String length;
    private String mass;
    private String name;
    private String volume;
    private String weigth;
    private String concrete;
    private Integer count;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date datacreate;



    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "make_id",
            referencedColumnName = "id")
    private Make make;

    @ManyToOne
    @JoinColumn(name = "ligthuser_id",
            referencedColumnName = "id")
    @JsonBackReference
    private LigthUser ligthuser;



}
