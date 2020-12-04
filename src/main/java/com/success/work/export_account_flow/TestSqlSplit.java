package com.success.work.export_account_flow;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Title：导出资金流水和代偿流水
 * @Author：wangchenggong
 * @Date 2020/12/4 11:24
 * @Description
 * @Version
 */
public class TestSqlSplit {

    public static void main(String[] args) throws IOException {

        String accountNos =
                "向海珊:C_2019012402271073\n" +
                "吴少杰:C_2019071102391581\n" +
                "侯张炜:C_2019080902412671\n" +
                "李岩:C_2019041902332145\n" +
                "闫文学:C_2019012002267623\n" +
                "冯元莉:C_2019031502305043\n" +
                "李芷懿:C_2019011402262253\n" +
                "林蓓蕾:C_2019042902343517\n" +
                "郭烨:C_2019091102430440\n" +
                "王玫:C_2019052902371177\n" +
                "刘金波:C_2019032202311068\n" +
                "潘娟:C_2017122605663198\n";
        String daichangInfos = "吴少杰GD1911012358130000:C_2018022600000447,C_2017122601076838,C_2017122601142094,C_2017122601137799\n" +
                "林蓓蕾GD1911012900118600:C_2017122601209330,C_2017122601013723,C_2017122601051607\n" +
                "侯张炜GD1911016332108860:C_2019021902282410,C_2017122601020880,C_2019052102366025,C_2020090702615168,C_2017122601150923,C_2018070500001643,C_2017122601020071,C_2019090902428138,C_2019090802427942,C_2020060102573750,C_2018030500000267,C_2019040102319330,C_2017122601134101,C_2017122601109119,C_2017122601133151,C_2020090502614664,C_2017122601027042,C_2018021100000840,C_2017122601143726,C_2018092900000401,C_2017122601053964,C_2017122601006318\n" +
                "郭烨GD1911016333126210:C_2018080200000202,C_2017122601097443,C_2017122601097742,C_2017122601049848,C_2019090902428378\n" +
                "李岩GD1941031102118900:C_2017122601173168\n" +
                "闫文学GD1941080201093620:C_2017122601062637,C_2017122601060600,C_2017122601076120,C_2017122601147547,C_2017122601144424\n" +
                "向海珊GD1944010506091950:C_2017122601084332,C_2017122601032350,C_2018062300000027,C_2017122601130841,C_2019102002465000\n" +
                "冯元莉GD1944010605107040:C_2017122601059643,C_2017122601176237,C_2019032602313234,C_2017122601011452,C_2017122601075071\n" +
                "王玫GD1944050701091140:C_2018012900001810,C_2018071500000012,C_2020052802571836,C_2017122601000402,C_2017122601081859,C_2018070400000048,C_2020052802571956,C_2018022600000234,C_2017122601090723,C_2017122601187007,C_2017122601090723,C_2018022600000234,C_2018070400000048,C_2020052802571956,C_2017122601081859,C_2017122601187007,C_2018012900001810,C_2017122601000402,C_2018071500000012,C_2020052802571836,C_2017122601081859,C_2017122601000402,C_2020052802571956,C_2018012900001810,C_2017122601090723,C_2018070400000048,C_2017122601187007,C_2018022600000234,C_2018071500000012,C_2020052802571836,C_2018070400000048,C_2020052802571956,C_2018022600000234,C_2017122601090723,C_2018071500000012,C_2017122601187007,C_2017122601081859,C_2017122601000402,C_2020052802571836,C_2018012900001810,C_2020052802571836,C_2017122601187007,C_2017122601000402,C_2020052802571956,C_2018071500000012,C_2018070400000048,C_2018012900001810,C_2018022600000234,C_2017122601081859,C_2017122601090723,C_2018071500000012,C_2017122601081859,C_2018070400000048,C_201802260000023\n" +
                "李芷懿GD1953060201038480:C_2017122601125818,C_2017122601081719\n" +
                "刘金波GD19D21020252022350:C_2017122601105916,C_2019032202310285\n" +
                "潘娟GD19D21020266031180:C_2017122601007487";


        List<String> accountNoList = Arrays.asList(accountNos.split("\\s+"));
        List<String> daichangInfoList = Arrays.asList(daichangInfos.split("\\s+"));


        String  daichangDir = "D:\\导出sql\\12人的代偿交易明细sql";
        String flowDir = "D:\\导出sql\\12人的资金进出明细sql";

        for (int i = 0; i < accountNoList.size(); i++) {
            String accountNoStr = accountNoList.get(i);
            String[] arr = accountNoStr.split(":");
            //System.out.println("-- 查询account="+accountNoStr+"的资金进出明细");
            String accountNo = arr[1];
            String sql = "SELECT\n" +
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
                    "\tt.remain_amount \"交易后余额\"\n" +
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
                    "\t\t\tmysql_hhr_bank_21_215.bank_depository_hhr.tran_logs t1\n" +
                    "\t\twhere t1.account_no IN ('"+accountNo+"')\n" +
                    "\t\tAND t1.account_no NOT LIKE 'O%'\n" +
                    "\t) t\n" +
                    "LEFT JOIN mysql_hhr_bank_21_215.bank_depository_hhr.order_batch_trans_detail t1 ON t.request_no = t1.request_no\n" +
                    "left join (select code,name from mysql_hhr_bank_21_215.bank_depository_hhr.sys_dict where type = 'batchBizTypeBook')s1\n" +
                    "on s1.CODE = t.biz_type\n" +
                    "left join (select code,name from mysql_hhr_bank_21_215.bank_depository_hhr.sys_dict where type = 'batchTransTypeBook')s2\n" +
                    "on s2.CODE = t.biz_type order by t.create_time desc \n" +
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
                    "\tt.remain_amount \"交易后余额\"\n" +
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
                    "\t\t\tmysql_hhr_bank_21_215.bank_depository_hhr.tran_logs_2019 t1\n" +
                    "\t\twhere t1.account_no IN ('"+accountNo+"')\n" +
                    "\t\tAND t1.account_no NOT LIKE 'O%'\n" +
                    "\t) t\n" +
                    "LEFT JOIN mysql_hhr_bank_21_215.bank_depository_hhr.order_batch_trans_detail t1 ON t.request_no = t1.request_no\n" +
                    "left join (select code,name from mysql_hhr_bank_21_215.bank_depository_hhr.sys_dict where type = 'batchBizTypeBook')s1\n" +
                    "on s1.CODE = t.biz_type\n" +
                    "left join (select code,name from mysql_hhr_bank_21_215.bank_depository_hhr.sys_dict where type = 'batchTransTypeBook')s2\n" +
                    "on s2.CODE = t.biz_type order by t.create_time desc ;";

            String fileName = arr[0]+"的资金进出明细.sql";
            FileUtils.writeStringToFile(new File(flowDir, fileName), sql, "UTF-8", false);
        }


        for (int i = 0; i < daichangInfoList.size(); i++) {
            String daichangInfo = daichangInfoList.get(i);
            String[] arr = daichangInfo.split(":");
            String fileName = arr[0]+"的代偿交易明细.sql";
            Set<String> investAccountNos = new HashSet<>(Arrays.asList(arr[1].split(",")));
            StringBuffer sb = new StringBuffer();
            investAccountNos.stream().forEach(item -> {
                sb.append(",'").append(item).append("'");
            });
            String investAccountNosResult = sb.substring(1);


            String daichangSql = "SELECT\n" +
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
                    "\tt.remain_amount \"交易后余额\"\n" +
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
                    "\t\t\tmysql_hhr_bank_21_215.bank_depository_hhr.tran_logs t1\n" +
                    "\t\tWHERE\n" +
                    "\t\t\tDATE_FORMAT(t1.create_time) in ('2020-11-08','2020-11-10','2020-11-11','2020-11-12','2020-11-13','2020-11-16','2020-11-17','2020-11-18','2020-11-19','2020-11-23')\n" +
                    "\t\t\tand t1.biz_type = 'AUTO_COMPENSATORY' and t1.account_no in ("+investAccountNosResult+")\n" +
                    "\t\tAND t1.account_no NOT LIKE 'O%'\n" +
                    "\t) t\n" +
                    "LEFT JOIN mysql_hhr_bank_21_215.bank_depository_hhr.order_batch_trans_detail t1 ON t.request_no = t1.request_no\n" +
                    "left join (select code,name from mysql_hhr_bank_21_215.bank_depository_hhr.sys_dict where type = 'batchBizTypeBook')s1\n" +
                    "on s1.CODE = t.biz_type\n" +
                    "left join (select code,name from mysql_hhr_bank_21_215.bank_depository_hhr.sys_dict where type = 'batchTransTypeBook')s2\n" +
                    "on s2.CODE = t.biz_type order by t.create_time desc;";
            FileUtils.writeStringToFile(new File(daichangDir, fileName), daichangSql, "UTF-8", false);
        }



    }
}
