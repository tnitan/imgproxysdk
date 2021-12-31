package dev.tnitan.imgproxysdk;

public interface ImgproxyConnectionProperties {

    String getKey();

    String getSalt();

    String getBaseUrl();

    String getPathPrefix();

    boolean getForceS3urlConversion();

}
