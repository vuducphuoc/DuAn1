package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.PhieuBanGiaoDao;
import DTO.PhieuBanGiaoDTO;
import Entity.PhieuBanGiao;
import EntityBeanUtil.PhieuBanGiaoBeanUtil;

public class PhieuBanGiaoDaoImpl extends AbstractDao<Integer, PhieuBanGiao> implements PhieuBanGiaoDao {

    @Override
    public PhieuBanGiaoDTO getByID(int id) {
        PhieuBanGiao phieuBanGiao = findById(id);
        return PhieuBanGiaoBeanUtil.entity2Dto(phieuBanGiao);
    }
}
