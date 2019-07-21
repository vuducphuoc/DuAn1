package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TAISAN")
public class TaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToOne
    @JoinColumn(name = "MANSX")
    private NhaSanXuat nhaSanXuat;

    @ManyToOne
    @JoinColumn(name = "MAPL")
    private PhanLoai phanLoai;

    @OneToMany(mappedBy = "taiSan")
    private List<PhieuBanGiao> phieuBanGiaoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PhanLoai getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(PhanLoai phanLoai) {
        this.phanLoai = phanLoai;
    }

    public List<PhieuBanGiao> getPhieuBanGiaoList() {
        return phieuBanGiaoList;
    }

    public void setPhieuBanGiaoList(List<PhieuBanGiao> phieuBanGiaoList) {
        this.phieuBanGiaoList = phieuBanGiaoList;
    }
}
