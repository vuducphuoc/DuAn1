package Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PHIEUBANGIAO")
public class PhieuBanGiao {

    @Id
    private String id;

    @JoinColumn(name = "NGAYLAP")
    private Date ngayLap;

    @OneToOne
    @JoinColumn(name = "NHANVIENBANGIAO")
    private NhanVien nhanVienBanGiao;

    @OneToOne
    @JoinColumn(name = "NHANVIENNHAN")
    private NhanVien nhanVienNhan;

    @ManyToOne
    @JoinColumn(name = "KHO")
    private Kho kho;

    @OneToMany(mappedBy = "phieuBanGiao")
    private List<PhieuBanGiaoChiTiet> phieuBanGiaoChiTietList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Kho getKho() {
        return kho;
    }

    public void setKho(Kho kho) {
        this.kho = kho;
    }

    public NhanVien getNhanVienBanGiao() {
        return nhanVienBanGiao;
    }

    public void setNhanVienBanGiao(NhanVien nhanVienBanGiao) {
        this.nhanVienBanGiao = nhanVienBanGiao;
    }

    public NhanVien getNhanVienNhan() {
        return nhanVienNhan;
    }

    public void setNhanVienNhan(NhanVien nhanVienNhan) {
        this.nhanVienNhan = nhanVienNhan;
    }

    public List<PhieuBanGiaoChiTiet> getPhieuBanGiaoChiTietList() {
        return phieuBanGiaoChiTietList;
    }

    public void setPhieuBanGiaoChiTietList(List<PhieuBanGiaoChiTiet> phieuBanGiaoChiTietList) {
        this.phieuBanGiaoChiTietList = phieuBanGiaoChiTietList;
    }

}
