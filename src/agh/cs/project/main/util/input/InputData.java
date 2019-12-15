package agh.cs.project.main.util.input;

import agh.cs.project.main.movement.Vector2d;

public class InputData
{
    public InputData()
    {
        jungleRatio = startEnergy = moveEnergy = plantEnergy = initialAnimalNumber =  0;
        mapSize = new Vector2d();
        jungleSize = new Vector2d();
    }


    public InputData(Vector2d size, double jungleRatio, int startEnergy, int moveEnergy, int plantEnergy, int initialAnimalNumber)
    {
        this.mapSize = size;
        this.jungleRatio = jungleRatio;
        this.jungleSize = new Vector2d((int) Math.floor(size.x * jungleRatio), (int) Math.floor(size.y * jungleRatio));
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.initialAnimalNumber = initialAnimalNumber;
    }

    public final Vector2d mapSize;
    public final double jungleRatio;
    public final Vector2d jungleSize;
    public final int startEnergy;
    public final int moveEnergy;
    public final int plantEnergy;
    public final int initialAnimalNumber;
}
