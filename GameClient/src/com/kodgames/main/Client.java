package com.kodgames.main;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends Frame{
	private final int CLIENT_WIDTH = 640;
	private final int CLIENT_HEIGHT = 480;
	
	private Image imageBuff;
	
	private DistantSoldier disSoldier;
	
	public Client(){
		disSoldier = new DistantSoldier();
	}
	
	public void init(){
		this.setBounds(0, 0, CLIENT_WIDTH, CLIENT_HEIGHT);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		this.addMouseListener(new ResponseMouseClicked());
		
		imageBuff = createImage(CLIENT_WIDTH, CLIENT_HEIGHT);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics bg = imageBuff.getGraphics();
		Color color = bg.getColor();
		bg.setColor(new Color(0,0,0));
		bg.fillRect(0, 0, imageBuff.getWidth(null), imageBuff.getHeight(null));
		bg.setColor(color);
		
		disSoldier.paint(bg);
		
		bg.dispose();
		
		g.drawImage(imageBuff, 0, 0, CLIENT_WIDTH, CLIENT_HEIGHT, null);
	}
	
	public void update(){
		disSoldier.update();
		
		this.repaint();
	}
	
	private class ResponseMouseClicked extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			disSoldier.responseMouseClicked(e);
		}
	}
}

class Object{
	private float x,y;
	private float vx,vy;
	private float speed;
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getVx() {
		return vx;
	}
	public void setVx(float vx) {
		this.vx = vx;
	}
	public float getVy() {
		return vy;
	}
	public void setVy(float vy) {
		this.vy = vy;
	}
	public float getSpeed(){
		return speed;
	}
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public void setVelocityByTwoPoint(float srcx, float srcy, float destx, float desty){
		float tmpx = destx-srcx;
		float tmpy = desty-srcy;
		
		float tmp = (float)Math.sqrt((double)(tmpx*tmpx+tmpy*tmpy));
		
		float t = tmp/speed;
		
		vx = tmpx/t;
		vy = tmpy/t;
	}
}

class DistantSoldier extends Object{
	private int width;
	private int height;
	
	public DistantSoldier(){
		width = 30;
		height = 30;
		init(100,100, 0f, 0f);
		setSpeed(1);
	}
	
	public void init(float x, float y, float vx, float vy){
		setX(x);
		setY(y);
		setVx(vx);
		setVy(vy);
	}
	
	public void paint(Graphics g){
		Color color = g.getColor();
		
		g.setColor(new Color(123,14,66));
		g.fillOval((int)(getX()-width/2), (int)(getY()-height/2), width, height);
	
		g.setColor(color);
	}
	
	public void update(){
		setX(getX()+getVx());
		setY(getY()+getVy());
	}

	public void responseMouseClicked(MouseEvent e){
		setVelocityByTwoPoint(getX(), getY(), e.getX(), e.getY());
	}
}
