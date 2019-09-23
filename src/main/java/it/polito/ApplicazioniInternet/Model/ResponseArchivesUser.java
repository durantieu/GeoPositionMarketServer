package it.polito.ApplicazioniInternet.Model;

public class ResponseArchivesUser {
    String nome;
    int nPositions;
    boolean available;
    int countCustomer;

    public ResponseArchivesUser() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnPositions() {
        return nPositions;
    }

    public void setnPositions(int nPositions) {
        this.nPositions = nPositions;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCountCustomer() {
        return countCustomer;
    }

    public void setCountCustomer(int countCustomer) {
        this.countCustomer = countCustomer;
    }
}
