package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "QUOCGIA")
public class QuocGia {

    @Id
    private String maqg;

    @Column(name = "TENQG")
    private String tenqg;

    @OneToMany(mappedBy = "quocGia", fetch = FetchType.LAZY)
    private List<NhaSanXuat> nhaSanXuatList;

    public String getMaqg() {
        return maqg;
    }

    public void setMaqg(String maqg) {
        this.maqg = maqg;
    }

    public String getTenqg() {
        return tenqg;
    }

    public void setTenqg(String tenqg) {
        this.tenqg = tenqg;
    }

    public List<NhaSanXuat> getNhaSanXuatList() {
        return nhaSanXuatList;
    }

    public void setNhaSanXuatList(List<NhaSanXuat> nhaSanXuatList) {
        this.nhaSanXuatList = nhaSanXuatList;
    }

    public QuocGia() {
    }

    public QuocGia(String maqg, String tenqg) {
        this.maqg = maqg;
        this.tenqg = tenqg;
    }

    @Override
    public String toString() {
        return tenqg + " - " + maqg;
    }
}
