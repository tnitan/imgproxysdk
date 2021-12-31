package dev.tnitan.imgproxysdk;

public final class TestUrlUtils {

    public static final String HTTP_URL = "http://test.com/a.jpg";
    public static final String BASE64_HTTP_URL = "aHR0cDovL3Rlc3QuY29tL2EuanBn";

    public static final String JUST_HTTP_BASE_URL = "http://test.com";
    public static final String BASE64_JUST_HTTP_BASE_URL = "aHR0cDovL3Rlc3QuY29t";

    public static final String JUST_HTTP_BASE_URL_TRAILING_SLASH = "http://test.com/";
    public static final String BASE64_JUST_HTTP_BASE_URL_TRAILING_SLASH = "aHR0cDovL3Rlc3QuY29tLw";

    public static final String HTTPS_URL = "https://test.com/a.jpg";
    public static final String BASE64_HTTPS_URL = "aHR0cHM6Ly90ZXN0LmNvbS9hLmpwZw";

    public static final String HTTPS_S3_URL = "https://test.com/aBucket/aFolder/a.jpg";
    public static final String BASE64_HTTPS_S3_URL = "aHR0cHM6Ly90ZXN0LmNvbS9hQnVja2V0L2FGb2xkZXIvYS5qcGc";

    public static final String HTTPS_S3_URL_ALT = "https://aBucket.test.com/aFolder/a.jpg";
    public static final String BASE64_HTTPS_S3_URL_ALT = "aHR0cHM6Ly9hQnVja2V0LnRlc3QuY29tL2FGb2xkZXIvYS5qcGc";

    public static final String S3_URL = "s3://bucketName/folder/folder1/folder2/a.jpg";
    public static final String BASE64_S3_URL = "czM6Ly9idWNrZXROYW1lL2ZvbGRlci9mb2xkZXIxL2ZvbGRlcjIvYS5qcGc";

    public static final String S3_URL_FALSE_POSITIVE = "s3://bucketName/s3.amazonaws.com/folder1/folder2/a.jpg";
    public static final String BASE64_S3_URL_FALSE_POSITIVE = "czM6Ly9idWNrZXROYW1lL3MzLmFtYXpvbmF3cy5jb20vZm9sZGVyMS9mb2xkZXIyL2EuanBn";

    public static final String HTTPS_S3_URL_FALSE_POSITIVE = "https://test.com/s3.amazonaws.com/folder1/folder2/a.jpg";
    public static final String BASE64_HTTPS_S3_URL_FALSE_POSITIVE = "aHR0cHM6Ly90ZXN0LmNvbS9zMy5hbWF6b25hd3MuY29tL2ZvbGRlcjEvZm9sZGVyMi9hLmpwZw";

}
