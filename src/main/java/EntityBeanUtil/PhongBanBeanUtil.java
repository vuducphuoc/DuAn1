package EntityBeanUtil;

import DTO.NhanVienDTO;
import DTO.PhieuBanGiaoDTO;
import DTO.PhongBanDTO;
import Entity.NhanVien;
import Entity.PhieuBanGiao;
import Entity.PhongBan;

import java.util.ArrayList;
import java.util.List;

public class PhongBanBeanUtil {

    public static PhongBanDTO entity2Dto(PhongBan entity) {
        PhongBanDTO dto = new PhongBanDTO();
        dto.setMapb(entity.getMapb());
        dto.setTenpb(entity.getTenpb());
        return  dto;
    }

    public static PhongBan dto2Entity(PhongBanDTO dto) {
        PhongBan entity = new PhongBan();
        entity.setMapb(dto.getMapb());
        entity.setTenpb(dto.getTenpb());
        return  entity;
    }
}
