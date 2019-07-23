package DAO.DAOImpl;

import Contant.CoreConstant;
import DAO.Abstract.AbstractDao;
import DAO.DAO.NhanVienDao;
import DTO.NhanVienDTO;
import Entity.NhanVien;
import Entity.PhongBan;
import EntityBeanUtil.NhanVienBeanUtil;
import Frame.Login.LoginFrame;
import Utils.DialogUtils;
import Utils.HibernateUtil;
import Utils.SingletonDaoUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class NhanVienDaoImpl extends AbstractDao<String, NhanVien> implements NhanVienDao {
    @Override
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

    @Override
    public List<NhanVienDTO> getNhanVienDtoList() {
        return null;
    }

    @Override
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

    public NhanVienDTO getById(String id) {
        NhanVien nhanVien = findById(id);
        NhanVienDTO nhanVienDTO = NhanVienBeanUtil.entity2Dto(nhanVien);
        return nhanVienDTO;
    }
}
