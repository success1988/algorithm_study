package com.success.work.desentized_info;

import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Title：敏感信息VO
 * @Author：wangchenggong
 * @Date 2020/11/24 8:25
 * @Description 所有包含了敏感字段的实体需继承该类
 * @Version
 */
@Data
public class SensitiveInfo {
    private String bankcardNo;
    private String mobile;
    private String idCardNo;
    private String idcardNo;
    private String regMobile;

    public static void handle(Object obj) throws InvocationTargetException, IllegalAccessException {
        modifySensitiveParam(obj, a -> a instanceof SensitiveInfo, b -> {
            SensitiveInfo baseVo = (SensitiveInfo) b;
            baseVo.setMobile(DesensitizedUtil.mobilePhone(baseVo.getMobile()));
            baseVo.setIdCardNo(DesensitizedUtil.idCardNum(baseVo.getIdCardNo()));
            baseVo.setIdcardNo(DesensitizedUtil.idCardNum(baseVo.getIdcardNo()));
            baseVo.setRegMobile(DesensitizedUtil.mobilePhone(baseVo.getRegMobile()));
            baseVo.setBankcardNo(DesensitizedUtil.bankCardNo(baseVo.getBankcardNo()));
        });
    }


    /**
     * 修改敏感参数
     *
     * @param obj 待修改参数所在对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void modifySensitiveParam(Object obj, Predicate<Object> pre, Consumer<Object> con) throws InvocationTargetException, IllegalAccessException {
        if (obj == null) return;
        if (pre.test(obj)) {
            con.accept(obj);
            return;
        }
        if (isBaseType(obj)){
            return;
        }
        Iterator iterator = null;
        if (obj.getClass().isArray()) {
            iterator = Arrays.asList((Object[]) obj).iterator();
        } else if (obj instanceof Iterable) {
            iterator = ((Iterable) obj).iterator();
        } else if (obj instanceof Map) {
            iterator = ((Map) obj).values().iterator();
        } else {
            PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(obj.getClass());
            for (PropertyDescriptor p : pds) {
                if (p.getReadMethod() != null) {
                    Object value = p.getReadMethod().invoke(obj);
                    if (value == obj) {
                        continue;
                    }
                    modifySensitiveParam(value, pre, con);
                }
            }
        }
        if (iterator != null) {
            while (iterator.hasNext()) {
                modifySensitiveParam(iterator.next(), pre, con);
            }
        }
    }

    /**
     * 判断object是否为基本类型
     *
     * @param object 对象
     * @return boolean
     */
    private static boolean isBaseType(Object object) {
        Class className = object.getClass();
        return className.equals(Integer.class) ||
                className.equals(Byte.class) ||
                className.equals(Long.class) ||
                className.equals(Double.class) ||
                className.equals(Float.class) ||
                className.equals(Character.class) ||
                className.equals(Short.class) ||
                className.equals(Boolean.class) ||
                className.equals(Class.class) ||
                className.equals(String.class);
    }
}
