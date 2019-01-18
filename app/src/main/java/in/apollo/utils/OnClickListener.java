package in.apollo.utils;

import in.apollo.model.pojo.FlickerItemPojo;

/**
 * ClickListner interface Gallery view model
 */
public interface OnClickListener {
    public void longPress(FlickerItemPojo flickerItemPojo);
    public void click(String url, String title);
}
