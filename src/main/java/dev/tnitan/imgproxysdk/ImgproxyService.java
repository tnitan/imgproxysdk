package dev.tnitan.imgproxysdk;

import dev.tnitan.imgproxysdk.properties.ImgproxyImageProperties;

public interface ImgproxyService {

    String INSECURE_PREFIX = "/insecure";

    String generateAbsoluteUrl(ImgproxyImageProperties imgHolder);

    String generateRelativeUrl(ImgproxyImageProperties imgHolder);

    String generateSignedUrl(ImgproxyImageProperties imgHolder);

}
