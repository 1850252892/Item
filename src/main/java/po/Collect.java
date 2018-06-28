package po;

/**
 *
 * @ClassName: po.Collect
 * @Description: 记录收藏信息
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:00
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class Collect {
    private String u_id;// 用户id
    private String g_id;// 商品id

    public Collect(String u_id, String g_id) {
        this.u_id = u_id;
        this.g_id = g_id;
    }

    public Collect() {

    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

}
