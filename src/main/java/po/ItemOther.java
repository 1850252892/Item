package po;

/**
 *
 * @ClassName: po.ItemOther
 * @Description: 商品的一些其他信息
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:03
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class ItemOther {
    private String id;//商品id
    private Integer browser;//浏览量
    private Integer collect;//收藏量
    private String status;//状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBrowser() {
        return browser;
    }

    public void setBrowser(Integer browser) {
        this.browser = browser;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
