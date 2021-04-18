/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import com.sun.istack.NotNull;
import javax.persistence.*;


@NamedNativeQueries(
        {

                // we use stored procedure to let database hold the search for us
                @NamedNativeQuery(
                        name = "FindUserToReset",
                        query = "SELECT * FROM utilisateur WHERE mail = :mail and cin = :cin",
                        resultClass = utilisateur.class

                ),

                @NamedNativeQuery(
                        name = "logUser",
                        query = "SELECT * FROM utilisateur WHERE username = :username and password = :password",
                        resultClass = utilisateur.class

                )
        }
)




@Entity
public class utilisateur {

    @Id // to specify primary key in table
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // id is auto Inc that's why we use a generated value
    private int id_user;

    private String nom;

    private String prenom;

    // all the field below cannot accept null values @NotNull
    // Cin, tel, mail, and user name cannot be duplicated @Column(unique = true)

    @NotNull @Column(unique = true, nullable = false)
    private String cin;

    @NotNull @Column(unique = true)
    private String tel;

    @NotNull @Column(unique = true,nullable = false)
    private String mail;
    @NotNull @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Boolean is_admin;

    public utilisateur() {

    }

    public utilisateur( String nom, String prenom, String cin, String tel, String mail, String username, String password, Boolean is_admin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.tel = tel;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "utilistateur{" +
                "id_user=" + id_user +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", username='" + username + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }





}

