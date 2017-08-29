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
package org.omnaest.svg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.omnaest.svg.SVGDrawer.SVGRenderResult;
import org.omnaest.svg.elements3D.SVG3DElement;

public class SVGDrawer3D
{
	private int	width;
	private int	height;
	private int	depth;

	private List<SVG3DElement> elements = new ArrayList<>();

	public SVGDrawer3D(int width, int height, int depth)
	{
		super();
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public SVGDrawer3D add(SVG3DElement... elements)
	{
		if (elements != null)
		{
			this.add(Arrays.asList(elements));
		}
		return this;
	}

	public SVGDrawer3D add(Collection<SVG3DElement> elements)
	{
		if (elements != null)
		{
			elements.stream()
					.forEach(this::add);
		}
		return this;
	}

	public SVGDrawer3D add(SVG3DElement element)
	{
		if (element != null)
		{
			this.elements.add(element);
		}
		return this;
	}

	public SVGRenderResult render(double angleX, double angleY, double angleZ)
	{
		SVGDrawer drawer = SVGUtils.getDrawer(-this.width / 2, -this.height / 2, this.width, this.height);
		drawer.setEmbedReloadTimer(300, TimeUnit.MILLISECONDS);
		drawer.addAll(this.elements	.stream()
									.map(element3D -> element3D.projection(angleX, angleY, angleZ, this.depth))
									.sorted((e1, e2) -> -1 * Double.compare(e1.getzIndex(), e2.getzIndex()))
									.map(elementAndZIndex -> elementAndZIndex.getElement())
									.collect(Collectors.toList()));
		return drawer.renderAsResult();
	}
}
