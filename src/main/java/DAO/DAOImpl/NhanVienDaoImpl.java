package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.NhanVienDao;
import Entity.NhanVien;
import Entity.PhongBan;
import Entity.TaiSan;
import Utils.HibernateUtil;
import Utils.SingletonDaoUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class NhanVienDaoImpl extends AbstractDao<String, NhanVien> implements NhanVienDao {

    public String getLastID() {
        NhanVien nv = new NhanVien();
        List<NhanVien> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append("NhanVien WHERE MANV LIKE :str ORDER BY MANV DESC");
            Query query = session.createQuery(sql.toString());
            query.setParameter("str", "NV" + "%");
            list = query.list();
            nv = list.get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return nv.getManv();
    }

    public List<NhanVien> getByPhongBan(PhongBan phongBan) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        String mapb = phongBan.getMapb();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where mapb = :mapb ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("mapb", mapb);
            nhanVienList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return nhanVienList;
    }

    public List<NhanVien> searchByProperty(Map<String, Object> property, PhongBan phongBan) {
    List<NhanVien> nhanVienList = new ArrayList<>();
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
        nhanVienList = query1.list();

        transaction.commit();
    } catch (HibernateException e) {
        transaction.rollback();
        throw e;
    } finally {
        session.close();
    }
    return nhanVienList;
}

    public boolean checkEmailExist(String email) {

        NhanVien nhanVien = findEqualUnique("taikhoan", email);
        if (nhanVien != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDeleteNhanVien(String id) {
        List<TaiSan> taiSanList = SingletonDaoUtil.getTaiSanDaoImpl().findAll();
        boolean check = false;
        for (TaiSan item : taiSanList) {
            String str1 = item.getNguoisudung();
            String[] temp = str1.split("\n");

            for(int i=0; i<temp.length; i++) {
                String maNV = temp[i].substring(0,7);
                if (maNV.equalsIgnoreCase(id)) check = true;
            }
        }
        return check;
    }
}
