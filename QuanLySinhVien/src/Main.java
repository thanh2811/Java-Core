import java.util.Scanner;

import controls.DataIO;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// doc du lieu
		DataIO.dataRoot = args[0];
		DataIO.loadData();
			
		String choice = "";
		
		do {
			// in menu
			printMenu1();
			// nhap lua chon
		
			System.out.println("Nhap lua chon: ");
			choice = sc.nextLine();
		
			switch(choice) {
			case "1": 
				capNhatDs();
				break;
			case "2":
				DataIO.displayBangDiem();
//				hienThiBangDiem();
				break;
			case "3": 
				timKiem();
				break;
			case "0":
				DataIO.onSystemExit();
				System.out.println("System exited");
				break;
			default:
				System.out.println("Nhap lai !");
				break;
			}
		}while(!choice.equals("0"));
	}

	private static void printMenuCapNhat() {
		// TODO Auto-generated method stub
		System.out.println("\t------------------");
		String s ="┌──────────────────────────────────┐\r\n"
				+ "│   Cap nhat danh sach             │\r\n"
				+ "├──────────────────────────────────┤\r\n"
				+ "│ 1. Cap nhat danh sach sinh vien  │\r\n"
				+ "│ 2. Cap nhat danh sach mon hoc    │\r\n"
				+ "│ 0. Tro ve menu truoc             │\r\n"
				+ "└──────────────────────────────────┘";
		System.out.println(s);
	}

	private static void timKiem() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String choice = "";
		
		do {
			System.out.println("\t------------------");
			System.out.println("┌──────────────────────────────────┐\r\n"
					         + "│   TIM KIEM                       │\r\n"
					         + "├──────────────────────────────────┤\r\n"
					         + "│ 1. Tim kiem theo ma sinh vien    │\r\n"
					         + "│ 2. Tim kiem theo ten sinh vien   │\r\n"
					         + "│ 0. Tro ve menu truoc             │\r\n"
					         + "└──────────────────────────────────┘"	);
		
			System.out.println("Nhap lua chon: ");
			choice = sc.nextLine();
		
			switch(choice) {
			case "1": 
				DataIO.searchByID();
				break;
			case "2":
				DataIO.searchByName();
				break;
			case "0":
				break;
			default:
				System.out.println("Nhap lai: ");
				break;
			}
		}while(!choice.equals("0"));
		
		
	}

	
	private static void capNhatDs() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String choice;
		do {
			// in menu
			printMenuCapNhat();
			// nhap lua chon
			System.out.print("Nhap lua chon: ");
			choice = sc.nextLine();
			
			switch(choice) {
			case "1": 
			   	capNhatSv();			   	
				break;
			case "2":
				DataIO.displayMonHoc();
				break;
			case "0": 
				break;
			default:
				System.out.println("Nhap lai !");
				break;
			
			}
		}while(!choice.equals("0"));
	  
	}

	private static void printMenuCapNhatSv() {
		// TODO Auto-generated method stub
		System.out.println("\t------------------");
		 String s =   "┌──────────────────────────────────┐\r\n"
			 		+ "│       DANH SACH SINH VIEN        │\r\n"
			 		+ "├──────────────────────────────────┤\r\n"
			 		+ "│ 1. Them sinh vien                │\r\n"
			 		+ "│ 2. Sua thong tin sv              │\r\n"
			 		+ "│ 3. Xoa sinh vien                 │\r\n"
			 		+ "│ 4. Hien thi danh sach            │\r\n"
			 		+ "│ 0. Tro ve menu truoc             │\r\n"
			 		+ "└──────────────────────────────────┘";
		   	System.out.println(s);
	}

	private static void capNhatSv() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String choice;
		do {
			printMenuCapNhatSv();
			System.out.print("Nhap lua chon: ");
			choice = sc.nextLine();

			switch(choice) {
			case "1": 
			   System.out.println("Nhap thong tin sinh vien theo dang\r\n"
			   		+ "[ho va ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]\r\n"
			   		+ "Vi du: Nguyen Van A;14/06/1996;Nam\r\n"
			   		+ "Nhap exit de quay lai\r\n");

			   	String info;
			   while(true) {
				   System.out.println( "Nhap:");
					info = sc.nextLine();
				   	if(info.equalsIgnoreCase("exit")) {
				   		break;
				   	}
				   	if(DataIO.addSv(info) == false) {
				   		break;
				   	}
			   }
			   
				break;
			case "2":
				DataIO.editSv();
				break;
			case "3":
				DataIO.deleteSv();
				break;
			case "4":
				DataIO.displaySvList();
				break;
			case "0": 
				break;
			default:
				System.out.println("Nhap lai !");
				break;
			
			}
		}while(!choice.equals("0"));
	}

	private static void printMenu1() {
		// TODO Auto-generated method stub
		System.out.println("\t------------------");
		System.out.println("┌────────────────────────┐");
		System.out.println("│          MENU          │");
		System.out.println("├────────────────────────┤");
		System.out.println("│1. Cap nhat danh sach.  │");
		System.out.println("│2. Hien thi bang diem.  │");
		System.out.println("│3. Tim kiem.            │");
		System.out.println("│0. Exit.                │");
		System.out.println("└────────────────────────┘");
	}
	
}

















