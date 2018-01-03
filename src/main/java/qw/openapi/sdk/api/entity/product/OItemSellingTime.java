package qw.openapi.sdk.api.entity.product;

import qw.openapi.sdk.api.enumeration.product.OItemWeekEnum;
import java.util.List;

public class OItemSellingTime {
    private List<OItemWeekEnum> weeks;
    private String beginDate;
    private String endDate;
    private List<OItemTime> times;

    public OItemSellingTime() {
    }

    public List<OItemWeekEnum> getWeeks() {
        return this.weeks;
    }

    public void setWeeks(List<OItemWeekEnum> weeks) {
        this.weeks = weeks;
    }

    public String getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<OItemTime> getTimes() {
        return this.times;
    }

    public void setTimes(List<OItemTime> times) {
        this.times = times;
    }
}
