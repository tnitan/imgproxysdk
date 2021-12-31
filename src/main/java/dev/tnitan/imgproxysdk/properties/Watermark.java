package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.PositionType;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Watermark implements ImgproxyUrlComponent {

    // watermark opacity modifier. Final opacity is calculated like base_opacity * opacity
    private Float opacity;

    // floating point number that defines watermark size relative to the resulting image size. When set to 0 or omitted,
    //  watermark size won’t be changed.
    private Float scale;

    // specifies the position of the watermark.
    private PositionType positionType;

    // specify watermark offset by X and Y axes. For re position, define spacing between tiles;
    private Integer xOffset;
    private Integer yOffset;

    @Override
    public String toUrlPathString() {
        // Order of properties in this list matters
        // wm:%opacity:%position:%x_offset:%y_offset:%scale
        return String.join(":", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(opacity),
            ImgproxyUtils.extractUrlFriendlyValue(positionType),
            ImgproxyUtils.extractUrlFriendlyValue(xOffset),
            ImgproxyUtils.extractUrlFriendlyValue(yOffset),
            ImgproxyUtils.extractUrlFriendlyValue(scale)
        )));
    }

}
