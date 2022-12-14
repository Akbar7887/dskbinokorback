package uz.dsk.binokorback.models.auth;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.criterion.Order;
import uz.dsk.binokorback.models.Orderb;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    private String phone;
    private String adress;

    private String password = "1";


    @ManyToMany(fetch = FetchType.EAGER, cascade =  {CascadeType.PERSIST})
    private Collection<Role> roles = new ArrayList<>();


    public AppUser(Long id, String name, String username, String phone, String adress, String password, Collection<Role> roles, List<Orderb> orderList) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.adress = adress;
        this.password = password;
        this.roles = roles;
    }

    public void removerole(Role newrole){
        if(this.roles.contains(newrole)){
            this.roles.remove(newrole);
        }
    }

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
