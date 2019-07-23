package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.PhongBanDao;
import DTO.PhongBanDTO;
import Entity.PhongBan;
import EntityBeanUtil.PhongBanBeanUtil;
import Utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;

public class PhongBanDaoImpl extends AbstractDao<String, PhongBan> implements PhongBanDao {

    @Override
    public List<PhongBanDTO> getAll() {
        List<PhongBan> phongBanList = findAll();
        List<PhongBanDTO> phongBanDTOList = new ArrayList<>();

        for (PhongBan item : phongBanList) {
            phongBanDTOList.add(PhongBanBeanUtil.entity2Dto(item));
        }

        return phongBanDTOList;
    }
}
