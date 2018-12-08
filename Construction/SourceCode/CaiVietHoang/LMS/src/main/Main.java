/*
. Full Name: Cai Việt Hoàng
. Date: 16 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package main;

import java.awt.EventQueue;

import boundary.LoginScreen;

/**
 * This class include main program block.
 * @author Cai Việt Hoàng
 */
public class Main
{
	/**
	 * This funtion to Launch the application.
	 * @author Cai Việt Hoàng
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginScreen window = new LoginScreen();
					window.getFrame().setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
}
