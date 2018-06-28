package tool;

/**
 *
 * @ClassName: tool.ResultMap
 * @Description: 请求处理后的返回集，包含操作状态和返回数据
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:18
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class ResultMap<T> {
    public static final int SUCCESS=1;//成功
    public static final int FAILED=0;//失败
    public static final int EXCEPTION=-1;//报出异常

    private int state;
    private T result;

    public ResultMap(int state, T result) {
        this.state = state;
        this.result = result;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
