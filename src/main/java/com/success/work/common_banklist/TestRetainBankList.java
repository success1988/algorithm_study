package com.success.work.common_banklist;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title：计算银行交集
 * @Author：wangchenggong
 * @Date 2020/11/5 16:18
 * @Description
 * @Version
 */
public class TestRetainBankList {


    public static void main(String[] args) {

        List<SysThirdSignConfig> configList = new ArrayList<>();

        SysThirdSignConfig s1 = new SysThirdSignConfig();
        s1.setCompanyId("ZND");
        s1.setThirdId("KUAIQIAN");
        s1.setBankCode("102");

        SysThirdSignConfig s2 = new SysThirdSignConfig();
        s2.setCompanyId("ZND");
        s2.setThirdId("KUAIQIAN");
        s2.setBankCode("103");

        SysThirdSignConfig s3 = new SysThirdSignConfig();
        s3.setCompanyId("ZDHR");
        s3.setThirdId("ZHONGJIN");
        s3.setBankCode("101");

        configList.add(s1);
        configList.add(s2);
        configList.add(s3);

        //严格模式，则取交集，否则取并集
        boolean isStrictMode = true;
        //获取银行列表的交集
        Set<String> bankCodeSet = getCommonBankSet(configList, isStrictMode);


        System.out.println(bankCodeSet);

    }


    /**
     * 对配置列表中支持的银行按照 “公司主体+三方” 取交集或并集，得到支持的银行列表
     * @param configList 配置列表
     * @param isStrictMode 是否是严格模式，严格模式则取交集，否则取并集
     * @return
     */
    private static Set<String> getCommonBankSet(List<SysThirdSignConfig> configList, boolean isStrictMode) {
        //按照  公司主体_三方  进行分组
        Map<String, List<SysThirdSignConfig>> companyThirdMap = configList.stream().collect(Collectors.groupingBy((item) -> item.getCompanyId() + "_" + item.getThirdId()));

        Map<String, Set<String>> companyThird2BanksMap = new HashMap<>();
        companyThirdMap.entrySet().forEach(entry ->{
            Set<String> bankSet = entry.getValue().stream().map(SysThirdSignConfig::getBankCode).collect(Collectors.toSet());
            companyThird2BanksMap.put(entry.getKey(), bankSet);
        });


        Collection<Set<String>> bankSets = companyThird2BanksMap.values();

        List<Set<String>> bankSetList = new ArrayList<Set<String>>(bankSets);
        //按长度排序，从长度最短的开始取交集
        //Collections.sort(bankSetList, (bs1, bs2) -> bs1.size()-bs2.size());

        Set<String> bankCodeSet = new HashSet<>();
        for (int i = 0; i < bankSetList.size(); i++) {
            Set<String> currentSet = bankSetList.get(i);
            if(i == 0){
                bankCodeSet.addAll(currentSet);
            }else {
                if(isStrictMode){
                    bankCodeSet.retainAll(currentSet);
                }else {
                    bankCodeSet.addAll(currentSet);
                }
            }
        }
        return bankCodeSet;
    }

}
