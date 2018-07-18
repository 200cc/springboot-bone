package me.cc200.base.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

public abstract class BeanUtil {

    /**
     * 拷贝属性时，忽略来源的值为null的属性。
     *
     * BeanUtils.copyProperties(Object source,Object target);方法有一个不足的地方，
     * 就是当source里的属性对应的属性值为null时，也会覆盖掉target里相同属性名的属性，即使target中该属性值已存在且不为null的属性值
     *
     * @param source
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, findNullPropertieNames(source));
    }

    public static String[] findNullPropertieNames(Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propDescs = beanWrapper.getPropertyDescriptors();
        return Arrays.stream(propDescs)
                .filter(pd -> null == beanWrapper.getPropertyValue(pd.getName()))
                .map(FeatureDescriptor::getName)
                .distinct()
                .toArray(String[]::new);
    }

}
