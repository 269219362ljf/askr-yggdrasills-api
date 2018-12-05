package askr.yaggdrasills.constant;

import askr.common.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Constants {

    private static Logger log = LoggerFactory.getLogger(Constants.class);

    @Value("${REDIS_EXPIRETIME_TIMEUNIT}")
    public String REDIS_EXPIRETIME_TIMEUNIT_STR;

    public TimeUnit getRedisExpiretimeTimeunit() {
        return CommonUtils.getRedisExpiretimeTimeunit(REDIS_EXPIRETIME_TIMEUNIT_STR.trim());
    }

}
