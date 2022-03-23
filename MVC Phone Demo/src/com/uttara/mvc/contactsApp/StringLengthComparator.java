package com.uttara.mvc.contactsApp;

import java.util.Comparator;

/*
 * Comparator class to compare String length of each line in 
 * contact book based on natural ordering
 */
public class StringLengthComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof ContactBean && o2 instanceof ContactBean) {
			String s1 = o1.toString();
			String s2 = o2.toString();
			return s1.length() - s2.length();
		}
		return 0;

	}
}
