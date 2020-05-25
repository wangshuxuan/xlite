package com.kedacom.xlite.meeting.api.result;

import java.io.Serializable;
import java.util.Date;

/**
 * 校验token
 * @author wangshuxuan
 * @date 2019/9/11 9:26
 */
public class ValidationTokenResult extends ErrorCode implements Serializable {

    private static final long serialVersionUID = 8248469722076800037L;
    private String token;
    private String moid;
    private String email;
    private String e164;
    private String account;
    private String mobile;
    private String jid;
    private String password;
    private String encryptionPassword;
    private String userDomainMoid;
    private String userDomainName;
    private String serviceDomainMoid;
    private String serviceDomainName;
    private String deviceGuid;
    private String nuServerId;
    private String deviceType;
    private Integer accountType;
    private Boolean binded;
    private Boolean enable;
    private Date createdAt;
    private Boolean limited;
    private String name;
    private String loginName;
    private Date validityPeriod;
    private String restrictUsedOn;
    private Integer restrictStrategy;
    private Boolean enableHD = false;
    private Boolean enableCall = false;
    private Boolean enableRoam = false;
    private Boolean enableSatellite = false;
    private Boolean enableSatelliteP2P = false;
    private Boolean userDomainAdmin = false;
    private Boolean enableWeibo = false;
    private Boolean weiboAdmin = false;
    private Boolean enableMeeting = false;
    private Boolean meetingAdmin = false;
    private Boolean enableMeetingSMS = false;
    private Boolean enableBMC = false;
    private Boolean enableUMC = false;
    private Boolean enableDCS = false;
    private Boolean enableVRS = false;
    private Boolean enableNM = false;
    private Boolean enableVenueMonitor = false;
    private Boolean serviceDomainAdmin = false;
    private Boolean defaultServiceDomainAdmin = false;
    private Boolean defaultUserDomainAdmin = false;
    private Boolean enableOut = false;
    private Boolean enableIncoming = false;
    private Boolean dcsAdmin = false;
    private Boolean vrsAdmin = false;
    private Boolean nmAdmin = false;
    private Boolean enableVideo = false;
    private Boolean enableLive = false;
    private Boolean enablePlay = false;
    private Boolean enableUnicat = false;
    private Boolean enableDownload = false;
    private Boolean cmsApproval = false;
    private Boolean editName = false;
    private Boolean enablePwdApprove = false;
    private Boolean isCurrentPlatformAccount = true;
    private Boolean adminAccount = false;
    private String pwdLevel;
    private String virMachineroomMoid;
    private Boolean cloudModelDisplay;
    private String cloudModelName;
    private Integer cloudModelType;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMoid() {
        return this.moid;
    }

    public void setMoid(String moid) {
        this.moid = moid;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getE164() {
        return this.e164;
    }

    public void setE164(String e164) {
        this.e164 = e164;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJid() {
        return this.jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserDomainMoid() {
        return this.userDomainMoid;
    }

    public void setUserDomainMoid(String userDomainMoid) {
        this.userDomainMoid = userDomainMoid;
    }

    public String getUserDomainName() {
        return this.userDomainName;
    }

    public void setUserDomainName(String userDomainName) {
        this.userDomainName = userDomainName;
    }

    public String getServiceDomainMoid() {
        return this.serviceDomainMoid;
    }

    public void setServiceDomainMoid(String serviceDomainMoid) {
        this.serviceDomainMoid = serviceDomainMoid;
    }

    public String getServiceDomainName() {
        return this.serviceDomainName;
    }

    public void setServiceDomainName(String serviceDomainName) {
        this.serviceDomainName = serviceDomainName;
    }

    public String getDeviceGuid() {
        return this.deviceGuid;
    }

    public void setDeviceGuid(String deviceGuid) {
        this.deviceGuid = deviceGuid;
    }

    public String getNuServerId() {
        return this.nuServerId;
    }

    public void setNuServerId(String nuServerId) {
        this.nuServerId = nuServerId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getAccountType() {
        return this.accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Boolean getBinded() {
        return this.binded;
    }

    public void setBinded(Boolean binded) {
        this.binded = binded;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getLimited() {
        return this.limited;
    }

    public void setLimited(Boolean limited) {
        this.limited = limited;
    }

    public Date getValidityPeriod() {
        return this.validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getRestrictUsedOn() {
        return this.restrictUsedOn;
    }

    public void setRestrictUsedOn(String restrictUsedOn) {
        this.restrictUsedOn = restrictUsedOn;
    }

    public Integer getRestrictStrategy() {
        return this.restrictStrategy;
    }

    public void setRestrictStrategy(Integer restrictStrategy) {
        this.restrictStrategy = restrictStrategy;
    }

    public Boolean getEnableHD() {
        return this.enableHD;
    }

    public void setEnableHD(Boolean enableHD) {
        this.enableHD = enableHD;
    }

    public Boolean getEnableCall() {
        return this.enableCall;
    }

    public void setEnableCall(Boolean enableCall) {
        this.enableCall = enableCall;
    }

    public Boolean getEnableRoam() {
        return this.enableRoam;
    }

    public void setEnableRoam(Boolean enableRoam) {
        this.enableRoam = enableRoam;
    }

    public Boolean getEnableSatellite() {
        return this.enableSatellite;
    }

    public void setEnableSatellite(Boolean enableSatellite) {
        this.enableSatellite = enableSatellite;
    }

    public Boolean getEnableSatelliteP2P() {
        return this.enableSatelliteP2P;
    }

    public void setEnableSatelliteP2P(Boolean enableSatelliteP2P) {
        this.enableSatelliteP2P = enableSatelliteP2P;
    }

    public Boolean getUserDomainAdmin() {
        return this.userDomainAdmin;
    }

    public void setUserDomainAdmin(Boolean userDomainAdmin) {
        this.userDomainAdmin = userDomainAdmin;
    }

    public Boolean getEnableWeibo() {
        return this.enableWeibo;
    }

    public void setEnableWeibo(Boolean enableWeibo) {
        this.enableWeibo = enableWeibo;
    }

    public Boolean getWeiboAdmin() {
        return this.weiboAdmin;
    }

    public void setWeiboAdmin(Boolean weiboAdmin) {
        this.weiboAdmin = weiboAdmin;
    }

    public Boolean getEnableMeeting() {
        return this.enableMeeting;
    }

    public void setEnableMeeting(Boolean enableMeeting) {
        this.enableMeeting = enableMeeting;
    }

    public Boolean getMeetingAdmin() {
        return this.meetingAdmin;
    }

    public void setMeetingAdmin(Boolean meetingAdmin) {
        this.meetingAdmin = meetingAdmin;
    }

    public Boolean getEnableMeetingSMS() {
        return this.enableMeetingSMS;
    }

    public void setEnableMeetingSMS(Boolean enableMeetingSMS) {
        this.enableMeetingSMS = enableMeetingSMS;
    }

    public Boolean getEnableBMC() {
        return this.enableBMC;
    }

    public void setEnableBMC(Boolean enableBMC) {
        this.enableBMC = enableBMC;
    }

    public Boolean getEnableUMC() {
        return this.enableUMC;
    }

    public void setEnableUMC(Boolean enableUMC) {
        this.enableUMC = enableUMC;
    }

    public Boolean getEnableDCS() {
        return this.enableDCS;
    }

    public void setEnableDCS(Boolean enableDCS) {
        this.enableDCS = enableDCS;
    }

    public Boolean getEnableVRS() {
        return this.enableVRS;
    }

    public void setEnableVRS(Boolean enableVRS) {
        this.enableVRS = enableVRS;
    }

    public Boolean getEnableNM() {
        return this.enableNM;
    }

    public void setEnableNM(Boolean enableNM) {
        this.enableNM = enableNM;
    }

    public Boolean getEnableVenueMonitor() {
        return this.enableVenueMonitor;
    }

    public void setEnableVenueMonitor(Boolean enableVenueMonitor) {
        this.enableVenueMonitor = enableVenueMonitor;
    }

    public Boolean getServiceDomainAdmin() {
        return this.serviceDomainAdmin;
    }

    public void setServiceDomainAdmin(Boolean serviceDomainAdmin) {
        this.serviceDomainAdmin = serviceDomainAdmin;
    }

    public Boolean getDefaultServiceDomainAdmin() {
        return this.defaultServiceDomainAdmin;
    }

    public void setDefaultServiceDomainAdmin(Boolean defaultServiceDomainAdmin) {
        this.defaultServiceDomainAdmin = defaultServiceDomainAdmin;
    }

    public Boolean getDefaultUserDomainAdmin() {
        return this.defaultUserDomainAdmin;
    }

    public void setDefaultUserDomainAdmin(Boolean defaultUserDomainAdmin) {
        this.defaultUserDomainAdmin = defaultUserDomainAdmin;
    }

    public Boolean getEnableOut() {
        return this.enableOut;
    }

    public void setEnableOut(Boolean enableOut) {
        this.enableOut = enableOut;
    }

    public Boolean getEnableIncoming() {
        return this.enableIncoming;
    }

    public void setEnableIncoming(Boolean enableIncoming) {
        this.enableIncoming = enableIncoming;
    }

    public Boolean getDcsAdmin() {
        return this.dcsAdmin;
    }

    public void setDcsAdmin(Boolean dcsAdmin) {
        this.dcsAdmin = dcsAdmin;
    }

    public Boolean getVrsAdmin() {
        return this.vrsAdmin;
    }

    public void setVrsAdmin(Boolean vrsAdmin) {
        this.vrsAdmin = vrsAdmin;
    }

    public Boolean getNmAdmin() {
        return this.nmAdmin;
    }

    public void setNmAdmin(Boolean nmAdmin) {
        this.nmAdmin = nmAdmin;
    }

    public Boolean getEnableVideo() {
        return this.enableVideo;
    }

    public void setEnableVideo(Boolean enableVideo) {
        this.enableVideo = enableVideo;
    }

    public Boolean getEnableLive() {
        return this.enableLive;
    }

    public void setEnableLive(Boolean enableLive) {
        this.enableLive = enableLive;
    }

    public Boolean getEnablePlay() {
        return this.enablePlay;
    }

    public void setEnablePlay(Boolean enablePlay) {
        this.enablePlay = enablePlay;
    }

    public Boolean getCmsApproval() {
        return this.cmsApproval;
    }

    public void setCmsApproval(Boolean cmsApproval) {
        this.cmsApproval = cmsApproval;
    }

    public Boolean getIsCurrentPlatformAccount() {
        return this.isCurrentPlatformAccount;
    }

    public void setIsCurrentPlatformAccount(Boolean isCurrentPlatformAccount) {
        this.isCurrentPlatformAccount = isCurrentPlatformAccount;
    }

    public Boolean getEnableUnicat() {
        return this.enableUnicat;
    }

    public void setEnableUnicat(Boolean enableUnicat) {
        this.enableUnicat = enableUnicat;
    }

    public Boolean getEnableDownload() {
        return this.enableDownload;
    }

    public void setEnableDownload(Boolean enableDownload) {
        this.enableDownload = enableDownload;
    }

    public Boolean getEditName() {
        return this.editName;
    }

    public void setEditName(Boolean editName) {
        this.editName = editName;
    }

    public String getPwdLevel() {
        return this.pwdLevel;
    }

    public void setPwdLevel(String pwdLevel) {
        this.pwdLevel = pwdLevel;
    }

    public Boolean getEnablePwdApprove() {
        return this.enablePwdApprove;
    }

    public void setEnablePwdApprove(Boolean enablePwdApprove) {
        this.enablePwdApprove = enablePwdApprove;
    }

    public Boolean getAdminAccount() {
        return !this.serviceDomainAdmin && !this.userDomainAdmin && !this.meetingAdmin && !this.nmAdmin ? false : true;
    }

    public void setAdminAccount(Boolean adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getEncryptionPassword() {
        return this.encryptionPassword;
    }

    public void setEncryptionPassword(String encryptionPassword) {
        this.encryptionPassword = encryptionPassword;
    }

    public String getVirMachineroomMoid() {
        return this.virMachineroomMoid;
    }

    public void setVirMachineroomMoid(String virMachineroomMoid) {
        this.virMachineroomMoid = virMachineroomMoid;
    }

    public Boolean getCloudModelDisplay() {
        return this.cloudModelDisplay;
    }

    public void setCloudModelDisplay(Boolean cloudModelDisplay) {
        this.cloudModelDisplay = cloudModelDisplay;
    }

    public String getCloudModelName() {
        return this.cloudModelName;
    }

    public void setCloudModelName(String cloudModelName) {
        this.cloudModelName = cloudModelName;
    }

    public Integer getCloudModelType() {
        return this.cloudModelType;
    }

    public void setCloudModelType(Integer cloudModelType) {
        this.cloudModelType = cloudModelType;
    }
}
