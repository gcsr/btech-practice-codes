package gui;

import java.awt.*;

public class gbc extends GridBagConstraints
{
	//int gridx,gridy,gridheight,gridwidth;
	public gbc(int gridx,int gridy)
	{
		this.gridx=gridx;
		this.gridy=gridy;
	}
	public gbc(int gridx,int gridy,int gridwidth,int gridheight)
	{
		this.gridx=gridx;
		this.gridy=gridy;
		this.gridheight=gridheight;
		this.gridwidth=gridwidth;
	}
	public gbc setAnchor(int anchor)
	{
		this.anchor=anchor;
		return this;
	}
	
	public gbc setFill(int fill)
	{
		this.fill=fill;
		return this;
	}
	
	public gbc setWeight(double weightx,double weighty)
	{
		this.weightx=weightx;
		this.weighty=weighty;
		return this;
	}
	public gbc setInsets(int distance)
	{
		this.insets=new Insets(distance,distance,distance,distance);
		return this;
	}
	public gbc setInsets(int top,int left,int bottom,int right)
	{
		this.insets=new Insets(top,left,bottom,right);
		return this;
	}
	public gbc setIpad(int ipadx,int ipady)
	{
		this.ipadx=ipadx;
		this.ipady=ipady;
		return this;
	}
}