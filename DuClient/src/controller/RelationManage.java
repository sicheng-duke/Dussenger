package controller;

import java.util.*;

import javax.swing.*;


public class RelationManage {
	private static ArrayList<String> Relation = new ArrayList<String>();
	private static ArrayList<String> OnLineFriend = new ArrayList<String>();
	private static ArrayList<String> Group = new ArrayList<String>();
	private static HashMap<String,String> GPMap = new HashMap<String, String>();
	

	public static HashMap<String, String> getGPMap() {
		return GPMap;
	}

	public static void setGPMap(HashMap<String, String> gPMap) {
		GPMap = gPMap;
	}

	public static ArrayList<String> getGroup() {
		return Group;
	}

	public static void setGroup(ArrayList<String> group) {
		Group = group;
	}
	
	public static void clearGroup()
	{
		Group.clear();
		Group = new ArrayList<String>();
	}
	private static HashMap<String,JLabel> friendList = new HashMap<String,JLabel>();
	
	public static HashMap<String, JLabel> getFriendList() {
		return friendList;
	}

	public static void setFriendList(HashMap<String, JLabel> friendList) {
		RelationManage.friendList = friendList;
	}

	public static  ArrayList<String> getRelation() {
		return Relation;
	}


	
	public static void setRelation(ArrayList<String> relation) {
		Relation = relation;
	}
	
	public static ArrayList<String> getOnlineFriend() {
		return OnLineFriend;
	}
	
	public static void clearRelation(){
		Relation.clear();
		Relation = new ArrayList<String>();		
	}
	
	public static void setOnlineFriend(ArrayList<String> onlineFriend) {
		OnLineFriend = onlineFriend;
	}
	public static void showRelation () {
		for (int i = 0; i < Relation.size(); i++) {
			System.out.println(Relation.get(i));
		}
	}
	public static void showOnLineFriend () {
		for (int i = 0; i < OnLineFriend.size(); i++) {
			System.out.println(OnLineFriend.get(i));
		}
	}
}
