package askr.common.utils;

import org.springframework.web.cors.CorsConfiguration;

import java.util.concurrent.TimeUnit;

public class CommonUtils {

    public static TimeUnit getRedisExpiretimeTimeunit(String REDIS_EXPIRETIME_TIMEUNIT_STR) {
        switch (REDIS_EXPIRETIME_TIMEUNIT_STR) {
            case "TimeUnit.NANOSECONDS":
                return TimeUnit.NANOSECONDS;
            case "TimeUnit.MICROSECONDS":
                return TimeUnit.MICROSECONDS;
            case "TimeUnit.MILLISECONDS":
                return TimeUnit.MILLISECONDS;
            case "TimeUnit.SECONDS":
                return TimeUnit.SECONDS;
            case "TimeUnit.MINUTES":
                return TimeUnit.MINUTES;
            case "TimeUnit.HOURS":
                return TimeUnit.HOURS;
            case "TimeUnit.DAYS":
                return TimeUnit.DAYS;
            default:
                return TimeUnit.SECONDS;
        }
    }

    //跨域访问全局配置
    public static CorsConfiguration buildCorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

}
