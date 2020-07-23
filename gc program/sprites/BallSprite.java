import java.awt.Rectangle;

public class BallSprite extends Sprite
{
	private static final String[] ballNames={"red.gif","blue.gif","green.gif","magenta.gif"
	};
	private static final int MAX_BALLS_RETURNED=16;
	
	private BugPanel bp;
	private BatSprite bat;
	private int numRebounds;
	private int nameIndex;
	
	public BallSprite(int w,int h,ImageLoader imsLd,BugPanel bp,BatSprite b)
	{
		super(w/2,0,w,h,imsLd,ballNames[0]);
		this.bp=bp;
		bat=b;
		nameIndex=0;
		numRebounds=MAX_BALLS_RETURNED/2;
		initPosition();
		
	}
	private void initPosition()
	{
		setImage(ballNames[nameIndex]);
		
		nameIndex=(nameIndex+1)%ballNames.length;
		int xStep=((Math.random()<.5)?-5:5);
		setStep(xStep,5);
	}
	public void updateSprite()
	{
		hasHitBat();
		goneOffScreen();
		hasHitWall();
		super.updateSprite();
	}
	private void hasHitWall()
	{
		if((locx<=0)&&(dx<0))
		{
			new playsequence().playsequenceok("A7#",0);
			dx=-dx;
		}
		else
			if((locx+getWidth()>=getPWidth())&&(dx>0))
			{
				new playsequence().playsequenceok("A7#",0);
				dx=-dx;
			}
		
	}
	private void goneOffScreen()
	{
		if(((locy+getHeight())<=0)&&(dy<0))
		{
			numRebounds++;
			if(numRebounds==MAX_BALLS_RETURNED)
				bp.gameOver();
			else
				initPosition();	
		}
		else
			if((locy>=getPHeight())&&(dy>0))
			{
				numRebounds--;
				if(numRebounds==0)
					bp.gameOver();
					else
						initPosition();
			}
	}
	private void hasHitBat()
	{

		Rectangle rect=getMyRectangle();
		if(rect.intersects(bat.getMyRectangle()))
		{
			new playsequence().playsequenceok("A7#",0);
			Rectangle interRect=rect.intersection(bat.getMyRectangle());
			dy=-dy;
			locy-=interRect.height;
		}
	}
	
}