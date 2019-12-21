package agh.cs.project.main.viewer.components;

import agh.cs.project.main.map.WorldMap;

import javax.swing.*;
import java.awt.*;

public class WorldStatsView extends JPanel
{
	public WorldStatsView(WorldMap logic)
	{
		super();
		this.logic = logic;
		setSize(500,400);
		setLayout(new GridLayout(6,2));
		addComponents();
	}

	private void addComponents()
	{
		createLabels();
		createDataLabels();
		add(animalCountLabel);
		add(animalCountData);
		add(grassCountLabel);
		add(grassCountData);
		add(dominantGenomeLabel);
		add(dominantGenomeData);
		add(averageEnergyLabel);
		add(averageEnergyData);
		add(averageLifespanLabel);
		add(averageLifespanData);
		add(averageChildCountLabel);
		add(averageChildCountData);
	}

	private void createDataLabels()
	{
		animalCountData = new JLabel(logic.getStats.animalCount());
		grassCountData = new JLabel(logic.getStats.grassCount());
		dominantGenomeData = new JLabel(logic.getStats.dominantGenome());

		int temp = logic.getStats.averageEnergy();
		if(temp < 0) averageEnergyData = new JLabel("No animals alive");
		else averageEnergyData = new JLabel(String.valueOf(temp));

		temp = logic.getStats.averageLifespan();
		if(temp < 0) averageLifespanData = new JLabel("No animals have died");
		else averageLifespanData = new JLabel(String.valueOf(logic.getStats.averageLifespan()));

		temp = logic.getStats.averageChildrenCount();
		if(temp < 0) averageChildCountData = new JLabel("No animals alive");
		else averageChildCountData = new JLabel(String.valueOf(logic.getStats.averageChildrenCount()));
	}

	private void createLabels()
	{
		animalCountLabel = new JLabel("Number of animals: ");
		grassCountLabel = new JLabel("Number of plants: ");
		dominantGenomeLabel = new JLabel("Dominant Genome: ");
		averageEnergyLabel = new JLabel("Average energy: ");
		averageLifespanLabel = new JLabel("Average lifespan: ");
		averageChildCountLabel = new JLabel("Average number of children: ");
	}
/*
	@Override
	public void repaint()
	{
		super.repaint();
		refreshData();
	}
 */

	public void refreshData()
	{
		animalCountData.setText(logic.getStats.animalCount());
		grassCountData.setText(logic.getStats.grassCount());
		dominantGenomeData.setText(logic.getStats.dominantGenome());

		int temp = logic.getStats.averageEnergy();
		if(temp < 0) averageEnergyData.setText("No animals alive");
		else averageEnergyData.setText(String.valueOf(temp));

		temp = logic.getStats.averageLifespan();
		if(temp < 0) averageLifespanData.setText("No animals have died");
		else averageLifespanData.setText(String.valueOf(temp));

		temp = logic.getStats.averageChildrenCount();
		if(temp < 0) averageChildCountData.setText("No animals alive");
		else averageChildCountData.setText(String.valueOf(temp));

	}

	private WorldMap logic;

	private JLabel animalCountLabel,
		grassCountLabel,
		dominantGenomeLabel,
		averageEnergyLabel,
		averageLifespanLabel,
		averageChildCountLabel;

	private JLabel animalCountData,
		grassCountData,
		dominantGenomeData,
		averageEnergyData,
		averageLifespanData,
		averageChildCountData;
}
