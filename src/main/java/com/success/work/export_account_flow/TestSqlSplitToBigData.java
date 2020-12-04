package com.success.work.export_account_flow;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title：给大数据那边提供的导出资金流水和代偿流水sql
 * @Author：wangchenggong
 * @Date 2020/12/4 11:24
 * @Description
 * @Version
 */
public class TestSqlSplitToBigData {

    public static void main(String[] args) throws IOException {
        /*String borrowAccountNos =
                "'C_2019012402271073',\n" +
                "'C_2019071102391581',\n" +
                "'C_2019080902412671',\n" +
                "'C_2019041902332145',\n" +
                "'C_2019012002267623',\n" +
                "'C_2019031502305043',\n" +
                "'C_2019011402262253',\n" +
                "'C_2019042902343517',\n" +
                "'C_2019091102430440',\n" +
                "'C_2019052902371177',\n" +
                "'C_2019032202311068',\n" +
                "'C_2017122605663198'\n";

        borrowAccountFlowSql(borrowAccountNos);*/


        String investAccountNos = "C_2018070400000048\n" +
                "C_2017122601081859\n" +
                "C_2017122601187007\n" +
                "C_2017122601187007\n" +
                "C_2020052802571836\n" +
                "C_2017122601000402\n" +
                "C_2018012900001810\n" +
                "C_2018071500000012\n" +
                "C_2017122601090723\n" +
                "C_2018070400000048\n" +
                "C_2020052802571956\n" +
                "C_2018022600000234\n" +
                "C_2017122601081859\n" +
                "C_2018070400000048\n" +
                "C_2017122601081859\n";
        String[] investAccountNoArr = investAccountNos.split("\\s+");
        Set<String> accountNoSet = new HashSet<>(Arrays.asList(investAccountNoArr));
        String investAccountNoWithoutRepeat = String.join("','",accountNoSet);
        String finalInvestAccountNo = "'"+investAccountNoWithoutRepeat+"'";
        investDaichangFlow(finalInvestAccountNo);

    }

    /**
     * 代偿流水
     * @param finalInvestAccountNo
     */
    private static void investDaichangFlow(String finalInvestAccountNo) {

        String investDaichangFlowSql = "SELECT\n" +
                "\tt.request_no \"交易订单号\",\n" +
                "\tt.flow_no \"账务序号\",\n" +
                "\tt.account_no \"账户编号\",\n" +
                "\tt. NAME \"用户名称\",\n" +
                "\ts1.NAME \"业务类型\",\n" +
                "\ts2.NAME \"交易类型\",\n" +
                "\tt.amount \"交易金额\",\n" +
                "\t(CASE t.account_dir WHEN '0' THEN '转出'\n" +
                "\t\tELSE '转入' END) \"资金方向\",\n" +
                "\tt.frozen_amount \"使用冻结金额\",\n" +
                "\tt.create_time \"记账时间\",\n" +
                "\tt.finish_time \"交易完成时间\",\n" +
                "\tt.remain_amount \"交易后余额\",\n" +
                "\tSUBSTRING(t.remark, LOCATE('_', t.remark)+1) \"合同编号\"\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tt1.id,\n" +
                "\t\t\tt1.request_no,\n" +
                "\t\t\tt1.account_no,\n" +
                "\t\t\tt1. NAME,\n" +
                "\t\t\tt1.biz_type,\n" +
                "\t\t\tt1.amount,\n" +
                "\t\t\tt1.account_dir,\n" +
                "\t\t\tt1.frozen_amount,\n" +
                "\t\t\tt1.create_time,\n" +
                "\t\t\tt1.finish_time,\n" +
                "\t\t\tt1.remain_amount,\n" +
                "\t\t\tt1.flow_no,\n" +
                "\t\t\tt1.remark\n" +
                "\t\tFROM\n" +
                "\t\t\ttran_logs t1\n" +
                "\t\tWHERE\n" +
                "\t\t\tt1.account_no in (%s)\n" +
                "\t\tAND t1.account_no NOT LIKE 'O%'\n" +
                "\t) t\n" +
                "LEFT JOIN order_batch_trans_detail t1 ON t.request_no = t1.request_no\n" +
                "left join (select code,name from sys_dict where type = 'batchBizTypeBook')s1\n" +
                "on s1.CODE = t.biz_type\n" +
                "left join (select code,name from sys_dict where type = 'batchTransTypeBook')s2\n" +
                "on s2.CODE = t.biz_type;";

        String result = investDaichangFlowSql.replace("%s", finalInvestAccountNo);
        System.out.println(result);

    }

    /**
     * 资金进出明细
     * @param borrowAccountNos
     */
    public static void borrowAccountFlowSql(String borrowAccountNos){
        String borrowAccountFlow =
                "SELECT\n" +
                "\tt.request_no \"交易订单号\",\n" +
                "\tt.flow_no \"账务序号\",\n" +
                "\tt.account_no \"账户编号\",\n" +
                "\tt. NAME \"用户名称\",\n" +
                "\ts1.NAME \"业务类型\",\n" +
                "\ts2.NAME \"交易类型\",\n" +
                "\tt.amount \"交易金额\",\n" +
                "\t(CASE t.account_dir WHEN '0' THEN '转出'\n" +
                "\t\tELSE '转入' END) \"资金方向\",\n" +
                "\tt.frozen_amount \"使用冻结金额\",\n" +
                "\tt.create_time \"记账时间\",\n" +
                "\tt.finish_time \"交易完成时间\",\n" +
                "\tt.remain_amount \"交易后余额\",\n" +
                "\tSUBSTRING(t.remark, LOCATE('_', t.remark)+1) \"合同编号\"\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tt1.id,\n" +
                "\t\t\tt1.request_no,\n" +
                "\t\t\tt1.account_no,\n" +
                "\t\t\tt1. NAME,\n" +
                "\t\t\tt1.biz_type,\n" +
                "\t\t\tt1.amount,\n" +
                "\t\t\tt1.account_dir,\n" +
                "\t\t\tt1.frozen_amount,\n" +
                "\t\t\tt1.create_time,\n" +
                "\t\t\tt1.finish_time,\n" +
                "\t\t\tt1.remain_amount,\n" +
                "\t\t\tt1.flow_no,\n" +
                "\t\t\tt1.remark \n" +
                "\t\tFROM\n" +
                "\t\t\ttran_logs t1\n" +
                "\t\twhere t1.account_no IN (%s)\n" +
                "\t\tAND t1.account_no NOT LIKE 'O%'\n" +
                "\t) t\n" +
                "LEFT JOIN order_batch_trans_detail t1 ON t.request_no = t1.request_no\n" +
                "left join (select code,name from sys_dict where type = 'batchBizTypeBook')s1\n" +
                "on s1.CODE = t.biz_type\n" +
                "left join (select code,name from sys_dict where type = 'batchTransTypeBook')s2\n" +
                "on s2.CODE = t.biz_type\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "\tt.request_no \"交易订单号\",\n" +
                "\tt.flow_no \"账务序号\",\n" +
                "\tt.account_no \"账户编号\",\n" +
                "\tt. NAME \"用户名称\",\n" +
                "\ts1.NAME \"业务类型\",\n" +
                "\ts2.NAME \"交易类型\",\n" +
                "\tt.amount \"交易金额\",\n" +
                "\t(CASE t.account_dir WHEN '0' THEN '转出'\n" +
                "\t\tELSE '转入' END) \"资金方向\",\n" +
                "\tt.frozen_amount \"使用冻结金额\",\n" +
                "\tt.create_time \"记账时间\",\n" +
                "\tt.finish_time \"交易完成时间\",\n" +
                "\tt.remain_amount \"交易后余额\",\n" +
                "\tSUBSTRING(t.remark, LOCATE('_', t.remark)+1) \"合同编号\"\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tt1.id,\n" +
                "\t\t\tt1.request_no,\n" +
                "\t\t\tt1.account_no,\n" +
                "\t\t\tt1. NAME,\n" +
                "\t\t\tt1.biz_type,\n" +
                "\t\t\tt1.amount,\n" +
                "\t\t\tt1.account_dir,\n" +
                "\t\t\tt1.frozen_amount,\n" +
                "\t\t\tt1.create_time,\n" +
                "\t\t\tt1.finish_time,\n" +
                "\t\t\tt1.remain_amount,\n" +
                "\t\t\tt1.flow_no,\n" +
                "\t\t\tt1.remark \n" +
                "\t\tFROM\n" +
                "\t\t\ttran_logs_2019 t1\n" +
                "\t\twhere t1.account_no IN (%s)\n" +
                "\t\tAND t1.account_no NOT LIKE 'O%'\n" +
                "\t) t\n" +
                "LEFT JOIN order_batch_trans_detail t1 ON t.request_no = t1.request_no\n" +
                "left join (select code,name from sys_dict where type = 'batchBizTypeBook')s1\n" +
                "on s1.CODE = t.biz_type\n" +
                "left join (select code,name from sys_dict where type = 'batchTransTypeBook')s2\n" +
                "on s2.CODE = t.biz_type;";


        String result = borrowAccountFlow.replace("%s", borrowAccountNos);
        System.out.println(result);
    }
}
