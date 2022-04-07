package com.github.erdragh.structures;

public interface IList<T> {
	public void put(T data);
	public void push(T data);
	public T take();
	public T pull();

	public T get(int i);
	public void set(int i, T data);

	public T remove(int i);
	public void insert(int i, T data);

	// public IList<T> filter(Function that returns a boolean depending on two inputs of T) TODO: learn functional programming
}
