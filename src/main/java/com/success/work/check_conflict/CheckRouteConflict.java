package com.success.work.check_conflict;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title：路由冲突检查（用Map对List分组的妙用）
 * @Author：wangchenggong
 * @Date 2020/10/26 5:34
 * @Description
 * @Version
 */
public class CheckRouteConflict {

    public static void main(String[] args) {

        /*//本次要批量修改的路由
        List<ChannelBank> editChannelBankList = channelBankDAO.selectByIdsWithLock(channelBankIdList);
        editChannelBankList.stream().forEach(item -> item.setWeight(weight));

        //查询所有路由，排除掉本次要批量修改的路由
        List<ChannelBank> allChannelBankList = channelBankDAO.selectList(new ChannelBank()).stream().filter(
                channelBank -> !channelBankIdList.contains(channelBank.getChannelBankId())
        ).collect(Collectors.toList());
        //加上本次要批量修改的路由，得到所有路由
        allChannelBankList.addAll(editChannelBankList);*/

        //模拟所有路由
        List<ChannelBank> allChannelBankList = new ArrayList<>();

        //缓存渠道id与公司主体的映射关系
        /*List<Channel> channels = channelDAO.selectList(new Channel());
        Map<Integer,String> channelId2CompanyIdMap = channels.stream().collect(Collectors.toMap(Channel::getChannelId, Channel::getCompanyId));*/

        //模拟渠道id与公司主体的映射关系
        Map<Integer,String> channelId2CompanyIdMap = new HashMap<>();

        //用于校验所有路由信息 是否存在优先级冲突的map集合
        Map<String, List<ChannelBank>> conflictCheckMap = new HashMap<>();

        for(ChannelBank channelBank : allChannelBankList){
            //按照 bankId_bizId_busiType_weight 将所有路由进行分组，组内元素需要校验优先级冲突
            String key = String.join("_"
                    ,channelBank.getBankId().toString()
                    , channelBank.getBizId().toString()
                    , channelBank.getBusiType().toString()
                    ,channelBank.getWeight().toString());

            if(conflictCheckMap.containsKey(key)){
                List<ChannelBank> currentValueList = conflictCheckMap.get(key);
                //校验当前channelBank与currentValueList中的每一个值是否冲突
                for(ChannelBank existCb : currentValueList){

                    if(channelBank.getChannelId().intValue() != existCb.getChannelId().intValue()){
                        String currentCompanyId = channelId2CompanyIdMap.get(channelBank.getChannelId());
                        String existCompanyId = channelId2CompanyIdMap.get(existCb.getChannelId());

                        if(StringUtils.isEmpty(currentCompanyId)
                                || StringUtils.isEmpty(existCompanyId)
                                || currentCompanyId.equals(existCompanyId)){
                            throw new RuntimeException( "同一银行在不同通道下优先级不能相同！");
                        }
                    }
                }
                //如果没有发生冲突，则添加到该组中
                currentValueList.add(channelBank);
            }else{
                List<ChannelBank> valueList = new ArrayList<ChannelBank>();
                valueList.add(channelBank);
                conflictCheckMap.put(key, valueList);
            }
        }
    }

}
