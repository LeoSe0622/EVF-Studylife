package Webtech.htw.demo.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import java.util.List;

@Entity
public class UserE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // TODO Einkaufsliste und Stock-Liste (@ManyToMany)
    //private List<ArtikelE> GList;
    //private List<ArtikelE> SList;

    public UserE(){}

    public UserE(String name, String emial, String password){
        this.email = emial;
        this.name = name;
        this.password = password;
    }

    //public List<ArtikelE> addGList(ArtikelE artikel){
    //    GList.add(artikel);
    //    return GList;
    //}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
}
