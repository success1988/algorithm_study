package com.success.work.export_account_flow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title：账户编号去重
 * @Author：wangchenggong
 * @Date 2020/12/4 14:36
 * @Description
 * @Version
 */
public class TestAccountNos {

    public static void main(String[] args) {
        String accountNos = "'C_2018012900001810','C_2018071500000012','C_2020052802571836','C_2017122601000402','C_2017122601081859','C_2018070400000048','C_2020052802571956','C_2018022600000234','C_2017122601090723','C_2017122601187007','C_2017122601007487','C_2017122601084332','C_2017122601032350','C_2018062300000027','C_2017122601130841','C_2019102002465000','C_2017122601090723','C_2018022600000234','C_2018070400000048','C_2020052802571956','C_2017122601081859','C_2017122601187007','C_2018012900001810','C_2017122601000402','C_2018071500000012','C_2020052802571836','C_2017122601081859','C_2017122601000402','C_2020052802571956','C_2018012900001810','C_2017122601090723','C_2018070400000048','C_2017122601187007','C_2018022600000234','C_2018071500000012','C_2020052802571836','C_2018070400000048','C_2020052802571956','C_2018022600000234','C_2017122601090723','C_2018071500000012','C_2017122601187007','C_2017122601081859','C_2017122601000402','C_2020052802571836','C_2018012900001810','C_2017122601209330','C_2017122601013723','C_2017122601051607','C_2017122601059643','C_2017122601176237','C_2019032602313234','C_2017122601011452','C_2017122601075071','C_2020052802571836','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2018071500000012','C_2018070400000048','C_2018012900001810','C_2018022600000234','C_2017122601081859','C_2017122601090723','C_2018071500000012','C_2017122601081859','C_2018070400000048','C_2018022600000234','C_2018012900001810','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2020052802571836','C_2017122601090723','C_2017122601090723','C_2018071500000012','C_2017122601187007','C_2020052802571956','C_2017122601081859','C_2020052802571836','C_2018012900001810','C_2018022600000234','C_2018070400000048','C_2017122601000402','C_2017122601081859','C_2017122601000402','C_2018071500000012','C_2020052802571956','C_2018022600000234','C_2017122601090723','C_2017122601187007','C_2018070400000048','C_2018012900001810','C_2020052802571836','C_2017122601000402','C_2020052802571836','C_2018070400000048','C_2017122601081859','C_2017122601187007','C_2020052802571956','C_2017122601090723','C_2018012900001810','C_2018022600000234','C_2018071500000012','C_2018071500000012','C_2017122601081859','C_2018070400000048','C_2020052802571956','C_2018022600000234','C_2017122601187007','C_2017122601000402','C_2020052802571836','C_2018012900001810','C_2017122601090723','C_2018071500000012','C_2018022600000234','C_2017122601081859','C_2017122601090723','C_2020052802571836','C_2018012900001810','C_2017122601187007','C_2018070400000048','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2017122601081859','C_2017122601090723','C_2017122601187007','C_2018012900001810','C_2020052802571836','C_2017122601000402','C_2020052802571956','C_2018022600000234','C_2017122601000402','C_2017122601081859','C_2018022600000234','C_2018070400000048','C_2020052802571956','C_2018012900001810','C_2017122601187007','C_2018071500000012','C_2020052802571836','C_2017122601090723','C_2018071500000012','C_2018022600000234','C_2020052802571836','C_2017122601090723','C_2017122601081859','C_2017122601000402','C_2020052802571956','C_2018012900001810','C_2018070400000048','C_2017122601187007','C_2017122601081859','C_2020052802571956','C_2018071500000012','C_2017122601090723','C_2017122601000402','C_2018022600000234','C_2020052802571836','C_2017122601187007','C_2018070400000048','C_2018012900001810','C_2017122601090723','C_2018022600000234','C_2017122601000402','C_2018071500000012','C_2017122601081859','C_2018070400000048','C_2020052802571956','C_2020052802571836','C_2018012900001810','C_2017122601187007','C_2018012900001810','C_2017122601000402','C_2017122601081859','C_2018022600000234','C_2017122601090723','C_2020052802571956','C_2020052802571836','C_2018070400000048','C_2018071500000012','C_2017122601187007','C_2017122601062637','C_2017122601060600','C_2017122601076120','C_2017122601147547','C_2017122601144424','C_2020052802571836','C_2017122601090723','C_2017122601081859','C_2017122601187007','C_2018012900001810','C_2018071500000012','C_2018070400000048','C_2018022600000234','C_2020052802571956','C_2017122601000402','C_2020052802571956','C_2017122601187007','C_2017122601081859','C_2020052802571836','C_2017122601090723','C_2018071500000012','C_2018012900001810','C_2018022600000234','C_2018070400000048','C_2017122601000402','C_2020052802571836','C_2017122601090723','C_2018070400000048','C_2020052802571956','C_2018012900001810','C_2017122601187007','C_2018022600000234','C_2017122601081859','C_2018071500000012','C_2017122601000402','C_2018071500000012','C_2017122601000402','C_2017122601090723','C_2018022600000234','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2017122601081859','C_2018012900001810','C_2020052802571956','C_2018071500000012','C_2017122601081859','C_2017122601090723','C_2020052802571836','C_2018022600000234','C_2020052802571956','C_2017122601187007','C_2018070400000048','C_2018012900001810','C_2017122601000402','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2018012900001810','C_2018071500000012','C_2017122601081859','C_2018070400000048','C_2020052802571836','C_2018022600000234','C_2017122601090723','C_2020052802571836','C_2018071500000012','C_2018070400000048','C_2017122601081859','C_2018022600000234','C_2017122601187007','C_2018012900001810','C_2017122601000402','C_2020052802571956','C_2017122601090723','C_2017122601125818','C_2017122601081719','C_2017122601090723','C_2018022600000234','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2018071500000012','C_2020052802571956','C_2017122601000402','C_2018012900001810','C_2017122601081859','C_2018022600000234','C_2017122601187007','C_2020052802571836','C_2018070400000048','C_2017122601090723','C_2017122601081859','C_2020052802571956','C_2018071500000012','C_2018012900001810','C_2017122601000402','C_2018022600000234','C_2020052802571956','C_2020052802571836','C_2017122601081859','C_2017122601187007','C_2018071500000012','C_2017122601000402','C_2018012900001810','C_2018070400000048','C_2017122601090723','C_2017122601000402','C_2018071500000012','C_2020052802571836','C_2018070400000048','C_2017122601090723','C_2018012900001810','C_2020052802571956','C_2017122601187007','C_2017122601081859','C_2018022600000234','C_2017122601187007','C_2018070400000048','C_2018071500000012','C_2020052802571836','C_2018022600000234','C_2017122601090723','C_2017122601081859','C_2018012900001810','C_2017122601000402','C_2020052802571956','C_2018012900001810','C_2017122601090723','C_2020052802571836','C_2018071500000012','C_2017122601081859','C_2018022600000234','C_2017122601000402','C_2018070400000048','C_2017122601187007','C_2020052802571956','C_2017122601090723','C_2018071500000012','C_2017122601081859','C_2017122601000402','C_2018022600000234','C_2017122601187007','C_2018012900001810','C_2020052802571956','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2020052802571956','C_2018022600000234','C_2020052802571836','C_2017122601090723','C_2018012900001810','C_2017122601000402','C_2018070400000048','C_2018071500000012','C_2017122601081859','C_2017122601000402','C_2018022600000234','C_2017122601187007','C_2020052802571836','C_2018012900001810','C_2018071500000012','C_2017122601090723','C_2017122601081859','C_2018070400000048','C_2020052802571956','C_2017122601090723','C_2017122601081859','C_2018071500000012','C_2018012900001810','C_2017122601000402','C_2018022600000234','C_2018070400000048','C_2020052802571956','C_2017122601187007','C_2020052802571836','C_2018071500000012','C_2018022600000234','C_2017122601081859','C_2020052802571956','C_2018070400000048','C_2017122601187007','C_2020052802571836','C_2017122601090723','C_2018012900001810','C_2017122601000402','C_2017122601000402','C_2020052802571956','C_2017122601187007','C_2018012900001810','C_2017122601090723','C_2018022600000234','C_2018070400000048','C_2017122601081859','C_2020052802571836','C_2018071500000012','C_2017122601081859','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2017122601187007','C_2020052802571836','C_2017122601090723','C_2018012900001810','C_2018022600000234','C_2017122601187007','C_2018071500000012','C_2017122601081859','C_2017122601090723','C_2020052802571956','C_2020052802571836','C_2018070400000048','C_2018022600000234','C_2018012900001810','C_2017122601000402','C_2017122601090723','C_2018070400000048','C_2020052802571836','C_2018012900001810','C_2017122601187007','C_2017122601081859','C_2020052802571956','C_2018022600000234','C_2017122601000402','C_2018071500000012','C_2020052802571836','C_2020052802571956','C_2017122601187007','C_2018022600000234','C_2017122601081859','C_2017122601090723','C_2018071500000012','C_2018070400000048','C_2017122601000402','C_2018012900001810','C_2020052802571836','C_2020052802571956','C_2018022600000234','C_2017122601090723','C_2017122601187007','C_2018071500000012','C_2017122601000402','C_2018012900001810','C_2018070400000048','C_2017122601081859','C_2018012900001810','C_2017122601187007','C_2020052802571956','C_2018070400000048','C_2017122601000402','C_2020052802571836','C_2018022600000234','C_2017122601081859','C_2018071500000012','C_2017122601090723','C_2018012900001810','C_2017122601000402','C_2020052802571836','C_2017122601081859','C_2018070400000048','C_2020052802571956','C_2018071500000012','C_2018022600000234','C_2017122601090723','C_2017122601187007','C_2017122601081859','C_2017122601090723','C_2020052802571956','C_2018022600000234','C_2018070400000048','C_2018012900001810','C_2018071500000012','C_2020052802571836','C_2017122601187007','C_2017122601000402','C_2017122601090723','C_2018071500000012','C_2018012900001810','C_2020052802571956','C_2017122601187007','C_2020052802571836','C_2017122601081859','C_2018070400000048','C_2018022600000234','C_2017122601000402','C_2017122601105916','C_2019032202310285','C_2019021902282410','C_2017122601020880','C_2019052102366025','C_2020090702615168','C_2017122601150923','C_2018070500001643','C_2017122601020071','C_2019090902428138','C_2019090802427942','C_2020060102573750','C_2018030500000267','C_2019040102319330','C_2017122601134101','C_2017122601109119','C_2017122601133151','C_2020090502614664','C_2017122601027042','C_2018021100000840','C_2017122601143726','C_2018092900000401','C_2017122601053964','C_2017122601006318','C_2018071500000012','C_2017122601000402','C_2017122601090723','C_2018012900001810','C_2020052802571836','C_2017122601187007','C_2020052802571956','C_2017122601081859','C_2018070400000048','C_2018022600000234','C_2018071500000012','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2018022600000234','C_2017122601081859','C_2018012900001810','C_2018070400000048','C_2020052802571836','C_2017122601090723','C_2018012900001810','C_2017122601081859','C_2017122601090723','C_2020052802571956','C_2018071500000012','C_2017122601000402','C_2018022600000234','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2017122601081859','C_2017122601090723','C_2018071500000012','C_2018070400000048','C_2020052802571836','C_2017122601187007','C_2018022600000234','C_2017122601000402','C_2020052802571956','C_2018012900001810','C_2018070400000048','C_2020052802571956','C_2017122601081859','C_2020052802571836','C_2017122601000402','C_2017122601187007','C_2018012900001810','C_2017122601090723','C_2018022600000234','C_2018071500000012','C_2018022600000234','C_2018070400000048','C_2017122601090723','C_2017122601081859','C_2018071500000012','C_2018012900001810','C_2020052802571836','C_2017122601187007','C_2020052802571956','C_2017122601000402','C_2018070400000048','C_2018012900001810','C_2018071500000012','C_2017122601000402','C_2020052802571836','C_2018022600000234','C_2017122601081859','C_2017122601090723','C_2020052802571956','C_2017122601187007','C_2018071500000012','C_2017122601090723','C_2018070400000048','C_2018012900001810','C_2018022600000234','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2020052802571836','C_2017122601081859','C_2017122601090723','C_2018022600000234','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2020052802571836','C_2018070400000048','C_2018012900001810','C_2017122601187007','C_2017122601081859','C_2017122601081859','C_2018012900001810','C_2018071500000012','C_2017122601187007','C_2018070400000048','C_2020052802571956','C_2018022600000234','C_2020052802571836','C_2017122601090723','C_2017122601000402','C_2018012900001810','C_2018071500000012','C_2017122601090723','C_2018022600000234','C_2017122601187007','C_2017122601000402','C_2020052802571836','C_2020052802571956','C_2018070400000048','C_2017122601081859','C_2017122601081859','C_2018012900001810','C_2020052802571956','C_2018071500000012','C_2018022600000234','C_2020052802571836','C_2017122601090723','C_2018070400000048','C_2017122601000402','C_2017122601187007','C_2018071500000012','C_2017122601090723','C_2017122601081859','C_2020052802571836','C_2018012900001810','C_2018070400000048','C_2017122601000402','C_2017122601187007','C_2018022600000234','C_2020052802571956','C_2017122601090723','C_2017122601000402','C_2020052802571956','C_2017122601081859','C_2018012900001810','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2018071500000012','C_2018022600000234','C_2020052802571956','C_2017122601000402','C_2017122601187007','C_2018012900001810','C_2018071500000012','C_2018070400000048','C_2017122601081859','C_2017122601090723','C_2020052802571836','C_2018022600000234','C_2018070400000048','C_2020052802571956','C_2018071500000012','C_2017122601090723','C_2020052802571836','C_2017122601081859','C_2017122601000402','C_2018022600000234','C_2018012900001810','C_2017122601187007','C_2020052802571956','C_2018070400000048','C_2018022600000234','C_2018071500000012','C_2017122601090723','C_2018012900001810','C_2020052802571836','C_2017122601081859','C_2017122601187007','C_2017122601000402','C_2018070400000048','C_2020052802571956','C_2020052802571836','C_2017122601187007','C_2017122601090723','C_2018012900001810','C_2017122601081859','C_2018071500000012','C_2018022600000234','C_2017122601000402','C_2017122601081859','C_2017122601187007','C_2017122601000402','C_2020052802571836','C_2018071500000012','C_2018012900001810','C_2020052802571956','C_2018022600000234','C_2017122601090723','C_2018070400000048','C_2018022600000234','C_2018070400000048','C_2017122601187007','C_2020052802571956','C_2017122601090723','C_2018071500000012','C_2020052802571836','C_2018012900001810','C_2017122601000402','C_2017122601081859','C_2018070400000048','C_2017122601090723','C_2018012900001810','C_2018022600000234','C_2017122601000402','C_2020052802571956','C_2018071500000012','C_2017122601081859','C_2017122601187007','C_2020052802571836','C_2020052802571956','C_2018071500000012','C_2017122601081859','C_2018022600000234','C_2017122601000402','C_2020052802571836','C_2018070400000048','C_2018012900001810','C_2017122601090723','C_2017122601187007','C_2017122601187007','C_2017122601090723','C_2020052802571836','C_2018012900001810','C_2018070400000048','C_2017122601081859','C_2018022600000234','C_2017122601000402','C_2018071500000012','C_2020052802571956','C_2018080200000202','C_2017122601097443','C_2017122601097742','C_2017122601049848','C_2019090902428378','C_2017122601000402','C_2018022600000234','C_2018070400000048','C_2017122601081859','C_2020052802571956','C_2017122601187007','C_2018012900001810','C_2020052802571836','C_2018071500000012','C_2017122601090723','C_2018071500000012','C_2017122601090723','C_2017122601081859','C_2018012900001810','C_2017122601000402','C_2020052802571836','C_2018022600000234','C_2018070400000048','C_2017122601187007','C_2020052802571956','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2017122601187007','C_2017122601081859','C_2020052802571836','C_2017122601090723','C_2018022600000234','C_2018012900001810','C_2018070400000048','C_2017122601081859','C_2018022600000234','C_2018070400000048','C_2017122601000402','C_2017122601187007','C_2018071500000012','C_2020052802571956','C_2018012900001810','C_2017122601090723','C_2020052802571836','C_2017122601090723','C_2018070400000048','C_2020052802571956','C_2017122601081859','C_2017122601187007','C_2020052802571836','C_2017122601000402','C_2018071500000012','C_2018012900001810','C_2018022600000234','C_2017122601187007','C_2018012900001810','C_2018070400000048','C_2017122601090723','C_2020052802571956','C_2020052802571836','C_2017122601081859','C_2018071500000012','C_2018022600000234','C_2017122601000402','C_2020052802571836','C_2017122601081859','C_2017122601187007','C_2018022600000234','C_2018070400000048','C_2017122601000402','C_2020052802571956','C_2018071500000012','C_2017122601090723','C_2018012900001810','C_2017122601090723','C_2018070400000048','C_2020052802571836','C_2018071500000012','C_2018012900001810','C_2017122601000402','C_2020052802571956','C_2017122601081859','C_2017122601187007','C_2018022600000234','C_2018022600000234','C_2020052802571836','C_2017122601000402','C_2017122601090723','C_2018012900001810','C_2020052802571956','C_2017122601081859','C_2018070400000048','C_2018071500000012','C_2017122601187007','C_2017122601090723','C_2017122601081859','C_2020052802571956','C_2018070400000048','C_2020052802571836','C_2018012900001810','C_2018022600000234','C_2017122601187007','C_2017122601000402','C_2018071500000012','C_2018012900001810','C_2018070400000048','C_2017122601081859','C_2020052802571836','C_2018022600000234','C_2018071500000012','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2017122601090723','C_2018022600000234','C_2017122601187007','C_2017122601000402','C_2017122601081859','C_2020052802571956','C_2017122601090723','C_2020052802571836','C_2018012900001810','C_2018071500000012','C_2018070400000048','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2020052802571956','C_2017122601081859','C_2020052802571836','C_2017122601090723','C_2017122601187007','C_2018022600000234','C_2018012900001810','C_2018012900001810','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2017122601081859','C_2017122601187007','C_2020052802571836','C_2018022600000234','C_2020052802571956','C_2017122601090723','C_2018070400000048','C_2018022600000234','C_2018071500000012','C_2017122601000402','C_2017122601081859','C_2020052802571836','C_2017122601090723','C_2020052802571956','C_2018012900001810','C_2017122601187007','C_2018012900001810','C_2020052802571956','C_2017122601000402','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2018071500000012','C_2018022600000234','C_2017122601081859','C_2017122601090723','C_2018071500000012','C_2018022600000234','C_2020052802571836','C_2017122601090723','C_2017122601000402','C_2018012900001810','C_2017122601081859','C_2017122601187007','C_2018070400000048','C_2020052802571956','C_2017122601081859','C_2020052802571956','C_2017122601187007','C_2018012900001810','C_2018070400000048','C_2020052802571836','C_2017122601000402','C_2017122601090723','C_2018071500000012','C_2018022600000234','C_2018071500000012','C_2017122601187007','C_2017122601090723','C_2018012900001810','C_2020052802571956','C_2017122601000402','C_2018022600000234','C_2017122601081859','C_2020052802571836','C_2018070400000048','C_2017122601187007','C_2017122601000402','C_2018022600000234','C_2018070400000048','C_2018012900001810','C_2017122601081859','C_2018071500000012','C_2017122601090723','C_2020052802571836','C_2020052802571956','C_2017122601090723','C_2018022600000234','C_2018012900001810','C_2018071500000012','C_2017122601187007','C_2017122601081859','C_2020052802571836','C_2017122601000402','C_2020052802571956','C_2018070400000048','C_2017122601187007','C_2017122601000402','C_2018070400000048','C_2020052802571836','C_2018071500000012','C_2017122601090723','C_2018012900001810','C_2018022600000234','C_2020052802571956','C_2017122601081859','C_2018071500000012','C_2017122601090723','C_2017122601081859','C_2017122601000402','C_2017122601187007','C_2020052802571956','C_2018022600000234','C_2020052802571836','C_2018070400000048','C_2018012900001810','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2017122601081859','C_2017122601187007','C_2018012900001810','C_2018022600000234','C_2017122601090723','C_2020052802571836','C_2018070400000048','C_2017122601173168','C_2018022600000234','C_2020052802571956','C_2018071500000012','C_2017122601000402','C_2020052802571836','C_2017122601090723','C_2017122601081859','C_2017122601187007','C_2018070400000048','C_2018012900001810','C_2018070400000048','C_2018071500000012','C_2017122601187007','C_2020052802571956','C_2017122601000402','C_2018022600000234','C_2020052802571836','C_2017122601081859','C_2018012900001810','C_2017122601090723','C_2018071500000012','C_2018022600000234','C_2017122601090723','C_2017122601000402','C_2018012900001810','C_2017122601187007','C_2020052802571956','C_2017122601081859','C_2020052802571836','C_2018070400000048','C_2018022600000234','C_2020052802571836','C_2017122601090723','C_2018071500000012','C_2020052802571956','C_2017122601081859','C_2018070400000048','C_2018012900001810','C_2017122601000402','C_2017122601187007','C_2017122601090723','C_2018022600000234','C_2018070400000048','C_2017122601187007','C_2018012900001810','C_2017122601000402','C_2020052802571956','C_2017122601081859','C_2018071500000012','C_2020052802571836','C_2017122601081859','C_2018012900001810','C_2018071500000012','C_2017122601090723','C_2018022600000234','C_2020052802571956','C_2018070400000048','C_2020052802571836','C_2017122601187007','C_2017122601000402','C_2020052802571956','C_2017122601090723','C_2017122601081859','C_2018070400000048','C_2017122601000402','C_2018012900001810','C_2020052802571836','C_2018022600000234','C_2017122601187007','C_2018071500000012','C_2018012900001810','C_2017122601187007','C_2017122601000402','C_2018070400000048','C_2018022600000234','C_2020052802571836','C_2017122601081859','C_2020052802571956','C_2018071500000012','C_2017122601090723','C_2018070400000048','C_2018012900001810','C_2018022600000234','C_2020052802571956','C_2020052802571836','C_2018071500000012','C_2017122601187007','C_2017122601081859','C_2017122601000402','C_2017122601090723','C_2018012900001810','C_2017122601081859','C_2017122601187007','C_2018071500000012','C_2020052802571956','C_2017122601000402','C_2020052802571836','C_2018070400000048','C_2018022600000234','C_2017122601090723','C_2017122601187007','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2017122601090723','C_2020052802571836','C_2017122601081859','C_2018012900001810','C_2018022600000234','C_2020052802571956','C_2017122601187007','C_2018012900001810','C_2020052802571836','C_2018022600000234','C_2020052802571956','C_2017122601000402','C_2017122601081859','C_2017122601090723','C_2018070400000048','C_2018071500000012','C_2017122601000402','C_2018070400000048','C_2020052802571956','C_2020052802571836','C_2017122601187007','C_2018022600000234','C_2017122601081859','C_2018071500000012','C_2018012900001810','C_2017122601090723','C_2017122601081859','C_2020052802571956','C_2020052802571836','C_2018022600000234','C_2018071500000012','C_2018070400000048','C_2017122601000402','C_2017122601187007','C_2017122601090723','C_2018012900001810','C_2017122601090723','C_2020052802571836','C_2017122601081859','C_2017122601187007','C_2018071500000012','C_2018070400000048','C_2018022600000234','C_2017122601000402','C_2018012900001810','C_2020052802571956','C_2017122601081859','C_2018070400000048','C_2018071500000012','C_2018022600000234','C_2020052802571836','C_2017122601000402','C_2018012900001810','C_2020052802571956','C_2017122601187007','C_2017122601090723','C_2017122601000402','C_2020052802571956','C_2020052802571836','C_2017122601081859','C_2018012900001810','C_2017122601187007','C_2018070400000048','C_2018022600000234','C_2018071500000012','C_2017122601090723','C_2018022600000234','C_2017122601090723','C_2017122601081859','C_2017122601187007','C_2018012900001810','C_2018071500000012','C_2020052802571836','C_2020052802571956','C_2017122601000402','C_2018070400000048','C_2018012900001810','C_2018022600000234','C_2017122601187007','C_2020052802571836','C_2020052802571956','C_2017122601090723','C_2018070400000048','C_2018071500000012','C_2017122601000402','C_2017122601081859','C_2020052802571836','C_2017122601081859','C_2017122601187007','C_2017122601090723','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2018012900001810','C_2018070400000048','C_2018022600000234','C_2020052802571836','C_2018070400000048','C_2020052802571956','C_2018022600000234','C_2018071500000012','C_2018012900001810','C_2017122601000402','C_2017122601090723','C_2017122601187007','C_2017122601081859','C_2018022600000234','C_2018070400000048','C_2017122601081859','C_2017122601187007','C_2017122601090723','C_2018071500000012','C_2020052802571956','C_2020052802571836','C_2018012900001810','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2017122601000402','C_2017122601090723','C_2020052802571836','C_2017122601081859','C_2018012900001810','C_2020052802571956','C_2017122601187007','C_2018022600000234','C_2018071500000012','C_2018070400000048','C_2017122601081859','C_2017122601090723','C_2020052802571956','C_2018012900001810','C_2018022600000234','C_2017122601000402','C_2020052802571836','C_2017122601187007','C_2017122601090723','C_2020052802571956','C_2020052802571836','C_2018012900001810','C_2018070400000048','C_2017122601000402','C_2018071500000012','C_2017122601081859','C_2017122601187007','C_2018022600000234','C_2017122601090723','C_2020052802571836','C_2017122601000402','C_2018022600000234','C_2020052802571956','C_2017122601187007','C_2018071500000012','C_2018012900001810','C_2017122601081859','C_2018070400000048','C_2020052802571836','C_2017122601187007','C_2017122601081859','C_2017122601090723','C_2017122601000402','C_2018071500000012','C_2020052802571956','C_2018022600000234','C_2018070400000048','C_2018012900001810','C_2018022600000234','C_2017122601090723','C_2018071500000012','C_2018012900001810','C_2020052802571956','C_2020052802571836','C_2017122601000402','C_2018070400000048','C_2017122601081859','C_2017122601187007','C_2017122601187007','C_2020052802571836','C_2017122601000402','C_2018012900001810','C_2018071500000012','C_2017122601090723','C_2018070400000048','C_2020052802571956','C_2018022600000234','C_2017122601081859','C_2018070400000048','C_2017122601081859','C_2020052802571836','C_2018022600000234','C_2017122601187007','C_2018012900001810','C_2017122601000402','C_2017122601090723','C_2018071500000012','C_2020052802571956','C_2020052802571956','C_2018022600000234','C_2018071500000012','C_2017122601081859','C_2017122601187007','C_2018012900001810','C_2017122601090723','C_2018070400000048','C_2017122601000402','C_2020052802571836','C_2018022600000447','C_2017122601076838','C_2017122601142094','C_2017122601137799','C_2018071500000012','C_2020052802571956','C_2017122601000402','C_2018022600000234','C_2017122601187007','C_2017122601090723','C_2020052802571836','C_2018012900001810','C_2018070400000048','C_2017122601081859','C_2017122601081859','C_2018071500000012','C_2017122601187007','C_2020052802571956','C_2020052802571836','C_2017122601000402','C_2018022600000234','C_2018070400000048','C_2017122601090723','C_2018012900001810','C_2017122601000402','C_2017122601187007','C_2017122601090723','C_2018022600000234','C_2020052802571956','C_2017122601081859','C_2018070400000048','C_2018012900001810','C_2020052802571836','C_2018071500000012','C_2018070400000048','C_2017122601187007','C_2017122601000402','C_2020052802571836','C_2018022600000234','C_2017122601090723','C_2018071500000012','C_2018012900001810','C_2020052802571956','C_2017122601081859','C_2020052802571956','C_2018012900001810','C_2017122601000402','C_2017122601187007','C_2018022600000234','C_2020052802571836','C_2018070400000048','C_2018071500000012','C_2017122601081859','C_2017122601090723','C_2020052802571836','C_2017122601187007','C_2018022600000234','C_2017122601090723','C_2018012900001810','C_2018070400000048','C_2020052802571956','C_2017122601000402','C_2018071500000012','C_2017122601081859','C_2017122601000402','C_2020052802571836','C_2017122601090723','C_2018071500000012','C_2018070400000048','C_2020052802571956','C_2018012900001810','C_2018022600000234','C_2017122601081859','C_2017122601187007','C_2020052802571836','C_2018012900001810','C_2018070400000048','C_2018071500000012','C_2017122601187007','C_2020052802571956','C_2017122601081859','C_2018022600000234','C_2017122601000402','C_2017122601090723','C_2018071500000012','C_2018012900001810','C_2017122601081859','C_2017122601090723','C_2018070400000048','C_2018022600000234','C_2017122601000402','C_2020052802571956','C_2017122601187007','C_2020052802571836','C_2020052802571956','C_2017122601000402','C_2018022600000234','C_2017122601187007','C_2017122601081859','C_2020052802571836','C_2018071500000012','C_2017122601090723','C_2018070400000048','C_2018012900001810','C_2018012900001810','C_2018022600000234','C_2017122601187007','C_2017122601081859','C_2020052802571836','C_2017122601090723','C_2017122601000402','C_2018071500000012','C_2018070400000048','C_2020052802571956'";
        Set<String> accountNoSet = new HashSet<>(Arrays.asList(accountNos.split(",")));
        System.out.println(String.join(",",accountNoSet));
    }
}