package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.GravityType;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Crop implements ImgproxyUrlComponent {

    /**
     * When width or height is greater than or equal to 1, imgproxy treats it as an absolute value.
     * When width or height is less than 1, imgproxy treats it as a relative value.
     * When width or height is set to 0, imgproxy will use the full width/height of the source image.
     */
    private Integer width;
    private Integer height;

    // When gravity is not set, imgproxy will use the value of the gravity option
    private GravityType gravityType;

    @Override
    public String toUrlPathString() {
        // Order of properties in this list matters
        // c:%width:%height:%gravity
        return String.join(":", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(width),
            ImgproxyUtils.extractUrlFriendlyValue(height),
            ImgproxyUtils.extractUrlFriendlyValue(gravityType)
        )));
    }

}
