package Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TAISAN")
public class TaiSan {

    @Id
    private String mats;

    @JoinColumn(name = "TENTS")
    private String tents;

    @JoinColumn(name = "MOTA")
    private String mota;

    @JoinColumn(name = "NGUYENGIA")
    private int nguyengia;

    @JoinColumn(name = "TILEKHAUHAO")
    private double tilekhauhao;

    @JoinColumn(name = "THOIGIANKHAUHAO")
    private double thoigiankhauhao;

    @JoinColumn(name = "NGAYBDSUDUNG")
    private Date ngaybdsudung;

    @JoinColumn(name = "NGUOISUDUNG")
    private String nguoisudung;

    @ManyToOne
    @JoinColumn(name = "MANSX")
    private NhaSanXuat nhaSanXuat;

    @ManyToOne
    @JoinColumn(name = "MAPL")
    private PhanLoaiTaiSan phanLoaiTaiSan;

    @ManyToOne
    @JoinColumn(name = "MAPB")
    private PhongBan phongBan;

    public String getNguoisudung() {
        return nguoisudung;
    }

    public void setNguoisudung(String nguoisudung) {
        this.nguoisudung = nguoisudung;
    }

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

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public PhanLoaiTaiSan getPhanLoaiTaiSan() {
        return phanLoaiTaiSan;
    }

    public void setPhanLoaiTaiSan(PhanLoaiTaiSan phanLoaiTaiSan) {
        this.phanLoaiTaiSan = phanLoaiTaiSan;
    }

    public Date getNgaybdsudung() {
        return ngaybdsudung;
    }

    public void setNgaybdsudung(Date ngaybdsudung) {
        this.ngaybdsudung = ngaybdsudung;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    @Override
    public String toString() {
        return tents + " - " + mats;
    }
}
