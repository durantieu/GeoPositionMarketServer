package it.polito.ApplicazioniInternet.DAO;

import it.polito.ApplicazioniInternet.Model.Position;
import it.polito.ApplicazioniInternet.Model.TimeInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class PositionDAO implements InterfaceDAO {

    @Autowired
    MongoTemplate mongoTemplate;


    private static final String COLLECTION = "Positions";


    public List<Position> getPositionInsidePoly(GeoJsonPolygon myPolygon){
        //qui oltre a restituire i soli punti presenti dovrei fare una ricerca e includere tutti i punti presenti nello stesso archivio delle posizioni
        // richieste

        List<Position> myList = null;
        try{

            Criteria criteria = new Criteria();
            org.springframework.data.mongodb.core.query.Query query =
                    new org.springframework.data.mongodb.core.query.Query(Criteria.where("geoPoint").within(myPolygon));


            myList = this.mongoTemplate.find(query, Position.class, COLLECTION);
        }catch(Exception e){
            System.out.println("======================ATTENZIONE: ERRORE NELLA QUERY GEOGRAFICA===========================");
            return null;
        }
        return myList;
    }

    public List<Position> getPositionsBelongingTimeInterval(TimeInterval timeInterval){
        List<Position> list = null;
        try {
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("timeStamp").gt(timeInterval.getTimeStampPartenza()).andOperator(Criteria.where("timeStamp").lt(timeInterval.getTimeStampArrivo())));
            list = this.mongoTemplate.find(query, Position.class, COLLECTION);
        }catch (Exception e){
            System.out.println("============================ECCEZIONE DURANTE LA QUERY=============================");
            return null;
        }
        return list;
    }

    public List<Position> getPositionsByOwner(String username) {
        List<Position> list = null;
       try {
           org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("owner").is(username));
           list = this.mongoTemplate.find(query, Position.class, COLLECTION);
       }catch (Exception e){
           System.out.println("============================ECCEZIONE DURANTE LA QUERY=============================");
           return null;
       }
        return list;
    }

    public List<Position> getPositionsByArchive(String archive){
        List<Position> list = null;
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("archivio").is(archive));
            query.with(new Sort(Sort.Direction.ASC, "timeStamp"));
            list = this.mongoTemplate.find(query, Position.class, COLLECTION);
        } catch (Exception e){
            System.out.println("============================ECCEZIONE DURANTE LA QUERY=============================");
            return null;
        }
        return list;
    }

    public List<Position> findAll(){
        List<Position> myList = null;
        try{
            myList = mongoTemplate.findAll(Position.class, COLLECTION);
        }catch (Exception e){
            System.out.println("===========================ECCEZIONE DURANTE LA QUERY ================================");
            return null;
        }
        return myList;
    }

    public void changeOwnerPosition(List<Position> positionsBought, String newOwner){

        for (Position p: positionsBought) {
            p.setOwner(newOwner);
            update(p);
        }

    }


    @Override
    public void create(Object  position) {
        mongoTemplate.insert((Position) position, COLLECTION);
    }

    @Override
    public Position readById(String id) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, Position.class, COLLECTION);
    }

    @Override //UPSERT -> se l'oggetto esiste già lo modifica, se non esiste, lo crea
    public void update(Object object) {
        mongoTemplate.save((Position) object, COLLECTION);
    }

    @Override
    public int deleteById(String id) {
        try{
            mongoTemplate.remove(id, COLLECTION);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }


    }
}
