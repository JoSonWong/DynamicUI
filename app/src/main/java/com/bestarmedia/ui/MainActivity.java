package com.bestarmedia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.bestarmedia.widget.container.HLayout;
import com.bestarmedia.widget.data.ElementAttr;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private HorizontalScrollView root;
    private static final String[] imageUrls = new String[]{
//            "https://github.com/yyued/SVGA-Samples/blob/master/posche.svga",
            "https://t8.baidu.com/it/u=3571592872,3353494284&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=f4a567c6769eaad10249738f62fdc600",
            "https://t8.baidu.com/it/u=2247852322,986532796&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=2b4b8e43dca93aab5c04b35edb160e5c",
            "https://t7.baidu.com/it/u=3204887199,3790688592&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=21c0920655a77c0308dd09cfd0c1825a",
            "https://t9.baidu.com/it/u=3363001160,1163944807&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=d0978212c98bad265a3d86e76c03d8ed",
            "https://t9.baidu.com/it/u=583874135,70653437&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=0c103bf8ee7fede4d6b937eefb6e2034",
            "https://t9.baidu.com/it/u=1307125826,3433407105&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=888074432029cb72ae1cbc1b075a64c0",
            "https://t9.baidu.com/it/u=2268908537,2815455140&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=ca7bbbf98cb87301070caa5c22cdf8c8",
            "https://t9.baidu.com/it/u=86853839,3576305254&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=a10d565cd74138f4d3b1e9d7c6b1d607",
            "https://t8.baidu.com/it/u=1484500186,1503043093&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1582695955&t=eb941966f889891cc34eef1bbcaf929c",
            "https://dss0.baidu.com/73F1bjeh1BF3odCf/it/u=2358783429,2193152643&fm=85&s=4A82C20B1E57DA1F37EDB1F10300C0B7",
            "https://dss0.baidu.com/73x1bjeh1BF3odCf/it/u=35604220,3256089654&fm=85&s=3A83814C56B2A67148F491890300C081",
            "http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.root);

        List<ElementAttr> data = new ArrayList<>();

        data.add(createImage(300, 800));

        //容器1
        ElementAttr container0 = new ElementAttr();
        container0.viewType = "c_VLayout";
        container0.width = 400;
        container0.height = 800;
        List<ElementAttr> elementImg0 = new ArrayList<>();
        elementImg0.add(createText(400, 200));
        elementImg0.add(createImage(400, 300));
        elementImg0.add(createImage(400, 300));
        container0.elements = elementImg0;
        data.add(container0);


        //容器
        ElementAttr container1 = new ElementAttr();
        container1.viewType = "c_VLayout";
        container1.width = 400;
        container1.height = 800;
        List<ElementAttr> elementImg = new ArrayList<>();
        elementImg.add(createImage(400, 400));
        elementImg.add(createImage(400, 400));
        container1.elements = elementImg;
        data.add(container1);


        //容器2
        ElementAttr container2 = new ElementAttr();
        container2.viewType = "c_VLayout";
        container2.width = 800;
        container2.height = 800;
        List<ElementAttr> container2Data = new ArrayList<>();

        ElementAttr container21 = new ElementAttr();
        container21.viewType = "c_HLayout";
        container21.width = 800;
        container21.height = 400;
        List<ElementAttr> container21Image = new ArrayList<>();
        container21Image.add(createImage(500, 400));
        container21Image.add(createImage(300, 400));
        container21.elements = container21Image;
        container2Data.add(container21);

        container2Data.add(createImage(800, 400));
        container2.elements = container2Data;
        data.add(container2);

        data.add(createBanner(800, 800));

        //容器3(3上+2下)
        ElementAttr container3 = new ElementAttr();
        container3.viewType = "c_VLayout";
        container3.width = 1000;
        container3.height = 800;

        List<ElementAttr> container3Data = new ArrayList<>();
        ElementAttr container31 = new ElementAttr();
        container31.viewType = "c_HLayout";
        container31.width = 1000;
        container31.height = 400;
        List<ElementAttr> container31Image = new ArrayList<>();
        container31Image.add(createImage(300, 400));
        container31Image.add(createImage(400, 400));
        container31Image.add(createImage(300, 400));
        container31.elements = container31Image;
        container3Data.add(container31);

        ElementAttr container32 = new ElementAttr();
        container32.viewType = "c_HLayout";
        container32.width = 1000;
        container32.height = 400;
        List<ElementAttr> container32Image = new ArrayList<>();
        container32Image.add(createImage(500, 400));
        container32Image.add(createBanner(500, 400));
        container32.elements = container32Image;
        container3Data.add(container32);

        container3.elements = container3Data;

        data.add(container3);

        Gson gson = new Gson();
        String json = gson.toJson(data);
        Log.d(getClass().getSimpleName(), "json data:" + json);
        HLayout hLayout = new HLayout(this);
        hLayout.createModules(data);

        root.addView(hLayout, new ScrollView.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.WRAP_CONTENT));
    }

    private ElementAttr createBanner(int w,int h){
        ElementAttr element = new ElementAttr();
        element.viewType = "v_banner";
        element.width = w;
        element.height = h;
        element.bgColor = randomColor();
        Random random = new Random();
        String imgUrl = imageUrls[random.nextInt(imageUrls.length)];
        element.url = imgUrl;
        element.action = imgUrl;
        element.textSize = 50;
        element.text = "模块C";
        element.textColor = randomColor();
        element.gravity = 17;
        element.corner = 0;
        element.paddings = new int[]{5, 10, 15, 20};
        element.images=imageUrls;
        return element;
    }

    private ElementAttr createText(int w, int h) {
        ElementAttr element = new ElementAttr();
        element.viewType = "v_text";
        element.width = w;
        element.height = h;
        element.bgColor = randomColor();
        Random random = new Random();
        element.action = imageUrls[random.nextInt(imageUrls.length)];
        element.textSize = 50;
        element.text = "模块B";
        element.textColor = randomColor();
        element.gravity = 17;
        element.corner = 0;
        element.paddings = new int[]{5, 10, 15, 20};
        return element;
    }


    private ElementAttr createImage(int w, int h) {
        ElementAttr element = new ElementAttr();
        element.viewType = "v_DImage";
        element.width = w;
        element.height = h;
        element.bgColor = randomColor();
        Random random = new Random();
        String imgUrl = imageUrls[random.nextInt(imageUrls.length)];
        element.url = imgUrl;
        element.action = imgUrl;
        element.textSize = 50;
        element.text = "模块A";
        element.textColor = randomColor();
        element.gravity = 17;
        element.corner = 0;
        element.paddings = new int[]{5, 10, 15, 20};
        return element;
    }

    public static String randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return convertRGBToHex(r, g, b);
    }

    //**将rgb色彩值转成16进制代码**
    public static String convertRGBToHex(int r, int g, int b) {
        String rFString, rSString, gFString, gSString,
                bFString, bSString, result;
        int red, green, blue;
        int rred, rgreen, rblue;
        red = r / 16;
        rred = r % 16;
        if (red == 10) rFString = "A";
        else if (red == 11) rFString = "B";
        else if (red == 12) rFString = "C";
        else if (red == 13) rFString = "D";
        else if (red == 14) rFString = "E";
        else if (red == 15) rFString = "F";
        else rFString = String.valueOf(red);

        if (rred == 10) rSString = "A";
        else if (rred == 11) rSString = "B";
        else if (rred == 12) rSString = "C";
        else if (rred == 13) rSString = "D";
        else if (rred == 14) rSString = "E";
        else if (rred == 15) rSString = "F";
        else rSString = String.valueOf(rred);

        rFString = rFString + rSString;

        green = g / 16;
        rgreen = g % 16;

        if (green == 10) gFString = "A";
        else if (green == 11) gFString = "B";
        else if (green == 12) gFString = "C";
        else if (green == 13) gFString = "D";
        else if (green == 14) gFString = "E";
        else if (green == 15) gFString = "F";
        else gFString = String.valueOf(green);

        if (rgreen == 10) gSString = "A";
        else if (rgreen == 11) gSString = "B";
        else if (rgreen == 12) gSString = "C";
        else if (rgreen == 13) gSString = "D";
        else if (rgreen == 14) gSString = "E";
        else if (rgreen == 15) gSString = "F";
        else gSString = String.valueOf(rgreen);

        gFString = gFString + gSString;

        blue = b / 16;
        rblue = b % 16;

        if (blue == 10) bFString = "A";
        else if (blue == 11) bFString = "B";
        else if (blue == 12) bFString = "C";
        else if (blue == 13) bFString = "D";
        else if (blue == 14) bFString = "E";
        else if (blue == 15) bFString = "F";
        else bFString = String.valueOf(blue);

        if (rblue == 10) bSString = "A";
        else if (rblue == 11) bSString = "B";
        else if (rblue == 12) bSString = "C";
        else if (rblue == 13) bSString = "D";
        else if (rblue == 14) bSString = "E";
        else if (rblue == 15) bSString = "F";
        else bSString = String.valueOf(rblue);
        bFString = bFString + bSString;
        result = "#" + rFString + gFString + bFString;
        return result;

    }
}
