package com.wolf.framework.middleware.hbase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.wolf.framework.middleware.hbase.config.HBaseConfig;
import com.wolf.framework.middleware.hbase.config.HBaseConfigProperties;
import com.wolf.framework.middleware.hbase.option.*;
import com.wolf.framework.middleware.hbase.result.HBaseResult;

import java.util.Arrays;
import java.util.List;

/**
 * HBaseTest
 *
 * @author rocky
 * @since 2023/8/2 15:31
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HBaseConfig.class, HBaseConfigProperties.class})
@ComponentScan("com.wolf.framework.middleware.hbase")
@TestPropertySource(locations = "classpath:application.yml")
@EnableConfigurationProperties
public class HBaseTest {

    @Test
    public void testGet() {
        GetOptionCriteria getOption = new GetOptionCriteria();
        getOption.setRowKey("row2");
        HBaseResult<PaymentHBase> result = HBase.get(getOption).table(PaymentHBase.class).execute();
        System.out.println(result);
        Assert.assertEquals("value100", result.getData().getPaymentNo());
    }

    @Test
    public void testGets(){
        GetsOptionCriteria gets = new GetsOptionCriteria();
        gets.setRowKeys(Arrays.asList("row1","row2"));
        HBaseResult<List<PaymentHBase>> result = HBase.gets(gets).table(PaymentHBase.class).execute();
        System.out.println(result);
        Assert.assertEquals("value100", result.getData().get(1).getPaymentNo());
        Assert.assertEquals("value1", result.getData().get(0).getPaymentNo());
    }

    @Test
    public void testPut() {
        PutOptionCriteria put = new PutOptionCriteria();
        put.setRowKey("row2");
        PaymentHBase data = new PaymentHBase();
        data.setPaymentNo("value100");
        put.setData(data);
        HBaseResult result = HBase.put(put).table(PaymentHBase.class).execute();
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testPuts(){
        PutsOptionCriteria puts = new PutsOptionCriteria();
        puts.setRowKeys(Arrays.asList("row3","row4"));
        PaymentHBase paymentHBase3 = new PaymentHBase();
        paymentHBase3.setPaymentNo("value3");
        PaymentHBase paymentHBase4 = new PaymentHBase();
        paymentHBase4.setPaymentNo("value4");
        puts.setData(Arrays.asList(paymentHBase3,paymentHBase4));
        HBaseResult result = HBase.puts(puts).table(PaymentHBase.class).execute();
        Assert.assertTrue(result.isSuccess());
    }



    @Test
    public void testScan(){
        ScanOptionCriteria scan = new ScanOptionCriteria();
        scan.setLimit(2);
        scan.setStartRowKey("row1");
        scan.setStopRowKey("row9");
        HBaseResult<List<PaymentHBase>> result = HBase.scan(scan).table(PaymentHBase.class).execute();
        System.out.println(result);
        Assert.assertEquals(2,result.getData().size());
    }
}
