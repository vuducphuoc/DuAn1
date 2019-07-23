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
        List<NhanVienDTO> nhanVienDTOList = new ArrayList<>();
        for (NhanVien item : entity.getNhanVienList()) {
            nhanVienDTOList.add(NhanVienBeanUtil.entity2Dto(item));
        }
        dto.setNhanVienList(nhanVienDTOList);

        List<PhieuBanGiaoDTO> phieuBanGiaoDTOList = new ArrayList<>();
        for (PhieuBanGiao item : entity.getPhieuBanGiaoList()) {
            phieuBanGiaoDTOList.add(PhieuBanGiaoBeanUtil.entity2Dto(item));
        }
        dto.setPhieuBanGiaoList(phieuBanGiaoDTOList);
        return  dto;
    }

    public static PhongBan dto2Entity(PhongBanDTO dto) {
        PhongBan entity = new PhongBan();
        entity.setMapb(dto.getMapb());
        entity.setTenpb(dto.getTenpb());
        return  entity;
    }
}
