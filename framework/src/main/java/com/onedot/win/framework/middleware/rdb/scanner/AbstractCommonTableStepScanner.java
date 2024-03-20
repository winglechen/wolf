package com.onedot.win.framework.middleware.rdb.scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.onedot.win.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author jings
 * @Date 2022/9/28
 */
@Slf4j
public abstract class AbstractCommonTableStepScanner {
    public static final String STOP_FLAG_PREFIX = "tts_stop:";
    public static final int MAX_RETRY_TIMES = 5;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    protected String genScanId() {
        String className = this.getClass().getSimpleName();
        return className + "-" + StringUtil.uuid().substring(0, 8);
    }

    public void stopScan(String scanId) {
        stringRedisTemplate.opsForValue().set(STOP_FLAG_PREFIX + scanId, "1", 10, TimeUnit.MINUTES);
    }

    public boolean isScannable(String scanId) {
        String flag = stringRedisTemplate.opsForValue().get(STOP_FLAG_PREFIX + scanId);
        return StringUtil.isBlank(flag);
    }

    protected boolean resaveStepTaskOnFiled() {
        return true;
    }

    public int maxRetryTimes() {
        return MAX_RETRY_TIMES;
    }

    public boolean isOverMaxRetryTimes(StepTaskParams stepParams) {
        return stepParams.getRetryCnt().compareTo(maxRetryTimes()) > 0;
    }

    /**
     * @param billNo    "20220701xxxxxx"
     * @param startDate "20220601", include
     * @param endDate   "20220831", include
     * @return
     */
    public boolean billNoBetweenInDate(String billNo, String startDate, String endDate) {
        if (StringUtil.isBlank(billNo) || billNo.length() < 8) {
            return false;
        }
        String dateInBillNo = billNo.substring(0, 8);

        if (StringUtil.notBlank(startDate) && dateInBillNo.compareTo(startDate) < 0) {
            return false;
        }

        return !StringUtil.notBlank(endDate) || dateInBillNo.compareTo(endDate) <= 0;
    }
}
