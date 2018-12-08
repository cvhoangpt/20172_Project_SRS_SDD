package Homework01;

import java.lang.Math.*;

public class NumberUtil
{
	boolean primeNumber(int A)// hàm kiểm tra số nguyên tố, hợp số
	{
		boolean check = true;
		if (A < 2)
			check = true;
		for (int i = 2; i <= Math.sqrt(A); i++)
		{
			if (A % i == 0)
				check = false;
		}
		return check;
	}

	boolean squareNumber(int B)// hàm kiểm tra số chính phương
	{
		boolean check;
		if (B == Math.sqrt(B) * Math.sqrt(B))
			check = true;
		else
			check = false;
		return check;
	}

	boolean perfectNumber(int C)// hàm kiểm tra số hoàn hảo
	{
		boolean check;
		int S = 0;
		for (int i = 1; i <= C; i++)
		{
			if (C % i == 0)
				S += i;
		}
		if (C == S)
			check = true;
		else
			check = false;
		return check;
	}
}
