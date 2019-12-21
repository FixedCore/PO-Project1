package agh.cs.project.main.viewer.components;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.util.input.InputData;

import javax.swing.*;
import java.awt.*;

public class WorldWindow extends JFrame
{
	public WorldWindow(InputData data, int initialAnimalCount)
	{
		super();
		worldLogic = new WorldMap(data, initialAnimalCount);
		setSize(800,400);
		createPanels();
		add(stats);
		add(view);
		setLayout(new GridLayout(1,2));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void createPanels()
	{
		stats = new WorldStatsView(worldLogic);
		view = new WorldMapView(worldLogic);
	}


	private JPanel stats, view;

	WorldMap worldLogic;
}
