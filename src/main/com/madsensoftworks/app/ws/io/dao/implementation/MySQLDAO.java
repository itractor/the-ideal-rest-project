package main.com.madsensoftworks.app.ws.io.dao.implementation;

import main.com.madsensoftworks.app.ws.io.dao.DAO;
import main.com.madsensoftworks.app.ws.io.entity.UserEntity;
import main.com.madsensoftworks.app.ws.shared.dto.UserDTO;
import main.com.madsensoftworks.app.ws.utils.HibernateUtils;
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

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public UserDTO getUserByUserName(String userName) {

        UserDTO userDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entity
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

    @Override
    public void closeConnection() {
        if(session !=null) {
            session.close();
        }
    }
}
