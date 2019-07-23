package DAO.DAOImpl;

import DAO.Abstract.AbstractDao;
import DAO.DAO.NhaSanXuatDao;
import DTO.NhaSanXuatDTO;
import Entity.NhaSanXuat;
import EntityBeanUtil.NhaSanXuatBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class NhaSanXuatDaoImpl extends AbstractDao<Integer, NhaSanXuat> implements NhaSanXuatDao {


    @Override
    public List<NhaSanXuatDTO> getAll() {
        List<NhaSanXuat> nhaSanXuatList = findAll();
        List<NhaSanXuatDTO> nhaSanXuatDTOList = new ArrayList<>();

        for (NhaSanXuat item : nhaSanXuatList) {
            nhaSanXuatDTOList.add(NhaSanXuatBeanUtil.entity2Dto(item));
        }
        return nhaSanXuatDTOList;
    }
}

