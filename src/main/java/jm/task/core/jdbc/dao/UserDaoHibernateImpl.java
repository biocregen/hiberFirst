package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.annotations.Entity;
import org.hibernate.criterion.CriteriaQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl extends Util implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();

        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS USER " +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER) ").addEntity(User.class);
        query.executeUpdate();
        ts.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS USER");
        query.executeUpdate();
        ts.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        ts.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        ts.commit();
        session.close();
    }

    @Override
    //  @Transactional
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        List<User> users = new ArrayList<>();
        users = session.createQuery("from User", User.class).list();
        ts.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
        ts.commit();
        session.close();
    }
}
