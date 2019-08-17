INSERT INTO VAITRO (ID, TENVAITRO)
VALUES
(1, N'Quản Trị Viên'),
(2, N'Quản Lý Kho'),
(3, N'Kế Toán'),
(4, N'Quản Lý Nhân Viên')

INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, NHANVIEN, TINHTRANG, VAITRO)
VALUES
  ('admin', 'admin', 'NV00001', 0, 1)

-- PHÒNG BAN
INSERT INTO PHONGBAN (ID, TENPHONGBAN)
VALUES
  ('PB00001', N'Phòng Quản Trị'),
('PB00002', N'Phòng Quản Lý Kho'),
('PB00003', N'Phòng Tài Chính Kế Toán'),
('PB00004', N'Phòng Kinh Tế Kỹ Thuật'),
('PB00005', N'Phòng Tổ Chức Hành Chính'),
('PB00006', N'Phòng Kinh Doanh Đa Ngành'),
('PB00007', N'Phòng Thị Trường')

INSERT INTO NHANVIEN (ID, TENNHANVIEN, GIOITINH, NGAYSINH, EMAIL, PHONGBAN)
VALUES
  ('NV00001', N'Vũ Đức Phước', 1, '1996-04-14', 'vuducphuoc.pi@gmail.com', 'PB00001')

-- QUỐC GIA
INSERT INTO QUOCGIA (ID, TENQUOCGIA)
VALUES 	('AR', N'Argentina'),
('CB', N'Campuchia'),
('CA', N'Canada'),
('DA', N'Đan Mạch'),
('DE', N'Đức'),
('KS', N'Hàn Quốc'),
('LA', N'Lào'),
('US', N'Mỹ'),
('JA', N'Nhật Bản'),
('FR', N'Pháp'),
('TH', N'Thái Lan'),
('SZ', N'Thụy Sĩ'),
('CH', N'Trung Quốc'),
('AS', N'Úc'),
('VN', N'Việt Nam')


-- NHÀ SẢN XUẤT
INSERT INTO NHASANXUAT (TENNHASANXUAT, QUOCGIA)
VALUES
  ( N'Toyota', N'JA'),
( N'Mazda', N'JA'),
(N'Asanzo', N'AS'),
(N'Lavie','VN'),
(N'Panasonic','VN')

-- KHO
INSERT INTO KHO (TENKHO)
VALUES (N'Kho 1'), (N'Kho 2'), (N'Kho 3')

INSERT INTO PHANLOAI (TENPHANLOAI)
VALUES
  (N'Công cụ dụng cụ'),
(N'Nhà cửa, Vật kiến trúc'),
(N'Phương tiện vận tải')

INSERT INTO TAISAN (ID, TENTAISAN, NGUYENGIA, THOIGIANKHAUHAO, TYLEKHAUHAO, NAMBDSUDUNG, NHASANXUAT, PHANLOAI, KHO)
VALUES
  ('TS00001', N'Mazda 2', 400000, 10, 10, 2008, 2, 3, 1)