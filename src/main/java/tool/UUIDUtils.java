package tool;

import java.util.UUID;

/**
 *
 * @ClassName: tool.UUIDUtils
 * @Description: 生产UUID
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:19
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
