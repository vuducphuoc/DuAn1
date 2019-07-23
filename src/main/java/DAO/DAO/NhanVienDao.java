package DAO.DAO;

import DAO.Abstract.GenericDao;
import DTO.NhanVienDTO;
import Entity.NhanVien;
import Entity.PhongBan;

import  java.util.List;

public interface NhanVienDao extends GenericDao<String, NhanVien> {
    boolean checkLogin(String email, String password);

    List<NhanVienDTO> getNhanVienDtoList ();

    String getLastID();
}
