package common;
import java.util.*;
public class Message implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private String mesType;

	private String sender;
	private String getter;
	private String con;
	private String sendTime;
	private String starter;
	private String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	private HashMap<String,String> groupMap;
	public HashMap<String, String> getGroupMap() {
		return groupMap;
	}
	public void setGroupMap(HashMap<String, String> groupMap) {
		this.groupMap = groupMap;
	}
	public String getStarter() {
		return starter;
	}
	public void setStarter(String starter) {
		this.starter = starter;
	}
	private ArrayList<String> friendList;
	private ArrayList<String> onLineFriendList;
	public ArrayList<String> getFriendList() {
		return friendList;
	}
	public void setFriendList(ArrayList<String> friendList) {
		this.friendList = friendList;
	}
	public ArrayList<String> getOnlineFriendList() {
		return onLineFriendList;
	}
	public void setOnlineFriendList(ArrayList<String> onLineUser) {
		ArrayList<String> intersection = new ArrayList<String>();
		for (int i = 0; i < onLineUser.size(); i++) {
			if (friendList.contains(onLineUser.get(i))) {
				intersection.add(onLineUser.get(i));
			}
		}
		this.onLineFriendList = intersection;			
	}
	public void show() {
		System.out.println("for " + getter);
		for (int i = 0; i < onLineFriendList.size(); i++) {
			System.out.println(onLineFriendList.get(i));
		}
	}
	public Message(){
		this.mesType = MessageType.default_type;
	}
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}

