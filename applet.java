package meme.javapacman.show;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import meme.javapacman.pacman.Pacman;

public class applet extends Applet
implements ActionListener
{
	private static final long serialVersionUID = -749993332452315528L;

	static Pacman pacMan=null;

	public void init()
	{
		setSize(50,50);
		// create button
		setLayout(new FlowLayout(FlowLayout.CENTER));
		Button play=new Button("PLAY");
		add(play);

		play.addActionListener(this);

		//      newGame();
	}

	void newGame()
	{
		pacMan=new Pacman();
	}
	public void actionPerformed(ActionEvent e)
	{
		if ( pacMan != null && ! pacMan.isFinalized() )
			// another is running
			return;
		newGame();
	}

}
