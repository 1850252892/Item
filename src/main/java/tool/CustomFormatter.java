package tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: CustomFormatter
 * @Description: 自定义格式化工具类
 * @author: zhoulk
 * @date: 2018/6/25 16:12
 * <p>
 * Modification History:
 * Date         Author            Description
 * ---------------------------------------------------------*
 * 2018/6/25      zhoulk            新建
 */

public class CustomFormatter {

    public static String baseFormat="yyyy-MM-dd HH:mm:ss";
    /**
     *
     * @Description: 格式化时间并返回
     *
     * @param fomat 格式
     * @param date 时间
     * @return 格式化之后的时间戳
     *
     * @version: v1.0.0
     * @author: zhou_lk
     * @date: 2018/6/25 16:20
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/25      zhoulk          v1.0.0             新建
     */
    public static String fomartterDateToString(String fomat,Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(fomat);
        return simpleDateFormat.format(date);
    };
}
