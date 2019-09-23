package it.polito.ApplicazioniInternet.Model;

public class ResponseArchivio {
    private String id;
    private int nPosizioni;

    public ResponseArchivio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getnPosizioni() {
        return nPosizioni;
    }

    public void setnPosizioni(int nPosizioni) {
        this.nPosizioni = nPosizioni;
    }
}
