package agh.cs.project.main.viewer.components;

import agh.cs.project.main.viewer.listeners.SimulationStartButtonListener;

import javax.swing.*;
import java.awt.*;

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
		JPanel panel = new JPanel();
		panel.add(createPathLabel());
		panel.add(createPathTextField());
		panel.add(createStartAnimalLabel());
		panel.add(createStartAnimalTextField());
		panel.add(createStartSimulationButton());
		panel.setLayout(new GridLayout(5, 1, 0,10));
		return panel;
	}

	private JLabel createPathLabel()
	{
		JLabel toReturn = new JLabel("Options file path:");;
		return toReturn;
	}

	private JTextField createPathTextField()
	{
		JTextField toReturn = new JTextField("assets/parameters.json");
		toReturn.setSize(150, 15);
		return toReturn;
	}

	private JLabel createStartAnimalLabel() {
		JLabel toReturn = new JLabel("Number of animals");
		return toReturn;
	}

	private JTextField createStartAnimalTextField() {
		JTextField toReturn = new JTextField(10);
		toReturn.setText("10");
		return toReturn;
	}

	private JButton createStartSimulationButton() {
		JButton toReturn = new JButton("Start simulation");
		toReturn.addActionListener(new SimulationStartButtonListener());
		return toReturn;
	}
}
