package study.daydayup.wolf.common.util.collection.joiner;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util.collection.joiner
 *
 * @author Wingle
 * @since 2020/5/18 9:30 下午
 **/
public class DefaultJoinerTest {

    @Test
    @SuppressWarnings("all")
    public void join_one() {
        List<Goods> goodsList = mockGoodsList();
        List<GoodsDetail> detailList = mockDetailList();

        Joiner<Goods, GoodsDetail> joiner = new DefaultJoiner<>(goodsList);
        joiner.on(Goods::setDetail, Goods::getId).join(detailList, GoodsDetail::getGoodsId);

        assertNotNull("DefaultJoiner join fail", goodsList);
        assertNotNull("DefaultJoiner join fail", goodsList.get(0));

        assertEquals("DefaultJoiner join fail", 20, goodsList.size());

        assertEquals("DefaultJoiner join fail", goodsList.get(0).getId(), goodsList.get(0).getDetail().getGoodsId());
        assertEquals("DefaultJoiner join fail", goodsList.get(0).getCid(), goodsList.get(0).getDetail().getCid());

        assertEquals("DefaultJoiner join fail", goodsList.get(10).getId(), goodsList.get(10).getDetail().getGoodsId());
        assertEquals("DefaultJoiner join fail", goodsList.get(10).getCid(), goodsList.get(10).getDetail().getCid());

        assertNotNull("DefaultJoiner join fail", goodsList.get(0).getDetail());
        assertNotNull("DefaultJoiner join fail", goodsList.get(10).getDetail());
        assertNotNull("DefaultJoiner join fail", goodsList.get(19).getDetail());
    }

    @Test
    @SuppressWarnings("all")
    public void join_two() {
        List<Goods> goodsList = mockGoodsList();
        List<GoodsDetail> detailList = mockDetailList();

        Joiner<Goods, GoodsDetail> joiner = new DefaultJoiner<>(goodsList);
        joiner.on(Goods::setDetail, Goods::getId, Goods::getCid)
                .join(detailList, GoodsDetail::getGoodsId, GoodsDetail::getCid);

        assertNotNull("DefaultJoiner join fail", goodsList);
        assertNotNull("DefaultJoiner join fail", goodsList.get(0));

        assertEquals("DefaultJoiner join fail", 20, goodsList.size());

        assertEquals("DefaultJoiner join fail", goodsList.get(0).getId(), goodsList.get(0).getDetail().getGoodsId());
        assertEquals("DefaultJoiner join fail", goodsList.get(0).getCid(), goodsList.get(0).getDetail().getCid());

        assertEquals("DefaultJoiner join fail", goodsList.get(10).getId(), goodsList.get(10).getDetail().getGoodsId());
        assertEquals("DefaultJoiner join fail", goodsList.get(10).getCid(), goodsList.get(10).getDetail().getCid());

        assertNotNull("DefaultJoiner join fail", goodsList.get(0).getDetail());
        assertNotNull("DefaultJoiner join fail", goodsList.get(10).getDetail());
        assertNotNull("DefaultJoiner join fail", goodsList.get(19).getDetail());
    }

    private List<Goods> mockGoodsList() {
        List<Goods> goodsList = new ArrayList<>();

        Goods goods;
        for (int i = 0; i < 20; i++) {
            goods = new Goods();
            goods.setId((long) i);

            if (i % 2 == 0) {
                goods.setCid("a");
            } else {
                goods.setCid("b");
            }

            goodsList.add(goods);
        }

        return goodsList;
    }

    private List<GoodsDetail> mockDetailList() {
        List<GoodsDetail> goodsList = new ArrayList<>();

        GoodsDetail goods;
        for (int i = 0; i < 20; i++) {
            goods = new GoodsDetail();
            goods.setGoodsId((long) i);
            goods.setDetail("detail_" + i);
            goods.setPics("pics_" + i);

            if (i % 2 == 0) {
                goods.setCid("a");
            } else {
                goods.setCid("b");
            }

            goodsList.add(goods);
        }

        return goodsList;
    }

    private List<GoodsSku> mockSkuList() {
        List<GoodsSku> goodsList = new ArrayList<>();

        GoodsSku goods;
        for (int i = 0; i < 20; i++) {
            goods = new GoodsSku();
            goods.setGoodsId((long) i);
            goods.setSkuId((long)i);
            goods.setSkuName("sku_" + i);

            if (i % 2 == 0) {
                goods.setCid("a");
            } else {
                goods.setCid("b");
            }

            goodsList.add(goods);
        }

        return goodsList;
    }

    @Data
    static class Goods {
        private Long id;
        private String cid;
        private GoodsDetail detail;
        private GoodsSku sku;
    }

    @Data
    static class GoodsDetail {
        private Long goodsId;
        private String cid;
        private String detail;
        private String pics;
    }

    @Data
    static class GoodsSku {
        private Long goodsId;
        private String cid;
        private Long skuId;
        private String skuName;
    }
}