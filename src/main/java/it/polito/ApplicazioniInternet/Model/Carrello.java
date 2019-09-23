package it.polito.ApplicazioniInternet.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "ShoppingCarts")
public class Carrello {
    @Id
    String owner;
    List<Archivio> carrello;
    Date lastTimeStamp;

    public Carrello() {
    }

    public Carrello(String owner, List<Archivio> carrello) {
        this.owner = owner;
        this.carrello = carrello;
        this.lastTimeStamp = new Date();
    }

    public Date getLastTimeStamp() {
        return lastTimeStamp;
    }

    public void setLastTimeStamp(Date lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Archivio> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<Archivio> carrello) {
        this.carrello = carrello;
    }

    public void addArchive(Archivio newArchivio){
        carrello.add(newArchivio);
        this.lastTimeStamp = new Date();
    }
    public void deleteArchive(Archivio toRemove){
        int i = 0;
        for (Archivio a: this.carrello) {
            if(a.equals(toRemove)){
                break;
            }
            i++;
        }
        this.carrello.remove(i);
        this.lastTimeStamp = new Date();
    }
}
