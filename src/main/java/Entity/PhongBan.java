package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PHONGBAN")
public class PhongBan {

    @Id
    private String id;

    @Column(name = "TENPHONGBAN")
    private String tenPhongBan;

    @OneToMany(mappedBy = "phongBan" ,fetch = FetchType.LAZY)
    private List<NhanVien>  nhanVienList;

    public PhongBan() {
    }

    public PhongBan(String id, String tenPhongBan) {
        this.id = id;
        this.tenPhongBan = tenPhongBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public List<NhanVien> getNhanVienList() {
        return nhanVienList;
    }

    public void setNhanVienList(List<NhanVien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }
}
