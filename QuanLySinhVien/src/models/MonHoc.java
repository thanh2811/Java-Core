package models;

public class MonHoc {
	private String ma, ten;
	private double heSo;
	public MonHoc(String ma, String ten, double heSo) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.heSo = heSo;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public double getHeSo() {
		return heSo;
	}
	public void setHeSo(double heSo) {
		this.heSo = heSo;
	}
	
}
