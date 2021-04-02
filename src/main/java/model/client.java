package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity

public class client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;


    @NotNull @Column(unique = true, nullable = false)
    private String cin;

    public client(int id, String nom, String prenom, String cin) {
        this.id = id;
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
        return "client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                '}';
    }
}
