package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.PresetType;
import dev.tnitan.imgproxysdk.TestUrlUtils;
import dev.tnitan.imgproxysdk.properties.enums.ImgproxyUrlPropertyNames;
import one.util.streamex.StreamEx;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImgproxyImagePropertiesTest extends ImgproxyUrlComponentTest {

    void asd() {
        assertEquals(
            String.format("/%s:%s/%s",
                ImgproxyUrlPropertyNames.PRESETS.getUrlPropertyName(),
                PresetType.THUMBNAIL_SMALL.getUrlPropertyValue(),
                TestUrlUtils.BASE64_HTTP_URL
            ),
            ImgproxyImageProperties.thumbnailSmall(TestUrlUtils.HTTP_URL).toUrlPathString()
        );
    }

    public static Stream<Arguments> provideValues() {
        return StreamEx.of(
            Stream.of(
                Arguments.of(ImgproxyImageProperties.builder().build(), ""),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.HTTP_URL).build(), String.format("/%s", TestUrlUtils.BASE64_HTTP_URL)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.JUST_HTTP_BASE_URL).build(), String.format("/%s", TestUrlUtils.BASE64_JUST_HTTP_BASE_URL)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.JUST_HTTP_BASE_URL_TRAILING_SLASH).build(), String.format("/%s", TestUrlUtils.BASE64_JUST_HTTP_BASE_URL_TRAILING_SLASH)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.HTTPS_URL).build(), String.format("/%s", TestUrlUtils.BASE64_HTTPS_URL)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.HTTPS_S3_URL).build(), String.format("/%s", TestUrlUtils.BASE64_HTTPS_S3_URL)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.HTTPS_S3_URL_ALT).build(), String.format("/%s", TestUrlUtils.BASE64_HTTPS_S3_URL_ALT)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.S3_URL).build(), String.format("/%s", TestUrlUtils.BASE64_S3_URL)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.S3_URL_FALSE_POSITIVE).build(), String.format("/%s", TestUrlUtils.BASE64_S3_URL_FALSE_POSITIVE)),
                Arguments.of(ImgproxyImageProperties.builder().url(TestUrlUtils.HTTPS_S3_URL_FALSE_POSITIVE).build(), String.format("/%s", TestUrlUtils.BASE64_HTTPS_S3_URL_FALSE_POSITIVE))
            )
        ).append(
            ResizeTest.provideValues().filter(item -> !item.get()[1].equals("")).map(item ->
                Arguments.of(ImgproxyImageProperties.builder()
                    .url(TestUrlUtils.HTTP_URL)
                    .resize((Resize) item.get()[0])
                    .build(), String.format("/%s:%s/%s", ImgproxyUrlPropertyNames.RESIZE.getUrlPropertyName(), item.get()[1], TestUrlUtils.BASE64_HTTP_URL))
            )
        ).append(
            CropTest.provideValues().filter(item -> !item.get()[1].equals("")).map(item ->
                Arguments.of(ImgproxyImageProperties.builder()
                    .url(TestUrlUtils.HTTP_URL)
                    .crop((Crop) item.get()[0])
                    .build(), String.format("/%s:%s/%s", ImgproxyUrlPropertyNames.CROP.getUrlPropertyName(), item.get()[1], TestUrlUtils.BASE64_HTTP_URL))
            )
        ).append(
            GravityTest.provideValues().filter(item -> !item.get()[1].equals("")).map(item ->
                Arguments.of(ImgproxyImageProperties.builder()
                    .url(TestUrlUtils.HTTP_URL)
                    .gravity((Gravity) item.get()[0])
                    .build(), String.format("/%s:%s/%s", ImgproxyUrlPropertyNames.GRAVITY.getUrlPropertyName(), item.get()[1], TestUrlUtils.BASE64_HTTP_URL))
            )
        ).append(
            PaddingTest.provideValues().filter(item -> !item.get()[1].equals("")).map(item ->
                Arguments.of(ImgproxyImageProperties.builder()
                    .url(TestUrlUtils.HTTP_URL)
                    .padding((Padding) item.get()[0])
                    .build(), String.format("/%s:%s/%s", ImgproxyUrlPropertyNames.PADDING.getUrlPropertyName(), item.get()[1], TestUrlUtils.BASE64_HTTP_URL))
            )
        );
    }

}
