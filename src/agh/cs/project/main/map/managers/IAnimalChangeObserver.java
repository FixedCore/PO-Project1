package agh.cs.project.main.map.managers;

import agh.cs.project.main.MapObjects.Animal;
import agh.cs.project.main.movement.Vector2d;

public interface IAnimalChangeObserver
{
	void animalHasSpawned(Animal a);
	void animalHasMoved(Animal a, Vector2d newPosition);
	void animalHasDied(Animal a);
}
