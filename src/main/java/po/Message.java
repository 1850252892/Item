package po;

/**
 *
 * @ClassName: po.Message
 * @Description: 消息
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:04
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class Message {
	public static Integer STATE_READ=1;//已读状态
	public static Integer STATE_UNREAD=0;//未读状态
	public static Integer TYPE_SWAP=0;//类型为交换
	public static Integer TYPE_COMMENT=1;//类型为评论
	public static Integer TYPE_RESULT=2;//类型为交换结果

	private String id;//消息id
	private String username;//用户id
	private String date;//消息时间
	private Integer  type;//消息类型
	private String message;//消息内容
	private Integer state;//消息状态
	private String path;//关联的跳转路径

    public Message(String id, String username, String date, Integer type, String message, Integer state, String path) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.type = type;
        this.message = message;
        this.state = state;
        this.path = path;
    }

    public Message() {
    }

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
