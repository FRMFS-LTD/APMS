package model;
//cette tache fait par :Razzouk Fatima Zohra
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity

public class client {
    @Id // pour spécifier la clé primaire dans la table
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // id is auto Incremante c'est pourquoi nous utilisons une valeur générée
    private int id;
    // tous les champs ci-dessous ne peuvent pas accepter les valeurs nulles @NotNull
    @NotNull
    private String nom;

    @NotNull
    private String prenom;


    @NotNull @Column(unique = true, nullable = false)
    private String cin;

    public client( String nom, String prenom, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }

    public client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }


    @Override
    public String toString() {
        return this.nom + " " + this.prenom;
    }
}
