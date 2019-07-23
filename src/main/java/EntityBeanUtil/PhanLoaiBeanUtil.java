package EntityBeanUtil;

import DTO.PhanLoaiDTO;
import DTO.TaiSanDTO;
import Entity.PhanLoai;
import Entity.TaiSan;

import java.util.ArrayList;
import java.util.List;

public class PhanLoaiBeanUtil {

    public static PhanLoaiDTO entity2Dto(PhanLoai entity) {
        PhanLoaiDTO dto = new PhanLoaiDTO();
        dto.setId(entity.getId());
        dto.setTenpl(entity.getTenpl());
//        List<TaiSanDTO> taiSanDTOList = new ArrayList<>();
//        for (TaiSan item : entity.getTaiSanList()) {
//            taiSanDTOList.add(TaiSanBeanUtil.entity2Dto(item));
//        }
//        dto.setTaiSanList(taiSanDTOList);
        return  dto;
    }

    public static PhanLoai dto2Entity(PhanLoaiDTO dto) {
        PhanLoai entity = new PhanLoai();
        entity.setId(dto.getId());
        entity.setTenpl(dto.getTenpl());
        return  entity;
    }
}
