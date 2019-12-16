package agh.cs.project.main;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.util.input.InputData;
import agh.cs.project.main.util.input.OptionsParser;

public class World
{
	public static void main(String[] args)
	{
		try
		{
			run(filePath);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
		}
	}

	private static void run(String path)
	{
		InputData progData = OptionsParser.parseDummy(path);
		WorldMap world = new WorldMap(progData);
		while(world.isPopulated()) world.run();
	}

	private static final String filePath = "parameters.json";
}
