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
            // Trả ra list <Entity>
            List<PhongBan> phongBanList = findAll();

            // List theo DTO
            List<PhongBanDTO> phongBanDTOList = new ArrayList<>();
            // Chuyển từ intity sang DTO
            for (PhongBan item : phongBanList) {
                PhongBanDTO dto = PhongBanBeanUtil.entity2Dto(item);
                phongBanDTOList.add(dto);
            }

            return phongBanDTOList;
    }
}
