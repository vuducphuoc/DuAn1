package DAO.DAOImpl;

import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaoCaoThongKeDaoImpl {

    public List<Object[]> getListThongKeTheoPhanLoai() {
        List<Object[]> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("sp_thongketaisantheophanloai");
            Query query =  session.createSQLQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public List<Object[]> getListThongKeTheoPhongBan() {
        List<Object[]> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("sp_thongketaisantheophongban");
            Query query =  session.createSQLQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public List<Object[]> getListThongKeTaiSanHetKhauHao(int id, String date1, String date2) {
        List<Object[]> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {

            StringBuilder sql = new StringBuilder("sp_thongketaisanhetkhauhao :param1");
            if (date1 != null) {
                sql.append(", :param2");
            }

            if (date2 != null) {
                sql.append(", :param3");
            }

            Query query =  session.createSQLQuery(sql.toString());
            query.setParameter("param1", id);

            if (date1 != null) {
                query.setParameter("param2", date1);
            }

            if (date2 != null) {
                query.setParameter("param3", date2);
            }

            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

}
