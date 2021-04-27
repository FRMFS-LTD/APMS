
/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

        package model;

        import com.sun.istack.NotNull;

import javax.persistence.*;


        @Entity
        public class Vehicule  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @NotNull
        @Column(unique = true, nullable = false)
        private String matriucle;





        @ManyToOne
        private client client;



        @ManyToOne
        private abonnement abonnement;


        public Vehicule() {
        }


        public Vehicule(String matriucle) {
        this.matriucle = matriucle;
        }

        public Vehicule(String matriucle, client client , abonnement abonnement) {
        this.matriucle = matriucle;

        this.client = client;

        this.abonnement = abonnement;

        }

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


        @Override
        public String toString() {
        return  matriucle; }
        }