package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.TaiKhoanDao;
import Entity.NhanVien;
import Entity.PhongBan;
import Entity.TaiKhoan;
import Entity.VaiTro;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaiKhoanDaoImpl extends AbstractDao<Integer, TaiKhoan> implements TaiKhoanDao {

    public TaiKhoan checkLogin(String taikhoan, String matkhau) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        TaiKhoan taiKhoan = null;

        try {
            String sql = " FROM "+ getPersistenceClassName() +" model WHERE model.tenTaiKhoan = :taikhoan AND model.matKhau = :matkhau";
            Query query = session.createQuery(sql.toString());
            query.setParameter("taikhoan", taikhoan);
            query.setParameter("matkhau", matkhau);

            taiKhoan = (TaiKhoan) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return taiKhoan;
    }

    public List<TaiKhoan> searchByProperty(Map<String, Object> property, VaiTro vaiTro) {
        List<TaiKhoan> taiKhoanList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object[] nameQuery = HibernateUtil.buildNameQuerySearch(property);

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where ( ").append(nameQuery[0]).append(" )");

            if (vaiTro != null) {
                sql1.append(" AND id_vaitro = ");
                sql1.append(vaiTro.getId());
            }

            Query query1 = session.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            taiKhoanList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return taiKhoanList;
    }

    public List<TaiKhoan> getListByVaiTro(VaiTro vaiTro) {
        List<TaiKhoan> taiKhoanList = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName()).append(" where ID_VAITRO = :vaiTro ");
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("vaiTro", vaiTro.getId());
            taiKhoanList = query1.list();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return taiKhoanList;
    }

    public boolean checkTenTaiKhoanExist(String tentaikhoan) {
        TaiKhoan taiKhoan = findEqualUnique("taikhoan", tentaikhoan);
        if (taiKhoan == null) {
            return false;
        } else {
            return true;
        }
    }

}
