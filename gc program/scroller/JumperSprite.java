//0 not jumping
//1 rising
//2 falling
public class JumperSprite extends Sprite
{
	int pwidth,pheight;
	BricksManager bricksMan;
	int period;
	int moveSize;
	private int upCount;
	private int vertStep;
	private int xWorld,yWorld;
	public boolean isStill;
	private int vetMoveMode; 
		private boolean isFacingRight;

	public JumperSprite(int w,int h,int bsize,BricksManager bman,ImageLoader imsLd,int period)
	{
		super(w/2,h/2,w,h,imsLd,"green.gif");	
		bricksMan=bman;
		this.period=period;
		moveSize=bsize;
		setStep(0,0);
		
		isStill=true;
		
		locy=bricksMan.findFloor(locx+getWidth()/2)-getHeight();
		//System.out.println(locy+ "first");
		xWorld=locx;
		yWorld=locy;
		vetMoveMode=0;
		vertStep=11;
		
		upCount=0;
		
	}
	public boolean willHitBrick()
	{
		System.out.println("willHitBrick");
		if(isStill)
			return false;
			
		int xTest;	
		if(isFacingRight)
			xTest=xWorld+moveSize;
		else
			xTest=xWorld-moveSize;
			
		int xMid=xTest+getWidth()/2;
		int yMid=yWorld+(int)(getHeight());
		
		return bricksMan.insideBrick(xMid,yMid);			
	}
	public void stayStill()
	{
		System.out.println("is Still");
		isStill=true;
	}
	public void moveLeft()
	{
		System.out.println("sprite moveleft");
		isFacingRight=false;
		isStill=false;
	}
	public void moveRight()
	{
		System.out.println("sprite moveright");
		isFacingRight=true;
		isStill=false;
	}
	public void updateSprite()
	{
		if(!isStill)
		{
			if(isFacingRight)
				xWorld+=moveSize;
			else
				xWorld-=moveSize;
			if(vetMoveMode==0)
				checkIfFalling();	
				
		}
		if(vetMoveMode==1)
			updateRising();
			
		if(vetMoveMode==2)
			updateFalling();
		super.updateSprite();		
	}
	
	private void checkIfFalling()
	{
		System.out.println("checkiffalling");
		int yTrans=bricksMan.checkBrickTop(xWorld+(getWidth()/2),yWorld+getHeight()+vertStep,vertStep);
		if(yTrans!=0)
			vetMoveMode=2;			
	}
	private void updateRising()
	{
		System.out.println("updaterising");
		if(upCount==8)
		{
			vetMoveMode=2;
			upCount=0;
		}
		else{
			int yTrans=bricksMan.checkBrickBase(xWorld+(getWidth()/2),yWorld-vertStep,vertStep);
				if(yTrans==0)
				{
					vetMoveMode=2;
					upCount=0;
				}	
				else
				{
					setStep(0,-yTrans);
					yWorld-=yTrans;
					upCount++;
				}	
					
					
		}
	}
	private void updateFalling()
	{
		System.out.println("updatefalling");
		int yTrans=bricksMan.checkBrickTop(xWorld+(getWidth()/2),yWorld+getHeight()+vertStep,vertStep);
				if(yTrans==0)
					finishJumping();
				else
				{
					setStep(0,+yTrans);
					yWorld+=yTrans;
				}	
	}
	private void finishJumping()
	{
		System.out.println("finishjumping");
		vetMoveMode=0;
		setStep(0,0);
		upCount=0;
	}
	public void jump()
	{
		System.out.println("jump");
		if(vetMoveMode==0)
		{
			vetMoveMode=1;
			upCount=0;
		}
	}
}