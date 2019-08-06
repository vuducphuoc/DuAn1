package DAO.DAOImpl;

import Contant.CoreConstant;
import DAO.Abstract.AbstractDao;
import DAO.DAO.NhanVienDao;
import DTO.NhanVienDTO;
import Entity.NhanVien;
import Entity.PhongBan;
import Frame.Login.LoginFrame;
import Utils.DialogUtils;
import Utils.HibernateUtil;
import Utils.SingletonDaoUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class NhanVienDaoImpl extends AbstractDao<String, NhanVien> implements NhanVienDao {
    public boolean checkLogin(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        LoginFrame.nvLogin = null;

        try {
            String sql = " FROM NhanVien"+" model WHERE model.taikhoan = :email AND model.matkhau = :password";
            Query query = session.createQuery(sql.toString());
            query.setParameter("email", email);
            query.setParameter("password", password);

            LoginFrame.nvLogin = (NhanVien) query.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        if (LoginFrame.nvLogin == null) {
            DialogUtils.showMessageDialog("Tài khoản hoặc mật khẩu không chính xác! Vui lòng thử lại", CoreConstant.TYPE_WARNING);
            return false;
        } else {
            if (LoginFrame.nvLogin.isVaitro() == CoreConstant.ADMIN) {
                return true;
            } else {
                DialogUtils.showMessageDialog("Xin lỗi! Tài khoản của bạn không có quyền truy cập vào phần mềm!", CoreConstant.TYPE_WARNING);
                return false;
            }
        }
    }

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
}
