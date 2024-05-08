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

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * HBaseDAOTest
 *
 * @author rocky
 * @since 2023/8/10 15:50
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HBaseConfig.class, HBaseConfigProperties.class})
@ComponentScan("com.wolf.framework.middleware.hbase")
@TestPropertySource(locations = "classpath:application.yml")
@EnableConfigurationProperties
public class HBaseDAOTest {

    @Resource
    private PaymentHBaseDAO paymentHBaseDAO;

    @Test
    public void testFind(){
        PaymentHBase paymentHBase = paymentHBaseDAO.find("row1");
        System.out.println(paymentHBase);
        Assert.assertEquals("value1",paymentHBase.getPaymentNo());
    }

    @Test
    public void testFindList(){
        List<PaymentHBase> list = paymentHBaseDAO.findList(Arrays.asList("row1","row2"));
        System.out.println(list);
        Assert.assertEquals(2,list.size());
    }

    @Test
    public void testQuery(){
        List<PaymentHBase> list = paymentHBaseDAO.query("row1", "row9", 100, true);
        System.out.println(list);
        Assert.assertEquals(4,list.size());
    }

    @Test
    public void testSave(){
        PaymentHBase paymentHBase = new PaymentHBase();
        paymentHBase.setPaymentNo("value2");
        boolean row2 = paymentHBaseDAO.save("row2", paymentHBase);
        Assert.assertTrue(row2);
    }

    @Test
    public void testBatchSave(){
        PaymentHBase paymentHBase3 = new PaymentHBase();
        paymentHBase3.setPaymentNo("value3");
        PaymentHBase paymentHBase4 = new PaymentHBase();
        paymentHBase4.setPaymentNo("value4");
        boolean save = paymentHBaseDAO.batchSave(Arrays.asList("row3", "row4"), Arrays.asList(paymentHBase3, paymentHBase4));
        Assert.assertTrue(save);
    }
}
