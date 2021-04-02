package com.design.zipcode;

public class StringTest {

	public static void main(String[] args) {
		String sql = "select";
		sql = sql + "empno, ename";
		sql = sql + " FROM emp";
		sql = sql + " WHERE empno > 7000";
		System.out.println(sql);
		String sql2 = "JAVA";
		sql2.replace('J', 'H');
		System.out.println(sql2);
		sql2 = sql2.replace('J', 'H');
		System.out.println(sql2);
	}

}
