package agh.cs.project.main.viewer.components;

import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.util.input.InputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldWindow extends JFrame implements ActionListener
{
	public WorldWindow(InputData data, int initialAnimalCount)
	{
		super();
		logic = new WorldMap(data, initialAnimalCount);
		timer = new Timer(100, this);
		setSize(1000,400);
		createPanels();
		add(stats);
		add(view);
		setLayout(new GridLayout(1,2));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		timer.start();
	}

	private void createPanels()
	{
		stats = new WorldStatsView(logic);
		view = new WorldMapView(logic);
	}


	private JPanel stats, view;
	private Timer timer;
	private WorldMap logic;

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		logic.run();
		stats.revalidate();
		((WorldStatsView)stats).refreshData();
		stats.repaint();
		view.repaint();
		if(!logic.isPopulated()) timer.stop();
	}
}
