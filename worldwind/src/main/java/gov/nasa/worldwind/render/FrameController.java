/*
 * Copyright (c) 2016 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package gov.nasa.worldwind.render;

public interface FrameController {

    FrameStatistics getFrameStatistics();

    void drawFrame(DrawContext dc);
}
