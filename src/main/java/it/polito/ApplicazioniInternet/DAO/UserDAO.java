package it.polito.ApplicazioniInternet.DAO;

import com.mongodb.client.result.DeleteResult;
import it.polito.ApplicazioniInternet.Model.Position;
import it.polito.ApplicazioniInternet.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAO implements UserInterfaceDAO {
    //accesso al database
    //Inserire il riferimento a MongoDB

    @Autowired
    private MongoTemplate mongo;


    private static final String COLLECTION = "Users";


    public User getUserDetails(String username){
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("username").is(username));
        User user = this.mongo.findOne(query, User.class, COLLECTION);
        return user;
    }



    public void addMoney(String username, Integer money){
        try{
            User user = getUserDetails(username);
            Integer wallet =user.getWallet();
            wallet += money;
            user.setWallet(wallet);
            update(user);
        }catch (Exception e){
            System.out.println("=========================Impossible to add money==========================");
        }

    }

    public void subMoney(String username, Integer money){
        try{
            User user = getUserDetails(username);
            Integer wallet =user.getWallet();
            wallet -= money;
            user.setWallet(wallet);
            update(user);
        }catch (Exception e){
            System.out.println("===============================Impossible to sub money======================");
        }

    }

    public boolean isPresent(String user){
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("username").is(user));
            User myUser =  this.mongo.findOne(query, User.class, COLLECTION);
            if(myUser != null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<User> findAll(){
        return this.mongo.findAll(User.class, COLLECTION);
    }

    public List<User> findUserByRole(String role){
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("permissions").is(role));
        List<User> myUsers =  this.mongo.find(query, User.class, COLLECTION);
        return myUsers;
    }

    public UserDAO (MongoTemplate mongoOperations){
        this.mongo=mongoOperations;
    }

    @Override
    public void create(User object) {
        mongo.insert((User) object);
    }

    @Override
    public User readById(String id) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(id));
        return this.mongo.findOne(query, User.class, COLLECTION);
    }

    @Override
    public void update(User object) {
        this.mongo.save( object, COLLECTION);
    }

    @Override
    public int deleteById(String id) {
        try{
            org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("_id").is(id));
            DeleteResult result = this.mongo.remove(query, User.class, COLLECTION);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }


    }

}
