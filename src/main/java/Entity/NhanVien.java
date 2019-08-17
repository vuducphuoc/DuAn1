package Entity;

import Contant.CoreConstant;
import Utils.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {

    @Id
    private String id;

    @Column(name = "TENNHANVIEN")
    private String tenNhanVien;

    @Column(name = "GIOITINH")
    private boolean gioiTinh;

    @Column(name = "NGAYSINH")
    private Date ngaySinh;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "PHONGBAN")
    private PhongBan phongBan;

//    @OneToOne(mappedBy = "nhanVienBanGiao")
//    private PhieuBanGiao phieuBanGiao1;
//
//    @OneToOne(mappedBy = "nhanVienNhan")
//    private PhieuBanGiao phieuBanGiao2;

    public NhanVien() {
    }

    public NhanVien(String id, String tenNhanVien, boolean gioiTinh, Date ngaySinh, String email, PhongBan phongBan) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.phongBan = phongBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

}
