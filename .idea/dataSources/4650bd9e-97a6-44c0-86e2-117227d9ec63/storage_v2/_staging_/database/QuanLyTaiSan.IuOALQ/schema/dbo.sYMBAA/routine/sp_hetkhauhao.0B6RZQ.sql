if OBJECT_ID('sp_hetkhauhao') is not null
	drop proc sp_hetkhauhao
go
create proc sp_hetkhauhao
as begin
	select tents,tenpl,NGAYBANGIAO,thoigiankhauhao
	from taisan ts join PHANLOAI pl on ts.mapl=pl.id
				   join PHIEUBANGIAO bg on ts.MATS=bg.MATS
	where (DATEDIFF(DAY,NGAYBANGIAO,GETDATE())/365)-thoigiankhauhao>=0
end

exec sp_hetkhauhao
go


