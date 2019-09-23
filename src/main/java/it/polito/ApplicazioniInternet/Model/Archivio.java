package it.polito.ApplicazioniInternet.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "Archives")
public class Archivio {
    @Id
    String nome;
    Boolean available;
    List<String> customers;
    String owner;
    Integer countCustomer;
    Integer nPositions;

    public Integer getnPositions() {
        return nPositions;
    }

    public void setnPositions(Integer nPositions) {
        this.nPositions = nPositions;
    }

    public Integer getCountCustomer() {
        return countCustomer;
    }

    public void setCountCustomer(Integer countCustomer) {
        this.countCustomer = countCustomer;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Archivio)) {
            return false;
        }
        Archivio myArchivio = (Archivio) obj;
        if(myArchivio.getNome().equals(this.nome)){
            return true;
        }
        else{
            return false;
        }
    }

    public Archivio(String nome, Boolean available, String owner){
        this.nome = nome;
        this.available = available;
        this.owner = owner;
        customers = new ArrayList<>();
        this.countCustomer = 0;
        this.nPositions = 0;

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public void addCustomer(String newCustomer){
        this.customers.add(newCustomer);
        countCustomer = this.customers.size();
    }
    public Boolean deleteCustomer(String customerToDelete){
        if(this.customers.contains(customerToDelete)){
            this.countCustomer = this.customers.size();
            return this.customers.remove(customerToDelete);

        }
        return false;
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
        this.countCustomer = this.customers.size();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void incrementNumberOfPosition(){
        this.nPositions++;
    }



}
