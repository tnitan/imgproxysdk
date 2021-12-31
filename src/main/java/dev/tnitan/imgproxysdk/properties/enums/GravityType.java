package dev.tnitan.imgproxysdk.properties.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GravityType implements ImgproxyUrlValue {

    // north (top edge);
    NORTH("no"),
    // south (bottom edge);
    SOUTH("so"),
    // east (right edge);
    EAST("ea"),
    // west (left edge);
    WEST("we"),
    // north-east (top-right corner);
    NORTH_EAST("noea"),
    // north-west (top-left corner);
    NORTH_WEST("nowe"),
    // south-east (bottom-right corner);
    SOUTH_EAST("soea"),
    // south-west (bottom-left corner);
    SOUTH_WEST("sowe"),
    // center;
    CENTER("ce"),
    // smart gravity. libvips detects the most "interesting" section of the image and considers it as the center of the resulting image. Offsets are not applicable here;
    SMART("sm"),
    // focus point gravity. x and y are floating point numbers between 0 and 1 that define the coordinates of the center of the resulting image. Treat 0 and 1 as right/left for x and top/bottom for y.
    FOCUS_POINT("fp");

    private final String urlPropertyValue;

}
