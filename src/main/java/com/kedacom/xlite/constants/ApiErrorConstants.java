package com.kedacom.xlite.constants;


import java.util.HashMap;
import java.util.Map;

/**
 * api错误常量
 * @author wangshuxuan
 */

public class ApiErrorConstants {

	/** API 错误信息相关 */
	public static final Map<String, String> ApiErrorMessage = new HashMap<String, String>();
	static {
		//登录认证
		ApiErrorMessage.put("10001", "软件认证失败");
		ApiErrorMessage.put("10002", "accountToken认证失败");
		ApiErrorMessage.put("10101", "password验证错误");
		ApiErrorMessage.put("10102", "用户登录超时或者没有登录");
		//帐号信息
		ApiErrorMessage.put("20000", "系统异常");
		ApiErrorMessage.put("20001", "企业已存在");
		ApiErrorMessage.put("20002", "企业不存在");
		ApiErrorMessage.put("20003", "E164号码已被使用");
		ApiErrorMessage.put("20004", "企业邮箱已被使用");
		ApiErrorMessage.put("20005", "号码不存在");
		ApiErrorMessage.put("20006", "账号不存在");
		ApiErrorMessage.put("20006-1", "账号不存在	验证终端账号");
		ApiErrorMessage.put("20006-2", "账号不存在	查询账号详细信息");
		ApiErrorMessage.put("20006-3", "账号不存在	修改账号详细信息");
		ApiErrorMessage.put("20006-4", "账号不存在	修改账号密码");
		ApiErrorMessage.put("20006-5", "账号不存在	修改个人介绍");
		ApiErrorMessage.put("20006-6", "账号不存在	设置添加联系人认证模式");
		ApiErrorMessage.put("20006-7", "账号不存在	设置是否被模糊搜索到");
		ApiErrorMessage.put("20007", "原密码输入错误");
		ApiErrorMessage.put("20007-1", "原密码输入错误	修改账号密码");
		ApiErrorMessage.put("20008", "用户名或密码错误");
		ApiErrorMessage.put("20008-1", "用户名或密码错误	验证终端账号");
		ApiErrorMessage.put("20009", "企业未开通微博");
		ApiErrorMessage.put("20010", "没有操作权限");
		ApiErrorMessage.put("20011", "登录设备错误");
		ApiErrorMessage.put("20011-1", "登录设备错误	验证终端账号");
		ApiErrorMessage.put("20012", "账号未开通漫游");
		ApiErrorMessage.put("20012-1", "账号未开通漫游	验证终端账号");
		ApiErrorMessage.put("20013", "账号未开通微博");
		ApiErrorMessage.put("20014", "账号已存在");
		ApiErrorMessage.put("20015", "未配置小秘书");
		ApiErrorMessage.put("20016", "网络连接失败");
		ApiErrorMessage.put("20017", "绑定状态的账号无法删除");
		ApiErrorMessage.put("20018", "频繁发送重置密码邮件错误");
		ApiErrorMessage.put("20019", "姓名已被使用");
		ApiErrorMessage.put("20020", "号码已被绑定");
		ApiErrorMessage.put("20021", "禁止修改姓名");
		ApiErrorMessage.put("20100", "账号已被使用");
		ApiErrorMessage.put("20101", "手机号已被使用");
		ApiErrorMessage.put("20102", "服务域名称已被使用");
		ApiErrorMessage.put("20103", "平台域名称已被使用");
		ApiErrorMessage.put("20104", "服务域不存在");
		ApiErrorMessage.put("20105", "IP已被使用");
		ApiErrorMessage.put("20106", "平台域不存在");
		ApiErrorMessage.put("20107", "姓名已被使用");
		ApiErrorMessage.put("20108", "手机号与E164号重复");
		ApiErrorMessage.put("29999", "登陆令牌已过期，需重新登录");
		//会议管理
		ApiErrorMessage.put("51101", "会议室不存在");
		ApiErrorMessage.put("51102", "会议时间冲突");
		ApiErrorMessage.put("51103", "不是会议参与人");
		ApiErrorMessage.put("51104", "会议不存在");
		ApiErrorMessage.put("51105", "会议待审批");
		ApiErrorMessage.put("51106", "会议已取消");
		ApiErrorMessage.put("51107", "会议已开始");
		ApiErrorMessage.put("51108", "会议尚未开始");
		ApiErrorMessage.put("51109", "会议已结束");
		ApiErrorMessage.put("51110", "会议室名称已存在");
		ApiErrorMessage.put("51111", "会议室已存在关联会议");
		ApiErrorMessage.put("51112", "不是会议创建人");
		ApiErrorMessage.put("51113", "通知类型不存在");
		ApiErrorMessage.put("51114", "会议参与人不存在");
		ApiErrorMessage.put("51115", "指定用户不存在");
		ApiErrorMessage.put("51116", "会议模板不存在");
		ApiErrorMessage.put("51117", "必要参数为空");
		ApiErrorMessage.put("51118", "日期或时间格式错误");
		ApiErrorMessage.put("51119", "开始时间大于结束时间");
		ApiErrorMessage.put("51120", "虚拟会议室不存在");
		ApiErrorMessage.put("51121", "入会方式的类型不存在");
		ApiErrorMessage.put("51122", "没有空闲的虚拟会议室");
		ApiErrorMessage.put("51123", "会议已经开始");
		ApiErrorMessage.put("51124", "视频会议在一天有重名");
		//系统处理出错
		ApiErrorMessage.put("99999", "系统处理出错");

	}



	
}
