package it.polito.ApplicazioniInternet.Model;

public class CarrelloRequest {
    String id; //nome dell'archivio
    boolean add;

    public CarrelloRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
