package com.kedacom.xlite.service;

import com.kedacom.xlite.vo.QuickChoiceVO;
import org.springframework.stereotype.Service;

/**
 * 语音助手
 * @author wangshuxuan
 * @date 2020/4/7 14:46
 */
@Service
public class VoiceAssistantServiceImpl implements VoiceAssistantService {

    /**
     * 获取快捷方案
     * @param locationInfo
     * @param originQRcode
     * @return
     */
    @Override
    public QuickChoiceVO getQuickChoice(String locationInfo, String originQRcode) {
        return null;
    }
}
