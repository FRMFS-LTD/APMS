package model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class abonnement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int id ;
    private String intitule ;
    private  int periode ;
    private float prix ;

    public abonnement( String intitule, int periode, float prix) {
        this.intitule = intitule;
        this.periode = periode;
        this.prix = prix;

    }

    public abonnement() {

    }

    public  int getId() {
        return id;
    }


    public String getIntitule() {
        return intitule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "abonnement{" +
                "id_abonnement=" + id +
                ", intitule='" + intitule + '\'' +
                ", periode=" + periode +
                ", prix=" + prix +
                '}';
    }
}
