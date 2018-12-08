import java.util.*;
public class Calculation 
{
	public static void main(String[] args)
	{
		float a,b,add,sub,mul,div;
		Scanner nhap = new Scanner(System.in);
		System.out.print("Insert digit a: ");
		a = nhap.nextFloat();
		System.out.print("Insert digit b: ");
		b = nhap.nextFloat();
		add = a+b;sub = a-b;mul = a*b;div = a/b;
		System.out.print("Below are some calculations\n");	
		System.out.printf("a + b = %.2f\na - b = %.2f\na * b = %.2f\na / b = %.2f",add,sub,mul,div);
		nhap.close();
	}
}