
package meme.javapacman.pacman;

class Pacmove
{

	int iDirScore[];

	int iValid[]; 

	Pac cPac;
	Ghost [] cGhost;
	Maze cMaze;

	Pacmove(Pac pac, Ghost ghost[], Maze maze)
	{
		iDirScore=new int[4];

		iValid=new int [4];
		cPac=pac;

		cGhost=new Ghost[4];
		for (int i=0; i<4; i++)
			cGhost[i]=ghost[i];

		cMaze=maze;
	}

	public int GetNextDir()
	throws Error
	{
		int i;

		// first, init to 0
		for (i=0; i<4; i++)
			iDirScore[i]=0;

		// add score for dot
		AddDotScore();

		// add score for ghosts
		AddGhostScore();

		// add score for powerdot
		AddPowerDotScore();

		// determine the direction based on scores

		for (i=0; i<4; i++)
			iValid[i]=1;

		int iHigh, iHDir;

		while (true) 
		{
			iHigh=-1000000;
			iHDir=-1;
			for (i=0; i<4; i++)
			{
				if (iValid[i] == 1 && iDirScore[i]>iHigh)
				{
					iHDir=i;
					iHigh=iDirScore[i];
				}
			}

			if (iHDir == -1)
			{
				throw new Error("Pacmove: can't find a way?");
			}

			if ( cPac.iX%16 == 0 && cPac.iY%16==0)
			{
				if ( cPac.mazeOK(cPac.iX/16 + Tables.iXDirection[iHDir],
						cPac.iY/16 + Tables.iYDirection[iHDir]) )
					return(iHDir);
			}
			else
				return(iHDir);

			iValid[iHDir]=0;

		}

		//	return(iHDir);  // will not reach here, ordered by javac
	}

	void AddGhostScore()
	{
		int iXDis, iYDis;	// distance
		double iDis;		// distance

		int iXFact, iYFact;

		// calculate ghosts one by one
		for (int i=0; i<4; i++)
		{
			iXDis=cGhost[i].iX - cPac.iX;
			iYDis=cGhost[i].iY - cPac.iY;

			iDis=Math.sqrt(iXDis*iXDis+iYDis*iYDis);

			if (cGhost[i].Status == cGhost[i].BLIND)
			{


			}
			else
			{
				// adjust iDis into decision factor

				iDis=18*13/iDis/iDis;
				iXFact=(int)(iDis*iXDis);
				iYFact=(int)(iDis*iYDis);

				if (iXDis >= 0)
				{
					iDirScore[Tables.LEFT] += iXFact;
				}
				else
				{
					iDirScore[Tables.RIGHT] += -iXFact;
				}

				if (iYDis >= 0)
				{
					iDirScore[Tables.UP] += iYFact;
				}
				else
				{
					iDirScore[Tables.DOWN] += -iYFact;
				}
			}
		}
	}

	void AddDotScore()
	{
		int i, j;

		int dDis, dShortest;
		int iXDis, iYDis;
		int iX=0, iY=0;

		dShortest=1000;

		// find the nearest dot
		for (i=0; i < Maze.HEIGHT; i++)
			for (j=0; j < Maze.WIDTH; j++)
			{
				if (cMaze.iMaze[i][j]==Maze.DOT)
				{
					iXDis=j*16-8-cPac.iX;
					iYDis=i*16-8-cPac.iY;
					dDis=iXDis*iXDis+iYDis*iYDis;

					if (dDis<dShortest)
					{
						dShortest=dDis;

						iX=iXDis; iY=iYDis;
					}
				}	
			}

		// now iX and iY is the goal (relative position)

		int iFact=100000;

		if (iX > 0)
		{
			iDirScore[Tables.RIGHT] += iFact;
		}
		else if (iX < 0)
		{
			iDirScore[Tables.LEFT] += iFact;
		}

		if (iY > 0)
		{
			iDirScore[Tables.DOWN] += iFact;
		}
		else if (iY<0)
		{
			iDirScore[Tables.UP] += iFact;
		}
	}

	void AddPowerDotScore()
	{

	}
}
