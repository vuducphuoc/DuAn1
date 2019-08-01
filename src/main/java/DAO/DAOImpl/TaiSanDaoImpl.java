package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.TaiSanDao;
import DTO.PhieuBanGiaoDTO;
import DTO.TaiSanDTO;
import Entity.*;
import EntityBeanUtil.PhieuBanGiaoBeanUtil;
import EntityBeanUtil.TaiSanBeanUtil;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TaiSanDaoImpl extends AbstractDao<String, TaiSan> implements TaiSanDao {

    @Override
    public TaiSanDTO getByID(String id) {
        TaiSan ts = findById(id);
        return TaiSanBeanUtil.entity2Dto(ts);
    }

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
}
