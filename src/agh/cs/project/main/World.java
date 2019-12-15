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

		}
	}

	private static void run(String path)
	{
		InputData progData = OptionsParser.parse(path);
		WorldMap world = new WorldMap(progData);

	}

	private static final String filePath = "parameters.json";
}
