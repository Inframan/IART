package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import neural.Network;

public class NPanel  extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Network neuronal = new Network(70, 33, 0.5, 1);
	private Graphics2D g2d;

	private NFrame nF;


	public NPanel(NFrame nF) throws IOException {
		this.nF = nF;

		setFocusable(true);
		requestFocus();
		//loadImage();
	}

	public void init()
	{		
		nF.frmNetwork.setResizable(true);
		setVisible(true);
		repaint();
		requestFocus();
	}

	public void loadImage() throws IOException {
		/*
		wallIMG = ImageIO.read(new File("res/wall.png"));
		pathIMG = ImageIO.read(new File("res/path.png"));
		exitIMG = ImageIO.read(new File("res/exit.png"));
		heroIMG = ImageIO.read(new File("res/hero.png"));
		swordIMG = ImageIO.read(new File("res/sword.png"));
		dragonIMG = ImageIO.read(new File("res/dragon.png"));
		DragonSwordIMG = ImageIO.read(new File("res/dragonSword.png"));
		heroSwordIMG = ImageIO.read(new File("res/HeroSword.png"));
		sleepDragonIMG = ImageIO.read(new File("res/SleepDragon.png"));
		eagleIMG = ImageIO.read(new File("res/eagle.png"));
		 */
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;
		g2d.draw3DRect(20, 20, 50, 50, false);;

	}
}
