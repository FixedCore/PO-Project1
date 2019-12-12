package agh.cs.project.main;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.util.InputData;
import agh.cs.project.main.util.OptionsParser;

public class World
{
	public static void main(String[] args)
	{
		try
		{
			run(args[1]);
		}
		catch(Exception ex)
		{

		}
	}

	private static void run(String path)
	{
		InputData progData = OptionsParser.parse(path);
		WorldMap world = new WorldMap(progData);
		while(world.isOn())
		{
			world.move();
		}
	}
}
