package com.success.work.share_order;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/10/21 15:34
 * @Description
 * @Version
 */
@Data
public class ShareInfoDTO implements Cloneable {
    /**
     * 公司主体标识
     */
    private String companyId;
    /**
     * 优先级(从1开始，不允许重复)
     */
    private Integer level;
    /**
     * 交易金额或交易成功的金额（单位是分）
     */
    private BigDecimal money;


    public ShareInfoDTO cloneDTO() throws Exception {
        ShareInfoDTO dto = (ShareInfoDTO) this.clone();
        return dto;
    }

    /**
     * 解析的时候已经排好续了
     *
     * @param shareInfo
     * @return
     */
    public static List<ShareInfoDTO> parse(String shareInfo) {
        if (StringUtils.isEmpty(shareInfo)) {
            return null;
        }
        List<ShareInfoDTO> shareInfoDTOS = null;
        try {
            shareInfoDTOS = JSONObject.parseArray(shareInfo, ShareInfoDTO.class);
            shareInfoDTOS = shareInfoDTOS.stream().sorted(Comparator.comparing(ShareInfoDTO::getLevel)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("解析分账信息json字符串报错,shareInfo:"+shareInfo, e);
        }
        return shareInfoDTOS;
    }

    /**
     * 求所有分账信息的金额的和
     * @param shareInfo
     * @return
     */
    public static BigDecimal sumMoney(String shareInfo) {
        BigDecimal result = BigDecimal.ZERO;
        if (StringUtils.isEmpty(shareInfo)) {
            return result;
        }

        try {
            result = parse(shareInfo).stream()
                    .map(ShareInfoDTO::getMoney)
                    .reduce(BigDecimal.ZERO, (x,y) -> x.add(y));
        } catch (Exception e) {
            throw new RuntimeException("求和分账信息出错,shareInfo:"+shareInfo, e);

        }
        return result;
    }
}

