package dev.tnitan.imgproxysdk.properties.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResizeType implements ImgproxyUrlValue {

    // resizes the image while keeping aspect ratio to fit given size;
    FIT("fit"),
    // resizes the image while keeping aspect ratio to fill given size and cropping projecting parts;
    FILL("fill"),
    // same as fill, but if the resized image is smaller than the requested size, imgproxy will crop the result to keep the requested aspect ratio;
    FILL_DOWN("fill-down"),
    // resizes the image without keeping aspect ratio;
    FORCE("force"),
    // if both source and resulting dimensions have the same orientation (portrait or landscape), imgproxy will use fill. Otherwise, it will use fit.
    AUTO("auto");

    final String urlPropertyValue;

}
