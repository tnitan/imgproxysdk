package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.ResizeType;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Resize implements ImgproxyUrlComponent {

    private ResizeType resizeType;

    private Integer width;
    private Integer height;

    // enlarge the image if it is smaller than the given size.
    private Boolean enlarge;
    private Boolean extend;

    @Override
    public String toUrlPathString() {
        // Order of properties in this list matters
        // rs:%resizing_type:%width:%height:%enlarge:%extend
        return String.join(":", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(resizeType),
            ImgproxyUtils.extractUrlFriendlyValue(width),
            ImgproxyUtils.extractUrlFriendlyValue(height),
            ImgproxyUtils.extractUrlFriendlyValue(enlarge),
            ImgproxyUtils.extractUrlFriendlyValue(extend)
        )));
    }

}
