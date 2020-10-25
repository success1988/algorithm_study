package com.success.work.check_conflict;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title：银行通道关系实体
 * @Author：wangchenggong
 * @Date 2020/10/26 5:42
 * @Description
 * @Version
 */
public class ChannelBank implements Serializable {
    /**
     * 银行和通道表的唯一标识
     */
    private Integer channelBankId;
    /**
     * 通道标识
     */
    private Integer channelId;
    /**
     * 银行编码
     */
    private Integer bankId;
    /**
     * 业务类型
     */
    private Integer busiType;
    /**
     * 单笔限额
     */
    private Integer singleMaxLimitMoney;
    /**
     * 银行在该通道编码
     */
    private String channelBankNum;
    /**
     * 日限额
     */
    private Long dayMaxLimitMoney;
    /**
     * 月限额
     */
    private Long monthMaxLimitMoney;
    /**
     * 日限次
     */
    private Integer dayMaxLimitTimes;
    /**
     * 月限次
     */
    private Integer monthMaxLimitTime;
    /**
     * 通道下的某个银行是否开启(true-开启，false-禁用)
     */
    private Boolean enabled;
    /**
     * 客户网关安全支付方式 1-U盾 2-口令卡
     */
    private String cipherWay;
    /**
     * 业务号
     */
    private Integer bizId;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 银行Id集合
     */
    private String bankIds;
    /**
     * 是否拆单
     */
    private Boolean isSplit;


    public Boolean getSplit() {
        return isSplit;
    }

    public void setSplit(Boolean split) {
        isSplit = split;
    }

    public String getBankIds() {
        return bankIds;
    }

    public void setBankIds(String bankIds) {
        this.bankIds = bankIds;
    }

    public Integer getChannelBankId() {
        return channelBankId;
    }

    public void setChannelBankId(Integer channelBankId) {
        this.channelBankId = channelBankId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getSingleMaxLimitMoney() {
        return singleMaxLimitMoney;
    }

    public void setSingleMaxLimitMoney(Integer singleMaxLimitMoney) {
        this.singleMaxLimitMoney = singleMaxLimitMoney;
    }

    public String getChannelBankNum() {
        return channelBankNum;
    }

    public void setChannelBankNum(String channelBankNum) {
        this.channelBankNum = channelBankNum == null ? null : channelBankNum.trim();
    }

    public Long getDayMaxLimitMoney() {
        return dayMaxLimitMoney;
    }

    public void setDayMaxLimitMoney(Long dayMaxLimitMoney) {
        this.dayMaxLimitMoney = dayMaxLimitMoney;
    }

    public Long getMonthMaxLimitMoney() {
        return monthMaxLimitMoney;
    }

    public void setMonthMaxLimitMoney(Long monthMaxLimitMoney) {
        this.monthMaxLimitMoney = monthMaxLimitMoney;
    }

    public Integer getDayMaxLimitTimes() {
        return dayMaxLimitTimes;
    }

    public void setDayMaxLimitTimes(Integer dayMaxLimitTimes) {
        this.dayMaxLimitTimes = dayMaxLimitTimes;
    }

    public Integer getMonthMaxLimitTime() {
        return monthMaxLimitTime;
    }

    public void setMonthMaxLimitTime(Integer monthMaxLimitTime) {
        this.monthMaxLimitTime = monthMaxLimitTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCipherWay() {
        return cipherWay;
    }

    public void setCipherWay(String cipherWay) {
        this.cipherWay = cipherWay;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }





}
