package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.GravityType;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

// c:%width:%height:%gravity
public class CropTest extends ImgproxyUrlComponentTest {

    public static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of(Crop.builder().build(), ""),
            Arguments.of(Crop.builder().width(1).build(), "1"),
            Arguments.of(Crop.builder().height(3).width(1).build(), "1:3"),
            Arguments.of(Crop.builder().height(3).width(1).gravityType(GravityType.CENTER).build(), "1:3:" + GravityType.CENTER.getUrlPropertyValue()),
            Arguments.of(Crop.builder().gravityType(GravityType.SMART).build(), "::" + GravityType.SMART.getUrlPropertyValue())
        );
    }

}
