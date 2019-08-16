package Entity;

import Contant.CoreConstant;
import Utils.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {

    @Id
    private String manv;

    @Column(name = "TENNV")
    private String tennv;

    @Column(name = "GIOITINH")
    private boolean gioitinh;

    @Column(name = "NGAYSINH")
    private Date ngaysinh;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "MAPB")
    private PhongBan phongBan;

    public NhanVien () {

    }

    public NhanVien(String manv, String tennv, boolean gioitinh, Date ngaysinh, String email, PhongBan phongBan) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.phongBan = phongBan;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
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

    @Override
    public String toString() {
        return tennv + " - " + manv;
    }
}
