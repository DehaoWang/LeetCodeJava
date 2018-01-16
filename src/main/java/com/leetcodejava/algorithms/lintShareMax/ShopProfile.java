package com.leetcodejava.algorithms.lintShareMax;

/**
 * Created by wangdehao on 17/11/6.
 */
public class ShopProfile {
    Integer shopId = 0;
    Integer onlineCli = 0;
    Integer onlineImp = 0;
    Double avgRoi = 0.0;
    Integer isStay = 0;

    public ShopProfile(Integer shopId, Integer onlineCli, Integer onlineImp, Double avgRoi, Integer isStay) {
        this.shopId = shopId;
        this.onlineCli = onlineCli;
        this.onlineImp = onlineImp;
        this.avgRoi = avgRoi;
        this.isStay = isStay;
    }
}
