package DTO;

public class NhaSanXuatDTO {

    private int id;
    private String tennsx;
    private QuocGiaDTO quocGia;

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

    public QuocGiaDTO getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(QuocGiaDTO quocGia) {
        this.quocGia = quocGia;
    }

    @Override
    public String toString() {
        return tennsx + " - " + id;
    }
}
