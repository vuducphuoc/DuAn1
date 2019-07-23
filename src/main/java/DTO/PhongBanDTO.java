package DTO;

import java.util.List;

public class PhongBanDTO {

    private String mapb;
    private String tenpb;
    private List<NhanVienDTO> nhanVienList;
    private List<PhieuBanGiaoDTO> phieuBanGiaoList;

    public String getMapb() {
        return mapb;
    }

    public void setMapb(String mapb) {
        this.mapb = mapb;
    }

    public String getTenpb() {
        return tenpb;
    }

    public void setTenpb(String tenpb) {
        this.tenpb = tenpb;
    }

    public List<NhanVienDTO> getNhanVienList() {
        return nhanVienList;
    }

    public void setNhanVienList(List<NhanVienDTO> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    public List<PhieuBanGiaoDTO> getPhieuBanGiaoList() {
        return phieuBanGiaoList;
    }

    public void setPhieuBanGiaoList(List<PhieuBanGiaoDTO> phieuBanGiaoList) {
        this.phieuBanGiaoList = phieuBanGiaoList;
    }
}
