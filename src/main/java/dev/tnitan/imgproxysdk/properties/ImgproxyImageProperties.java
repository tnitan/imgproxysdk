package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.PresetType;
import dev.tnitan.imgproxysdk.properties.enums.ResizeType;
import dev.tnitan.imgproxysdk.properties.enums.ImgproxyUrlPropertyNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * Order in which operations are applied:
 * - If it is needed to resize an image with an alpha-channel, premultiplies one to handle alpha correctly;
 * - resizes the image to the desired size;
 * - colorspace fix;
 * - rotates/flip the image according to EXIF metadata;
 * - crops the image using specified gravity;
 * - fills the image background if the background color was specified;
 * - applies gaussian blur and sharpen filters;
 * - watermark if one was specified;
 */
@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
public class ImgproxyImageProperties implements ImgproxyUrlComponent {

    // should be overwritten by @ImgproxyService based on @ImgproxyConnectionProperties, changes behavior of toUrlPathString
    boolean forceS3UrlConversion;

    final String url;

    private List<PresetType> presets;
    private Size size;
    private Resize resize;

    private ResizeType resizeType;
    private Integer width;
    private Integer height;
    private Boolean enlarge;
    private Extend extend;

    private Integer minWidth;
    private Integer minHeight;

    // multiply the image dimensions according to this factor for HiDPI (Retina) devices.
    //  The value must be greater than 0. default: 1
    private Float dpr;

    private Gravity gravity;
    private Crop crop;
    private Padding padding;

    private String format;

    // When set to 1, t or true, imgproxy will automatically rotate images based onon the EXIF Orientation parameter
    //  (if available in the image meta data). The orientation tag will be removed from the image anyway.
    //  Normally this is controlled by the IMGPROXY_AUTO_ROTATE configuration but this procesing option allows the
    //  configuration to be set for each request.
    private Boolean autoRotate;
    // Rotates the image on the specified angle. The orientation from the image metadata is applied before the rotation
    //  unless autorotation is disabled.
    private Integer rotate;

    // R:G:B or HEX, default: null
    private String background;

    // gaussian blur filter to the resulting image. sigma defines the size of a mask imgproxy will use., default: null
    private Integer blur;

    // sharpen filter to the resulting image. sigma the size of a mask imgproxy will use., default: null
    private Float sharpen;

    // pixelate filter to the resulting image. size is the size of a pixel., default: null
    private Integer pixelate;

    // default: null
    private Watermark watermark;

    // Redefines quality of the resulting image, percentage. When 0, quality is assumed based on IMGPROXY_QUALITY
    //  and format_quality.
    private Integer quality;

    private String extension;

    public ImgproxyImageProperties(String bucketName, String resourcePath) {
        this(bucketName, StringUtils.EMPTY, resourcePath);
    }

    public ImgproxyImageProperties(String bucketName, String folderName, String resourcePath) {
        this(ImgproxyUtils.composeS3Url(bucketName, folderName, resourcePath));
    }

    public static ImgproxyImageProperties instanceWithPreset(ImgproxyImageProperties imgproxyImageProperties, PresetType presetType) {
        imgproxyImageProperties.setPresets(Collections.singletonList(presetType));

        return imgproxyImageProperties;
    }

    public static ImgproxyImageProperties instanceWithPreset(PresetType presetType, String url) {
        return instanceWithPreset(new ImgproxyImageProperties(url), presetType);
    }

    public static ImgproxyImageProperties instanceWithPreset(PresetType presetType, String bucketName, String resourcePath) {
        return instanceWithPreset(new ImgproxyImageProperties(bucketName, resourcePath), presetType);
    }

    public static ImgproxyImageProperties instanceWithPreset(PresetType presetType, String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(new ImgproxyImageProperties(bucketName, folderName, resourcePath), presetType);
    }

    public static ImgproxyImageProperties thumbnailSmall(String url) {
        return instanceWithPreset(PresetType.THUMBNAIL_SMALL, url);
    }

    public static ImgproxyImageProperties thumbnailSmall(String bucketName, String resourcePath) {
        return instanceWithPreset(PresetType.THUMBNAIL_SMALL, bucketName, resourcePath);
    }

    public static ImgproxyImageProperties thumbnailSmall(String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(PresetType.THUMBNAIL_SMALL, bucketName, folderName, resourcePath);
    }

    public static ImgproxyImageProperties thumbnailMedium(String url) {
        return instanceWithPreset(PresetType.THUMBNAIL_MEDIUM, url);
    }

    public static ImgproxyImageProperties thumbnailMedium(String bucketName, String resourcePath) {
        return instanceWithPreset(PresetType.THUMBNAIL_MEDIUM, bucketName, resourcePath);
    }

    public static ImgproxyImageProperties thumbnailMedium(String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(PresetType.THUMBNAIL_MEDIUM, bucketName, folderName, resourcePath);
    }

    public static ImgproxyImageProperties thumbnailLarge(String url) {
        return instanceWithPreset(PresetType.THUMBNAIL_LARGE, url);
    }

    public static ImgproxyImageProperties thumbnailLarge(String bucketName, String resourcePath) {
        return instanceWithPreset(PresetType.THUMBNAIL_LARGE, bucketName, resourcePath);
    }

    public static ImgproxyImageProperties thumbnailLarge(String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(PresetType.THUMBNAIL_LARGE, bucketName, folderName, resourcePath);
    }

    public static ImgproxyImageProperties imageSmall(String url) {
        return instanceWithPreset(PresetType.IMAGE_SMALL, url);
    }

    public static ImgproxyImageProperties imageSmall(String bucketName, String resourcePath) {
        return instanceWithPreset(PresetType.IMAGE_SMALL, bucketName, resourcePath);
    }

    public static ImgproxyImageProperties imageSmall(String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(PresetType.IMAGE_SMALL, bucketName, folderName, resourcePath);
    }

    public static ImgproxyImageProperties imageMedium(String url) {
        return instanceWithPreset(PresetType.IMAGE_MEDIUM, url);
    }

    public static ImgproxyImageProperties imageMedium(String bucketName, String resourcePath) {
        return instanceWithPreset(PresetType.IMAGE_MEDIUM, bucketName, resourcePath);
    }

    public static ImgproxyImageProperties imageMedium(String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(PresetType.IMAGE_MEDIUM, bucketName, folderName, resourcePath);
    }

    public static ImgproxyImageProperties imageLarge(String url) {
        return instanceWithPreset(PresetType.IMAGE_LARGE, url);
    }

    public static ImgproxyImageProperties imageLarge(String bucketName, String resourcePath) {
        return instanceWithPreset(PresetType.IMAGE_LARGE, bucketName, resourcePath);
    }

    public static ImgproxyImageProperties imageLarge(String bucketName, String folderName, String resourcePath) {
        return instanceWithPreset(PresetType.IMAGE_LARGE, bucketName, folderName, resourcePath);
    }

    @Override
    public String toUrlPathString() {
        if (StringUtils.isEmpty(url)) return StringUtils.EMPTY;
        final String extensionValue = extension == null ? StringUtils.EMPTY : "." + ImgproxyUtils.extractUrlFriendlyValue(extension, StringUtils.EMPTY);
        // Order of properties in this list matters
        return "/" + String.join("/", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(presets, ImgproxyUrlPropertyNames.PRESETS.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(size, ImgproxyUrlPropertyNames.SIZE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(resize, ImgproxyUrlPropertyNames.RESIZE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(resizeType, ImgproxyUrlPropertyNames.RESIZE_TYPE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(width, ImgproxyUrlPropertyNames.WIDTH.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(height, ImgproxyUrlPropertyNames.HEIGHT.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(enlarge, ImgproxyUrlPropertyNames.ENLARGE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(extend, ImgproxyUrlPropertyNames.EXTEND.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(minWidth, ImgproxyUrlPropertyNames.MIN_WIDTH.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(minHeight, ImgproxyUrlPropertyNames.MIN_HEIGHT.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(dpr, ImgproxyUrlPropertyNames.DPR.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(gravity, ImgproxyUrlPropertyNames.GRAVITY.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(crop, ImgproxyUrlPropertyNames.CROP.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(padding, ImgproxyUrlPropertyNames.PADDING.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(format, ImgproxyUrlPropertyNames.FORMAT.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(autoRotate, ImgproxyUrlPropertyNames.AUTO_ROTATE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(rotate, ImgproxyUrlPropertyNames.ROTATE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(background, ImgproxyUrlPropertyNames.BACKGROUND.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(blur, ImgproxyUrlPropertyNames.BLUR.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(sharpen, ImgproxyUrlPropertyNames.SHARPEN.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(pixelate, ImgproxyUrlPropertyNames.PIXELATE.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(watermark, ImgproxyUrlPropertyNames.WATERMARK.getUrlPropertyName()),
            ImgproxyUtils.extractUrlFriendlyValue(quality, ImgproxyUrlPropertyNames.QUALITY.getUrlPropertyName()),
            Base64.getUrlEncoder().withoutPadding().encodeToString(
                (forceS3UrlConversion ? ImgproxyUtils.determineAndConvertS3Url(url) : url).getBytes()
            )
        ), true)) + extensionValue;
    }

}
