package Entity;

import javax.persistence.*;
import Entity.QuocGia;

@Entity
@Table(name = "NHASANXUAT")
public class NhaSanXuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENNHASANXUAT")
    private String tenNhaSanXuat;

    @ManyToOne()
    @JoinColumn(name = "QUOCGIA")
    private QuocGia quocGia;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String tenNhaSanXuat, QuocGia quocGia) {
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.quocGia = quocGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public QuocGia getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(QuocGia quocGia) {
        this.quocGia = quocGia;
    }

    @Override
    public String toString() {
        return tenNhaSanXuat + " - " + quocGia.getTenQuocGia();
    }
}
