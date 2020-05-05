package study.daydayup.wolf.business.union.task.service;

/**
 * study.daydayup.wolf.business.union.task.service
 *
 * @author Wingle
 * @since 2020/2/7 10:36 上午
 **/
public interface DailyKoiService {
    void countPvAndUv();
    void countRegister();

    /**
     * count customer info state
     * 1) basic_info_count
     * 2) liveness_count
     * 3) bankcard_count
     * 4) aadhaar_count
     * 5) pan_card_count
     * 6) password_count
     * 7) ... ...
     */
    void countIndianInfoState();
}
