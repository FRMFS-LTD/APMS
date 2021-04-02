package Model;

import javax.persistence.*;

@Entity
public class Abonnement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int id_abonnement ;
    private String intitule ;
    private  int periode ;
    private float prix ;

    public Abonnement(int id_abonnement, String intitule, int periode, float prix) {
        this.id_abonnement = id_abonnement;
        this.intitule = intitule;
        this.periode = periode;
        this.prix = prix;
    }

    public Abonnement() {

    }

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public String getIntitule() {
        return intitule;
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
                "id_abonnement=" + id_abonnement +
                ", intitule='" + intitule + '\'' +
                ", periode=" + periode +
                ", prix=" + prix +
                '}';
    }
}
