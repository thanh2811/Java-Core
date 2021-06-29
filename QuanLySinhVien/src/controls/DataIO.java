package controls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import models.Diem;
import models.MonHoc;
import models.SinhVien;


public class DataIO {

	public static ArrayList<SinhVien> dsSinhVien = new ArrayList<SinhVien>(); 
	public static ArrayList<Diem> dsDiem = new ArrayList<Diem>();
	public static ArrayList<MonHoc> dsMonHoc = new ArrayList<MonHoc>();
	
	
	
	public static String dataRoot = "";
	public static String fileSinhVien = "sinhvien.txt";
	public static String fileDiem = "diem.txt";
	public static String fileMonHoc = "monhoc.txt";
	static Scanner sc = new Scanner(System.in);
	
	public static void loadData() {
		// load ds sv
		loadSinhVien();
		loadMonHoc();
		loadDiem();
		
	}
	
	private static void loadDiem() {
		FileReader freader = null;
		BufferedReader buffer = null;
		File file = new File(dataRoot + "\\" + fileDiem);
		
		try {
			freader = new FileReader(file);
			buffer = new BufferedReader(freader);
			String line = "";
			while((line = buffer.readLine()) != null) {
				if(line.startsWith("#")) continue;
				String data[] = line.split(";");
				String maSv = data[0];
				String maMh = data[1];
				String diemS = data[2];
				Double diem = Double.valueOf(diemS);
				Diem d = new Diem(maSv, maMh, diem);
				dsDiem.add(d);

			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(buffer != null) buffer.close();
				if(freader != null) freader.close();
			} catch(Exception e) {
				
			}
		}
		
		
	}
	// 
	private static void loadMonHoc() {
		FileReader freader = null;
		BufferedReader buffer = null;
		File file = new File(dataRoot + "\\" + fileMonHoc);
		
		try {
			freader = new FileReader(file);
			buffer = new BufferedReader(freader);
			String line = "";
			while((line = buffer.readLine()) != null) {
				if(line.startsWith("#")) continue;
				String data[] = line.split(";");
				String ma = data[0];
				String ten = data[1];
				String hs = data[2];
				double heSo = Double.parseDouble(hs);
				MonHoc mh = new MonHoc(ma, ten, heSo);
				dsMonHoc.add(mh);				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(buffer != null) buffer.close();
				if(freader != null) freader.close();
			} catch(Exception e) {
				
			}
		}
		
		
	}
	private static void loadSinhVien() {
		FileReader freader = null;
		BufferedReader buffer = null;
		File file = new File(dataRoot + "\\" + fileSinhVien);
		
		try {
			freader = new FileReader(file);
			buffer = new BufferedReader(freader);
			String line = "";
			while((line = buffer.readLine()) != null) {
				if(line.startsWith("#")) continue;
				String data[] = line.split(";");
				String ma = data[0];
				String hoDem = data[1];
				String ten = data[2];
				String ngaySinh = data[3];
				String gioiTinh = data[4];
				SinhVien s = new SinhVien(ma, hoDem, ten, ngaySinh, gioiTinh);
				dsSinhVien.add(s);
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(buffer != null) buffer.close();
				if(freader != null) freader.close();
			} catch(Exception e) {
				
			}
		}
		
		
	}
	
	public static SinhVien getSinhVien(String ma) {
		for (int i = 0; i < dsSinhVien.size(); i++) {
			if(dsSinhVien.get(i).getMa().equals(ma)) {
				return dsSinhVien.get(i);
			}
		}
		return null;
	}
	
	public static MonHoc getMonHoc(String ma) {
		for (MonHoc mh : dsMonHoc) {
			if(mh.getMa().equals(ma)) return mh;
		}
		return null;
	}
	public static Diem getDiem(String maSv, String maMh) {
		for (Diem diem : dsDiem) {
			if(diem.getMaSv().equals(maSv) && diem.getMaMh().equals(maMh)) {
				return diem;
			}
		}
		return null;
	}
	public static ArrayList<Diem> getDiems(String maSv, ArrayList<MonHoc> dsMon) {
		ArrayList<Diem> diemList = new ArrayList<Diem>();
		for (MonHoc monHoc : dsMon) {
			String maMh = monHoc.getMa();
			for (Diem diem : dsDiem) {
				if(maMh.equals(diem.getMaMh()) && diem.getMaSv().equals(maSv)) {
					diemList.add(diem);
				}
			}
		}
		return null;
	}
	
	public static boolean addSv(String info) {
		String lastId;
		int maSv;
		if(dsSinhVien.size() > 0) {
			lastId = dsSinhVien.get(dsSinhVien.size()-1).getMa();
			maSv = Integer.parseInt(lastId.substring(2)) + 1;
		} else {
			maSv = 1;
		}
		
		String ma = "SV";
		int tmp = maSv, dem = 0;
		while(tmp!=0) {
			tmp /= 10;
			dem++;
		}
		for (int i = 0; i < 5 - dem; i++) {
			ma = ma.concat("0");
		}
		ma = ma.concat(Integer.toString(maSv));
		String data[]; 
		String hoTen, hoDem = null, ten = null, ngaySinh = null,gioiTinh = null;
		try {
			data = info.split(";");
			hoTen = data[0];
			hoDem = hoTen.substring(0,hoTen.lastIndexOf(" "));
			ten = hoTen.substring(hoTen.lastIndexOf(" ") + 1);
			ngaySinh = data[1];
			gioiTinh = data[2];
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi: " + e.getMessage());
			return false;
		}

		SinhVien s = new SinhVien(ma, hoDem, ten, ngaySinh, gioiTinh);
		dsSinhVien.add(s);
		
		System.out.println("Them thanh cong!");
		s.displayInfo();
		return true;
	}
	
	public static void editSv() {
		System.out.println("Nhap ma sinh vien can sua: ");
		String ma = sc.nextLine();
		
		boolean found = false;
		for (SinhVien sv : dsSinhVien) {
			if(sv.getMa().equals(ma)) {
				found = true;
				System.out.println("Tim thay sinh vien:");
				sv.displayInfo();
				
				// edit here
				System.out.println("Nhap lai thong tin sinh vien theo dang\r\n"
						+ "[ho va ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]\r\n"
						+ "Neu khong muon sua muc nao thi bo trong muc do\r\n"
						+ "Vi du:Nguyen Van A;;Nam \nNhap: " );
				String info = sc.nextLine();
				String data[] = info.split(";");
				if(data.length > 0 && data[0].length()>0) {
					String hoTen = data[0];
					int sp = hoTen.lastIndexOf(" ");
					String hoDem;
					if(sp > -1)
						hoDem = hoTen.substring(0,hoTen.lastIndexOf(" "));
					else 
						hoDem = "";
					String ten;
					if(sp > -1)
						ten = hoTen.substring(hoTen.lastIndexOf(" ") + 1);
					else
						ten = hoTen;
						
					sv.setHoDem(hoDem);
					sv.setTen(ten);
				}
				if(data.length>1 && data[1].length() > 0) {
					sv.setNgaySinh(data[1]);
				}
				if(data.length>2 && data[2].length() > 0) {
					sv.setGioiTinh(data[2]);
				}
				System.out.println("Sua thanh cong !");
				sv.displayInfo();
				break;
			}
		}
		if(found == false)
			System.out.println("Khong tim thay ma sinh vien");
	}

	public static void deleteSv() {
		// TODO Auto-generated method stub
		System.out.println("Nhap ma sinh vien can xoa: ");
		String ma = sc.nextLine();
		
		boolean found = false;
		for (int i = 0;i<dsSinhVien.size();i++) {
			SinhVien sv = dsSinhVien.get(i);
			
			if(sv.getMa().equals(ma)) {
				found = true;
				System.out.println("Tim thay sinh vien:");
				sv.displayInfo();
				System.out.println("Ban co chac muon xoa (y/n) ?");
				String cf = "";
				while(true) {
					cf = sc.nextLine();
					if(cf.equals("y")) {
						dsSinhVien.remove(i);
						System.out.println("Xoa thanh cong!");
						break;
					}
					else if(cf.equals("n")) {
						System.out.println("Huy xoa thanh cong!");
						break;
					}
					else {
						System.out.println("Nhap y/n: ");
					}
				}
				break;
			}
		}
		
		if(found == false)
			System.out.println("Khong tim thay ma sinh vien");
	}

	public static void displaySvList() {
		// TODO Auto-generated method stub
		
		// count page number
		System.out.println("--------Danh sach sinh vien--------");
		String title = "┌─────────┬───────────────────────┬───────────┬────────────┬─────────┐\r\n"
				     + "│   Ma    │  Ho dem               │  Ten      │ ngay sinh  │Gioi tinh│\r\n"
				     + "├─────────┼───────────────────────┼───────────┼────────────┼─────────┤";
		int soTrang = dsSinhVien.size() / 30;
		if(soTrang * 30 < dsSinhVien.size()) soTrang++;
		int page = 1;
		// sort by name
		Comparator<SinhVien> comparator = new Comparator<SinhVien>() {

			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				return o1.getTen().compareTo(o2.getTen());
			}
		};
		dsSinhVien.sort(comparator);
		
		// display
		System.out.println(title);
		for(int i=0;i<30 && i<dsSinhVien.size();i++) {
			SinhVien sv = dsSinhVien.get(i);
			System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
		}
		System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
		System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
		
		
		String choice = "";
		
		do {
			System.out.println("1. Xem trang tiep theo        3. Den trang cuoi             \r\n"
					+ "2. Tro lai trang truoc        4. Den trang dau tien         \r\n"
					+ "5. Xem trang cu the           0. Tro ve menu truoc");
			System.out.println("Nhap lua chon: ");
			choice = sc.nextLine();
			

			switch(choice) {
			case "1": // View next page
				if(page >= soTrang || page < 1) {
					System.out.println("Khong co trang nay!");
					break;
				}
				System.out.println(title);
				for (int i = page*30; i < page*30+30 && i<dsSinhVien.size() ; i++) {
					SinhVien sv = dsSinhVien.get(i);
					System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t--------Page "+ (page+1) +"/" + soTrang + " --------");
				page++;
				break;
			case "2": // previous page
				if(page < 2) {
					System.out.println("Khong co trang nay!");
					break;
				}
				page -= 2;
				System.out.println(title);
				for (int i = page*30; i < page*30+30 && i<dsSinhVien.size() ; i++) {
					SinhVien sv = dsSinhVien.get(i);
					System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t--------Page "+ (page+1) +"/" + soTrang + " --------");
				page++;
				break;
			case "4": // first page
				System.out.println(title);
				page = 1;
				
				for(int i=0;i<30 && i<dsSinhVien.size();i++) {
					SinhVien sv = dsSinhVien.get(i);
					System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
				
				break;
			case "3": // last page
				System.out.println(title);
				page = soTrang;
				for(int i=(page-1)*30;i<(page-1)*30+30 && i<dsSinhVien.size();i++) {
					SinhVien sv = dsSinhVien.get(i);
					System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
				break;
			case "5": // specific page  
				System.out.println("Nhap trang can hien thi: ");
//				page = sc.nextInt();
//				sc.nextLine();

				try {
					page = Integer.parseInt(sc.next());
				}catch (Exception e) {
					System.out.println("Loi: " + e.getMessage());
					// TODO: handle exception
					break;
				}
				
				if(page<1 || page>soTrang) {
					System.out.println("Khong co trang nay!");
					break;
				}
				System.out.println(title);
				for(int i=(page-1)*30;i<(page-1)*30+30 && i<dsSinhVien.size();i++) {
					SinhVien sv = dsSinhVien.get(i);
					System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
				break;
			case "0":// previous menu
				break;
			default:
				System.out.println("Nhap lai !");
				break;
			}
		}while(!choice.equals("0"));
		
		
	}

	public static void displayMonHoc() {
		// TODO Auto-generated method stub
		dsMonHoc.sort(new Comparator<MonHoc>() {

			@Override
			public int compare(MonHoc o1, MonHoc o2) {
				// TODO Auto-generated method stub
				return o1.getTen().compareTo(o2.getTen());
			}
		});
		
		String title = "\t--------Danh sach mon hoc--------\n"
						+  "┌──────┬────────────────────────────┬───────┐\r\n"
				         + "│  Ma  │        Ten mon hoc         │ he so │\r\n"
				         + "├──────┼────────────────────────────┼───────┤";
		
	
	
	int soTrang = dsMonHoc.size() / 30 ;
	if(soTrang * 30 < dsMonHoc.size()) soTrang++;
	int page = 1;

	
	// display
	System.out.println(title);
	for(int i=0;i<30 && i<dsMonHoc.size();i++) {
		MonHoc mh = dsMonHoc.get(i);
		System.out.format("│ %-5s│ %-26s │ %-2s   │\n" , mh.getMa(), mh.getTen(), mh.getHeSo());
	}
	System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
	System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
	
	
	String choice = "";
	
	do {
		System.out.println("1. Xem trang tiep theo        3. Den trang cuoi             \r\n"
				+ "2. Tro lai trang truoc        4. Den trang dau tien         \r\n"
				+ "5. Xem trang cu the           0. Tro ve menu truoc");
		System.out.println("Nhap lua chon: ");
		choice = sc.nextLine();
		

		switch(choice) {
		case "1": // View next page
			if(page >= soTrang || page < 1) {
				System.out.println("Khong co trang nay!");
				break;
			}
			System.out.println(title);
			for (int i = page*30; i < page*30+30 && i<dsSinhVien.size() ; i++) {
				MonHoc mh = dsMonHoc.get(i);
				System.out.format("│ %-5s│ %-26s │ %-2s   │\n" , mh.getMa(), mh.getTen(), mh.getHeSo());
			}
			System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
			System.out.println("\t--------Page "+ (page+1) +"/" + soTrang + " --------");
			page++;
			break;
		case "2": // previous page
			if(page < 2) {
				System.out.println("Khong co trang nay!");
				break;
			}
			page -= 2;
			System.out.println(title);
			for (int i = page*30; i < page*30+30 && i<dsSinhVien.size() ; i++) {
				MonHoc mh = dsMonHoc.get(i);
				System.out.format("│ %-5s│ %-26s │ %-2s   │\n" , mh.getMa(), mh.getTen(), mh.getHeSo());
			}
			System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
			System.out.println("\t--------Page "+ (page+1) +"/" + soTrang + " --------");
			page++;
			break;
		case "4": // first page
			System.out.println(title);
			page = 1;
			
			for(int i=0;i<30 && i<dsSinhVien.size();i++) {
				MonHoc mh = dsMonHoc.get(i);
				System.out.format("│ %-5s│ %-26s │ %-2s   │\n" , mh.getMa(), mh.getTen(), mh.getHeSo());
			}
			System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
			System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
			
			break;
		case "3": // last page
			System.out.println(title);
			page = soTrang;
			for(int i=(page-1)*30;i<(page-1)*30+30 && i<dsSinhVien.size();i++) {
				MonHoc mh = dsMonHoc.get(i);
				System.out.format("│ %-5s│ %-26s │ %-2s   │\n" , mh.getMa(), mh.getTen(), mh.getHeSo());
			}
			System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
			System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
			break;
		case "5": // specific page  
			System.out.println("Nhap trang can hien thi: ");
//			page = sc.nextInt();
//			sc.nextLine();

			try {
				page = Integer.parseInt(sc.next());
			}catch (Exception e) {
				System.out.println("Loi: " + e.getMessage());
				// TODO: handle exception
				break;
			}
			
			if(page<1 || page>soTrang) {
				System.out.println("Khong co trang nay!");
				break;
			}
			System.out.println(title);
			for(int i=(page-1)*30;i<(page-1)*30+30 && i<dsSinhVien.size();i++) {
				MonHoc mh = dsMonHoc.get(i);
				System.out.format("│ %-5s│ %-26s │ %-2s   │\n" , mh.getMa(), mh.getTen(), mh.getHeSo());
			}
			System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
			System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
			break;
		case "0":// previous menu
			break;
		default:
			System.out.println("Nhap lai !");
			break;
		}
	}while(!choice.equals("0"));

	}

	public static void displayBangDiem() {
		
		//sort SV
		dsSinhVien.sort(new Comparator<SinhVien>() {

			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				// TODO Auto-generated method stub
				return o1.getMa().compareTo(o2.getMa());
			}
		});
		
		
		
		for (int i=0; i<30 && i<dsSinhVien.size();i++) {
			SinhVien sv = dsSinhVien.get(i);
			double heso = 0;
			double dtk = 0;
			for (int j=0; j<dsDiem.size();j++) {
				Diem diem = dsDiem.get(j);
				for (int k=0; k<dsMonHoc.size();k++) {
					MonHoc mh = dsMonHoc.get(k);
					if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
						dtk += (diem.getDiem() * mh.getHeSo());
						heso += mh.getHeSo();
					}
				}
			}
			if(heso != 0) dtk /= heso;
			dtk = (double)(Math.round(dtk*100d))/100d;
			
			System.out.println("┌──────────────────────────────────────────┐");
			 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
			 System.out.format("│ DTK: %33.2f   │\n", dtk);
			System.out.println("├──────────────────────────────────────────┤");
			
			boolean hasDtk = false;
			for (int j=0;j<dsDiem.size();j++) {
				Diem diem = dsDiem.get(j);
				
				for (int k=0; k<30 && k<dsMonHoc.size();k++) {
					MonHoc mh = dsMonHoc.get(k);
					if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
						hasDtk = true;
						System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
					}
					
				}
				
			}
			String noti = "Chua co diem";
			if(!hasDtk)
				System.out.format("│ %38s   │\n",noti);
			System.out.println("└──────────────────────────────────────────┘");
		}
		
		int soTrang = dsSinhVien.size() / 30;
		if(soTrang * 30 < dsSinhVien.size()) soTrang++;
		int page = 1;
		
		
		// display
		
		
		System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
		
		
		String choice = "";
		
		do {
			System.out.println("1. Xem trang tiep theo        3. Den trang cuoi             \r\n"
					+ "2. Tro lai trang truoc        4. Den trang dau tien         \r\n"
					+ "5. Xem trang cu the           0. Tro ve menu truoc");
			System.out.println("Nhap lua chon: ");
			choice = sc.nextLine();
			

			switch(choice) {
			case "1": // View next page
				if(page >= soTrang || page < 1) {
					System.out.println("Khong co trang nay!");
					break;
				}
				
				for (int i = page*30; i < page*30+30 && i<dsSinhVien.size() ; i++) {
					SinhVien sv = dsSinhVien.get(i);
					double heso = 0;
					double dtk = 0;
					for (int j=0; j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						for (int k=0; k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								dtk += (diem.getDiem() * mh.getHeSo());
								heso += mh.getHeSo();
							}
						}
					}
					if(heso != 0) dtk /= heso;
					dtk = (double)(Math.round(dtk*100d))/100d;
					
					System.out.println("┌──────────────────────────────────────────┐");
					 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
					 System.out.format("│ DTK: %33.2f   │\n", dtk);
					System.out.println("├──────────────────────────────────────────┤");
					
					boolean hasDtk = false;
					for (int j=0;j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						
						for (int k=0; k<30 && k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								hasDtk = true;
								System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
							}
							
						}
						
					}
					String noti = "Chua co diem";
					if(!hasDtk)
						System.out.format("│ %38s   │\n",noti);
					System.out.println("└──────────────────────────────────────────┘");
				}
				
				System.out.println("\t--------Page "+ (page+1) +"/" + soTrang + " --------");
				page++;
				break;
			case "2": // previous page
				if(page < 2) {
					System.out.println("Khong co trang nay!");
					break;
				}
				page -= 2;
				
				for (int i = page*30; i < page*30+30 && i<dsSinhVien.size() ; i++) {
					SinhVien sv = dsSinhVien.get(i);
					double heso = 0;
					double dtk = 0;
					for (int j=0; j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						for (int k=0; k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								dtk += (diem.getDiem() * mh.getHeSo());
								heso += mh.getHeSo();
							}
						}
					}
					if(heso != 0) dtk /= heso;
					dtk = (double)(Math.round(dtk*100d))/100d;
					
					System.out.println("┌──────────────────────────────────────────┐");
					 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
					 System.out.format("│ DTK: %33.2f   │\n", dtk);
					System.out.println("├──────────────────────────────────────────┤");
					
					boolean hasDtk = false;
					for (int j=0;j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						
						for (int k=0; k<30 && k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								hasDtk = true;
								System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
							}
							
						}
						
					}
					String noti = "Chua co diem";
					if(!hasDtk)
						System.out.format("│ %38s   │\n",noti);
					System.out.println("└──────────────────────────────────────────┘");
				}
				
				System.out.println("\t--------Page "+ (page+1) +"/" + soTrang + " --------");
				page++;
				break;
			case "4": // first page
				
				page = 1;
				
				for (int i=0; i<30 && i<dsSinhVien.size();i++) {
					SinhVien sv = dsSinhVien.get(i);
					double heso = 0;
					double dtk = 0;
					for (int j=0; j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						for (int k=0; k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								dtk += (diem.getDiem() * mh.getHeSo());
								heso += mh.getHeSo();
							}
						}
					}
					if(heso != 0) dtk /= heso;
					dtk = (double)(Math.round(dtk*100d))/100d;
					
					System.out.println("┌──────────────────────────────────────────┐");
					 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
					 System.out.format("│ DTK: %33.2f   │\n", dtk);
					System.out.println("├──────────────────────────────────────────┤");
					
					boolean hasDtk = false;
					for (int j=0;j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						
						for (int k=0; k<30 && k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								hasDtk = true;
								System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
							}
							
						}
						
					}
					String noti = "Chua co diem";
					if(!hasDtk)
						System.out.format("│ %38s   │\n",noti);
					
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
				
				break;
			case "3": // last page
				
				page = soTrang;
				for(int i=(page-1)*30;i<(page-1)*30+30 && i<dsSinhVien.size();i++) {
					SinhVien sv = dsSinhVien.get(i);
					double heso = 0;
					double dtk = 0;
					for (int j=0; j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						for (int k=0; k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								dtk += (diem.getDiem() * mh.getHeSo());
								heso += mh.getHeSo();
							}
						}
					}
					if(heso != 0) dtk /= heso;
					dtk = (double)(Math.round(dtk*100d))/100d;
					
					System.out.println("┌──────────────────────────────────────────┐");
					 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
					 System.out.format("│ DTK: %33.2f   │\n", dtk);
					System.out.println("├──────────────────────────────────────────┤");
					
					boolean hasDtk = false;
					for (int j=0;j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						
						for (int k=0; k<30 && k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								hasDtk = true;
								System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
							}
							
						}
						
					}
					String noti = "Chua co diem";
					if(!hasDtk)
						System.out.format("│ %38s   │\n",noti);
					System.out.println("└──────────────────────────────────────────┘");
				}
					
				System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
				break;
			case "5": // specific page  
				System.out.println("Nhap trang can hien thi: ");
//				page = sc.nextInt();
//				sc.nextLine();

				try {
					page = Integer.parseInt(sc.next());
				}catch (Exception e) {
					System.out.println("Loi: " + e.getMessage());
					// TODO: handle exception
					break;
				}
				
				if(page<1 || page>soTrang) {
					System.out.println("Khong co trang nay!");
					break;
				}
				
				for(int i=(page-1)*30;i<(page-1)*30+30 && i<dsSinhVien.size();i++) {
					SinhVien sv = dsSinhVien.get(i);
					double heso = 0;
					double dtk = 0;
					for (int j=0; j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						for (int k=0; k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								dtk += (diem.getDiem() * mh.getHeSo());
								heso += mh.getHeSo();
							}
						}
					}
					if(heso != 0) dtk /= heso;
					dtk = (double)(Math.round(dtk*100d))/100d;
					
					System.out.println("┌──────────────────────────────────────────┐");
					 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
					 System.out.format("│ DTK: %33.2f   │\n", dtk);
					System.out.println("├──────────────────────────────────────────┤");
					
					boolean hasDtk = false;
					for (int j=0;j<dsDiem.size();j++) {
						Diem diem = dsDiem.get(j);
						
						for (int k=0; k<30 && k<dsMonHoc.size();k++) {
							MonHoc mh = dsMonHoc.get(k);
							if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
								hasDtk = true;
								System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
							}
							
						}
						
					}
					String noti = "Chua co diem";
					if(!hasDtk)
						System.out.format("│ %38s   │\n",noti);
					System.out.println("└──────────────────────────────────────────┘");
				}
				
				System.out.println("\t--------Page "+ (page) +"/" + soTrang + " --------");
				break;
			case "0":// previous menu
				break;
			default:
				System.out.println("Nhap lai !");
				break;
			}
		}while(!choice.equals("0"));
		
	}

	public static void searchByID() {
		// TODO Auto-generated method stub
		
		System.out.println("Nhap ma sinh vien: ");
		
		String ma = sc.nextLine();
		boolean hasSv = false;
		
		for(int i=0; i<dsSinhVien.size();i++) {
			SinhVien sv = dsSinhVien.get(i);
			
			if(sv.getMa().equals(ma)) {
				hasSv = true;
				double heso = 0;
				double dtk = 0;
				for (int j=0; j<dsDiem.size();j++) {
					Diem diem = dsDiem.get(j);
					for (int k=0; k<dsMonHoc.size();k++) {
						MonHoc mh = dsMonHoc.get(k);
						if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
							dtk += (diem.getDiem() * mh.getHeSo());
							heso += mh.getHeSo();
						}
					}
				}
				if(heso != 0) dtk /= heso;
				dtk = (double)(Math.round(dtk*100d))/100d;
				
				System.out.println("┌──────────────────────────────────────────┐");
				 System.out.format("│ %-7s %30s   │\n", sv.getMa(), sv.getName());
				 System.out.format("│ DTK: %33.2f   │\n", dtk);
				System.out.println("├──────────────────────────────────────────┤");
				
				boolean hasDtk = false;
				for (int j=0;j<dsDiem.size();j++) {
					Diem diem = dsDiem.get(j);
					
					for (int k=0; k<30 && k<dsMonHoc.size();k++) {
						MonHoc mh = dsMonHoc.get(k);
						if(diem.getMaSv().equals(sv.getMa()) && diem.getMaMh().equals(mh.getMa())) {
							hasDtk = true;
							System.out.format("│ %-7s%-21s      %-6.2f │\n", mh.getMa(), mh.getTen(),diem.getDiem());
						}
						
					}
			}
				String noti = "Chua co diem";
				if(!hasDtk)
					System.out.format("│ %38s   │\n",noti);
				System.out.println("└──────────────────────────────────────────┘");
			
				break;
			}
			
		}
		if(!hasSv) System.out.println("Khong ton tai sinh vien nay!");
		
	}

	public static void searchByName() {
		System.out.println("Nhap ten sinh vien: ");
		String name = sc.nextLine();
		
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		for (SinhVien sv : dsSinhVien) {
			if(sv.getName().toLowerCase().contains(name.toLowerCase())) {
				list.add(sv);
			}
		}
		// count page number
				System.out.println("--------Danh sach sinh vien--------");
				String title = "┌─────────┬───────────────────────┬───────────┬────────────┬─────────┐\r\n"
						     + "│   Ma    │  Ho dem               │  Ten      │ ngay sinh  │Gioi tinh│\r\n"
						     + "├─────────┼───────────────────────┼───────────┼────────────┼─────────┤";
				int soTrang = list.size() / 30;
				if(soTrang * 30 < dsSinhVien.size()) soTrang++;
				int page = 1;
				
				
				// display
				System.out.println(title);
				for(int i=0;i<30 && i<list.size();i++) {
					SinhVien sv = list.get(i);
					System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
				}
				System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
				System.out.println("\t-------- Page "+ (page) +"/" + soTrang + " --------");
				
				
				String choice = "";
				
				do {
					System.out.println("1. Xem trang tiep theo        3. Den trang cuoi             \r\n"
							+ "2. Tro lai trang truoc        4. Den trang dau tien         \r\n"
							+ "5. Xem trang cu the           0. Tro ve menu truoc\n"
							+ "6. Xem chi tiet diem sinh vien");
					System.out.println("Nhap lua chon: ");
					choice = sc.nextLine();
					

					switch(choice) {
					case "1": // View next page
						if(page >= soTrang || page < 1) {
							System.out.println("Khong co trang nay!");
							break;
						}
						System.out.println(title);
						for (int i = page*30; i < page*30+30 && i<list.size() ; i++) {
							SinhVien sv = list.get(i);
							System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
						}
						System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
						System.out.println("\t-------- Page "+ (page+1) +"/" + soTrang + " --------");
						page++;
						break;
					case "2": // previous page
						if(page < 2) {
							System.out.println("Khong co trang nay!");
							break;
						}
						page -= 2;
						System.out.println(title);
						for (int i = page*30; i < page*30+30 && i<list.size() ; i++) {
							SinhVien sv = dsSinhVien.get(i);
							System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
						}
						System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
						System.out.println("\t-------- Page "+ (page+1) +"/" + soTrang + " --------");
						page++;
						break;
					case "4": // first page
						System.out.println(title);
						page = 1;
						
						for(int i=0;i<30 && i<list.size();i++) {
							SinhVien sv = list.get(i);
							System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
						}
						System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
						System.out.println("\t-------- Page "+ (page) +"/" + soTrang + " --------");
						
						break;
					case "3": // last page
						System.out.println(title);
						page = soTrang;
						for(int i=(page-1)*30;i<(page-1)*30+30 && i<list.size();i++) {
							SinhVien sv = list.get(i);
							System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
						}
						System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
						System.out.println("\t-------- Page "+ (page) +"/" + soTrang + " --------");
						break;
					case "5": // specific page  
						System.out.println("Nhap trang can hien thi: ");
//						page = sc.nextInt();
//						sc.nextLine();

						try {
							page = Integer.parseInt(sc.next());
						}catch (Exception e) {
							System.out.println("Loi: " + e.getMessage());
							// TODO: handle exception
							break;
						}
						
						if(page<1 || page>soTrang) {
							System.out.println("Khong co trang nay!");
							break;
						}
						System.out.println(title);
						for(int i=(page-1)*30;i<(page-1)*30+30 && i<list.size();i++) {
							SinhVien sv = list.get(i);
							System.out.format("│ %s │ %-21s │ %-10s│ %s │ %-8s│\n", sv.getMa(), sv.getHoDem(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh());
						}
						System.out.println("└─────────┴───────────────────────┴───────────┴────────────┴─────────┘");
						System.out.println("\t-------- Page "+ (page) +"/" + soTrang + " --------");
						break;
					case "6":
						searchByID();
						
						break;
					case "0":// previous menu
						break;
					default:
						System.out.println("Nhap lai !");
						break;
					}
				}while(!choice.equals("0"));
	}

	public static void onSystemExit() {
		// TODO Auto-generated method stub
		FileWriter fileWriter = null;
		BufferedWriter buffer = null;
		File file = new File(dataRoot + "\\" + fileSinhVien);
		
			try {
				fileWriter = new FileWriter(file);
				buffer = new BufferedWriter(fileWriter);
				for (SinhVien sv : dsSinhVien) {
					buffer.write(sv.getMa() + ";" + sv.getHoDem() +";"
							+ sv.getTen() + ";" + sv.getNgaySinh()+";"+sv.getGioiTinh()+"\n");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Loi: " + e);
			} finally {
				try {
					if(buffer != null )
						buffer.close();
					if(fileWriter != null) 
						fileWriter.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}