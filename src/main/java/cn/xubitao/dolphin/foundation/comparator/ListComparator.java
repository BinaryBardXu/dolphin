package cn.xubitao.dolphin.foundation.comparator;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by 15031046 on 2015/12/19.
 */
public class ListComparator extends Comparator {
    public static boolean compare(Object beforeItem, Object afterItem, Object[] excludeFields) {
        JSONArray beforeArray = (JSONArray) JSONArray.toJSON(beforeItem);

        JSONArray afterArray = (JSONArray) JSONArray.toJSON(afterItem);
        if (beforeArray.size() != afterArray.size()) {
            return false;
        }
        for (Object field : excludeFields) {
            for (int i = 0; i < beforeArray.size(); i++) {
                if (beforeArray.getJSONObject(i).containsKey(field) || afterArray.getJSONObject(i).containsKey(field)) {
                    beforeArray.getJSONObject(i).remove(field);
                    afterArray.getJSONObject(i).remove(field);
                }
            }
        }
        return compare(beforeArray, afterArray);
    }

    public static Boolean compare(List beforeItem, List afterItem) {
        if (beforeItem == null || afterItem == null) {
            printDiff("两个List中，有一个为null", beforeItem, afterItem);
            return false;
        }
        if (beforeItem.size() != afterItem.size()) {
            printDiff("两个List的Size不同", beforeItem, afterItem);
            return false;
        }
        for (Object item : beforeItem) {
            if (!isContain(item, afterItem)) {
                printDiff("一个对象没有在两个List中同时出现，具体信息请继续查看下一条输出。异常对象->" + item, beforeItem, afterItem);
                return false;
            }
        }
        for (Object item : afterItem) {
            if (!isContain(item, beforeItem)) {
                printDiff("一个对象没有在两个List中同时出现，具体信息请继续查看上一条输出。异常对象->" + item, afterItem, beforeItem);
                return false;
            }
        }
        return true;
    }

    private static Boolean isContain(Object item, List targetList) {
        if (item instanceof Map) {
            for (Object targetItem : targetList) {
                if (targetItem instanceof Map) {
                    boolean result = MapComparator.compare((Map) item, (Map) targetItem, false);
                    if (result) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
            findOutWhatsDiffInDetail((Map) item, targetList);
        }

        if (item instanceof List) {
            for (Object targetItem : targetList) {
                if (targetItem instanceof List) {
                    boolean result = ListComparator.compare((List) item, (List) targetItem);
                    if (result) {
                        return true;
                    }
                }
            }
        }

        for (Object targetItem : targetList) {
            if (targetItem.equals(item)) {
                return true;
            }
        }
        return false;
    }

    private static void findOutWhatsDiffInDetail(Map item, List targetList) {
        for (Object key : item.keySet()) {
            {
                boolean isHasKey = false;
                boolean isHasValue = false;
                for (Object targetItem : targetList) {
                    if (!(targetItem instanceof Map)) {
                        continue;
                    }
                    Map targetMap = (Map) targetItem;
                    if (targetMap.containsKey(key)) {
                        isHasKey = true;
                    }
                    if (People.compare(item.get(key), targetMap.get(key))) {
                        isHasValue = true;
                    }
                }
                if (!isHasKey) {
                    printDiff("没有找到Key:" + key, item, targetList);
                }
                if (!isHasValue) {
                    printDiff("目标集合元素中没有匹配到的key：" + key + "->" + item.get(key), item, targetList);
                }
            }
        }
    }
}
