package com.saferich.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 业务类列表转换工具（引用Spring转换）
 *
 * @author Steven
 * @date 2016年8月1日
 */
public class BeanListSpringUtil {
    public static <T, D> List<D> getList(List<T> t, Class<D> clazz) throws InstantiationException, IllegalAccessException {
        List<D> m_DList = new ArrayList<>();
        for (T tSingle : t) {
            D dObject = clazz.newInstance();
            BeanUtils.copyProperties(tSingle, dObject);
            m_DList.add(dObject);
        }
        return m_DList;
    }
}
