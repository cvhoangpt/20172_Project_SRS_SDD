package Homework01;

import java.util.Scanner;//khai báo thư viện Scanner

public class HelloWorld
{
	public static void main(String[] args)
	{
		DateUtil valid = new DateUtil();//Khởi tạo biến valid
		DateUtil display = new DateUtil();//Khởi tạo biến display
		String s;
		int day, month, year;
		Scanner insert = new Scanner(System.in);
		do //Hàm nhập và kiểm tra valid
		{
		System.out.println("Enter your name: ");
		s = insert.nextLine();
		System.out.print("Enter your birthday: ");
		System.out.println("Day: ");
		day = insert.nextInt();
		System.out.println("Month: ");
		month = insert.nextInt();
		System.out.println("Year: ");
		year = insert.nextInt();
		} while (valid.checkDate(day,month,year) == false);
		
		System.out.print("Hello " + s + ", your age is " + display.calculateAge(year));//In ra màn hình
		insert.close();
	}
}
