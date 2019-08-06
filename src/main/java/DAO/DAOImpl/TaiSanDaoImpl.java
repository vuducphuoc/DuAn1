package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.TaiSanDao;
import DTO.TaiSanDTO;
import Entity.*;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaiSanDaoImpl extends AbstractDao<String, TaiSan> implements TaiSanDao {

    public List<TaiSan> getByNhaSanXuat(NhaSanXuat nhaSanXuat) {
        List<TaiSan> taiSanList = new ArrayList<>();
        int maNSX = nhaSanXuat.getId();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where mansx = :mansx ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("mansx", maNSX);
            taiSanList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return taiSanList;
    }

    public String getLastID() {
        TaiSan taiSan = new TaiSan();

        List<TaiSan> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append("TaiSan WHERE MATS LIKE :str ORDER BY MATS DESC");
            Query query = session.createQuery(sql.toString());
            query.setParameter("str", "TS" + "%");
            list = query.list();
            taiSan = list.get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return taiSan.getMats();
    }

    public List<TaiSan> searchByProperty(Map<String, Object> property, NhaSanXuat nhaSanXuat) {
        List<TaiSan> taiSanList = new ArrayList<>();
        String mansx = String.valueOf(nhaSanXuat.getId());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = HibernateUtil.buildNameQuerySearch(property);

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where (mansx = :mansx) AND ( ").append(nameQuery[0]).append(" )");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("mansx", mansx);
            setParameterToQuery(nameQuery, query1);
            taiSanList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return taiSanList;
    }
}
