package agh.cs.project.main;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.util.input.InputData;
import agh.cs.project.main.util.input.OptionsParser;

public class World
{
	public static void main(String[] args)
	{
		run(args[0], Integer.parseInt(args[1]));
		/*
		try
		{

		}
		catch(Exception ex)
		{
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
		}
		 */
	}

	private static void run(String path, int animalNumber)
	{
		InputData progData = OptionsParser.parse(path);
		WorldMap world = new WorldMap(progData, animalNumber);
		while(world.isPopulated())
		{
			world.run();
			System.out.println(world.getYear());
		}
		System.out.println("koniec");
	}
}
