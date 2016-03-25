package controller;

import java.util.ArrayList;


public class RelationManage {
	private static ArrayList<String> Relation = new ArrayList<String>();

	public static  ArrayList<String> getRelation() {
		return Relation;
	}

	public static void setRelation(ArrayList<String> relation) {
		Relation = relation;
	}
	
	
}
