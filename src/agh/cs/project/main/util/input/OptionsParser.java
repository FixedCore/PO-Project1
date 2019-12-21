package agh.cs.project.main.util.input;

import agh.cs.project.main.movement.Vector2d;
import com.google.gson.Gson;

import java.io.FileReader;

public class OptionsParser
{
	public static InputData parse(String path) {
		Gson gson = new Gson();
		FileReader input;
		try
		{
			input = new FileReader(path);
		}
		catch (Exception e)
		{
			System.out.println("File " + path + " does not exist");
			throw new IllegalArgumentException("File does not exist");
		}
		InputDataIntermediate halfData = gson.fromJson(input, InputDataIntermediate.class);
		InputData fullData = new InputData(halfData);
		System.out.println(fullData.toString());
		return fullData;
	}

	public static InputData parseDummy(String path)
	{
		return new InputData(new Vector2d(20,20), 0.25, 20, 1, 5);
	}
}
