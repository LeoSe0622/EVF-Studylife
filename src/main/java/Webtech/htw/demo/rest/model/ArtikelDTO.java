package Webtech.htw.demo.rest.model;

public class ArtikelDTO {
     private String name;

     public void Artikel(String name){
         this.name = name;
     }

     public String getName(){
         return this.name;
     }
}
