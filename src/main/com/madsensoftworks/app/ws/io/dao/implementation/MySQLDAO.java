package com.madsensoftworks.app.ws.io.dao.implementation;

import com.madsensoftworks.app.ws.io.dao.DAO;
import com.madsensoftworks.app.ws.io.entity.UserEntity;
import com.madsensoftworks.app.ws.shared.dto.UserDTO;
import com.madsensoftworks.app.ws.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MySQLDAO implements DAO {

    Session session;

    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public UserDTO getUserByUserName(String userName) {

        UserDTO userDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("email"), userName));

        // Fetch single result
        Query<UserEntity> query = session.createQuery(criteria);
        List<UserEntity> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            UserEntity userEntity = resultList.get(0);
            userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
        }

        return userDto;
    }


    public UserDTO getUser(String id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("publicUserId"), id));

        // Fetch single result
        UserEntity userEntity = session.createQuery(criteria).getSingleResult();

        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    public UserDTO saveUser(UserDTO user) {
        UserDTO returnValue = null;
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();

        returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public void updateUserProfile(UserDTO userProfile) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userProfile, userEntity);

        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
    }
}