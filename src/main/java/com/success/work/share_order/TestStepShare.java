package com.success.work.share_order;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/10/21 16:09
 * @Description
 * @Version
 */
public class TestStepShare {

    public static void main(String[] args) throws Exception {

        String shareInfo = "[{\"companyId\":\"HYRD\",\"money\":20000,\"level\":1},{\"companyId\":\"JHWJ\",\"money\":3000,\"level\":2},{\"companyId\":\"TJSS\",\"money\":50000,\"level\":3}]";
        String successShareInfo = "[{\"companyId\":\"HYRD\",\"money\":15000,\"level\":1},{\"companyId\":\"JHWJ\",\"money\":0,\"level\":2},{\"companyId\":\"TJSS\",\"money\":0,\"level\":3}]";


        String planShareInfo = computePlanShareInfo(shareInfo, successShareInfo, new BigDecimal(15000));
        System.out.println(planShareInfo);

    }

    /**
     * 计算本步骤的分账信息
     * @param shareInfo 目标分账信息
     * @param successShareInfo 已交易成功的分账信息(可能为空)
     * @param stepAmount  本步骤可分配的金额
     * @return
     */
    private static String computePlanShareInfo(String shareInfo, String successShareInfo, BigDecimal stepAmount) throws Exception {
        //计算本步骤的分账信息
        List<ShareInfoDTO> planShareInfoDTOList = new ArrayList<>();

        //获取目标分账信息
        List<ShareInfoDTO> targetShareInfoDTOList = ShareInfoDTO.parse(shareInfo);
        //获取目标分账信息
        List<ShareInfoDTO> successShareInfoDTOList = ShareInfoDTO.parse(successShareInfo);

        /*int index = 计算出当前要处理的公司主体index
        while(本步骤可分配的金额 > 0){
            BigDecimal needMoney = 计算出当前要处理的公司主体所需要的金额
            if(不够满足 或 刚好满足 当前index公司主体){
                本步骤可分配的金额 全部分给当前index公司主体，记录到planShareInfoDTOList中
                break;
            }else{
                needMoney分给当前index公司主体，记录到planShareInfoDTOList中
                本步骤可分配的金额 -= needMoney;
                index++;
            }
        }*/

        int index;
        for (index = 0; index < targetShareInfoDTOList.size(); index++) {
            if(successShareInfoDTOList.get(index).getMoney().compareTo(targetShareInfoDTOList.get(index).getMoney()) < 0){
                break;
            }
        }

        while(stepAmount.compareTo(BigDecimal.ZERO) > 0){

            BigDecimal needMoney = targetShareInfoDTOList.get(index).getMoney().subtract(successShareInfoDTOList.get(index).getMoney());

            ShareInfoDTO currentShareInfoDTO = targetShareInfoDTOList.get(index).cloneDTO();
            if(stepAmount.compareTo(needMoney) <= 0){
                //不够满足 或 刚好满足 当前index公司主体，记录到planShareInfoDTOList中
                currentShareInfoDTO.setMoney(stepAmount);
                planShareInfoDTOList.add(currentShareInfoDTO);
                break;
            }else{
                //needMoney分给当前index公司主体，记录到planShareInfoDTOList中
                currentShareInfoDTO.setMoney(needMoney);
                planShareInfoDTOList.add(currentShareInfoDTO);
                stepAmount = stepAmount.subtract(needMoney);
                index++;
            }
        }

        return JSON.toJSONString(planShareInfoDTOList);
    }

    /**
     * 其他方案：其实也是一回事儿
     *
     * for 遍历所有商户(share_info) {
     *
     *     if (商户总金额 == 商户成功金额) {//此商户已经完成了全部扣款,所以这次就是0
     *         此商户分到0, 进入下一个商户
     *     }
     *
     *     if (交易总金额 <= 0) {//本次交易单已经被优先级高的商户用完了,所以剩余的商户都是0
     *         此商户分到0, 进入下次循环
     *         break;
     *     }
     *
     *     此商户商户需要 = (商户总金额 - 商户成功)
     *     //取小金额
     *     if (交易单金额 > 此商户商户需要) {//此交易单足够满足当前商户,并且有剩余
     *         此商户商户需要
     *     } else {//本次交易不能满足此商户需要,先把当前可以交易的金额用光
     *         交易单金额
     *         break;
     *     }
     *     //更新交易单
     *     交易单金额 = 交易单金额 - 此商户需要的
     *
     * }
     */


}
