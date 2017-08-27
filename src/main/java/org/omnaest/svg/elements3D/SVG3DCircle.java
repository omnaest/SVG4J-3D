/*

	Copyright 2017 Danny Kunz

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


*/
package org.omnaest.svg.elements3D;

import org.omnaest.svg.elements.SVGCircle;
import org.omnaest.svg.elements.base.SVGElement;
import org.omnaest.vector.Vector;

public class SVG3DCircle implements SVG3DElement
{
	private int	x;
	private int	y;
	private int	z;
	private int	r;

	private String	strokeColor	= "white";
	private String	fillColor	= "red";
	private int		strokeWidth	= 3;

	public SVG3DCircle(int x, int y, int z, int r)
	{
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
	}

	@Override
	public SVGElementAndZIndex projection(double angleX, double angleY, double angleZ, int depth)
	{
		Vector location = new Vector(this.x, this.y, this.z).rotate(angleX, angleY, angleZ);

		double maxDistance = Math.sqrt(depth);
		int projectedRadius = (int) (this.r * maxDistance / Math.sqrt(maxDistance * maxDistance + location.getZ()));

		SVGElement element = new SVGCircle((int) location.getX(), (int) location.getY(), projectedRadius)	.setFillColor(this.fillColor)
																											.setStrokeColor(this.strokeColor)
																											.setStrokeWidth(this.strokeWidth);

		double zIndex = location.getZ();
		return new SVGElementAndZIndex(element, zIndex);
	}

	public SVG3DCircle setStrokeWidth(int strokeWidth)
	{
		this.strokeWidth = strokeWidth;
		return this;
	}

	public SVG3DElement setFillColor(String fillColor)
	{
		this.fillColor = fillColor;
		return this;
	}

	public SVG3DCircle setStrokeColor(String strokeColor)
	{
		this.strokeColor = strokeColor;
		return this;
	}

}
