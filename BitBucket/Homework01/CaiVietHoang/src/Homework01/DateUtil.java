package Homework01;

//import java.util.Date;
import java.util.Calendar;

public class DateUtil
{
	boolean checkDate(int day, int month, int year)//hàm kiểm tra ngày tháng năm hợp lệ
	{
		boolean check = false;
		if (month == 2)
		{
			if (1 <= day && day <= 28)
				check = true;
			else
				check = false;
		}

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		{
			if (1 <= day && day <= 31)
				check = true;
			else
				check = false;
		}
		
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			if (1 <= day && day <= 30)
				check = true;
			else
				check = false;
		}
		return check;
	}

	int calculateAge(int year) // hàm tính tuổi
	{
		Calendar today = Calendar.getInstance();
		int year0 = today.get(Calendar.YEAR);
		int age = year0 - year;
		return age;
	}
}
