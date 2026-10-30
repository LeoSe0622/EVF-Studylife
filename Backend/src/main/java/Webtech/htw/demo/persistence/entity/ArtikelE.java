package Webtech.htw.demo.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class ArtikelE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private String name;

     public ArtikelE(){}
     public void Artikel(String name){
         this.name = name;
     }

     public String getName(){
         return this.name;
     }
}
