package tool;

/**
 * @ClassName: MessageSelect
 * @Description: 消息查询条件
 * @author: zhou_lk
 * @date: 2018/6/25 17:24
 * <p>
 * Modification History:
 * Date         Author            Description
 * ---------------------------------------------------------*
 * 2018/6/25      zhoulk            新建
 */

public class MessageSelect {
    private String id;
    private String username;
    private Integer type;
    private Integer state;
    private String date;
    private Integer startLine;
    private Integer limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
