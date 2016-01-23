/*
* Copyright (C), 2002-2015, 苏宁易购电子商务有限公司
 * FileName: MapUtil.java
 * Author:   徐必涛
 * Date:     2015/6/8 11:45
 * Description:
 * History:
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间      版本号        描述
 */
package cn.xubitao.dolphin.foundation.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈描述〉<br>
 * 〈功能详细描述〉
 *
 * @author 徐必涛
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Maps {
    public static Map<String, Object> build(Object... keyValues) throws Exception {
        Map result = new HashMap();
        for (int i = 0; i < keyValues.length; i = i + 2) {
            Object value = keyValues[i + 1];
            if (value == null) continue;
            result.put(keyValues[i], value);
        }
        return result;
    }
}
