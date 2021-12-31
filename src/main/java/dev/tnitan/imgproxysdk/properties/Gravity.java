package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.GravityType;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Gravity implements ImgproxyUrlComponent {

    private GravityType gravityType;
    private Integer xOffset;
    private Integer yOffset;

    @Override
    public String toUrlPathString() {
        // Order of properties in this list matters
        // g:%type:%x_offset:%y_offset
        return String.join(":", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(gravityType),
            ImgproxyUtils.extractUrlFriendlyValue(xOffset),
            ImgproxyUtils.extractUrlFriendlyValue(yOffset)
        )));
    }

}
