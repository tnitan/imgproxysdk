package dev.tnitan.imgproxysdk.properties.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImgproxyUrlPropertyNames {

    PRESETS("pr"),
    SIZE("s"),
    RESIZE("rs"),
    RESIZE_TYPE("rt"),
    WIDTH("w"),
    HEIGHT("h"),
    ENLARGE("el"),
    EXTEND("ex"),
    MIN_WIDTH("mw"),
    MIN_HEIGHT("mh"),
    DPR("dpr"),
    GRAVITY("g"),
    CROP("c"),
    PADDING("pd"),
    FORMAT("f"),
    AUTO_ROTATE("ar"),
    ROTATE("rot"),
    BACKGROUND("bg"),
    BLUR("bl"),
    SHARPEN("sh"),
    PIXELATE("pix"),
    WATERMARK("wm"),
    QUALITY("q");

    // URL and EXTENSION are intentionally omitted because they require special handling
    // URL("url", null),
    // EXTENSION("extension", null)

    final String urlPropertyName;

}