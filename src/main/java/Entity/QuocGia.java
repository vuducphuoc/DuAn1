package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "QUOCGIA")
public class QuocGia {

    @Id
    private String id;

    @Column(name = "TENQUOCGIA")
    private String tenQuocGia;

    @OneToMany(mappedBy = "quocGia", fetch = FetchType.LAZY)
    private List<NhaSanXuat> nhaSanXuatList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public List<NhaSanXuat> getNhaSanXuatList() {
        return nhaSanXuatList;
    }

    public void setNhaSanXuatList(List<NhaSanXuat> nhaSanXuatList) {
        this.nhaSanXuatList = nhaSanXuatList;
    }

    public QuocGia() {
    }

    public QuocGia(String id, String tenQuocGia) {
        this.id = id;
        this.tenQuocGia = tenQuocGia;
    }

    @Override
    public String toString() {
        return    tenQuocGia + " - " + id;
    }
}
