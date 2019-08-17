package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "KHO")
public class Kho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENKHO")
    private String tenkho;

    @OneToMany(mappedBy = "kho")
    private List<TaiSan> taiSanList;

    @OneToMany(mappedBy = "kho")
    private List<PhieuBanGiao> phieuBanGiaoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkho() {
        return tenkho;
    }

    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }

    public List<TaiSan> getTaiSanList() {
        return taiSanList;
    }

    public void setTaiSanList(List<TaiSan> taiSanList) {
        this.taiSanList = taiSanList;
    }

    public List<PhieuBanGiao> getPhieuBanGiaoList() {
        return phieuBanGiaoList;
    }

    public void setPhieuBanGiaoList(List<PhieuBanGiao> phieuBanGiaoList) {
        this.phieuBanGiaoList = phieuBanGiaoList;
    }
}
