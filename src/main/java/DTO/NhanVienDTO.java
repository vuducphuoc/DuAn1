package DTO;

import java.util.Date;

public class NhanVienDTO {

    private String manv;
    private String tennv;
    private boolean gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String taikhoan;
    private String matkhau;
    private boolean vaitro;
    private PhongBanDTO phongBan;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String manv, String tennv, boolean gioitinh, Date ngaysinh, String diachi, String taikhoan, String matkhau, boolean vaitro, PhongBanDTO phongBan) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
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

    public PhongBanDTO getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBanDTO phongBan) {
        this.phongBan = phongBan;
    }
}
