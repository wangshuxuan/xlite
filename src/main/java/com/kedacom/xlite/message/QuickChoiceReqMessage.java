package com.kedacom.xlite.message;

/**
 * 请求快捷
 * @author wangshuxuan
 * @date 2020/4/7 14:42
 */
public class QuickChoiceReqMessage {

    private String locationInfo;

    private String originQRcode;

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getOriginQRcode() {
        return originQRcode;
    }

    public void setOriginQRcode(String originQRcode) {
        this.originQRcode = originQRcode;
    }
}
