package dev.tnitan.imgproxysdk.properties.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PositionType implements ImgproxyUrlValue {

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
    // replicate watermark to fill the whole image;
    SMART("re");

    private final String urlPropertyValue;

}
