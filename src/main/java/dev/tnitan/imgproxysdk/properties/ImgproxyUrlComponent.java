package dev.tnitan.imgproxysdk.properties;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This interface should be implemented by all Object types used in building of url for Imgproxy
interface ImgproxyUrlComponent {

    String toUrlPathString();

    default List<String> toUrlPathListFormat(List<String> imagePropertyValues) {
        return toUrlPathListFormat(imagePropertyValues, false);
    }

    default List<String> toUrlPathListFormat(List<String> imagePropertyValues, boolean skipAllEmpty) {
        boolean skipEmpty = true;
        List<String> result = new ArrayList<>();
        // iterate the array in reverse order to avoid adding empty trailing values
        for (int i = imagePropertyValues.size() - 1; i >= 0; i--) {
            String currentValue = imagePropertyValues.get(i);
            if (!skipEmpty || !StringUtils.isEmpty(currentValue)) {
                // stop skipping empty items after populating the list with a real value
                skipEmpty = skipAllEmpty;
                result.add(currentValue);
            }
        }
        // normalize values before returning them
        Collections.reverse(result);

        return result;
    }

}
