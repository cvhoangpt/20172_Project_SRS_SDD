package sohoanhao;

import java.util.Scanner;

public class PerfectNumber {

	public static boolean SoHoanHao(int n) {
		int S=0;
		for(int i=1;i<n;i++) {
			if(n%i==0) {
				S=S+i;
			}
		}
		if(S==n) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("nhap vao so can kiem tra : ");
		n=sc.nextInt();
		
		boolean kt=SoHoanHao(n);
		if(kt==true) {
			System.out.println(n+ " la so hoan hao");
		}
		if(kt==false) {
			System.out.println(n+ " khong la so hoan hao");
		}
	}
}