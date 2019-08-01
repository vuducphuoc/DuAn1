package Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PHIEUBANGIAO")
public class PhieuBanGiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NGAYBANGIAO")
    private Date ngaybangiao;

    @Column(name = "NGUOISUDUNG")
    private String nguoisudungs;

    @ManyToOne
    @JoinColumn(name = "MAPB")
    private PhongBan phongBan;

    @ManyToOne
    @JoinColumn(name = "MATS")
    private TaiSan taiSan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgaybangiao() {
        return ngaybangiao;
    }

    public void setNgaybangiao(Date ngaybangiao) {
        this.ngaybangiao = ngaybangiao;
    }

    public String getNguoisudungs() {
        return nguoisudungs;
    }

    public void setNguoisudungs(String nguoisudungs) {
        this.nguoisudungs = nguoisudungs;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public TaiSan getTaiSan() {
        return taiSan;
    }

    public void setTaiSan(TaiSan taiSan) {
        this.taiSan = taiSan;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
