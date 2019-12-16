package agh.cs.project.main.util.input;

import agh.cs.project.main.movement.Vector2d;

public class OptionsParser
{
	public static InputData parse(String path)
	{
		return new InputData();
	}
	public static InputData parseDummy(String path)
	{
		return new InputData(new Vector2d(20,20), 0.25, 20, 1, 5, 4);
	}
}
