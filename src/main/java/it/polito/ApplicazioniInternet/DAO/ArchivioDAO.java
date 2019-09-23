package it.polito.ApplicazioniInternet.DAO;

import it.polito.ApplicazioniInternet.Model.Archivio;
import it.polito.ApplicazioniInternet.Model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractReactiveHealthIndicator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArchivioDAO implements InterfaceDAO {

    @Autowired
    MongoTemplate mongoTemplate;
    private static final String COLLECTION = "Archives";

    public List<Archivio> findByCustomer(String customer){
        List<Archivio> myList;
        boolean trovato = false;
        List<Archivio> listByCustomer = new ArrayList<>();
        try{
           myList = findAll();
            for (Archivio a: myList ) {
                trovato = false;
                for (String cust: a.getCustomers()) {
                    if(cust.equals(customer)){
                        trovato = true;
                        break;
                    }
                }
                if(trovato){
                    listByCustomer.add(a);
                }
            }
            return listByCustomer;
        }catch (Exception e){
            System.out.println("===========================ECCEZIONE DURANTE LA QUERY ================================");
            return null;
        }
    }

    public List<Archivio> findByOwner(String own){
        List<Archivio> myList;
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("owner").is(own));
            return myList = mongoTemplate.find(query, Archivio.class, COLLECTION);
        }catch (Exception e){
            System.out.println("===========================ECCEZIONE DURANTE LA QUERY ================================");
            return null;
        }
    }

    public List<Archivio> findAll(){
        List<Archivio> myArchs;
        try{
            myArchs = mongoTemplate.findAll(Archivio.class, COLLECTION);
        }catch (Exception e){
            System.out.println("===========================ECCEZIONE DURANTE LA QUERY ================================");
            return null;
        }
        return myArchs;
    }

    public Archivio getArchiveByName(String archive){
        Archivio myArchivio;
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("nome").is(archive));
            myArchivio = this.mongoTemplate.findOne(query, Archivio.class, COLLECTION);
            return  myArchivio;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean isArchiveAvailable(String archive){
        Archivio myArchivio;

        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("nome").is(archive));
        myArchivio = this.mongoTemplate.findOne(query, Archivio.class, COLLECTION);
        if(myArchivio != null){
            return myArchivio.getAvailable();
        }
        else{
            return false;
        }
    }

    public String getOwnerByName(String archiveName){
        Archivio name;

        org.springframework.data.mongodb.core.query.Query query= new org.springframework.data.mongodb.core.query.Query(Criteria.where("nome").is(archiveName));
        name = this.mongoTemplate.findOne(query, Archivio.class, COLLECTION);

        if(name != null){
            return name.getOwner();
        }else{
            return null;
        }
    }

    @Override
    public void create(Object archivio) {
        mongoTemplate.insert((Archivio) archivio, COLLECTION);
    }
    @Override
    public Position readById(String id) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, Position.class, COLLECTION);
    }
    @Override
    public void update(Object object) { //UPSERT
        mongoTemplate.save((Archivio) object, COLLECTION);
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
