package Utils;

import DAO.DAOImpl.*;

public class SingletonDaoUtil {
    private static NhanVienDaoImpl nhanVienDaoImpl = null;
    private static NhaSanXuatDaoImpl nhaSanXuatDaoImpl = null;
    private static PhanLoaiDaoImpl phanLoaiTaiSanDaoImpl = null;
    private static PhongBanDaoImpl phongBanDaoImpl = null;
    private static QuocGiaDaoImpl quocGiaDaoImpl = null;
    private static TaiSanDaoImpl taiSanDaoImpl = null;
    private static TaiKhoanDaoImpl taiKhoanDaoImpl = null;
    private static VaiTroDaoImpl vaiTroDaoImpl = null;
    private static BaoCaoThongKeDaoImpl baoCaoThongKeDaoImpl = null;

    private static PhieuBanGiaoDaoImpl phieuBanGiaoDaoImpl = null;
    private static PhieuBanGiaoChiTietDaoImpl phieuBanGiaoChiTietDaoImpl = null;

    public static PhieuBanGiaoDaoImpl getPhieuBanGiaoDaoImpl() {
        if (phieuBanGiaoDaoImpl == null) {
            phieuBanGiaoDaoImpl = new PhieuBanGiaoDaoImpl();
        }
        return phieuBanGiaoDaoImpl;
    }

    public static PhieuBanGiaoChiTietDaoImpl getPhieuBanGiaoChiTietDaoImpl() {
        if (phieuBanGiaoChiTietDaoImpl == null) {
            phieuBanGiaoChiTietDaoImpl = new PhieuBanGiaoChiTietDaoImpl();
        }
        return phieuBanGiaoChiTietDaoImpl;
    }

    public static NhanVienDaoImpl getNhanVienDaoImpl() {
        if (nhanVienDaoImpl == null) {
            nhanVienDaoImpl = new NhanVienDaoImpl();
        }
        return nhanVienDaoImpl;
    }

    public static BaoCaoThongKeDaoImpl getBaoCaoThongKeDaoImpl() {
        if (baoCaoThongKeDaoImpl == null) {
            baoCaoThongKeDaoImpl = new BaoCaoThongKeDaoImpl();
        }
        return baoCaoThongKeDaoImpl;
    }
    public static NhaSanXuatDaoImpl getNhaSanXuatDaoImpl() {
        if (nhaSanXuatDaoImpl == null) {
            nhaSanXuatDaoImpl = new NhaSanXuatDaoImpl();
        }
        return nhaSanXuatDaoImpl;
    }
    public static PhanLoaiDaoImpl getPhanLoaiTaiSanDaoImpl() {
        if (phanLoaiTaiSanDaoImpl == null) {
            phanLoaiTaiSanDaoImpl = new PhanLoaiDaoImpl();
        }
        return phanLoaiTaiSanDaoImpl;
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
    public static TaiKhoanDaoImpl getTaiKhoanDaoImpl() {
        if (taiKhoanDaoImpl == null) {
            taiKhoanDaoImpl = new TaiKhoanDaoImpl();
        }
        return taiKhoanDaoImpl;
    }
    public static VaiTroDaoImpl getVaiTroDaoImpl() {
        if (vaiTroDaoImpl == null) {
            vaiTroDaoImpl = new VaiTroDaoImpl();
        }
        return vaiTroDaoImpl;
    }

}
