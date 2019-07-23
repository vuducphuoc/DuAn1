package EntityBeanUtil;

import DTO.NhaSanXuatDTO;
import DTO.QuocGiaDTO;
import Entity.NhaSanXuat;
import Entity.QuocGia;

import java.util.ArrayList;
import java.util.List;

public class QuocGiaBeanUtil {

    public static QuocGiaDTO entity2Dto(QuocGia entity) {
        QuocGiaDTO dto = new QuocGiaDTO();
        dto.setMaqg(entity.getMaqg());
        dto.setTenqg(entity.getTenqg());
        List<NhaSanXuatDTO> nhaSanXuatDTOList = new ArrayList<>();
        for (NhaSanXuat item : entity.getNhaSanXuatList()) {
            nhaSanXuatDTOList.add(NhaSanXuatBeanUtil.entity2Dto(item));
        }
        dto.setNhaSanXuatList(nhaSanXuatDTOList);
        return  dto;
    }

    public static QuocGia dto2Entity(QuocGiaDTO dto) {
        QuocGia entity = new QuocGia();
        entity.setMaqg(dto.getMaqg());
        entity.setTenqg(dto.getTenqg());
        return  entity;
    }
}
