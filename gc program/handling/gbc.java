package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;


public class gbc extends GridBagConstraints
{
	public gbc(int x,int y)
	{
		gridx=x;
		gridy=y;
	}
	public gbc(int x,int y,int r,int s)
	{
		gridx=x;
		gridy=y;
		gridwidth=r;
		gridheight=s;
		
	}
	public gbc setAnchor(int x)
	{
		anchor=x;
		return this;
	}
	public gbc setFill(int f)
	{
		fill=f;
		return this;
		
	}
	public gbc setWeight(double x,double y)
	{
		weightx=x;
		weighty=y;
		return this;
	}
	public gbc setInsets(int d)
	{
		insets=new Insets(d,d,d,d);
		return this;
	}
	public gbc setInsets(int a,int b,int c,int d)
	{
		insets=new Insets(a,b,c,d);
		return this;
	}
	public gbc setIpad(int x,int y)
	{
		ipadx=x;
		ipady=y;
		return this;
	}
}