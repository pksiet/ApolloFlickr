package in.apollo.utils;

import in.apollo.model.pojo.FlickerItemPojo;

public interface OnClickListener {
    public void longPress(FlickerItemPojo flickerItemPojo);
    public void click(String url, String title);
}
