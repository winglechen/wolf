package study.daydayup.wolf.common.io.sql.builder;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 10:26 上午
 **/
public class InsertBuilderTest {
    @Test
    public void newInsertTest() {
        InsertBuilder builder = build(true, true);

        String columnExpect = "(`id`, `name`, `bod`, `created_at`) VALUES ";
        String columns = builder.getInsertColumns();
        String valuesExpect = "(?, ?, ?, ?)";
        String values = builder.getInsertValues();
        List<Object> prepareData = builder.getPreparedDataList();

        assertEquals("InsertBuilder.getInsertColumns length not equals", columnExpect.length(), columns.length());

        assertTrue("InsertBuilder.getInsertColumns contains fail", columns.contains("`id`"));
        assertTrue("InsertBuilder.getInsertColumns contains fail", columns.contains("`name`"));
        assertTrue("InsertBuilder.getInsertColumns contains fail", columns.contains("`bod`"));
        assertTrue("InsertBuilder.getInsertColumns contains fail", columns.contains("`created_at`"));
        assertTrue("InsertBuilder.getInsertColumns contains fail", columns.contains("`) VALUES "));

        assertEquals("InsertBuilder.getInsertValues fail", valuesExpect, values);
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("?"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("?)"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("(?"));

        assertNotNull("InsertBuilder.getPreparedDataList null return", prepareData);
        assertEquals("InsertBuilder.getPreparedDataList size not match", 4, prepareData.size());
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(Integer.valueOf(123)));
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains("test"));
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(LocalDate.of(2020, 9, 1)));
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(LocalDateTime.of(2020, 9, 1, 1, 1, 1)));
    }

    @Test
    public void multiInsertTest() {
        InsertBuilder builder = build(true, false);

        String columnExpect = ", ";
        String columns = builder.getInsertColumns();
        String valuesExpect = "(?, ?, ?, ?)";
        String values = builder.getInsertValues();
        List<Object> prepareData = builder.getPreparedDataList();

        assertEquals("InsertBuilder.getInsertColumns length not equals", columnExpect.length(), columns.length());
        assertEquals("InsertBuilder.getInsertColumns length not equals", columnExpect, columns);

        assertEquals("InsertBuilder.getInsertValues fail", valuesExpect, values);
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("?"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("?)"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("(?"));

        assertNotNull("InsertBuilder.getPreparedDataList null return", prepareData);
        assertEquals("InsertBuilder.getPreparedDataList size not match", 4, prepareData.size());
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(Integer.valueOf(123)));
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains("test"));
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(LocalDate.of(2020, 9, 1)));
        assertTrue("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(LocalDateTime.of(2020, 9, 1, 1, 1, 1)));
    }

    @Test
    public void noPrepareTest() {
        InsertBuilder builder = build(false, false);

        String columnExpect = ", ";
        String columns = builder.getInsertColumns();
        String values = builder.getInsertValues();
        List<Object> prepareData = builder.getPreparedDataList();

        assertEquals("InsertBuilder.getInsertColumns length not equals", columnExpect.length(), columns.length());
        assertEquals("InsertBuilder.getInsertColumns length not equals", columnExpect, columns);

        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("123"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("'test'"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("'2020-09-01'"));
        assertTrue("InsertBuilder.getInsertValues contains fail", values.contains("'2020-09-01 01:01:01'"));

        assertNotNull("InsertBuilder.getPreparedDataList null return", prepareData);
        assertEquals("InsertBuilder.getPreparedDataList size not match", 0, prepareData.size());
        assertFalse("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(Integer.valueOf(123)));
        assertFalse("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains("test"));
        assertFalse("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(LocalDate.of(2020, 9, 1)));
        assertFalse("InsertBuilder.getPreparedDataList contains fail",  prepareData.contains(LocalDateTime.of(2020, 9, 1, 1, 1, 1)));
    }

    private InsertBuilder build(boolean prepared, boolean isFirstValue) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 123);
        data.put("name", "test");
        data.put("bod", LocalDate.of(2020, 9, 1));
        data.put("created_at", LocalDateTime.of(2020, 9, 1, 1, 1, 1));

        return InsertBuilder.build(data, prepared, isFirstValue);
    }
}