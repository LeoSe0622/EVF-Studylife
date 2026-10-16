package Webtech.htw.demo.rest.model;

import java.util.List;

public class UserDTO {

    private String name;
    private String email;
    private String password;
    private List<ArtikelDTO> GList;
    private List<ArtikelDTO> SList;

    public UserDTO(String name, String email, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public List<ArtikelDTO> addGList(ArtikelDTO artikel){
        GList.add(artikel);
        return GList;
    }

    public List<ArtikelDTO> getGList(){
        return GList;
    }

    public String getName(){
        return this.name;
    }
}
