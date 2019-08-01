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

    @Column(name = "DIACHI")
    private String diachi;

    @Column(name = "TAIKHOAN")
    private String taikhoan;

    @Column(name = "MATKHAU")
    private String matkhau;

    @Column(name = "VAITRO")
    private boolean vaitro;

    @ManyToOne
    @JoinColumn(name = "MAPB")
    private PhongBan phongBan;

    public NhanVien () {

    }
    public NhanVien(String manv, String tennv, boolean gioitinh, Date ngaysinh, String diachi, String taikhoan, String matkhau, boolean vaitro, PhongBan phongBan) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.taikhoan = taikhoan;
        this.vaitro = vaitro;
        this.phongBan = phongBan;

        if (vaitro == CoreConstant.ADMIN) {
            this.matkhau = "123@123a";
        } else {
            this.matkhau = null;
        }
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public boolean isVaitro() {
        return vaitro;
    }

    public void setVaitro(boolean vaitro) {
        this.vaitro = vaitro;
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
