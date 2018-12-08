package squarenumber;
import java.util.Scanner;
public class SquareNumber {

	public static void main(String[] args) {
		
		
		 {

			 {
				Scanner scan = new Scanner(System.in);
				int n;
				System.out.println("nhap so can kiem tra : ");
				n= scan.nextInt();
				float kq = (float) Math.sqrt(n);
				if (kq == (int)kq)
				{
					System.out.println(n+ " la so chinh phuong ");
				}
				else
				{
					System.out.println(n+ " khong la so chinh phuong ");
				}

			}

		}

	}

}

