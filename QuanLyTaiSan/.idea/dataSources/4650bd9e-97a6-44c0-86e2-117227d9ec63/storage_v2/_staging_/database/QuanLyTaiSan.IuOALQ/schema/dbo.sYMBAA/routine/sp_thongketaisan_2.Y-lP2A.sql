if OBJECT_ID('sp_thongketaisan_2') is not null
  drop proc sp_thongketaisan_2
go

create proc sp_thongketaisan_2
as begin
	SELECT TENPB, COUNT(TAISAN.MATS), SUM(CAST(NGUYENGIA AS BIGINT)) FROM dbo.PHONGBAN
				LEFT JOIN PHIEUBANGIAO on PHONGBAN.MAPB = PHIEUBANGIAO.MAPB
				LEFT JOIN TAISAN on PHIEUBANGIAO.MATS = TAISAN.MATS
	GROUP BY TENPB
end

exec sp_thongketaisan_2

--proc #3
if OBJECT_ID('sp_hetkhauhao') is not null
	drop proc sp_hetkhauhao
go
