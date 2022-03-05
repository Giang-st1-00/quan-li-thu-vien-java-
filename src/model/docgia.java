package model;

import java.sql.Date;
import java.util.Objects;

public class docgia {
	private sach ttsach;
	private int cmnd;
	private String hovaten;
	private Date ngaysinh;
	private boolean gioitinh;
	private Date ngaymuon;
	private Date ngaytra;
	public docgia() {
		
	}
	public docgia(sach ttsach, int cmnd, String hovaten, Date ngaysinh, boolean gioitinh, Date ngaymuon,
			Date ngaytra) {
		this.ttsach = ttsach;
		this.cmnd = cmnd;
		this.hovaten = hovaten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.ngaymuon = ngaymuon;
		this.ngaytra = ngaytra;
	}

	
	public sach getTtsach() {
		return ttsach;
	}


	public void setTtsach(sach ttsach) {
		this.ttsach = ttsach;
	}


	public int getCmnd() {
		return cmnd;
	}


	public void setCmnd(int cmnd) {
		this.cmnd = cmnd;
	}


	public String getHovaten() {
		return hovaten;
	}


	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}


	public Date getNgaysinh() {
		return ngaysinh;
	}


	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}


	public boolean isGioitinh() {
		return gioitinh;
	}


	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}


	public Date getNgaymuon() {
		return ngaymuon;
	}


	public void setNgaymuon(Date ngaymuon) {
		this.ngaymuon = ngaymuon;
	}


	public Date getNgaytra() {
		return ngaytra;
	}


	public void setNgaytra(Date ngaytra) {
		this.ngaytra = ngaytra;
	}


	@Override
	public String toString() {
		return "thongtindocgia [ttsach=" + ttsach + ", cmnd=" + cmnd + ", hovaten=" + hovaten + ", ngaysinh=" + ngaysinh
				+ ", gioitinh=" + gioitinh + ", ngaymuon=" + ngaymuon + ", ngaytra=" + ngaytra + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cmnd, gioitinh, hovaten, ngaymuon, ngaysinh, ngaytra, ttsach);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		docgia other = (docgia) obj;
		return cmnd == other.cmnd && gioitinh == other.gioitinh && Objects.equals(hovaten, other.hovaten)
				&& Objects.equals(ngaymuon, other.ngaymuon) && Objects.equals(ngaysinh, other.ngaysinh)
				&& Objects.equals(ngaytra, other.ngaytra) && Objects.equals(ttsach, other.ttsach);
	}
}
