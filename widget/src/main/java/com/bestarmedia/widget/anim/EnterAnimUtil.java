package com.bestarmedia.widget.anim;

import com.bestarmedia.widget.container.EnterAnimLayout;

public class EnterAnimUtil {

    public static Anim getRandomEnterAnim(EnterAnimLayout enterAnimLayout) {
//        Random random = new Random();
//        int nextInt = random.nextInt(10);
//        switch (nextInt) {
//            case 0:
//                return new AnimHeZhuang(enterAnimLayout);
//            case 1:
//                return new AnimLingXing(enterAnimLayout);
//            case 2:
//                return new AnimLunZi(enterAnimLayout);
//            case 3:
//                return new AnimPiLie(enterAnimLayout);
//            case 4:
//                return new AnimQiPan(enterAnimLayout);
//            case 5:
//                return new AnimShanXingZhanKai(enterAnimLayout);
//            case 6:
//                return new AnimShiZiXingKuoZhan(enterAnimLayout);
//            case 7:
//                return new AnimSuiJiXianTiao(enterAnimLayout);
//            case 8:
//                return new AnimXiangNeiRongJie(enterAnimLayout);
//            case 9:
//                return new AnimYuanXingKuoZhan(enterAnimLayout);
//            default:
        return new AnimBaiYeChuang(enterAnimLayout);
    }
}
