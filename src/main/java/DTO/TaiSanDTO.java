package DTO;


import java.util.List;

public class TaiSanDTO {

    private String mats;
    private String tents;
    private String mota;
    private int nguyengia;
    private double tilekhauhao;
    private double thoigiankhauhao;
    private NhaSanXuatDTO nhaSanXuat;
    private PhanLoaiDTO phanLoai;
    private List<PhieuBanGiaoDTO> phieuBanGiaoList;

    public String getMats() {
        return mats;
    }

    public void setMats(String mats) {
        this.mats = mats;
    }

    public String getTents() {
        return tents;
    }

    public void setTents(String tents) {
        this.tents = tents;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getNguyengia() {
        return nguyengia;
    }

    public void setNguyengia(int nguyengia) {
        this.nguyengia = nguyengia;
    }

    public double getTilekhauhao() {
        return tilekhauhao;
    }

    public void setTilekhauhao(double tilekhauhao) {
        this.tilekhauhao = tilekhauhao;
    }

    public double getThoigiankhauhao() {
        return thoigiankhauhao;
    }

    public void setThoigiankhauhao(double thoigiankhauhao) {
        this.thoigiankhauhao = thoigiankhauhao;
    }

    public NhaSanXuatDTO getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuatDTO nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public PhanLoaiDTO getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(PhanLoaiDTO phanLoai) {
        this.phanLoai = phanLoai;
    }

    public List<PhieuBanGiaoDTO> getPhieuBanGiaoList() {
        return phieuBanGiaoList;
    }

    public void setPhieuBanGiaoList(List<PhieuBanGiaoDTO> phieuBanGiaoList) {
        this.phieuBanGiaoList = phieuBanGiaoList;
    }
}
