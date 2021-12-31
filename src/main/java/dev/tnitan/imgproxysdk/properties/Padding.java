package dev.tnitan.imgproxysdk.properties;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Padding implements ImgproxyUrlComponent {

    // top padding (and all other sides if they won’t be set explicitly);
    private Integer top;
    // right padding (and left if it won’t be set explicitly);
    private Integer right;
    // bottom padding;
    private Integer bottom;
    // left padding.
    private Integer left;

    @Override
    public String toUrlPathString() {
        // Order of properties in this list matters
        // pd:%top:%right:%bottom:%left
        return String.join(":", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(top),
            ImgproxyUtils.extractUrlFriendlyValue(right),
            ImgproxyUtils.extractUrlFriendlyValue(bottom),
            ImgproxyUtils.extractUrlFriendlyValue(left)
        )));
    }
}
