package EntityBeanUtil;

import DTO.PhieuBanGiaoDTO;
import DTO.TaiSanDTO;
import Entity.PhieuBanGiao;
import Entity.TaiSan;

import java.util.ArrayList;
import java.util.List;

public class TaiSanBeanUtil {

    public static TaiSanDTO entity2Dto(TaiSan entity) {
        TaiSanDTO dto = new TaiSanDTO();
        dto.setMats(entity.getMats());
        dto.setTents(entity.getTents());
        dto.setMota(entity.getMota());
        dto.setNguyengia(entity.getNguyengia());
        dto.setTilekhauhao(entity.getTilekhauhao());
        dto.setThoigiankhauhao(entity.getThoigiankhauhao());
        dto.setNhaSanXuat(NhaSanXuatBeanUtil.entity2Dto(entity.getNhaSanXuat()));
        dto.setPhanLoai(PhanLoaiBeanUtil.entity2Dto(entity.getPhanLoai()));
//        List<PhieuBanGiaoDTO> phieuBanGiaoDTOArrayList = new ArrayList<>();
//        for (PhieuBanGiao item : entity.getPhieuBanGiaoList()) {
//            phieuBanGiaoDTOArrayList.add(PhieuBanGiaoBeanUtil.entity2Dto(item));
//        }
        return  dto;
    }

    public static TaiSan dto2Entity(TaiSanDTO dto) {
        TaiSan entity = new TaiSan();
        entity.setMats(dto.getMats());
        entity.setTents(dto.getTents());
        entity.setMota(dto.getMota());
        entity.setNguyengia(dto.getNguyengia());
        entity.setTilekhauhao(dto.getTilekhauhao());
        entity.setThoigiankhauhao(dto.getThoigiankhauhao());
        entity.setNhaSanXuat(NhaSanXuatBeanUtil.dto2Entity(dto.getNhaSanXuat()));
        entity.setPhanLoai(PhanLoaiBeanUtil.dto2Entity(dto.getPhanLoai()));
        return  entity;
    }
}
