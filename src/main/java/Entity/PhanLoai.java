package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PHANLOAI")
public class PhanLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENPL")
    private String tenpl;

    @OneToMany(mappedBy = "nhaSanXuat" , fetch = FetchType.LAZY)
    private List<TaiSan> taiSanList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenpl() {
        return tenpl;
    }

    public void setTenpl(String tenpl) {
        this.tenpl = tenpl;
    }

    public List<TaiSan> getTaiSanList() {
        return taiSanList;
    }

    public void setTaiSanList(List<TaiSan> taiSanList) {
        this.taiSanList = taiSanList;
    }
}
