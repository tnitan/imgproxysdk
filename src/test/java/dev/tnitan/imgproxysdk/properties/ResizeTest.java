package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.ResizeType;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

// rs:%resizing_type:%width:%height:%enlarge:%extend
public class ResizeTest extends ImgproxyUrlComponentTest {

    public static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of(Resize.builder().build(), ""),
            Arguments.of(Resize.builder().width(1).build(), ":1"),
            Arguments.of(Resize.builder().height(3).width(1).build(), ":1:3"),
            Arguments.of(Resize.builder().height(3).width(1).resizeType(ResizeType.FILL_DOWN).build(), ResizeType.FILL_DOWN.getUrlPropertyValue() + ":1:3"),
            Arguments.of(Resize.builder().resizeType(ResizeType.FORCE).build(), ResizeType.FORCE.getUrlPropertyValue())
        );
    }

}
