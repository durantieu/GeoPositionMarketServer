package it.polito.ApplicazioniInternet.DAO;

import it.polito.ApplicazioniInternet.Model.Archivio;
import it.polito.ApplicazioniInternet.Model.Carrello;
import it.polito.ApplicazioniInternet.Model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarrelloDAO implements InterfaceDAO{

    @Autowired
    MongoTemplate mongoTemplate;
    private static final String COLLECTION = "ShoppingCarts";

    public boolean doesTheCartExist(String user){
        try{
            org.springframework.data.mongodb.core.query.Query query = new  org.springframework.data.mongodb.core.query.Query(Criteria.where("owner").is(user));
            Carrello carrello = mongoTemplate.findOne(query, Carrello.class, COLLECTION);
            if(carrello != null) {
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }


    public void deleteByOwner(String owner){
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("owner").is(owner));
            mongoTemplate.remove(query, Carrello.class, COLLECTION);
        }catch (Exception e){
            System.out.println("===========================ECCEZIONE DURANTE LA QUERY ================================");
        }
    }

    public Carrello readByOwner(String owner){
        Carrello carrello;
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("owner").is(owner));
            return carrello = mongoTemplate.findOne(query, Carrello.class, COLLECTION);
        }catch (Exception e){
            System.out.println("===========================ECCEZIONE DURANTE LA QUERY ================================");
            return null;
        }
    }

    @Override
    public void create(Object carrello) {
        mongoTemplate.insert((Carrello) carrello, COLLECTION);
    }

    @Override
    public Carrello readById(String id) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, Carrello.class, COLLECTION);
    }

    @Override
    public void update(Object Carrello) {
        mongoTemplate.save((Carrello) Carrello, COLLECTION);
    }

    @Override
    public int deleteById(String id) {
        try{
            mongoTemplate.remove(id, COLLECTION);
            return 1;

        }catch (Exception e){

            return 0;
        }
    }
}
