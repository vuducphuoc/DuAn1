package Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PHIEUBANGIAOCHITIET")
public class PhieuBanGiaoChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "TAISAN")
    private TaiSan taiSan;

    @ManyToOne
    @JoinColumn(name = "PHIEUBANGIAO")
    private PhieuBanGiao phieuBanGiao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaiSan getTaiSan() {
        return taiSan;
    }

    public void setTaiSan(TaiSan taiSan) {
        this.taiSan = taiSan;
    }

    public PhieuBanGiao getPhieuBanGiao() {
        return phieuBanGiao;
    }

    public void setPhieuBanGiao(PhieuBanGiao phieuBanGiao) {
        this.phieuBanGiao = phieuBanGiao;
    }
}
