package cn.xubitao.dolphin.foundation.comparator;

import java.util.Map;

/**
 * Created by 15031046 on 2016/1/11.
 */
public class People {
    public static boolean compare(Object dataObject1, Object dataObject2, Object... excludeFields) {
        return Bean.compare(dataObject1, dataObject2, excludeFields);
    }

    public static Map<String, Object> toMap(Object dataObject) {
        return Bean.toMap(dataObject);
    }
}
