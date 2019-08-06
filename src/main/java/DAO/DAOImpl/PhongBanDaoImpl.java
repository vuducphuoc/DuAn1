package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.PhongBanDao;
import DTO.PhongBanDTO;
import Entity.NhanVien;
import Entity.PhongBan;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            sql.append("PhongBan WHERE MAPB LIKE :str ORDER BY MANV DESC");
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
}
