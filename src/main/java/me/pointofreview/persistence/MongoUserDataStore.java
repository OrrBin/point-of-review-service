package me.pointofreview.persistence;

import me.pointofreview.core.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoUserDataStore implements UserDataStore {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean existsUsername(String username){
        return mongoTemplate.exists(Query.query(Criteria.where("username").is(username)), User.class);
    }

    @Override
    public User getUserById(String userId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("id").is(userId)), User.class);
    }

    @Override
    public User getUserByUsername(String username) {
        return mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
    }

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        var user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean createUser(User user) {
        mongoTemplate.insert(user);
        return true;
    }

    @Override
    public boolean addReport(User user, String reportType) {
        var reported = user.getReport().addReport(reportType);
        if (!reported)
            return false;
        mongoTemplate.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        mongoTemplate.save(user);
        return true;
    }

    @Override
    public void resetDatabase() {
        mongoTemplate.dropCollection(User.class);
    }

    @Override
    public boolean updateUserReputation(User user,String voterId,String sourceId,Impression impression) {
        user.updateImpressions(voterId,sourceId,impression);
        mongoTemplate.save(user);
        return true;
    }

    @Override
    public List<Notification> getNotificationsByUsername(String username) {
        var user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        if (user == null)
            return null;
        return user.getNotifications();
    }

    @Override
    public boolean addNotification(String username, Notification notification) {
        var user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        if (user == null)
            return false;
        user.getNotifications().add(0, notification);
        mongoTemplate.save(user);
        return true;
    }
}
