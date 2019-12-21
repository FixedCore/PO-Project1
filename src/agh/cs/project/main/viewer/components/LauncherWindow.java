package agh.cs.project.main.viewer.components;

import agh.cs.project.main.util.input.InputData;
import agh.cs.project.main.util.input.OptionsParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LauncherWindow extends JFrame
{
	public LauncherWindow()
	{
		super("Simulation setup");
		setSize(300,500);
		add(createMainPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JPanel createMainPanel()
	{
		panel = new JPanel();
		worlds = new ArrayList<>();
		createAllComponents();
		panel.add(pathLabel);
		panel.add(pathField);
		panel.add(countLabel);
		panel.add(countField);
		panel.add(startButton);

		panel.setLayout(new GridLayout(5, 1, 0,10));
		return panel;
	}

	private void createAllComponents()
	{
		createPathLabel();
		createPathTextField();
		createStartAnimalLabel();
		createStartAnimalTextField();
		createStartSimulationButton();
	}

	private void createPathLabel()
	{
		pathLabel = new JLabel("Options file path:");;
	}

	private void createPathTextField()
	{
		pathField = new JTextField("assets/parameters.json");
		pathField.setSize(150, 15);
	}

	private void createStartAnimalLabel()
	{
		countLabel= new JLabel("Number of animals");
	}

	private void createStartAnimalTextField()
	{
		countField = new JTextField(10);
		countField.setText("100");
	}

	private void createStartSimulationButton() {
		startButton = new JButton("Start simulation");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				System.out.println("Pressed button");
				launchSimulation();
			}
		});
	}

	private void launchSimulation()
	{
		InputData data = parsePathText();
		int animalCount = parseCountText();
		if(data == null) System.out.println("No data provided");
		if(animalCount <= 0) System.out.println("Animal count failed");
		if(data != null && animalCount > 0) startNewSimulation(data, animalCount);
	}

	private InputData parsePathText()
	{
		String path = pathField.getText();
		InputData data = null;
		try
		{
			data = OptionsParser.parse(path);
		}
		catch (IllegalArgumentException ex)
		{
			System.out.println("File does not exist");
			return null;
		}
		catch(Exception ex)
		{
			System.out.println("Parsing failed");
			return null;
		}
		return data;
	}

	private int parseCountText()
	{
		String input = countField.getText();
		int out = 0;
		try
		{
			out = Integer.parseInt(input);
		}
		catch (Exception ex)
		{
			System.out.println("This is not a valid number");
			return -1;
		}
		return out;
	}

	private void startNewSimulation(InputData data, int animalCount)
	{
		WorldWindow warudo = new WorldWindow(data, animalCount);
		worlds.add(warudo);
	}

	private JPanel panel;
	private JLabel pathLabel, countLabel;
	private JTextField pathField, countField;
	private JButton startButton;

	private List<WorldWindow> worlds;
}
