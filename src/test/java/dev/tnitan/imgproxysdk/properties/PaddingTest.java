package dev.tnitan.imgproxysdk.properties;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

// rs:%resizing_type:%width:%height:%enlarge:%extend
public class PaddingTest extends ImgproxyUrlComponentTest {

    public static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of(Padding.builder().build(), ""),
            Arguments.of(Padding.builder().top(1).build(), "1"),
            Arguments.of(Padding.builder().right(3).top(1).build(), "1:3"),
            Arguments.of(Padding.builder().right(3).top(1).left(7).build(), "1:3::7"),
            Arguments.of(Padding.builder().left(7).build(), ":::7")
        );
    }

}
