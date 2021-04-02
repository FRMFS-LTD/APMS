package Model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stationnement")
public class Stationnement {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id_stationnement;
    @NotNull Dyate dateEntree;
    @NotNull  Date dateSortie;
    @ManyToOne   @JoinColumn(name="id_parking",referencedColumnName = "id_parking", nullable=false)
    private Parking parking;
    @ManyToOne @JoinColumn(name="id_typetarrif",referencedColumnName = "id_typetarrif",nullable = false)
    private Typetarif typetarif;

}
