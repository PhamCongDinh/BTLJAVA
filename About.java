package meme.javapacman.pacman;

import java.awt.*;
import java.awt.event.*;

class About extends Window
implements MouseListener
{
	private static final long serialVersionUID = -6444989674095739037L;

	final String[] about = {
			"",
			"javaPacman",
			"",
			"  - the designer was created by group 8-",
			"",
			"there is an intelligent pacman game implmented in Java",
			""
	};

	About(Frame parent)
	{
		super(parent);

		setSize(420, 280);
		setLocation(100, 100);
		show();

		addMouseListener(this);
	}

	public void paint(Graphics g2d)
	{
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Arial", Font.BOLD, 12));
		for (int i=0; i<about.length; i++)
			g2d.drawString(about[i], 6, (i+1)*18);
	}

	public void mouseClicked(MouseEvent e)
	{
		dispose();
		// e.consume();
	}

	public void mousePressed(MouseEvent e) 
	{}

	public void mouseReleased(MouseEvent e) 
	{}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}



