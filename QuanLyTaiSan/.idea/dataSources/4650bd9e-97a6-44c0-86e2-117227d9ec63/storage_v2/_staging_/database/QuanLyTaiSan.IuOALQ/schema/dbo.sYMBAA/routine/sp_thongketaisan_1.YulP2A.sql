if OBJECT_ID('sp_thongketaisan_1') is not null
	drop proc sp_thongketaisan_1
go
create proc sp_thongketaisan_1
as begin
	SELECT DISTINCT pl.TENPL, COUNT(ts.MATS) as 'SL', SUM(CAST(NGUYENGIA AS BIGINT)) as 'Tong gia tri'
	FROM dbo.TAISAN ts INNER JOIN dbo.PHANLOAI pl on ts.MAPL = pl.ID
	GROUP BY pl.TENPL
END

exec sp_thongketaisan_1

--proc #2
if OBJECT_ID('sp_thongketaisan_2') is not null
	drop proc sp_thongketaisan_2
go


