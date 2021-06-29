package models;

public class Diem {
	private String maSv, maMh;
	private double diem;
	public Diem(String maSv, String maMh, double diem) {
		super();
		this.maSv = maSv;
		this.maMh = maMh;
		this.diem = diem;
	}
	public String getMaSv() {
		return maSv;
	}
	public void setMaSv(String maSv) {
		this.maSv = maSv;
	}
	public String getMaMh() {
		return maMh;
	}
	public void setMaMh(String maMh) {
		this.maMh = maMh;
	}
	public double getDiem() {
		return diem;
	}
	public void setDiem(double diem) {
		this.diem = diem;
	}
	
}
