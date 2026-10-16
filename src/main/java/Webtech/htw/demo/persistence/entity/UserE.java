package Webtech.htw.demo.persistence.entity;

import java.util.List;

public class UserE {

    private String name;
    private String email;
    private String password;
    private List<ArtikelE> GList;
    private List<ArtikelE> SList;

    public UserE(String name, String emial, String password){
        this.email = emial;
        this.name = name;
        this.password = password;
    }

    public List<ArtikelE> addGList(ArtikelE artikel){
        GList.add(artikel);
        return GList;
    }

    public
}
