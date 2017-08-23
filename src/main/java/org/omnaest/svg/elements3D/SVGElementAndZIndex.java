package org.omnaest.svg.elements3D;

import org.omnaest.svg.elements.base.SVGElement;

public class SVGElementAndZIndex
{
	private SVGElement	element;
	private double		zIndex;

	public SVGElementAndZIndex(SVGElement element, double zIndex)
	{
		super();
		this.element = element;
		this.zIndex = zIndex;
	}

	public SVGElement getElement()
	{
		return element;
	}

	public double getzIndex()
	{
		return zIndex;
	}

	@Override
	public String toString()
	{
		return "SVGElementAndZIndex [element=" + element + ", zIndex=" + zIndex + "]";
	}

}