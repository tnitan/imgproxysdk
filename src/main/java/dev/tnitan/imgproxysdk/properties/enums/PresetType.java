package dev.tnitan.imgproxysdk.properties.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PresetType implements ImgproxyUrlValue {

    LOGO("logo"),
    THUMBNAIL_SMALL("thumbsm"),
    THUMBNAIL_MEDIUM("thumbmd"),
    THUMBNAIL_LARGE("thumblg"),
    IMAGE_SMALL("imgsm"),
    IMAGE_MEDIUM("imgmd"),
    IMAGE_LARGE("imglg");

    private final String urlPropertyValue;

}
