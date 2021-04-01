
package model;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id_client;

    private String nom;

    private String prenom;
    @NotNull @Column(unique = true, nullable = false)
    private String cin;


    public client() {

    }

    public client( String nom, String prenom, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;

    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +

                '}';
    }
}
