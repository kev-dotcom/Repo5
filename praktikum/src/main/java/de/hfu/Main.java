package de.hfu;

import java.util.*;

public class Main {
	public static void main(String... args) {
		String eingabe;
		String out = "";
		Scanner s = new Scanner(System.in);
		System.out.print("Zeichenkette eingeben > ");
		eingabe = s.next();
		for(int i = 0; i < eingabe.length(); i++) {
			if(((int) eingabe.charAt(i)) >= 97 && ((int) eingabe.charAt(i)) <= 122) {
				out += (char)((int)(eingabe.charAt(i)) - 32);
			} else {
				out += eingabe.charAt(i);
			}
		}
		System.out.println(out);
	}
}