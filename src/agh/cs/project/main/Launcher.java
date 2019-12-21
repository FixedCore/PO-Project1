package agh.cs.project.main;

import agh.cs.project.main.viewer.components.LauncherWindow;

import javax.swing.*;
import java.util.Arrays;

public class Launcher
{
	public static void main(String[] args)
	{
		try
		{
			run(args[0], Integer.parseInt(args[1]));
		}
		catch(Exception ex)
		{
			System.out.println("EXCEPTION");
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
			System.out.println(Arrays.toString(ex.getStackTrace()));
		}
	}

	private static void run(String path, int animalNumber)
	{
		JFrame optionsWindow = new LauncherWindow();
	}
}
