package controller;

import java.util.ArrayList;


public class RelationManage {
	private static ArrayList<String> Relation = new ArrayList<String>();
	private static ArrayList<String> OnLineFriend = new ArrayList<String>();
	public static  ArrayList<String> getRelation() {
		return Relation;
	}

	public static void setRelation(ArrayList<String> relation) {
		Relation = relation;
	}
	
	public static ArrayList<String> getOnlineFriend() {
		return OnLineFriend;
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
