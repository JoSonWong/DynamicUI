package com.bestarmedia.widget.data;

import java.util.List;

public class ElementAttr {
    public String viewType;//0 基本元素，1容器
    public int width;//宽度
    public int height;//高度
    public String url;//图片url
    public String bgColor;//背景颜色
    public String action;
    public String text;//文本
    public String textColor;//文字颜色
    public int textSize;//文字大小
    public int gravity;
    public int layoutGravity;
    public int corner;//圆角
    public int[] margins;
    public int[] paddings;
    public String images[];
    public List<ElementAttr> elements;
}
