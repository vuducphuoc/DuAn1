package EntityBeanUtil;

import DTO.PhieuBanGiaoDTO;
import Entity.PhieuBanGiao;

public class PhieuBanGiaoBeanUtil {

    public static PhieuBanGiaoDTO entity2Dto(PhieuBanGiao entity) {
        PhieuBanGiaoDTO dto = new PhieuBanGiaoDTO();
        dto.setId(entity.getId());
        dto.setNgaybangiao(entity.getNgaybangiao());
        dto.setTaiSan(TaiSanBeanUtil.entity2Dto(entity.getTaiSan()));
        return  dto;
    }

    public static PhieuBanGiao dto2Entity(PhieuBanGiaoDTO dto) {
        PhieuBanGiao entity = new PhieuBanGiao();
        entity.setId(dto.getId());
        entity.setNgaybangiao(dto.getNgaybangiao());
        entity.setPhongBan(PhongBanBeanUtil.dto2Entity(dto.getPhongBan()));
        entity.setTaiSan(TaiSanBeanUtil.dto2Entity(dto.getTaiSan()));
        return  entity;
    }
}
