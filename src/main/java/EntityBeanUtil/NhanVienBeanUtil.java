package EntityBeanUtil;

import DTO.NhanVienDTO;
import Entity.NhanVien;
import Utils.SingletonDaoUtil;

import java.util.HashMap;
import java.util.Map;

public class NhanVienBeanUtil {

    public static NhanVienDTO entity2Dto(NhanVien entity) {
        NhanVienDTO dto = new NhanVienDTO();
        dto.setManv(entity.getManv());
        dto.setTennv(entity.getTennv());
        dto.setGioitinh(entity.isGioitinh());
        dto.setNgaysinh(entity.getNgaysinh());
        dto.setDiachi(entity.getDiachi());
        dto.setTaikhoan(entity.getTaikhoan());
        dto.setMatkhau(entity.getMatkhau());
        dto.setVaitro(entity.isVaitro());
        return  dto;
    }

    public static NhanVien dto2Entity(NhanVienDTO dto) {
        NhanVien entity = new NhanVien();
        entity.setManv(dto.getManv());
        entity.setTennv(dto.getTennv());
        entity.setGioitinh(dto.isGioitinh());
        entity.setNgaysinh(dto.getNgaysinh());
        entity.setDiachi(dto.getDiachi());
        entity.setTaikhoan(dto.getTaikhoan());
        entity.setMatkhau(dto.getMatkhau());
        entity.setVaitro(dto.isVaitro());
        entity.setPhongBan(PhongBanBeanUtil.dto2Entity(dto.getPhongBan()));
        return  entity;
    }
}
