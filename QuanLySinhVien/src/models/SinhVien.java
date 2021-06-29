package models;

public class SinhVien {
	private String ma, hoDem, ten, ngaySinh, gioiTinh;

	public SinhVien(String ma, String hoDem, String ten, String ngaySinh, String gioiTinh) {
		super();
		this.ma = ma;
		this.hoDem = hoDem;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	
	public String getName() {
		return getHoDem() + " " + getTen();
	};
	
	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public void displayInfo() {
		System.out.println("[  "+ma + "  " + getName() + " " + ngaySinh + "  " + gioiTinh +"  ]");
	}
	
}
