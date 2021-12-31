package dev.tnitan.imgproxysdk.properties;

import dev.tnitan.imgproxysdk.properties.enums.GravityType;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Extend implements ImgproxyUrlComponent {

    private Boolean extend;
    private GravityType gravityType;

    @Override
    public String toUrlPathString() {
        // Order of properties in this list matters
        // ex:%extend:%gravity
        return String.join(":", toUrlPathListFormat(Arrays.asList(
            ImgproxyUtils.extractUrlFriendlyValue(extend),
            ImgproxyUtils.extractUrlFriendlyValue(gravityType)
        )));
    }

}
