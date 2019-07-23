package DTO;

import java.util.List;

public class QuocGiaDTO {

    private String maqg;
    private String tenqg;
    private List<NhaSanXuatDTO> nhaSanXuatList;

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

    public List<NhaSanXuatDTO> getNhaSanXuatList() {
        return nhaSanXuatList;
    }

    public void setNhaSanXuatList(List<NhaSanXuatDTO> nhaSanXuatList) {
        this.nhaSanXuatList = nhaSanXuatList;
    }
}
