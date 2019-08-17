package Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TAISAN")
public class TaiSan {

    @Id
    private String id;

    @JoinColumn(name = "TENTAISAN")
    private String tenTaiSan;

    @JoinColumn(name = "MOTA")
    private String moTa;

    @JoinColumn(name = "NGUYENGIA")
    private int nguyenGia;

    @JoinColumn(name = "TYLEKHAUHAO")
    private double tyLeKhauHao;

    @JoinColumn(name = "THOIGIANKHAUHAO")
    private int thoiGianKhauHao;

    @JoinColumn(name = "NAMBDSUDUNG")
    private int namBDSuDung;

    @JoinColumn(name = "TINHTRANG")
    private boolean tinhTrang;

    @ManyToOne
    @JoinColumn(name = "NHASANXUAT")
    private NhaSanXuat nhaSanXuat;

    @ManyToOne
    @JoinColumn(name = "PHANLOAI")
    private PhanLoai phanLoai;

    @ManyToOne
    @JoinColumn(name = "KHO")
    private Kho kho;

    @OneToOne (mappedBy = "taiSan")
    private PhieuBanGiaoChiTiet phieuBanGiaoChiTiet;

    public TaiSan() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTaiSan() {
        return tenTaiSan;
    }

    public void setTenTaiSan(String tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getNguyenGia() {
        return nguyenGia;
    }

    public void setNguyenGia(int nguyenGia) {
        this.nguyenGia = nguyenGia;
    }

    public double getTyLeKhauHao() {
        return tyLeKhauHao;
    }

    public void setTyLeKhauHao(double tyLeKhauHao) {
        this.tyLeKhauHao = tyLeKhauHao;
    }

    public int getThoiGianKhauHao() {
        return thoiGianKhauHao;
    }

    public void setThoiGianKhauHao(int thoiGianKhauHao) {
        this.thoiGianKhauHao = thoiGianKhauHao;
    }

    public int getNamBDSuDung() {
        return namBDSuDung;
    }

    public void setNamBDSuDung(int namBDSuDung) {
        this.namBDSuDung = namBDSuDung;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public PhanLoai getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(PhanLoai phanLoai) {
        this.phanLoai = phanLoai;
    }

    public Kho getKho() {
        return kho;
    }

    public void setKho(Kho kho) {
        this.kho = kho;
    }

    public PhieuBanGiaoChiTiet getPhieuBanGiaoChiTiet() {
        return phieuBanGiaoChiTiet;
    }

    public void setPhieuBanGiaoChiTiet(PhieuBanGiaoChiTiet phieuBanGiaoChiTiet) {
        this.phieuBanGiaoChiTiet = phieuBanGiaoChiTiet;
    }
}
