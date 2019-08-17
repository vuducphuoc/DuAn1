package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VAITRO")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENVAITRO")
    private String tenvaitro;

    @OneToMany(mappedBy = "vaiTro",fetch = FetchType.LAZY)
    List<TaiKhoan> taiKhoanList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenvaitro() {
        return tenvaitro;
    }

    public void setTenvaitro(String tenvaitro) {
        this.tenvaitro = tenvaitro;
    }

    public List<TaiKhoan> getTaiKhoanList() {
        return taiKhoanList;
    }

    public void setTaiKhoanList(List<TaiKhoan> taiKhoanList) {
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public String toString() {
        return tenvaitro;
    }
}
