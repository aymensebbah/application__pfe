package com.example.applicationpfe;

public class Client extends user {
    private int clientUid;
    private String prenom;
    private String motDePasse;
    private String nom;
    private String Cin;

    public Client(int clientUid, String motDePasse, String nom) {
        this.clientUid = clientUid;
        this.motDePasse = motDePasse;
        this.nom = nom;
    }

    public int getClientUid() {
        return clientUid;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNom() {
        return nom;
    }
    public String getCin() {
        return Cin;
    }
public String  getPrenom(){ return prenom;}

}
