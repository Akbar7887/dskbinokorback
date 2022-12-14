package uz.dsk.binokorback.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table
public class Kompleks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typehouse;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateproject;

    private String customer;
    private String statusbuilding;

    private String mainimagepath;

    private String mainimagepathfirst;

    private String mainimagepathsecond;

    private String customerlink;

    private boolean web = false;


    @OneToMany(mappedBy = "kompleks",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JsonManagedReference
    private Set<Dom> domSet = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Active active = Active.ACTIVE;

    public void addDom(Dom dom) {
        if (!domSet.contains(dom)) {
            domSet.add(dom);
            dom.setKompleks(this);
        }
    }

    public void aremoveDom(Dom dom) {
        if (domSet.contains(dom)) {
            domSet.remove(dom);
            dom.setKompleks(null);
        }
    }

    public Kompleks() {
    }

    public Kompleks(Long id, String typehouse, String title, String description, Date dateproject, String customer, String statusbuilding, String mainimagepath, String mainimagepathfirst, String mainimagepathsecond, String customerlink, boolean web, Set<Dom> domSet, Active active) {
        this.id = id;
        this.typehouse = typehouse;
        this.title = title;
        this.description = description;
        this.dateproject = dateproject;
        this.customer = customer;
        this.statusbuilding = statusbuilding;
        this.mainimagepath = mainimagepath;
        this.mainimagepathfirst = mainimagepathfirst;
        this.mainimagepathsecond = mainimagepathsecond;
        this.customerlink = customerlink;
        this.web = web;
        this.domSet = domSet;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypehouse() {
        return typehouse;
    }

    public void setTypehouse(String typehouse) {
        this.typehouse = typehouse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateproject() {
        return dateproject;
    }

    public void setDateproject(Date dateproject) {
        this.dateproject = dateproject;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatusbuilding() {
        return statusbuilding;
    }

    public void setStatusbuilding(String statusbuilding) {
        this.statusbuilding = statusbuilding;
    }

    public String getMainimagepath() {
        return mainimagepath;
    }

    public void setMainimagepath(String mainimagepath) {
        this.mainimagepath = mainimagepath;
    }

    public String getCustomerlink() {
        return customerlink;
    }

    public void setCustomerlink(String customerlink) {
        this.customerlink = customerlink;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public Set<Dom> getDomSet() {

        Set<Dom> doms = this.domSet.stream().filter(dom -> dom.getActive().equals(Active.ACTIVE)).collect(Collectors.toSet());

        return doms;
    }

    public void setDomSet(Set<Dom> domSet) {
        this.domSet = domSet;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public String getMainimagepathfirst() {
        return mainimagepathfirst;
    }

    public void setMainimagepathfirst(String mainimagepathfirst) {
        this.mainimagepathfirst = mainimagepathfirst;
    }

    public String getMainimagepathsecond() {
        return mainimagepathsecond;
    }

    public void setMainimagepathsecond(String mainimagepathsecond) {
        this.mainimagepathsecond = mainimagepathsecond;
    }
}
