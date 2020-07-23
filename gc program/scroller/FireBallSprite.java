public class FireBallSprite extends Sprite
{
	int pwidth,pheight;
	JackPanel jp;
	ImageLoader imsLoader;
	JumperSprite js;
	public FireBallSprite(int pw,int ph,JackPanel jp,JumperSprite js)
	{
		super(pw,ph/2,pw,ph,imsLd,"red.gif");
		pwidth=pw;
		pheight=ph;
		this.jp=jp;
		this.js=js;
		imsLoader=new ImageLoader();
		
	}
	private void initPosition()
	{
		
		nameIndex=(nameIndex+1)%ballNames.length;
		setPosition((int)(getPWidth()*Math.random()),0);
		int xStep=((Math.random()<.5)?-5:5);
		setStep(xStep,5);
	}
}