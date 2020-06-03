package study.daydayup.wolf.business.uc.setting.agent.util;

import study.daydayup.wolf.business.uc.setting.api.entity.Status;

import java.util.BitSet;

/**
 * study.daydayup.wolf.business.uc.setting.agent.util
 *
 * @author Wingle
 * @since 2020/4/9 11:32 下午
 **/
public class StatusUtil {
    public static BitSet initStatus(Status status) {
        long[] sArr = {
                status.getS1(), status.getS2(), status.getS3(), status.getS4(), status.getS5(),
                status.getS6(), status.getS7(), status.getS8(), status.getS9(), status.getS10(),

                status.getS11(), status.getS12(), status.getS13(), status.getS14(), status.getS15(),
                status.getS16(), status.getS17(), status.getS18(), status.getS19(), status.getS20(),
                1
        };

        return BitSet.valueOf(sArr);
    }

    public static void setStatus(Status status, long[] sArray) {
        status.setS1(sArray[1]); status.setS2(sArray[2]); status.setS3(sArray[3]); status.setS4(sArray[4]); status.setS5(sArray[5]);
        status.setS6(sArray[6]); status.setS7(sArray[7]); status.setS8(sArray[8]); status.setS9(sArray[9]); status.setS10(sArray[10]);

        status.setS11(sArray[11]); status.setS12(sArray[12]); status.setS13(sArray[13]); status.setS14(sArray[14]); status.setS15(sArray[15]);
        status.setS16(sArray[16]); status.setS17(sArray[17]); status.setS18(sArray[18]); status.setS19(sArray[19]); status.setS20(sArray[20]);

    }

    public static long[] formatBitArray(long[] s, int length) {
        long[] sArray = new long[length + 1];
        sArray[0] = 0;
        System.arraycopy(s, 0, sArray, 1, length);

        return sArray;
    }
}
