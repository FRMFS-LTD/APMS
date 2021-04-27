package model;


import javax.persistence.*;

@Entity
public class typetarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for generating the id
    protected  int id_typetarif;

    @Column(unique = true, nullable = false)
    protected String typetarif;

    protected float prix;

    public typetarif(){ }

    public typetarif(String typetarif, float prix){
        this.typetarif = typetarif;
        this.prix = prix;
    }

    public int getId_typetarif() {
        return id_typetarif;
    }

    public void setId_typetarif(int id_typetarif) {
        this.id_typetarif = id_typetarif;
    }

    public String getTypetarif() {
        return typetarif;
    }

    public void setTypetarif(String typetarif) {
        this.typetarif = typetarif;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return  String.valueOf(prix);
    }
}
