package com.github.erdragh.structures;

public class SingleLinkedList<T> implements IList<T> {

	private Node<T> start;

	public SingleLinkedList() {
		start = new EndNode<T>();
	}

	private abstract static class Node<T> {
		public abstract Node<T> put(T data);
		public abstract T take();
		public abstract T take(T current);
		
		public abstract T get(int i, int current);
		public abstract Node<T> set(int i, int current, T data);

		public abstract Node<T> remove(int i, int current);
		public abstract Node<T> insert(int i, int current, T data);

		public abstract Node<T> getNext();
		public abstract T getData();
	}

	private static class DataNode<T> extends Node<T> {

		private Node<T> next;
		private T data;

		public DataNode(Node<T> next, T data){
			this.next = next;
			this.data = data;
		}

		@Override
		public Node<T> getNext() {
			return next;
		}

		@Override
		public T getData() {
			return data;
		}

		@Override
		public Node<T> put(T data) {
			next = next.put(data);
			return this;
		}

		@Override
		public T take() {
			return next.take(data);
		}

		@Override
		public T take(T current) {
			return next.take(current);
		}

		@Override
		public T get(int i, int current) {
			return (i == current++) ? data : next.get(i, current);
		}

		@Override
		public Node<T> set(int i, int current, T data) {
			if (i == current++) {
				this.data = data;
			} else {
				next = next.set(i, current, data);
			}
			return this;
		}

		@Override
		public Node<T> remove(int i, int current) {
			if (i == current++) {
				return next;
			} else {
				next = next.remove(i, current);
				return this;
			}
		}

		@Override
		public Node<T> insert(int i, int current, T data) {
			if (i == current++) {
				return new DataNode<T>(this, data);
			} else {
				next = next.insert(i, current, data);
				return this;
			}
		}

	}

	private static class EndNode<T> extends Node<T> {

		@Override
		public Node<T> put(T data) {
			return new DataNode<T>(this, data);
		}

		@Override
		public T take() {
			return null;
		}

		@Override
		public T take(T current) {
			return current;
		}

		@Override
		public T get(int i, int current) {
			System.err.println("Index out of bounds for the List");
			return null;
		}

		@Override
		public Node<T> set(int i, int current, T data) {
			System.err.println("Index out of bounds for the List");
			return this;
		}

		@Override
		public Node<T> remove(int i, int current) {
			System.err.println("Index out of bounds for the List");
			return this;
		}

		@Override
		public Node<T> insert(int i, int current, T data) {
			System.err.println("Index out of bounds for the List");
			return this;
		}

		@Override
		public Node<T> getNext() {
			return this;
		}

		@Override
		public T getData() {
			return null;
		}

	}

	@Override
	public void put(T data) {
		start = start.put(data);
	}

	@Override
	public void push(T data) {
		start = new DataNode<T>(start, data);
	}

	@Override
	public T take() {
		return start.take();
	}

	@Override
	public T pull() {
		T data = start.getData();
		start = start.getNext();
		return data;
	}

	@Override
	public T get(int i) {
		return start.get(i, 0);
	}

	@Override
	public void set(int i, T data) {
		start = start.set(i, 0, data);
	}

	@Override
	public T remove(int i) {
		T data = start.get(i, 0);
		start = start.remove(i, 0);
		return data;
	}

	@Override
	public void insert(int i, T data) {
		start = start.insert(i, 0, data);
	}
	
}
