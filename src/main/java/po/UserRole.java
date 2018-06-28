package po;

/**
 *
 * @ClassName: po.UserRole
 * @Description: 用户权限
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:05
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class UserRole {

	private String username;
	private String password;
	private String role;

	public UserRole(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
