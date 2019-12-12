package agh.cs.project.main.movement;

import java.util.Random;

public enum MapDirection {
	NORTH,
	NORTHEAST,
	EAST,
	SOUTHEAST,
	SOUTH,
	SOUTHWEST,
	WEST,
	NORTHWEST;

	public MapDirection toRight()
	{
		switch (this)
		{
			case NORTH:
				return NORTHEAST;
			case NORTHEAST:
				return EAST;
			case EAST:
				return SOUTHEAST;
			case SOUTHEAST:
				return SOUTH;
			case SOUTH:
				return SOUTHWEST;
			case SOUTHWEST:
				return WEST;
			case WEST:
				return NORTHWEST;
			case NORTHWEST:
				return NORTH;
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public MapDirection toLeft()
	{
		switch (this)
		{
			case NORTH:
				return NORTHWEST;
			case NORTHEAST:
				return NORTH;
			case EAST:
				return NORTHEAST;
			case SOUTHEAST:
				return EAST;
			case SOUTH:
				return SOUTHEAST;
			case SOUTHWEST:
				return SOUTH;
			case WEST:
				return SOUTHWEST;
			case NORTHWEST:
				return WEST;
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public Vector2d toUnitVector()
	{
		switch (this)
		{
			case NORTH:
				return new Vector2d(0,1);
			case NORTHEAST:
				return new Vector2d(1,1);
			case EAST:
				return new Vector2d(1,0);
			case SOUTHEAST:
				return new Vector2d(1,-1);
			case SOUTH:
				return new Vector2d(0,-1);
			case SOUTHWEST:
				return new Vector2d(-1,-1);
			case WEST:
				return new Vector2d(-1,0);
			case NORTHWEST:
				return new Vector2d(-1,1);
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	@Override
	public String toString()
	{
		switch (this)
		{
			case NORTH:
				return "N";
			case NORTHEAST:
				return "NE";
			case EAST:
				return "E";
			case SOUTHEAST:
				return "SE";
			case SOUTH:
				return "S";
			case SOUTHWEST:
				return "SW";
			case WEST:
				return "W";
			case NORTHWEST:
				return "NW";
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public String toLongString()
	{
		switch (this)
		{
			case NORTH:
				return "NORTH";
			case NORTHEAST:
				return "NORTH EAST";
			case EAST:
				return "EAST";
			case SOUTHEAST:
				return "SOUTHAST";
			case SOUTH:
				return "SOUTH";
			case SOUTHWEST:
				return "SOUTHWEST";
			case WEST:
				return "WEST";
			case NORTHWEST:
				return "NORTHWEST";
			default:
				throw new IllegalStateException("Unexpected value: " + this);
		}
	}

	public MapDirection rotateBy(int n)
	{
		n %= 8;
		MapDirection next = this;
		for(int i=0;i<n;i++) next = next.toRight();
		return next;
	}

	public static MapDirection getRandom()
	{
		if(randomizer == null) randomizer = new Random();
		MapDirection toReturn  = NORTH;
		toReturn.rotateBy(randomizer.nextInt(8));
		return toReturn;
	}


	private static Random randomizer;
}
