package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PHANLOAI")
public class PhanLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENPHANLOAI")
    private String tenPhanLoai;

    @OneToMany(mappedBy = "phanLoai")
    private List<TaiSan> taiSanList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhanLoai() {
        return tenPhanLoai;
    }

    public void setTenPhanLoai(String tenPhanLoai) {
        this.tenPhanLoai = tenPhanLoai;
    }

    public List<TaiSan> getTaiSanList() {
        return taiSanList;
    }

    public void setTaiSanList(List<TaiSan> taiSanList) {
        this.taiSanList = taiSanList;
    }
}
