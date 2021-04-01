package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id_parking;

    protected String address;
    protected int nbplace ;
    protected int nbplacelibre;
    protected String ville ;

    public parking(){

    }
    public parking(String address, int nbplace, int nbplacelibre, String ville) {
        this.address = address;
        this.nbplace = nbplace;
        this.nbplacelibre = nbplacelibre;
        this.ville = ville;
    }

    public int getId_parking() {
        return id_parking;
    }

    public void setId_parking(int id_parking) {
        this.id_parking = id_parking;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    public int getNbplacelibre() {
        return nbplacelibre;
    }

    public void setNbplacelibre(int nbplacelibre) {
        this.nbplacelibre = nbplacelibre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
