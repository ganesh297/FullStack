package com.selenium.bootcamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date curD = new Date();
		System.out.println(dateFormat.format(curD));
		String currentDate="10";
		String currentDateWithoutZero=currentDate.replaceAll("^0+(?!$)", "");
		System.out.println(currentDateWithoutZero);
	}

}
