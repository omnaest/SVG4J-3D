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

import org.omnaest.svg.elements.SVGText;
import org.omnaest.svg.elements.base.SVGElement;
import org.omnaest.vector.Vector;

public class SVG3DText implements SVG3DElement
{
	private int	x;
	private int	y;
	private int	z;

	private String	text;
	private int		fontSize	= SVGText.DEFAULT_FONTSIZE;

	public SVG3DText(int x, int y, int z, String text)
	{
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.text = text;
	}

	public SVG3DText setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
		return this;
	}

	@Override
	public SVGElementAndZIndex projection(double angleX, double angleY, double angleZ, int depth)
	{
		Vector location = new Vector(this.x, this.y, this.z).rotate(angleX, angleY, angleZ);

		double maxDistance = Math.sqrt(depth);
		int projectedFontSize = (int) (this.fontSize * maxDistance / Math.sqrt(maxDistance * maxDistance + location.getZ()));

		SVGElement element = new SVGText((int) location.getX(), (int) location.getY(), this.text).setFontSize(projectedFontSize);

		double zIndex = location.getZ();
		return new SVGElementAndZIndex(element, zIndex);
	}

}
