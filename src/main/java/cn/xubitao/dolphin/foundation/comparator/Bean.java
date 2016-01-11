package cn.xubitao.dolphin.foundation.comparator;

import cn.xubitao.dolphin.foundation.exceptions.DolphinException;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 15031046 on 2015/12/7.
 */
public class Bean {

    public static boolean compare(Object beforeItem, Object afterItem, Object... excludeFields) {
        if (beforeItem == null && afterItem == null) {
            return true;
        }
        if (beforeItem == null || afterItem == null) {
            return false;
        }
        if (beforeItem.getClass().isPrimitive() || beforeItem instanceof String || beforeItem instanceof Long
                || beforeItem instanceof Integer || afterItem instanceof Boolean || beforeItem instanceof Double) {
            return beforeItem.equals(afterItem);
        }
        if (beforeItem instanceof Map && afterItem instanceof Map) {
            return MapComparator.compare(beforeItem, afterItem, excludeFields, true);
        }
        if (beforeItem instanceof List && afterItem instanceof List) {
            return ListComparator.compare(beforeItem, afterItem, excludeFields);
        }
        try {
            return MapComparator.compare(beforeItem, afterItem, excludeFields, true);
        } catch (Exception e) {
            throw new DolphinException(e.getCause().toString());
        }
    }
    /**
     * 将目标数据对象转换为Map
     *
     * @param dataObject
     * @return Map对象
     */
    public static Map<String, Object> toMap(Object dataObject) {
        if (dataObject == null) {
            return null;
        }
        if (dataObject instanceof Map) {
            return (Map<String, Object>) dataObject;
        }
        JSONObject.toJSON(dataObject);
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) JSONObject.toJSON(dataObject);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (jsonObject != null && jsonObject.containsKey("success")) {
            jsonObject.remove("success");
        }
        if (jsonObject.isEmpty()) {
            throw new DolphinException(dataObject + "转出后的Map是空的");
        }
        Field[] fields = dataObject.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                Object fieldValue = Bean.getFieldValue(dataObject, field.getName());
                if (fieldValue == null || field.getName().equals("serialVersionUID")) {
                    jsonObject.remove(field.getName());
                }
            }
        } catch (Exception e) {
        }

        return new HashMap<String, Object>(jsonObject);
    }
    public static Object getFieldValue(Object sourceObject, String fieldName)
            throws InvocationTargetException, IllegalAccessException {
        Class<? extends Object> sourceClass = sourceObject.getClass();

        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        if (methodName.indexOf("jacoco") > -1) {
            return null;
        }
        if (!fieldName.equals("serialVersionUID")) {
            Method method;
            try {
                method = sourceClass.getMethod("get" + methodName);
            } catch (NoSuchMethodException e) {
                return null;
            }
            return method.invoke(sourceObject);
        }
        return null;
    }
}
