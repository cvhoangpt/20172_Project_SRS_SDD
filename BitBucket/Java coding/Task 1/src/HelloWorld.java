import java.util.*;
public class HelloWorld 
{
	public static void main(String[] args)
	{
		String ten;
		Scanner nhap = new Scanner(System.in);
		System.out.print("Insert your name: ");
		ten = nhap.nextLine();
		System.out.printf("Hello %s",ten);
		nhap.close();
	}
}