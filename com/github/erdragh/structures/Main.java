package com.github.erdragh.structures;

public class Main {
	public static void main(String[] args) {
		SingleLinkedList<String> list = new SingleLinkedList<>();
		for (int i = 0; i < 10; i++) {
			list.put("String " + i);
		}
		list.set(4, "String should be replaced");
		System.out.println(list.length());
		System.out.println(list.take());
		System.out.println(list.length());
		System.out.println(list.get(4));
	}
}
