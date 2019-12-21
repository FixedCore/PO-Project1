package agh.cs.project.main.viewer.components;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.MapObjects.Grass;
import agh.cs.project.main.MapObjects.MapObject;
import agh.cs.project.main.map.WorldMap;
import agh.cs.project.main.movement.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;

public class WorldMapView extends JPanel
{
	public WorldMapView(WorldMap logic)
	{
		super();
		setSize(500,400);
		this.logic = logic;
		calculateCellDimensions();
	}

	private void calculateCellDimensions()
	{
		cellWidth = 500 /  logic.getStats.getData().mapSize.x;
		cellHeight = 400 / logic.getStats.getData().mapSize.y;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		paintMapElements(g);
	}

	private void paintMapElements(Graphics g)
	{
		for(int i = 0; i < logic.getStats.getData().mapSize.x; i++)
		{
			for(int j = 0; j < logic.getStats.getData().mapSize.y; j++)
			{
				Vector2d pos = new Vector2d(i,j);
				if(logic.isOccupied(pos))
				{
					drawObjectAt(g, pos);
				}
			}
		}
	}

	private void drawObjectAt(Graphics g, Vector2d pos)
	{
		MapObject o = (MapObject)logic.objectAt(pos);
		int x = getDrawingXCoordinate(pos);
		int y = getDrawingYCoordinate(pos);
		if(o instanceof Grass)
		{
			g.setColor(Color.YELLOW);
			g.fillRect(x,y,cellWidth, cellHeight);
		}
		if(o instanceof Animal)
		{
			g.setColor(calculateAnimalColor((Animal)o));
			g.fillRect(x,y,cellWidth,cellHeight);
		}
	}

	private Color calculateAnimalColor(Animal a)
	{
		int averageEnergy = logic.getStats.averageEnergy();
		int diff = a.getEnergy() - averageEnergy;
		return new Color(constrainColor(127 - diff), constrainColor(127 + diff), 0);
	}

	private int getDrawingXCoordinate(Vector2d pos)
	{
		return pos.x * cellWidth;
	}

	private int getDrawingYCoordinate(Vector2d pos)
	{
		return pos.y * cellHeight;
	}

	private int constrainColor(int val)
	{
		if(val < 0) return 0;
		if(val > 255) return 255;
		return val;
	}

	int cellWidth, cellHeight;
	private WorldMap logic;
}
