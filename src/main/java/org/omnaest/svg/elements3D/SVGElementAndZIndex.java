/*******************************************************************************
 * Copyright 2021 Danny Kunz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
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
