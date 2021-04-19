/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import com.sun.istack.NotNull;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Stationnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Date dateEntree;
    private Date dateSortie;

    @ManyToOne
    private parking parking;

    @ManyToOne
    private Vehicule vehicule;

    @ManyToOne
    private typetarif typetarif;

    public Stationnement(Date dateEntree, Date dateSortie, parking parking, Vehicule vehicule, typetarif typetarif) {
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.parking = parking;
        this.vehicule = vehicule;
        this.typetarif = typetarif;
    }

    public Stationnement() {
        
    }

    public int getId() {return id; }

    public Date getDateEntree() {return dateEntree;}

    public Date getDateSortie() {return dateSortie;}

    public parking getParking() {return parking;}

    public Vehicule getVehicule() {return vehicule;}

    public typetarif getTypetarif() {return typetarif;}

    public void setDateEntree(Date dateEntree) {this.dateEntree = dateEntree;}

    public void setDateSortie(Date dateSortie) {this.dateSortie = dateSortie; }

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
