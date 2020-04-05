package study.daydayup.wolf.demo.my.starter.ds;

import java.util.BitSet;

/**
 * study.daydayup.wolf.demo.my.starter.ds
 *
 * @author Wingle
 * @since 2020/2/21 6:33 下午
 **/
public class BitSetDemo {
    public static void main(String[] args) {
       long[] lArray = {
               0, 0, 0, 0, 0,   0, 0, 0, 0, 0,
               0, 0, 0, 0, 0,   0, 0, 0, 0, 0,
               1
       };

        BitSet bitSet1 = BitSet.valueOf(lArray);

        long[] arr1 = bitSet1.toLongArray();

        BitSet bitSet2 = new BitSet(64 * 20);

        long[] arr2 = bitSet2.toLongArray();


    }
}
