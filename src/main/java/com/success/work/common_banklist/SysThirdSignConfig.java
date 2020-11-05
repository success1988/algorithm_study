package com.success.work.common_banklist;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title：三方支付灵活配置
 * @Author：wangchenggong
 * @Date 2020/11/5 16:16
 * @Description
 * @Version
 */
public class SysThirdSignConfig implements Serializable {
    private Long id;

    /**
     * 系统标识
     */
    private String systemSign;

    /**
     * 三方标识
     */
    private String thirdId;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 银行编码
     */
    private String bankCode;

    /**
     * 是否需要签约
     */
    private Boolean isNeedSign;

    /**
     * 是否强制签约
     */
    private Boolean isForceSign;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private String companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取系统标识
     */
    public String getSystemSign() {
        return systemSign;
    }

    /**
     * 设置系统标识
     */
    public void setSystemSign(String systemSign) {
        this.systemSign = systemSign == null ? null : systemSign.trim();
    }

    /**
     * 获取三方标识
     */
    public String getThirdId() {
        return thirdId;
    }

    /**
     * 设置三方标识
     */
    public void setThirdId(String thirdId) {
        this.thirdId = thirdId == null ? null : thirdId.trim();
    }

    /**
     * 获取渠道类型
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * 设置渠道类型
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }


    public Boolean getNeedSign() {
        return isNeedSign;
    }

    public void setNeedSign(Boolean needSign) {
        isNeedSign = needSign;
    }

    public Boolean getForceSign() {
        return isForceSign;
    }

    public void setForceSign(Boolean forceSign) {
        isForceSign = forceSign;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
