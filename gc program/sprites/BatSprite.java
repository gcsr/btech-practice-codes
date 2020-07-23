public class BatSprite extends Sprite
{
	private static final int FLOOR_DIST=71;
	
	private int period;
	
	public BatSprite(int w,int h,ImageLoader imsLd,int p)
	{
		super(w/2,h-FLOOR_DIST,w,h,imsLd,"bat.gif");
		period=p;
		setStep(0,0);
	}
	public void moveLeft()
	{
		setStep(-XSTEP,0);
	}
	public void moveRight()
	{
		setStep(XSTEP,0);
	}
	public void stayStill()
	{
		setStep(0,0);
	
	}
	public void mouseMove(int x)
	{
		if(x<locx)
			moveLeft();
		else	
		if(x>(locx+getWidth()))
			moveRight();
		
		else
			stayStill();		
	}
	
	public void updateSprite()
	{
		if((locx+getWidth()<=0)&&(dx<0))
			locx=getPWidth()-1;
			
		else if((locx>=getPWidth()-1)&&(dx>0))
			locx=0;
			
		super.updateSprite();		
	}
}