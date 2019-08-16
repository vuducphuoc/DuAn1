package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.TaiSanDao;
import Entity.*;
import Utils.HibernateUtil;
import Utils.SingletonDaoUtil;
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

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where nhaSanXuat = :nhaSanXuat ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("nhaSanXuat", nhaSanXuat);
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

    public List<TaiSan> getByPhongBan(PhongBan phongBan) {
        List<TaiSan> taiSanList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where phongBan = :mapb ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("mapb", phongBan);
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

    public List<TaiSan> getByPhanLoai(PhanLoaiTaiSan phanLoaiTaiSan) {
        List<TaiSan> taiSanList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where phanLoaiTaiSan = :phanLoaiTaiSan ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("phanLoaiTaiSan", phanLoaiTaiSan);
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

    public List<TaiSan> searchByProperty(Map<String, Object> property, String gia, String phanloai) {
        List<TaiSan> taiSanList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = null;
        try {
            StringBuilder sql1 = new StringBuilder("from " +getPersistenceClassName()).append(" WHERE (1 = 1) ");

            if (gia != null) {
                sql1.append(" AND ( ").append(gia).append(" )");
            }

            if (phanloai != null) {
                sql1.append(" AND ( ").append(phanloai).append(" )");
            }

            if (property != null) {
                nameQuery = HibernateUtil.buildNameQuerySearch(property);
                sql1.append(" AND ( ").append(nameQuery[0]).append(" )");
            }

            Query query1 = session.createQuery(sql1.toString());
            if (property != null) {
                setParameterToQuery(nameQuery, query1);
            }
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
