package in.apollo.utils;

import java.util.Comparator;

import in.apollo.model.pojo.FlickerItemPojo;

/**
 Used for sorting in ascending order of
        roll number
 */
public class SortByDateComparator implements Comparator<FlickerItemPojo> {
    @Override
    public int compare(FlickerItemPojo o1, FlickerItemPojo o2) {
        o1.getDateTaken();
        o2.getDateTaken();
        return (int) (Utils.getLongFromDate(o2.getDateTaken()) - Utils.getLongFromDate(o1.getDateTaken()));
    }
}
