package Webtech.htw.demo.rest.model;

public class ArtikelDTO {
     private String name;
     private int id;

     public void ArtikelDTO(String name){
         this.name = name;
     }

     public String getName(){
         return this.name;
     }
}
