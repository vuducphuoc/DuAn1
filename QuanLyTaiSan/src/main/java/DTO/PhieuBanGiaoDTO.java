package DTO;

public class PhieuBanGiaoDTO {

    private int id;
    private String ngaybangiao;
    private String nguoisudungs;
    private PhongBanDTO phongBan;
    private TaiSanDTO taiSan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgaybangiao() {
        return ngaybangiao;
    }

    public void setNgaybangiao(String ngaybangiao) {
        this.ngaybangiao = ngaybangiao;
    }

    public String getNguoisudungs() {
        return nguoisudungs;
    }

    public void setNguoisudungs(String nguoisudungs) {
        this.nguoisudungs = nguoisudungs;
    }

    public PhongBanDTO getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBanDTO phongBan) {
        this.phongBan = phongBan;
    }

    public TaiSanDTO getTaiSan() {
        return taiSan;
    }

    public void setTaiSan(TaiSanDTO taiSan) {
        this.taiSan = taiSan;
    }
}
