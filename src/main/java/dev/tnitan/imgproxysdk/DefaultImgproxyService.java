package dev.tnitan.imgproxysdk;

import dev.tnitan.imgproxysdk.properties.ImgproxyImageProperties;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public class DefaultImgproxyService implements ImgproxyService {

    private final HmacUtils hmacUtilsInstance;
    private final byte[] hmacSalt;
    private final ImgproxyConnectionProperties imgproxyConnectionProperties;

    @SneakyThrows
    public DefaultImgproxyService(ImgproxyConnectionProperties imgproxyConnectionProperties) {
        this.imgproxyConnectionProperties = imgproxyConnectionProperties;
        if (StringUtils.isEmpty(imgproxyConnectionProperties.getKey()) || StringUtils.isEmpty(imgproxyConnectionProperties.getSalt())) {
            hmacUtilsInstance = null;
            hmacSalt = null;
        } else {
            hmacUtilsInstance = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, Hex.decodeHex(imgproxyConnectionProperties.getKey()));
            hmacSalt = Hex.decodeHex(imgproxyConnectionProperties.getSalt());
        }
    }

    @Override
    public String generateAbsoluteUrl(ImgproxyImageProperties imgHolder) {
        return imgproxyConnectionProperties.getBaseUrl() + generateRelativeUrl(imgHolder);
    }

    @Override
    public String generateRelativeUrl(ImgproxyImageProperties imgHolder) {
        return imgproxyConnectionProperties.getPathPrefix() + generateSignedUrl(imgHolder);
    }

    @Override
    public String generateSignedUrl(ImgproxyImageProperties imgHolder) {
        imgHolder.setForceS3UrlConversion(imgproxyConnectionProperties.getForceS3urlConversion());
        final String path = imgHolder.toUrlPathString();
        if (hmacUtilsInstance == null) {
            return INSECURE_PREFIX + path;
        } else {
            return "/" + Base64.getUrlEncoder().withoutPadding().encodeToString(
                hmacUtilsInstance.hmac(ArrayUtils.addAll(hmacSalt, path.getBytes()))
            ) + path;
        }
    }

}
