package agh.cs.project.main.util;

public class InputData
{
    public InputData(Vector2d size, double jungleRatio, int startEnergy, int moveEnergy, int plantEnergy, int minimumBreedingEnergy)
    {
        this.size = size;
        this.jungleRatio = jungleRatio;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
    }
    public final Vector2d size;
    public final double jungleRatio;
    public final int startEnergy;
    public final int moveEnergy;
    public final int plantEnergy;
}
