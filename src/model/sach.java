package model;

import java.util.Objects;

public class sach {
	private int masach;
	private String tensach;
	public sach() {
		
	}
	public sach(int masach, String tensach) {
		this.masach = masach;
		this.tensach = tensach;
	}
	public int getMasach() {
		return masach;
	}
	public void setMasach(int masach) {
		this.masach = masach;
	}
	public String getTensach() {
		return tensach;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	@Override
	public String toString() {
		return "sach [masach=" + masach + ", tensach=" + tensach + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(masach, tensach);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		sach other = (sach) obj;
		return masach == other.masach && Objects.equals(tensach, other.tensach);
	}
	
	
}
