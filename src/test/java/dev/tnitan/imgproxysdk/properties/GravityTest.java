package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.GravityType;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

// g:%type:%x_offset:%y_offset
public class GravityTest extends ImgproxyUrlComponentTest {

    public static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of(Gravity.builder().build(), ""),
            Arguments.of(Gravity.builder().xOffset(1).build(), ":1"),
            Arguments.of(Gravity.builder().yOffset(3).xOffset(1).build(), ":1:3"),
            Arguments.of(Gravity.builder().yOffset(3).xOffset(1).gravityType(GravityType.CENTER).build(), GravityType.CENTER.getUrlPropertyValue() + ":1:3"),
            Arguments.of(Gravity.builder().gravityType(GravityType.SMART).build(), GravityType.SMART.getUrlPropertyValue())
        );
    }

}
