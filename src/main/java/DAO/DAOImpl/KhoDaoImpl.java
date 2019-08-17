package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.KhoDao;
import DAO.DAO.NhanVienDao;
import Entity.Kho;
import Entity.NhanVien;
import Entity.PhongBan;
import Entity.TaiSan;
import Utils.HibernateUtil;
import Utils.SingletonDaoUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KhoDaoImpl extends AbstractDao<Integer, Kho> implements KhoDao {
}
