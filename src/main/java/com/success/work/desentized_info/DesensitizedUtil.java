package com.success.work.desentized_info;

import org.apache.commons.lang3.StringUtils;

/**
 * @Title：数据脱敏处理工具
 * @Author：wangchenggong
 * @Date 2020/11/23 22:22
 * @Description
 * @Version
 */
public class DesensitizedUtil {

    /**
     * 手机号脱敏处理：显示前三位，后四位，其他隐藏。共计11位，例如：139****1234
     * @param mobile
     * @return
     */
    public static String mobilePhone(String mobile){
        if (StringUtils.isEmpty(mobile)) {
            return "";
        }
        return StringUtils.overlay(mobile, "****", 3, 7);
    }

    /**
     * 身份证号脱敏处理： 显示前六位，隐藏中间八位，剩余显示。共计18位或者15位，比如：110110********1234
     * @param idCardNo
     * @return
     */
    public static String idCardNum(String idCardNo){
        if (StringUtils.isEmpty(idCardNo)) {
            return "";
        }
        return StringUtils.overlay(idCardNo, "********", 6, 14);
    }

    /**
     * 银行卡号脱敏处理：显示前六位，隐藏中间六位，剩余后几位显示。共计12-19位，例如：622688******125
     * @param bankCardNo
     * @return
     */
    public static String bankCardNo(String bankCardNo){
        if (StringUtils.isEmpty(bankCardNo)) {
            return "";
        }
        return StringUtils.overlay(bankCardNo, "******", 6, 12);
    }


}
