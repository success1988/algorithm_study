package com.success.work.share_sign;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/11/20 18:08
 * @Description
 * @Version
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThirdSignConfigDTO  implements Serializable {
    /**
     * 三方机构ID，见枚举
     */
    private String thirdId;
    /**
     * 三方机构名称
     */
    private String thirdName;
    /**
     * 银行编码
     */
    private String bankCode;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 公司主体
     */
    private String companyId;
    /**
     * 排序，数字越小，越靠前
     */
    private int sort;
    /**
     * 是否需要签约false：否，true：是
     */
    private boolean isNeedSign;
    /**
     * 是否强制签约false：否，true：是
     */
    private boolean isForceSign;
    /**
     * 用户是否签约false：否，true：是
     */
    private Boolean isSign;

    /**
     * 银行图标url
     */
    private String bankIconUrl;

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean getIsNeedSign() {
        return isNeedSign;
    }

    public void setIsNeedSign(boolean needSign) {
        isNeedSign = needSign;
    }

    public boolean getIsForceSign() {
        return isForceSign;
    }

    public void setIsForceSign(boolean forceSign) {
        isForceSign = forceSign;
    }

    public Boolean getIsSign() {
        return isSign;
    }

    public void setIsSign(Boolean sign) {
        isSign = sign;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankIconUrl() {
        return bankIconUrl;
    }

    public void setBankIconUrl(String bankIconUrl) {
        this.bankIconUrl = bankIconUrl;
    }
}
