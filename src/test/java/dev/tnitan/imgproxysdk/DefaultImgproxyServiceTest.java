package dev.tnitan.imgproxysdk;

import dev.tnitan.imgproxysdk.properties.ImgproxyImageProperties;
import dev.tnitan.imgproxysdk.properties.ImgproxyImagePropertiesTest;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Base64;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultImgproxyServiceTest {

    static ImgproxyService secureImgproxyService;
    static ImgproxyService insecureImgproxyService;
    static ImgproxyConnectionProperties secureImgproxyConnectionProperties;
    static ImgproxyConnectionProperties insecureImgproxyConnectionProperties;

    @BeforeAll
    static void setUp() {
        secureImgproxyConnectionProperties = new ImgproxyConnectionProperties() {
            @Override
            public String getKey() {
                return "d34db33f";
            }

            @Override
            public String getSalt() {
                return "b44df00d";
            }

            @Override
            public String getBaseUrl() {
                return "http://localhost:1337";
            }

            @Override
            public String getPathPrefix() {
                return "/not/images";
            }

            @Override
            public boolean getForceS3urlConversion() {
                return false;
            }
        };

        secureImgproxyService = new DefaultImgproxyService(secureImgproxyConnectionProperties);

        insecureImgproxyConnectionProperties = new ImgproxyConnectionProperties() {
            @Override
            public String getKey() {
                return "";
            }

            @Override
            public String getSalt() {
                return "";
            }

            @Override
            public String getBaseUrl() {
                return "http://localhost:9008";
            }

            @Override
            public String getPathPrefix() {
                return "/not/images";
            }

            @Override
            public boolean getForceS3urlConversion() {
                return false;
            }
        };

        insecureImgproxyService = new DefaultImgproxyService(insecureImgproxyConnectionProperties);
    }

    @ParameterizedTest
    @MethodSource("providedInsecureRelativeUrls")
    void image_url_and_settings__generating_insecure_relative_url__composed_relative_insecure_url(ImgproxyImageProperties imgproxyImageProperties, String expectedUrl) {
        assertEquals(expectedUrl, insecureImgproxyService.generateRelativeUrl(imgproxyImageProperties));
    }

    static Stream<Arguments> providedInsecureRelativeUrls() {
        final String pathPrefix = secureImgproxyConnectionProperties.getPathPrefix();
        return ImgproxyImagePropertiesTest.provideValues().map(item ->
            Arguments.of(item.get()[0], pathPrefix + ImgproxyService.INSECURE_PREFIX + item.get()[1])
        );
    }

    @ParameterizedTest
    @MethodSource("providedInsecureAbsoluteUrls")
    void image_url_and_settings__generating_insecure_absolute_url__composed_absolute_insecure_url(ImgproxyImageProperties imgproxyImageProperties, String expectedUrl) {
        assertEquals(expectedUrl, insecureImgproxyService.generateAbsoluteUrl(imgproxyImageProperties));
    }

    static Stream<Arguments> providedInsecureAbsoluteUrls() {
        final String baseUrl = insecureImgproxyConnectionProperties.getBaseUrl();
        return providedInsecureRelativeUrls().map(item ->
            Arguments.of(item.get()[0], baseUrl + item.get()[1])
        );
    }

    @SneakyThrows
    private static String signPath(String salt, String key, String path) {
        HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, Hex.decodeHex(key));
        return "/" + Base64.getUrlEncoder().withoutPadding().encodeToString(
            hmacUtils.hmac(ArrayUtils.addAll(Hex.decodeHex(salt), path.getBytes()))
        ) + path;
    }

    @ParameterizedTest
    @MethodSource("providedBaselineResults")
    void strings_with_known_result__generating_signed_path__paths_match(String testString, String expected) {
        assertEquals(expected, signPath("baaaad", "ca77", testString));
    }

    static Stream<Arguments> providedBaselineResults() {
        return Stream.of(
            Arguments.of("/rs::1/https://test.com", "/GbnSsHO7-iOs1XJkwsu6xg71lvY3dyhJuonDiTrjWMs/rs::1/https://test.com"),
            Arguments.of("some-random-value", "/IewXnxEJOuTUh6Yv58F41kIKQ3JsAs5kHpEtK9Y0U5Isome-random-value"),
            Arguments.of("/h:100/w:200/http://domain.com/image.jpg?invalid=true", "/kQABm28SIXcSqJ_ca-V7ywG-WlclxjJG_UKCbi7iXME/h:100/w:200/http://domain.com/image.jpg?invalid=true")
        );
    }

    @ParameterizedTest
    @MethodSource("providedSecureRelativeUrls")
    void image_url_and_settings__generating_secure_relative_url__composed_relative_secure_url(ImgproxyImageProperties imgproxyImageProperties, String expectedUrl) {
        assertEquals(expectedUrl, secureImgproxyService.generateRelativeUrl(imgproxyImageProperties));
    }

    static Stream<Arguments> providedSecureRelativeUrls() {
        final String salt = secureImgproxyConnectionProperties.getSalt();
        final String key = secureImgproxyConnectionProperties.getKey();
        final String pathPrefix = secureImgproxyConnectionProperties.getPathPrefix();
        return ImgproxyImagePropertiesTest.provideValues().map(item ->
            Arguments.of(item.get()[0], String.format("%s%s", pathPrefix, signPath(salt, key, item.get()[1].toString())))
        );
    }

    @ParameterizedTest
    @MethodSource("providedSecureAbsoluteUrls")
    void image_url_and_settings__generating_secure_absolute_url__composed_absolute_secure_url(ImgproxyImageProperties imgproxyImageProperties, String expectedUrl) {
        assertEquals(expectedUrl, secureImgproxyService.generateAbsoluteUrl(imgproxyImageProperties));
    }

    static Stream<Arguments> providedSecureAbsoluteUrls() {
        final String baseUrl = secureImgproxyConnectionProperties.getBaseUrl();
        return providedSecureRelativeUrls().map(item ->
            Arguments.of(item.get()[0], baseUrl + item.get()[1])
        );
    }

}
