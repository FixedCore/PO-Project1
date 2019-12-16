package agh.cs.project.main.util.input;

import agh.cs.project.main.movement.Vector2d;

public class InputData
{
    public InputData(InputDataIntermediate half)
    {
        this.mapSize = new Vector2d(half.width, half.height);
        this.jungleRatio = half.jungleRatio;
        this.jungleSize = new Vector2d((int)(half.width * half.jungleRatio), (int)(half.height * half.jungleRatio));
        this.startEnergy = half.startEnergy;
        this.moveEnergy = half.moveEnergy;
        this.plantEnergy = half.plantEnergy;
    }

    public InputData()
    {
        jungleRatio = startEnergy = moveEnergy = plantEnergy =  0;
        mapSize = new Vector2d();
        jungleSize = new Vector2d();
    }


    public InputData(Vector2d size, double jungleRatio, int startEnergy, int moveEnergy, int plantEnergy)
    {
        this.mapSize = size;
        this.jungleRatio = jungleRatio;
        this.jungleSize = new Vector2d((int) Math.floor(size.x * jungleRatio), (int) Math.floor(size.y * jungleRatio));
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Map size = ");
        builder.append(mapSize.toString());
        builder.append("\r\nJungle ratio = ");
        builder.append(jungleRatio);
        builder.append("\r\nStartEnergy = ");
        builder.append(startEnergy);
        builder.append("\r\nmoveEnergy = ");
        builder.append(moveEnergy);
        builder.append("\r\nplantEnergy = ");
        builder.append(plantEnergy);
        builder.append("\r\n");
        return builder.toString();
    }

    public final Vector2d mapSize;
    public final double jungleRatio;
    public final Vector2d jungleSize;
    public final int startEnergy;
    public final int moveEnergy;
    public final int plantEnergy;
}
