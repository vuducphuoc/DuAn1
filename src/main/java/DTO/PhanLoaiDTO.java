package DTO;


import java.util.List;

public class PhanLoaiDTO {

    private int id;
    private String tenpl;
    private List<TaiSanDTO> taiSanList;

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

    public List<TaiSanDTO> getTaiSanList() {
        return taiSanList;
    }

    public void setTaiSanList(List<TaiSanDTO> taiSanList) {
        this.taiSanList = taiSanList;
    }
}
