package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.PhieuBanGiaoDao;
import Entity.NhanVien;
import Entity.PhieuBanGiao;
import Entity.PhongBan;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhieuBanGiaoDaoImpl extends AbstractDao<Integer, PhieuBanGiao> implements PhieuBanGiaoDao {

    public List<PhieuBanGiao> getByPhongBan(PhongBan phongBan) {
        List<PhieuBanGiao> phieuBanGiaoList = new ArrayList<>();
        String mapb = phongBan.getMapb();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where mapb = :mapb ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("mapb", mapb);
            phieuBanGiaoList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return phieuBanGiaoList;
    }

    public List<PhieuBanGiao> searchByProperty(Map<String, Object> property, PhongBan phongBan) {
        List<PhieuBanGiao> phieuBanGiaoDaoList = new ArrayList<>();
        String mapb = phongBan.getMapb();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = HibernateUtil.buildNameQuerySearch(property);

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where (mapb = :mapb) AND ( ").append(nameQuery[0]).append(" )");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("mapb", mapb);
            setParameterToQuery(nameQuery, query1);
            phieuBanGiaoDaoList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return phieuBanGiaoDaoList;
    }
}
