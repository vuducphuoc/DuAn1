package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.QuocGiaDao;
import Entity.PhongBan;
import Entity.QuocGia;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuocGiaDaoImpl extends AbstractDao<String, QuocGia> implements QuocGiaDao {

    public List<QuocGia> searchByProperty(Map<String, Object> property) {
        List<QuocGia> quocGiaList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = HibernateUtil.buildNameQuerySearch(property);

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where ( ").append(nameQuery[0]).append(" )");
            Query query1 = session.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            quocGiaList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return quocGiaList;
    }
}
