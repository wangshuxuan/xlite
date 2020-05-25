package com.kedacom.xlite.controller;

import com.kedacom.xlite.enums.ResultStatusEnum;
import com.kedacom.xlite.service.VoiceAssistantService;
import com.kedacom.xlite.vo.QuickChoiceVO;
import com.kedacom.xlite.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 语音助手
 * @author wangshuxuan
 * @date 2020/4/7 14:06
 */
@RestController
@RequestMapping("/voiceAssistant")
public class VoiceAssistantController {

    @Resource
    private VoiceAssistantService voiceAssistantService;

    @PostMapping("/getQuickChoice")
    public ResultVO getQuickChoice(String locationInfo, String originQRcode) {
        QuickChoiceVO quickChoiceVO = voiceAssistantService.getQuickChoice(locationInfo, originQRcode);
        return new ResultVO<>(ResultStatusEnum.SUCCESS.getCode(), quickChoiceVO);
    }

}
