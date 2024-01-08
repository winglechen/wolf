package study.daydayup.wolf.framework.middleware.sharding.util;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TradeShardingTest {
    private LinkedHashSet<String> dbShards;
    private LinkedHashSet<String> tableShards_0;
    private LinkedHashSet<String> tableShards_1;
    private LinkedHashSet<String> tableShards_29;

    @Before
    public void setup() throws Exception {
        dbShards = new LinkedHashSet<>(Arrays.asList(
                "pay-0", "pay-1", "pay-2", "pay-3", "pay-16", "pay-17", "pay-18", "pay-19", "pay-20", "pay-21", "pay-22", "pay-23", "pay-24", "pay-25", "pay-26", "pay-27", "pay-28", "pay-29", "pay-30", "pay-31"
        ));
        tableShards_0 = new LinkedHashSet<>(Arrays.asList(
                "payment_0", "payment_4", "payment_8", "payment_12", "payment_16", "payment_20", "payment_24", "payment_28", "payment_32", "payment_36", "payment_40", "payment_44", "payment_48", "payment_52", "payment_56", "payment_60", "payment_64", "payment_68", "payment_72", "payment_76", "payment_80", "payment_84", "payment_88", "payment_92", "payment_96", "payment_100", "payment_104", "payment_108", "payment_112", "payment_116", "payment_120", "payment_124", "payment_128", "payment_132", "payment_136", "payment_140", "payment_144", "payment_148", "payment_152", "payment_156", "payment_160", "payment_164", "payment_168", "payment_172", "payment_176", "payment_180", "payment_184", "payment_188", "payment_192", "payment_196", "payment_200", "payment_204", "payment_208", "payment_212", "payment_216", "payment_220", "payment_224", "payment_228", "payment_232", "payment_236", "payment_240", "payment_244", "payment_248", "payment_252"
        ));
        tableShards_1 = new LinkedHashSet<>(Arrays.asList(
                "payment_1", "payment_5", "payment_9", "payment_13", "payment_17", "payment_21", "payment_25", "payment_29", "payment_33", "payment_37", "payment_41", "payment_45", "payment_49", "payment_53", "payment_57", "payment_61", "payment_65", "payment_69", "payment_73", "payment_77", "payment_81", "payment_85", "payment_89", "payment_93", "payment_97", "payment_101", "payment_105", "payment_109", "payment_113", "payment_117", "payment_121", "payment_125", "payment_129", "payment_133", "payment_137", "payment_141", "payment_145", "payment_149", "payment_153", "payment_157", "payment_161", "payment_165", "payment_169", "payment_173", "payment_177", "payment_181", "payment_185", "payment_189", "payment_193", "payment_197", "payment_201", "payment_205", "payment_209", "payment_213", "payment_217", "payment_221", "payment_225", "payment_229", "payment_233", "payment_237", "payment_241", "payment_245", "payment_249", "payment_253"
        ));

        tableShards_29 = new LinkedHashSet<>(Arrays.asList(
                "payment_1037", "payment_1053", "payment_1069", "payment_1085", "payment_1101", "payment_1117", "payment_1133", "payment_1149", "payment_1165", "payment_1181", "payment_1197", "payment_1213", "payment_1229", "payment_1245", "payment_1261", "payment_1277", "payment_1293", "payment_1309", "payment_1325", "payment_1341", "payment_1357", "payment_1373", "payment_1389", "payment_1405", "payment_1421", "payment_1437", "payment_1453", "payment_1469", "payment_1485", "payment_1501", "payment_1517", "payment_1533", "payment_1549", "payment_1565", "payment_1581", "payment_1597", "payment_1613", "payment_1629", "payment_1645", "payment_1661", "payment_1677", "payment_1693", "payment_1709", "payment_1725", "payment_1741", "payment_1757", "payment_1773", "payment_1789", "payment_1805", "payment_1821", "payment_1837", "payment_1853", "payment_1869", "payment_1885", "payment_1901", "payment_1917", "payment_1933", "payment_1949", "payment_1965", "payment_1981", "payment_1997", "payment_2013", "payment_2029", "payment_2045"
        ));
    }

    @Test
    public void shard_extend_db() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20220804085617611819292024100075")));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(dbShards, shardingValue, 4);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"pay-29"}, result.toArray());
    }

    @Test
    public void shard_normal_db() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("account_id", new ArrayList<>(Arrays.asList(80)));
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20220804085617600819292024100075")));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(dbShards, shardingValue, 4);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"pay-1"}, result.toArray());
    }

    @Test
    public void shard_2021_order() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20210804085617610259292024100075")));
        columnNameAndShardingValuesMap.put("account_id", new ArrayList<>(Arrays.asList(80)));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(dbShards, shardingValue, 4);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"pay-0"}, result.toArray());
    }

    @Test
    public void shard_2021_order_2() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("account_id", new ArrayList<>(Arrays.asList(81)));
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20210804085617610259292024100075")));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(dbShards, shardingValue, 4);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"pay-1"}, result.toArray());
    }

    // 没有account_id，默认走第一个分片
    @Test
    public void shard_2021_order_no_account_id() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20210804085617610259292024100075")));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(dbShards, shardingValue, 4);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"pay-0"}, result.toArray());
    }

    @Test
    public void shard_table() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20220804085617600819292024100075")));
        columnNameAndShardingValuesMap.put("payee_id", new ArrayList<>(Arrays.asList(80)));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(tableShards_1, shardingValue, 256);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"payment_81"}, result.toArray());
    }

    @Test
    public void shard_table_29() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payment_attempt_no", new ArrayList<>(Arrays.asList("20220804085617611819292024100075")));
        columnNameAndShardingValuesMap.put("payee_id", new ArrayList<>(Arrays.asList(80)));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(tableShards_29, shardingValue, 256);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"payment_1181"}, result.toArray());
    }

    @Test
    public void shard_table_only_id() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payee_id", new ArrayList<>(Arrays.asList(100000005)));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(tableShards_1, shardingValue, 256);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"payment_5"}, result.toArray());
    }

    @Test
    public void shard_db_only_id() {
        HashMap<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap = new HashMap<>();
        columnNameAndShardingValuesMap.put("payee_id", new ArrayList<>(Arrays.asList(100000005)));
        ComplexKeysShardingValue<Comparable<?>> shardingValue = new ComplexKeysShardingValue<Comparable<?>>("payment", columnNameAndShardingValuesMap, new HashMap<>());
        Collection<String> result = TradeSharding.shard(dbShards, shardingValue, 4);
        assertEquals(1, result.size());
        assertArrayEquals(new String[]{"pay-1"}, result.toArray());
    }


    @Test
    public void findShard_null() {
        String result = TradeSharding.findShard(new ArrayList<String>(), "pay");

        assertEquals(null, result);
    }
}