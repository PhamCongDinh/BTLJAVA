package meme.javapacman.pacman;

public class Cuty
{
	public static int RandDo(int iOdds)
	{
		if ( Math.random()*iOdds < 1 )
			return(1);
		return(0);
	}	

	// return a random number within [0..iTotal)
	public static int RandSelect(int iTotal)
	{
		double a;
		a=Math.random();
		a=a*iTotal;
		return( (int) a );
	}

	public static int IntSign(int iD)
	{
		if (iD==0)
			return(0);
		if (iD>0)
			return(1);
		else
			return(-1);
	}
}
