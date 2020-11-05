package com.success.work.check_conflict;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title：路由冲突检查（用Map对List分组的妙用）
 * @Author：wangchenggong
 * @Date 2020/10/26 5:34
 * @Description
 * 两个路由同时满足以下三个条件时会发生优先级冲突：
 *  * a) 系统标识+业务类型+交易类型+对公对私+银行+优先级  都相同，
 *  * b) 两个路由 选用的  通道不同
 *  * c) 两个路由选用的通道 所针对的公司主相同（两者任意一个为空，或者两者都非空但相同，都可认为是公司主体相同）
 * @Version
 */
public class CheckRouteConflict {





    public static void main(String[] args) {

        List<String> conflictRowIndexList = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11");

        //stream filter方法若无元素符合条件，则返回长度为0的空集合
        List<String> myList = conflictRowIndexList.stream().filter(
                s -> s.length()>3
        ).collect(Collectors.toList());

        System.out.println(myList.size());
        for(String str : myList){
            System.out.println(str);
        }


        /*String errorIndexes = String.join("、",conflictRowIndexList);
        if(conflictRowIndexList.size() > 10){
            List<String> subList = conflictRowIndexList.subList(0, 10);
            errorIndexes = String.join("、",subList)+"...";
        }

        System.out.println(errorIndexes);*/

       /* CheckRouteConflict checkRouteConflict = new CheckRouteConflict();
        checkRouteConflict.batchUpdateChannelBank(new HashMap<Integer,String> (), new ArrayList<Integer>(), 10, true, true);*/
    }


    /**
     * 批量修改路由信息（权重、状态和拆单标识）
     *   1.校验是否存在优先级冲突
     *   	a)校验勾选的路由信息之间是否存在优先级冲突
     *   	b)校验勾选的路由信息与库中其他路由信息之间是否存在优先级冲突
     *   2.执行批量修改路由操作
     * @param id2RowIndexMap 勾选的路由对应的行号数组
     * @param channelBankIdList 要修改的路由id列表
     * @param weight 要设置的权重
     * @param enabled 要设置的路由状态
     * @param isSplit 是否拆单（可能为空）
     * @return
     * @throws CheckParamsException
     */
    //@Transactional(rollbackFor = Exception.class)
    //@Override
    public int batchUpdateChannelBank(Map<Integer,String> id2RowIndexMap, List<Integer> channelBankIdList, Integer weight, Boolean enabled, Boolean isSplit) throws CheckParamsException {


        //1. 校验是否存在优先级冲突
        //用于存储发生优先级冲突的路由行号
        List<String> conflictRowIndexList = new ArrayList<>();

        //本次要批量修改的路由
        //List<ChannelBank> editChannelBankList = channelBankDAO.selectByIdsWithLock(channelBankIdList);
        //模拟本次要批量修改的路由
        List<ChannelBank> editChannelBankList = new ArrayList<>();
        editChannelBankList.stream().forEach(item -> item.setWeight(weight));

        //缓存渠道id与公司主体的映射关系
        /*List<Channel> channels = channelDAO.selectList(new Channel());
        Map<Integer,String> channelId2CompanyIdMap = channels.stream().collect(Collectors.toMap(Channel::getChannelId, Channel::getCompanyId));*/
        //渠道id与公司主体的映射关系
        Map<Integer,String> channelId2CompanyIdMap = new HashMap<>();

        //a).先校验本次要批量修改的路由之间是否存在优先级冲突
        checkRouteWeightConflict(true, id2RowIndexMap, conflictRowIndexList, channelId2CompanyIdMap, editChannelBankList, null);


        //b).再校验本次要批量修改的路由与数据库中其他路路由是否存在优先级冲突

        //查询所有路由，排除掉本次要批量修改的路由，得到库中其他路由列表
        /*List<ChannelBank> otherChannelBankList = channelBankDAO.selectList(new ChannelBank()).stream().filter(
                channelBank -> !channelBankIdList.contains(channelBank.getChannelBankId())
        ).collect(Collectors.toList());*/
        //库中其他路由列表
        List<ChannelBank> otherChannelBankList = new ArrayList<>();


        //对库中其他路由列表进行分组，用于对当前勾选的路由进行优先级冲突检查
        Map<String,List<ChannelBank>> otherChannelBankMap = checkRouteWeightConflict(false, id2RowIndexMap, conflictRowIndexList, channelId2CompanyIdMap, otherChannelBankList, null);

        //校验勾选的路由信息与库中其他路由信息之间是否存在优先级冲突
        checkRouteWeightConflict(true, id2RowIndexMap, conflictRowIndexList, channelId2CompanyIdMap, editChannelBankList, otherChannelBankMap);

        //2.执行批量修改路由操作
        //int row = channelBankDAO.batchUpdateByIds(channelBankIdList, weight, enabled, isSplit);
        return 0;
    }


    /**
     * 对指定的路由信息进行优先级冲突检查
     * @param needCheck  是否进行优先级冲突检查的标识
     * @param id2RowIndexMap 勾选的id与行号的对应关系
     * @param conflictRowIndexList 发生优先级冲突的行号列表
     * @param channelId2CompanyIdMap 路由id与公司主体的映射关系
     * @param channelBankListToCheck 要检查的channelBankList
     * @param conflictCheckMap 对channelBankList分组后的map
     * @return 对channelBankList分组后的map
     * @throws CheckParamsException
     */
    private Map<String,List<ChannelBank>> checkRouteWeightConflict(boolean needCheck, Map<Integer, String> id2RowIndexMap, List<String> conflictRowIndexList, Map<Integer, String> channelId2CompanyIdMap, List<ChannelBank> channelBankListToCheck, Map<String,List<ChannelBank>> conflictCheckMap) throws CheckParamsException {
        //用于校验所有路由信息 是否存在优先级冲突的map集合
        if(conflictCheckMap == null){
            conflictCheckMap = new HashMap<>();
        }

        for(ChannelBank channelBank : channelBankListToCheck){
            //按照 bankId_bizId_busiType_weight 将所有路由进行分组，组内元素需要校验优先级冲突
            String key = String.join("_"
                    ,channelBank.getBankId().toString()
                    , channelBank.getBizId().toString()
                    , channelBank.getBusiType().toString()
                    ,channelBank.getWeight().toString());

            if(conflictCheckMap.containsKey(key)){
                List<ChannelBank> currentValueList = conflictCheckMap.get(key);
                //如果需要校验
                if(needCheck){
                    //校验当前channelBank与currentValueList中的每一个值是否冲突
                    for(ChannelBank existCb : currentValueList){

                        if(channelBank.getChannelId().intValue() == existCb.getChannelId().intValue()){
                            continue;
                        }

                        String currentCompanyId = channelId2CompanyIdMap.get(channelBank.getChannelId());
                        String existCompanyId = channelId2CompanyIdMap.get(existCb.getChannelId());

                        if(StringUtils.isNotEmpty(currentCompanyId)
                                && StringUtils.isNotEmpty(existCompanyId)
                                && !currentCompanyId.equals(existCompanyId)){
                            continue;
                        }

                        //将该channelBankId对应的行数存储到结果中
                        conflictRowIndexList.add(id2RowIndexMap.get(channelBank.getChannelBankId()));
                        //一旦检测到该channelBank存在优先级冲突，则继续检测下一个channelBank
                        break;
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

        if(conflictRowIndexList.size() > 0){
            throw new CheckParamsException( "序号"+String.join("、",conflictRowIndexList)+":同一银行在不同通道下优先级不能相同！");
        }
        return conflictCheckMap;
    }


}
