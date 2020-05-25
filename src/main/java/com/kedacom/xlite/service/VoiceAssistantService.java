package com.kedacom.xlite.service;

import com.kedacom.xlite.vo.QuickChoiceVO;

/**
 * 语音助手
 * @author wangshuxuan
 * @date 2020/4/7 14:46
 */
public interface VoiceAssistantService {
    /**
     * 获取快捷方案
     * @param locationInfo
     * @param originQRcode
     * @return
     */
    QuickChoiceVO getQuickChoice(String locationInfo, String originQRcode);
}
