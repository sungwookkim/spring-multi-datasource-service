package com.datasource.entity.goods;

import java.time.LocalDateTime;

/**
 * <pre>
 *     상품 Entity
 * </pre>
 */
public class Goods {
    private Long goodsSeq;

    private final Long memberSeq;
    private final String name;
    private final double price;
    private final LocalDateTime regDtm = LocalDateTime.now();

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
        this.memberSeq = null;
    }

    public Goods(Long memberSeq, String name, double price) {
        this.memberSeq = memberSeq;
        this.name = name;
        this.price = price;
    }

    public Long getGoodsSeq() {
        return goodsSeq;
    }

    public Long getMemberSeq() {
        return memberSeq;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getRegDtm() {
        return regDtm;
    }
}