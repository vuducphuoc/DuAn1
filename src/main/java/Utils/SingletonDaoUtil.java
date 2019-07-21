package Utils;


import DAO.DAOImpl.*;


/**
 * Created by Admin on 2/9/2017.
 */
public class SingletonDaoUtil {
    private static NhanVienDaoImpl nhanVienDaoImpl = null;
    private static NhaSanXuatDaoImpl nhaSanXuatDaoImpl = null;
    private static PhanLoaiDaoImpl phanLoaiDaoImpl = null;
    private static PhieuBanGiaoDaoImpl phieuBanGiaoDaoImpl = null;
    private static PhongBanDaoImpl phongBanDaoImpl = null;
    private static QuocGiaDaoImpl quocGiaDaoImpl = null;
    private static TaiSanDaoImpl taiSanDaoImpl = null;


    public static NhanVienDaoImpl getNhanVienDaoImpl() {
        if (nhanVienDaoImpl == null) {
            nhanVienDaoImpl = new NhanVienDaoImpl();
        }
        return nhanVienDaoImpl;
    }
    public static NhaSanXuatDaoImpl getNhaSanXuatDaoImpl() {
        if (nhaSanXuatDaoImpl == null) {
            nhaSanXuatDaoImpl = new NhaSanXuatDaoImpl();
        }
        return nhaSanXuatDaoImpl;
    }
    public static PhanLoaiDaoImpl getPhanLoaiDaoImpl() {
        if (phanLoaiDaoImpl == null) {
            phanLoaiDaoImpl = new PhanLoaiDaoImpl();
        }
        return phanLoaiDaoImpl;
    }
    public static PhieuBanGiaoDaoImpl getPhieuBanGiaoDaoImpl() {
        if (phieuBanGiaoDaoImpl == null) {
            phieuBanGiaoDaoImpl = new PhieuBanGiaoDaoImpl();
        }
        return phieuBanGiaoDaoImpl;
    }
    public static PhongBanDaoImpl getPhongBanDaoImpl() {
        if (phongBanDaoImpl == null) {
            phongBanDaoImpl = new PhongBanDaoImpl();
        }
        return phongBanDaoImpl;
    }
    public static QuocGiaDaoImpl getQuocGiaDaoImpl() {
        if (quocGiaDaoImpl == null) {
            quocGiaDaoImpl = new QuocGiaDaoImpl();
        }
        return quocGiaDaoImpl;
    }
    public static TaiSanDaoImpl getTaiSanDaoImpl() {
        if (taiSanDaoImpl == null) {
            taiSanDaoImpl = new TaiSanDaoImpl();
        }
        return taiSanDaoImpl;
    }

}
