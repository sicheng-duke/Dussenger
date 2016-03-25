package common;

public class UserInfo implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String passwd;
	private int Msg_type;
	// 0->login
	// 1->regisister
	// 2->ipConnect
	public UserInfo(){}
	public UserInfo(int type)
	{
		Msg_type = type;
	}
	public int getMsg_type() {
		return Msg_type;
	}
	public void setMsg_type(int msg_type) {
		Msg_type = msg_type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	

}