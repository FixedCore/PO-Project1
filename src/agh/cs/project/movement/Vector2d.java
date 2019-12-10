package agh.cs.project.movement;

public class Vector2d
{
	public Vector2d()
	{
		x = y = 0;
	}

	public Vector2d(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2d(Vector2d source)
	{
		this.x = source.x;
		this.y = source.y;
	}

	public Vector2d add(Vector2d other)
	{
		return new Vector2d(this.x + other.x, this.y + other.y);
	}

	public Vector2d subtract(Vector2d other)
	{
		return new Vector2d(this.x - other.x, this.y - other.y);
	}

	public boolean precedes(Vector2d other)
	{
		return this.x <= other.x && this.y <= other.y;
	}

	public boolean follows(Vector2d other)
	{
		return this.x >= other.x && this.y >= other.y;
	}


	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object other)
	{
		if(this==other) return true;
		if(!(other instanceof Vector2d)) return false;
		Vector2d tested=(Vector2d) other;
		return (this.x == tested.x && this.y == tested.y);
	}

	@Override
	public int hashCode()
	{
		return (x * 83) + (y * 41);
	}

	public final int x, y;
}
