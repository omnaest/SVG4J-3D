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

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.omnaest.svg.elements3D.SVG3DCircle;
import org.omnaest.svg.elements3D.SVG3DLine;

public class SVG3DUtilsTest
{

    @Test
    @Ignore
    public void testGetDrawer() throws Exception
    {
        //
        SVGDrawer3D renderer = SVG3DUtils.getDrawer(1000, 800, 200);

        renderer.add(new SVG3DCircle(100, 0, 0, 20));
        renderer.add(new SVG3DCircle(0, 100, 0, 20));
        renderer.add(new SVG3DCircle(0, 0, 100, 20));

        renderer.add(new SVG3DLine(100, 0, 0, 0, 100, 0));
        renderer.add(new SVG3DLine(100, 0, 0, 0, 0, 100));
        renderer.add(new SVG3DLine(0, 100, 0, 0, 0, 100));

        //
        double angleX = 0.0;
        double angleY = 0.0;
        double angleZ = 0.0;
        while (true)
        {
            angleX += 1.0;
            angleY += 5.0;
            renderer.render(angleX, angleY, angleZ)
                    .writeToFile(new File("C:/Temp/test3D.svg"));
            Thread.sleep(200);

        }
    }

}
