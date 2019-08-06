package Entity;

import javax.persistence.*;
import Entity.QuocGia;

@Entity
@Table(name = "NHASANXUAT")
public class NhaSanXuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TENNSX")
    private String tennsx;

    @ManyToOne()
    @JoinColumn(name = "MAQG")
    private QuocGia quocGia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennsx() {
        return tennsx;
    }

    public void setTennsx(String tennsx) {
        this.tennsx = tennsx;
    }

    public QuocGia getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(QuocGia quocGia) {
        this.quocGia = quocGia;
    }

    public NhaSanXuat() {
    }

    public NhaSanXuat(String tennsx, QuocGia quocGia) {
        this.tennsx = tennsx;
        this.quocGia = quocGia;
    }

    @Override
    public String toString() {
        return tennsx + " - " + quocGia.getTenqg();
    }
}
