package com.bestarmedia.widget.data;

import java.util.ArrayList;
import java.util.List;

public class BannerItem {

    public String imageUrl;
    public String title;
    public int viewType;


    public BannerItem(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<BannerItem> getTestData() {
        List<BannerItem> list = new ArrayList<>();
        list.add(new BannerItem("https://img.zcool.cn/community/011ad05e27a173a801216518a5c505.jpg", null, 1));
        list.add(new BannerItem("https://img.zcool.cn/community/0148fc5e27a173a8012165184aad81.jpg", null, 1));
        list.add(new BannerItem("https://img.zcool.cn/community/013c7d5e27a174a80121651816e521.jpg", null, 1));
        list.add(new BannerItem("https://img.zcool.cn/community/01b8ac5e27a173a80120a895be4d85.jpg", null, 1));
        list.add(new BannerItem("https://img.zcool.cn/community/01a85d5e27a174a80120a895111b2c.jpg", null, 1));
        list.add(new BannerItem("https://img.zcool.cn/community/01085d5e27a174a80120a8958791c4.jpg", null, 1));
        list.add(new BannerItem("https://img.zcool.cn/community/01f8735e27a174a8012165188aa959.jpg", null, 1));
        return list;
    }
}
