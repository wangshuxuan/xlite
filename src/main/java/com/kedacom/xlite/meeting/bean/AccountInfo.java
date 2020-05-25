package com.kedacom.xlite.meeting.bean;

import com.kedacom.xlite.meeting.api.result.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountInfo
 * @author wangshuxuan
 * @date 2019/1/19 13:31
 */
public class AccountInfo extends ErrorCode {

    private String moid;
    private String email;
    private String e164;
    private String account;
    private String mobile;
    private String jid;
    private String name;
    private String seat;
    private String extNum;
    private String jobNum;
    private String brief;
    private String sex;
    private String userDomainMoid;
    private String portrait256;
    private Boolean enable;
    private Boolean enableMeeting;
    private Boolean meetingAdmin;
    private Boolean defaultUserDomainAdmin;
    private Boolean cmsApproval;

    private List<Department> depts = new ArrayList<Department>();

    public String getMoid() {
        return moid;
    }

    public void setMoid(String moid) {
        this.moid = moid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getE164() {
        return e164;
    }

    public void setE164(String e164) {
        this.e164 = e164;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Department> getDepts() {
        return depts;
    }

    public void setDepts(List<Department> depts) {
        this.depts = depts;
    }

    public String getUserDomainMoid() {
        return userDomainMoid;
    }

    public void setUserDomainMoid(String userDomainMoid) {
        this.userDomainMoid = userDomainMoid;
    }

    public String getPortrait256() {
        return portrait256;
    }

    public void setPortrait256(String portrait256) {
        this.portrait256 = portrait256;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getEnableMeeting() {
        return enableMeeting;
    }

    public void setEnableMeeting(Boolean enableMeeting) {
        this.enableMeeting = enableMeeting;
    }

    public Boolean getMeetingAdmin() {
        return meetingAdmin;
    }

    public void setMeetingAdmin(Boolean meetingAdmin) {
        this.meetingAdmin = meetingAdmin;
    }

    public Boolean getDefaultUserDomainAdmin() {
        return defaultUserDomainAdmin;
    }

    public void setDefaultUserDomainAdmin(Boolean defaultUserDomainAdmin) {
        this.defaultUserDomainAdmin = defaultUserDomainAdmin;
    }

    public Boolean getCmsApproval() {
        return cmsApproval;
    }

    public void setCmsApproval(Boolean cmsApproval) {
        this.cmsApproval = cmsApproval;
    }
}
