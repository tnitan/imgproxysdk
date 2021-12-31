package dev.tnitan.imgproxysdk.properties;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class ImgproxyUrlComponentTest {

    @ParameterizedTest
    @MethodSource("provideValues")
    void values__trailing_empty_values_exist__trailing_empty_values_are_removed(ImgproxyUrlComponent imgproxyUrlComponentTest, String expected) {
        assertEquals(expected, imgproxyUrlComponentTest.toUrlPathString());
    }

}
