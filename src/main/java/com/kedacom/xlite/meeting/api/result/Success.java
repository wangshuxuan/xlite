package com.kedacom.xlite.meeting.api.result;

/**
 * @author wangshuxuan
 * @date 2020/4/3 13:27
 */
public class Success extends ErrorCode {

    private boolean success;

    private String description;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
