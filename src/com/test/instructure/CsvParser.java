package com.test.instructure;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CsvParser {

	public static Map<String, String> courseName = new HashMap<String, String>();
	public static Map<String, String> courseState = new HashMap<String, String>();
	public static Map<String, String> studentName = new HashMap<String, String>();
	public static Map<String, String> studentUserId = new HashMap<String, String>();
	public static Map<String, String> studentState = new HashMap<String, String>();
	public static boolean courseb = false;
	public static boolean studentb = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = args[0];
		String path1 = args[1];
		BufferedReader br = null;
		String line;
		String[] course;
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine())!=null){
				course = line.split(",");
				if(course[0].equals("course_id")){
					courseb = true;
					continue;
				}
				if (course[0].equals("user_id")){
					studentb = true;
					continue;
				}
				if (courseb) {
					courseName.put(course[0], course[1]);
					courseState.put(course[0], course[2]);
				}
				if(studentb){
					studentName.put(course[1], course[2]);
					studentUserId.put(course[2], course[0]);
					studentState.put(course[2], course[3] + "," + course[1]);
				}
					
					
			}
			courseb = false;
			studentb = false;
			
			line = null;
			br=null;
			br = new BufferedReader(new FileReader(path1));
			while ((line = br.readLine())!=null){
				course = line.split(",");
				if(course[0].equals("course_id")){
					courseb = true;
					continue;
				}
				if (course[0].equals("user_id")){
					studentb = true;
					continue;
				}
				if (courseb) {
					courseName.put(course[0], course[1]);
					courseState.put(course[0], course[2]);
				}
				if(studentb){
					studentName.put(course[1], course[2]);
					studentUserId.put(course[2], course[0]);
					studentState.put(course[2], course[3] + "," + course[1]);
				}
					
					
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> activeCourses = new ArrayList<String>();
		Map<String, ArrayList<String>> studentsForActiveCourses = new HashMap<String, ArrayList<String>>();
		ArrayList<String> courseid = new ArrayList<String>();		
		for(String str : courseState.keySet()) {
			System.out.println(courseState.get(str));
			if(courseState.get(str).equals("active")){
				activeCourses.add(courseName.get(str));
				courseid.add(str);
			}
		}
		for(String str :activeCourses){
			System.out.println(str);
			String temp = "";
			for(Map.Entry<String, String> entry :courseName.entrySet()) {
				if(entry.getValue().equals(str));
				temp = entry.getKey();
			}
			System.out.println( "temp is " + temp);
			System.out.println(studentState.get(temp));
			String[] str1 = (studentState.get(temp)).split(",");
			if(str1[0].equals("active")){
				if(studentsForActiveCourses.get(temp) == null){
					ArrayList a = new ArrayList<String>();
					a.add(str1[1]);
					studentsForActiveCourses.put(temp, a);
				} else {
				studentsForActiveCourses.get(temp).add(str1[1]);
				}
			}
		}
		System.out.println("active courses are");
		for( String s :activeCourses) System.out.print(s + ", ");
		
		System.out.println( "\nstudents associated are");
		for(String s :studentsForActiveCourses.keySet()) {
			for ( String str: studentsForActiveCourses.get(s)){
				System.out.print(str + " ,");
			}
		}
		
	}

}
