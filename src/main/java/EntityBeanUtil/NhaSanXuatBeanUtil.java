package EntityBeanUtil;

import DTO.NhaSanXuatDTO;
import Entity.NhaSanXuat;

public class NhaSanXuatBeanUtil {

    public static NhaSanXuatDTO entity2Dto(NhaSanXuat entity) {
        NhaSanXuatDTO dto = new NhaSanXuatDTO();
        dto.setId(entity.getId());
        dto.setTennsx(entity.getTennsx());
        return  dto;
    }

    public static NhaSanXuat dto2Entity(NhaSanXuatDTO dto) {
        NhaSanXuat entity = new NhaSanXuat();
        entity.setId(dto.getId());
        entity.setTennsx(dto.getTennsx());
        entity.setQuocGia(QuocGiaBeanUtil.dto2Entity(dto.getQuocGia()));
        return  entity;
    }
}
