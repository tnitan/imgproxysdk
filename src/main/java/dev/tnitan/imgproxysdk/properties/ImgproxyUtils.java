package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.ImgproxyUrlValue;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

class ImgproxyUtils {

    public static String extractUrlFriendlyValue(Object value) {
        return extractUrlFriendlyValue(value, StringUtils.EMPTY);
    }

    public static String extractUrlFriendlyValue(Boolean value) {
        return extractUrlFriendlyValue(value, StringUtils.EMPTY);
    }

    public static String extractUrlFriendlyValue(ImgproxyUrlValue value) {
        return extractUrlFriendlyValue(value, StringUtils.EMPTY);
    }

    // replace booleans with 1/0
    public static String extractUrlFriendlyValue(Boolean value, String imagePropertyName) {
        return value == null ? StringUtils.EMPTY : value ?
            prefixValue("1", imagePropertyName) :
            prefixValue("0", imagePropertyName);
    }

    // use the property value from enum instead of the actual enum
    public static String extractUrlFriendlyValue(Collection<?> values, String imagePropertyName) {
        return values == null || values.isEmpty() ? StringUtils.EMPTY : prefixValue(
            values.stream().filter(obj -> !Objects.isNull(obj)).map(value -> {
                if (value instanceof ImgproxyUrlValue) {
                    return extractUrlFriendlyValue(((ImgproxyUrlValue) value).getUrlPropertyValue());
                } else {
                    return extractUrlFriendlyValue(value);
                }
            }).collect(Collectors.joining(":")), imagePropertyName);
    }

    // use the property value from enum instead of the actual enum
    public static String extractUrlFriendlyValue(ImgproxyUrlValue value, String imagePropertyName) {
        return value == null ? StringUtils.EMPTY : prefixValue(value.getUrlPropertyValue(), imagePropertyName);
    }

    // use the property value from enum instead of the actual enum
    public static String extractUrlFriendlyValue(ImgproxyUrlComponent value, String imagePropertyName) {
        return value == null ? StringUtils.EMPTY : prefixValue(value.toUrlPathString(), imagePropertyName);
    }

    public static String extractUrlFriendlyValue(Object value, String imagePropertyName) {
        return value == null ? StringUtils.EMPTY : prefixValue(value.toString(), imagePropertyName);
    }

    public static String prefixValue(String value, String prefix) {
        return StringUtils.isEmpty(prefix) || StringUtils.isEmpty(value) ? value : prefix + ':' + value;
    }

    // helper method that converts http/s:// s3 urls to s3:// urls, if it's the case
    // eg.:
    // "https://bucketName.s3.amazonaws.com/folder/36277/Screenshot_422.png" -> "s3://bucketName/folder/36277/Screenshot_422.png"
    // "https://s3.amazonaws.com/folder/bucketName/36277/Screenshot_422.png" -> "s3://bucketName/folder/36277/Screenshot_422.png"
    public static String determineAndConvertS3Url(String url) {
        final String s3baseUrl = "s3.amazonaws.com";
        if (!url.contains(s3baseUrl)) return url;
        String[] urlParts = url.split("/");
        int lastUsedIndex = 0;
        for (int i = 0; i < urlParts.length; i++) {
            if (urlParts[i].contains(s3baseUrl)) {
                lastUsedIndex = i;
                break;
            }
        }
        // treat occurrences of "s3.amazonaws.com" that appear later than 2nd index false-positive
        if (lastUsedIndex > 2) return url;
        // attempt to extract bucket name from URL
        String bucketName = urlParts[lastUsedIndex].substring(0, urlParts[lastUsedIndex].indexOf(s3baseUrl));
        if (bucketName.isEmpty()) {
            lastUsedIndex++;
            bucketName = urlParts[lastUsedIndex];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = lastUsedIndex + 1; i < urlParts.length; i++) {
            sb.append("/").append(urlParts[i]);
        }

        return composeS3Url(bucketName, StringUtils.EMPTY, sb.toString());
    }

    public static String composeS3Url(String bucketName, String folderName, String resourcePath) {
        return "s3://" + bucketName + "/" + folderName + resourcePath;
    }

}
