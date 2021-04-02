package model;

import com.sun.istack.NotNull;
import model.*;

import javax.persistence.*;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String matriucle;


    @ManyToOne
    @JoinColumn(name = "idClient", nullable = true)
    private client client;


    @ManyToOne
    @JoinColumn(name = "idAbonnement", nullable = true)
    private abonnement abonnement;


    public Vehicule() {
    }

    public Vehicule(int id, String matriucle, model.client client, model.abonnement abonnement) {
        this.id = id;
        this.matriucle = matriucle;
        this.client = client;
        this.abonnement = abonnement;
    }


    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatriucle() {
        return matriucle;
    }

    public void setMatriucle(String matriucle) {
        this.matriucle = matriucle;
    }

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(abonnement abonnement) {
        this.abonnement = abonnement;
    }


}
