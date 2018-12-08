package Homework01;

import java.util.Scanner;//khai báo thư viện Scanner

public class FirstNumberApp
{
	public static void main(String[] args)
	{
		double s;
		int check = 0;
		Scanner insert = new Scanner(System.in);
		do
		{
			System.out.print("Enter an integer: ");
			s = insert.nextDouble();
			if (s == (int) s)
				check = 1;// kiểm tra xem có phải số nguyên không
		} while (check == 0);
		insert.close();

		NumberUtil valid = new NumberUtil();
		// Các lệnh in ra màn hình
		if (valid.primeNumber((int) s) == true)
			System.out.print("This is prime number");
		else
			System.out.println("This is composite number");

		if (valid.squareNumber((int) s) == true)
			System.out.println("This is square number");
		else
			System.out.println("This isn't square number");

		if (valid.perfectNumber((int) s) == true)
			System.out.println("This is perfect number");
		else
			System.out.println("This isn't perfect number");
	}
}
