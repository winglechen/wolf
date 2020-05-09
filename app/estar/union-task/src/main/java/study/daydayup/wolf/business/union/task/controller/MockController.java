package study.daydayup.wolf.business.union.task.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.state.NewState;
import study.daydayup.wolf.business.trade.api.domain.state.base.PaidState;
import study.daydayup.wolf.business.union.task.dts.mock.TradeMock;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.util.lang.CharsetUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/13 1:04 下午
 **/
@RestController
public class MockController {
    @Resource
    private TradeMock tradeMock;
    @Resource
    private JdbcTemplate jdbc;

    @RequestMapping("/mock/all")
    public String mock() {
        tradeMock.run();

        return "mocking ...";
    }

    @RequestMapping("/mock/audit")
    public String audit() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("audit.csv");
        Reader reader = new InputStreamReader(in, CharsetUtil.UTF_8);

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withTrim()
                .parse(reader);

        for (CSVRecord record : records) {
            insertKoi(record);
            insertPreviewCount(record);
            insertRequestCount(record);
            insertPaidCount(record);
        }

        return "mock audit ...";
    }

    //"id","ordId","date","source","installCount","registerCount","basicInfoCount","aadhaarCount","bankCardCount"
    private void insertKoi(CSVRecord record) {
        Map<String, Object> data = new HashMap<>();
        data.put("org_id", 1);
        data.put("date", parseDate(record.get("date")));
        data.put("source", record.get("source"));
        data.put("install_count", Integer.valueOf(record.get("installCount")));
        data.put("register_count", Integer.valueOf(record.get("registerCount")));
        data.put("basic_info_count", Integer.valueOf(record.get("basicInfoCount")));
        data.put("aadhaar_count", Integer.valueOf(record.get("aadhaarCount")));
        data.put("bank_card_count", Integer.valueOf(record.get("bankCardCount")));

        Sql sql = Sql.insert("daily_koi").values(data);
        System.out.println(sql.getSql());

        jdbc.update(sql.getSql(), sql.getData());
    }

    //"auditPreviewCount","auditRequestCount","auditPaidCount","auditPaidAmount")
    private void insertPreviewCount(CSVRecord record) {
        Map<String, Object> data = new HashMap<>();
        data.put("org_id", 1);
        data.put("date", parseDate(record.get("date")));
        data.put("source", record.get("source"));
        data.put("trade_type", TradeTypeEnum.LOAN_CONTRACT.getCode());
        data.put("state", (new NewState()).getCode());
        data.put("trade_count", Integer.valueOf(record.get("auditPreviewCount")));

        Sql sql = Sql.insert("daily_trade").values(data);
        System.out.println(sql.getSql());

        jdbc.update(sql.getSql(), sql.getData());
    }

    private void insertRequestCount(CSVRecord record) {
        Map<String, Object> data = new HashMap<>();
        data.put("org_id", 1);
        data.put("date", parseDate(record.get("date")));
        data.put("source", record.get("source"));
        data.put("trade_type", TradeTypeEnum.AUDIT_FEE.getCode());
        data.put("state", (new NewState()).getCode());
        data.put("trade_count", Integer.valueOf(record.get("auditRequestCount")));

        Sql sql = Sql.insert("daily_trade").values(data);
        System.out.println(sql.getSql());

        jdbc.update(sql.getSql(), sql.getData());
    }

    private void insertPaidCount(CSVRecord record) {
        Map<String, Object> data = new HashMap<>();
        data.put("org_id", 1);
        data.put("date", parseDate(record.get("date")));
        data.put("source", record.get("source"));
        data.put("trade_type", TradeTypeEnum.AUDIT_FEE.getCode());
        data.put("state", (new PaidState()).getCode());
        data.put("trade_count", Integer.valueOf(record.get("auditPaidCount")));
        BigDecimal amount = new BigDecimal(record.get("auditPaidAmount"));
        amount = DecimalUtil.scale(amount);
        data.put("trade_amount", amount);

        Sql sql = Sql.insert("daily_trade").values(data);
        System.out.println(sql.getSql());

        jdbc.update(sql.getSql(), sql.getData());
    }

    private LocalDate parseDate(String date) {
        if (date == null) {
            return null;
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, format);
    }
}
