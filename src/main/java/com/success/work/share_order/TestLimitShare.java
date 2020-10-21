package com.success.work.share_order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title：由于单笔限额的存在，这进行安排
 * @Author：wangchenggong
 * @Date 2020/10/21 22:16
 * @Description
 * @Version
 */
public class TestLimitShare {

    public static void main(String[] args) {
        BigDecimal limitMoney = new BigDecimal(500);
        BigDecimal totalMoney = new BigDecimal(2700);
        List<BigDecimal> stepMoneyList = splitMoneyForSteps(totalMoney, limitMoney);
        System.out.println(stepMoneyList);
    }

    /**
     * 拆分业务单金额
     * @param totalMoney
     * @param limitMoney
     * @return
     */
    private static List<BigDecimal> splitMoneyForSteps(BigDecimal totalMoney, BigDecimal limitMoney) {
        List<BigDecimal> stepMoneyList = new ArrayList<>();

        while(totalMoney.compareTo(BigDecimal.ZERO) > 0){
            //若达到limitMoney,则扣除limitMoney后继续
            if(totalMoney.compareTo(limitMoney) <= 0){
                stepMoneyList.add(totalMoney);
                break;
            }

            //若未达到limitMoney,则扣除limitMoney后继续
            stepMoneyList.add(limitMoney);
            totalMoney = totalMoney.subtract(limitMoney);
        }

        return stepMoneyList;
    }

    /**
     * 方式二：拆分业务单金额
     * @param totalMoney
     * @param limitMoney
     * @return
     */
    private static List<BigDecimal> splitMoneyForSteps2(BigDecimal totalMoney, BigDecimal limitMoney) {
        List<BigDecimal> stepMoneyList = new ArrayList<>();
        int totalMoneyValue = totalMoney.intValue();
        int limitMoneyValue = limitMoney.intValue();

        List<BigDecimal> detailMoneyList = new ArrayList<>();
        //计算余数
        int remainder =  totalMoneyValue % limitMoneyValue;
        //计算拆单的数目
        int stepNum = (remainder  == 0) ?  (totalMoneyValue/limitMoneyValue) : (totalMoneyValue/limitMoneyValue) + 1;
        for(int i=0; i<stepNum; i++){
            if(i < stepNum -1){
                detailMoneyList.add(new BigDecimal(limitMoneyValue));
            }else{
                //最后一笔交易单的交易金额 若有余数,则为余数，否则与路由的单笔限额一致
                if(remainder == 0){
                    detailMoneyList.add(new BigDecimal(limitMoneyValue));
                }else{
                    detailMoneyList.add(new BigDecimal(remainder));
                }
            }
        }
        return detailMoneyList;
    }
}
