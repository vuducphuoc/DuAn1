package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.NhaSanXuatDao;
import DTO.NhaSanXuatDTO;
import Entity.NhaSanXuat;
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
}

