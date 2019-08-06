package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PHONGBAN")
public class PhongBan {

    @Id
    private String mapb;

    @Column(name = "TENPB")
    private String tenpb;

    @OneToMany(mappedBy = "phongBan" ,fetch = FetchType.LAZY)
    private List<NhanVien>  nhanVienList;

    @OneToMany(mappedBy = "phongBan",fetch = FetchType.LAZY)
    private List<PhieuBanGiao> phieuBanGiaoList;

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

    public List<NhanVien> getNhanVienList() {
        return nhanVienList;
    }

    public void setNhanVienList(List<NhanVien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    public List<PhieuBanGiao> getPhieuBanGiaoList() {
        return phieuBanGiaoList;
    }

    public void setPhieuBanGiaoList(List<PhieuBanGiao> phieuBanGiaoList) {
        this.phieuBanGiaoList = phieuBanGiaoList;
    }

    public PhongBan() {
    }

    public PhongBan(String mapb, String tenpb) {
        this.mapb = mapb;
        this.tenpb = tenpb;
    }

    @Override
    public String toString() {
        return tenpb + " - " + mapb;
    }
}
