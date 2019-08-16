package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.NhaSanXuatDao;
import Entity.NhaSanXuat;
import Entity.QuocGia;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhaSanXuatDaoImpl extends AbstractDao<Integer, NhaSanXuat> implements NhaSanXuatDao {

    public List<NhaSanXuat> searchByProperty(Map<String, Object> property) {
        List<NhaSanXuat> nhaSanXuatList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = HibernateUtil.buildNameQuerySearch(property);

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where ( ").append(nameQuery[0]).append(" )");
            Query query1 = session.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            nhaSanXuatList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return nhaSanXuatList;
    }

    public boolean checkNhaSanXuatExist(String name, QuocGia quocGia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        NhaSanXuat nhaSanXuat = new NhaSanXuat();

        try {
            String sql = " FROM "+getPersistenceClassName()+" model WHERE model.TENNSX :name && MAQG = :maqg";
            Query query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("maqg", quocGia.getMaqg());
            nhaSanXuat = (NhaSanXuat) query.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
       if (nhaSanXuat == null) {
           return false;
       } else {
           return true;
       }
    }
}

