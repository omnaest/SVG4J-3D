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

import java.util.Arrays;

import org.omnaest.svg.elements.SVGPolygon;
import org.omnaest.svg.elements.base.SVGElement;
import org.omnaest.svg.elements.base.SVGVector;
import org.omnaest.vector.Vector;

public class SVG3DLine implements SVG3DElement
{
	private int	x1;
	private int	y1;
	private int	z1;
	private int	x2;
	private int	y2;
	private int	z2;

	public SVG3DLine(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}

	@Override
	public SVGElementAndZIndex projection(double angleX, double angleY, double angleZ, int depth)
	{
		Vector startLocation = new Vector(this.x1, this.y1, this.z1).rotate(angleX, angleY, angleZ);
		Vector endLocation = new Vector(this.x2, this.y2, this.z2).rotate(angleX, angleY, angleZ);

		Vector deltaVector = endLocation.subtract(startLocation);
		Vector deltaNormVector = deltaVector.normVector();

		double maxDistance = Math.sqrt(depth);
		int startStrokeWidth = (int) (5 * maxDistance / Math.sqrt(maxDistance * maxDistance + startLocation.getZ()));
		int endStrokeWidth = (int) (5 * maxDistance / Math.sqrt(maxDistance * maxDistance + endLocation.getZ()));

		//SVGElement element = new SVGLine((int) startLocation.getX(), (int) startLocation.getY(), (int) endLocation.getX(), (int) endLocation.getY());

		SVGElement element = new SVGPolygon(Arrays.asList(	this.createSVGVector(startLocation.add(deltaNormVector	.multiply(startStrokeWidth)
																													.rotateZ(90))),
															this.createSVGVector(startLocation.add(deltaNormVector	.multiply(startStrokeWidth)
																													.rotateZ(-90))),
															this.createSVGVector(endLocation.add(deltaNormVector.multiply(endStrokeWidth)
																												.rotateZ(-90))),
															this.createSVGVector(endLocation.add(deltaNormVector.multiply(endStrokeWidth)
																												.rotateZ(90))))).setStrokeColor("white");

		double zIndex = Math.round(startLocation.add(deltaVector.divide(2))
												.getZ());

		return new SVGElementAndZIndex(element, zIndex);
	}

	private SVGVector createSVGVector(Vector vector)
	{
		return new SVGVector()
		{
			@Override
			public double getY()
			{
				return vector.getY();
			}

			@Override
			public double getX()
			{
				return vector.getX();
			}
		};
	}

}
