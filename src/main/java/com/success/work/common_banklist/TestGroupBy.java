package com.success.work.common_banklist;

import com.success.work.check_conflict.ChannelBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title：测试List分组Collectors.groupingBy
 * @Author：wangchenggong
 * @Date 2020/11/5 16:02
 * @Description
 * @Version
 */
public class TestGroupBy {

    public static void main(String[] args) {

        ChannelBank cb1 = new ChannelBank();
        cb1.setBankId(102);
        cb1.setBizId(1);
        cb1.setWeight(10);


        ChannelBank cb2 = new ChannelBank();
        cb2.setBankId(103);
        cb2.setBizId(2);
        cb2.setWeight(9);


        ChannelBank cb3 = new ChannelBank();
        cb3.setBankId(102);
        cb3.setBizId(1);
        cb3.setWeight(8);

        List<ChannelBank> list = new ArrayList<>();
        list.add(cb1);
        list.add(cb2);
        list.add(cb3);

        Map<String, List<ChannelBank>> map = list.stream().collect(Collectors.groupingBy((item) -> item.getBankId() + "_" + item.getBizId()));
        System.out.println(map);


    }
}
