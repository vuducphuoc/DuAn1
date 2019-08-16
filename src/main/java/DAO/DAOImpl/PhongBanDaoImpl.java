package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.PhongBanDao;
import Entity.NhanVien;
import Entity.PhongBan;
import Utils.HibernateUtil;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhongBanDaoImpl extends AbstractDao<String, PhongBan> implements PhongBanDao {

    public String getLastID() {
        PhongBan phongBan = new PhongBan();
        List<PhongBan> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append("PhongBan WHERE MAPB LIKE :str ORDER BY MAPB DESC");
            Query query = session.createQuery(sql.toString());
            query.setParameter("str", "PB" + "%");
            list = query.list();
            phongBan = list.get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return phongBan.getMapb();
    }

    public List<PhongBan> searchByProperty(Map<String, Object> property) {
        List<PhongBan> phongBanList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = HibernateUtil.buildNameQuerySearch(property);

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where ( ").append(nameQuery[0]).append(" )");
            Query query1 = session.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            phongBanList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return phongBanList;
    }

    public boolean checkNhanVienExistInPhongBan(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        PhongBan result = null;
        boolean kt = false;

        try {
            String sql = " FROM "+getPersistenceClassName()+" model WHERE model.mapb = :value";
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", id);
            result = (PhongBan) query.uniqueResult();

            if (result.getNhanVienList().size() > 0) {
                kt = true;
            }

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return kt;
    }

    public boolean checkPhongBanExist(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        boolean kt = false;

        try {
            PhongBan result = findEqualUnique("TENPB", name);

            if (result != null) {
                kt = true;
            }

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return kt;
    }

}
