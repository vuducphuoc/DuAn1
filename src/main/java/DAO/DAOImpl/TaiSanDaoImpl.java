package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.TaiSanDao;
import DTO.PhieuBanGiaoDTO;
import DTO.TaiSanDTO;
import Entity.TaiSan;
import EntityBeanUtil.PhieuBanGiaoBeanUtil;
import EntityBeanUtil.TaiSanBeanUtil;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaiSanDaoImpl extends AbstractDao<String, TaiSan> implements TaiSanDao {

    @Override
    public TaiSanDTO getByID(String id) {
        TaiSan ts = findById(id);
        return TaiSanBeanUtil.entity2Dto(ts);
    }
}
