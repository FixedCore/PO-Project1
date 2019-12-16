package agh.cs.project.main.MapObjects;

import agh.cs.project.main.movement.Vector2d;

public class AnimalUpdate
{
	public AnimalUpdate(Animal animal, Vector2d newPosition)
	{
		this.animal = animal;
		this.newPosition = newPosition;
	}
	public final Animal animal;
	public final  Vector2d newPosition;
}
