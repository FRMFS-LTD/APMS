
package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Stationnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate dateEntree;
    private LocalDate dateSortie;

    @ManyToOne
    private parking parking;

    @ManyToOne
    private Vehicule vehicule;

    @ManyToOne
    private typetarif typetarif;

    public Stationnement(LocalDate dateEntree, LocalDate dateSortie, parking parking, Vehicule vehicule, typetarif typetarif) {
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.parking = parking;
        this.vehicule = vehicule;
        this.typetarif = typetarif;
    }

    public Stationnement() {
        
    }

    public int getId() {return id; }

    public LocalDate getDateEntree() {return dateEntree;}

    public LocalDate getDateSortie() {return dateSortie;}

    public parking getParking() {return parking;}

    public Vehicule getVehicule() {return vehicule;}

    public typetarif getTypetarif() {return typetarif;}

    public void setDateEntree(LocalDate dateEntree) {this.dateEntree = dateEntree;}

    public void setDateSortie(LocalDate dateSortie) {this.dateSortie = dateSortie; }

    public void setParking(model.parking parking) {this.parking = parking;}

    public void setVehicule(Vehicule vehicule) {this.vehicule = vehicule;}

    public void setTypetarif(model.typetarif typetarif) {this.typetarif = typetarif; }

    @Override
    public String toString() {
        return "Stationnement{" +
                "id=" + id +
                ", dateEntree=" + dateEntree +
                ", dateSortie=" + dateSortie +
                ", parking=" + parking +
                ", vehicule=" + vehicule +
                ", typetarif=" + typetarif +
                '}';
    }
}
