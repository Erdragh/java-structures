package com.github.erdragh.structures;

public interface IList<T> {
	/**
	 * Puts the data element at the end of the list
	 * @param data the data to be used
	 */
	public void put(T data);
	/**
	 * Puts the data element at the beginning of the list
	 * @param data the data to be used
	 */
	public void push(T data);
	/**
	 * Gives back the data at the end of the list and deletes the element
	 * @return the data element of the last list element
	 */
	public T take();
	/**
	 * Gives back the data of the first element of the list and deletes the element
	 * @return the data element of the list's first element
	 */
	public T pull();

	/**
	 * Returns the data at the given index
	 * @param i the index from where the data will be given
	 * @return the data that will be given
	 */
	public T get(int i);
	/**
	 * Sets the data at the specified index to be the given. This will replace the old data. To insert the data instead, use the insert method
	 * @param i the index where the data will be replaced
	 * @param data the data that will be used to replace
	 */
	public void set(int i, T data);

	/**
	 * Removes the node at the specified index
	 * @param i the index of the node to be removed
	 */
	public void remove(int i);
	/**
	 * Inserts the data at the specified index, this will push the other indexes one up and therefore does not delete another data point. To replace a value look at the set method
	 * @param i
	 * @param data
	 */
	public void insert(int i, T data);

	/**
	 * This will remove all the data points after the given index. The data at the index will remain.
	 * @param i the index after which everything will be removed
	 */
	public void removeAfter(int i);
	/**
	 * This will remove all the data points after the given index. The data at the index will remain.
	 * @param i the index after which everything will be removed
	 */
	public void removeBefore(int i);

	/**
	 * This will completely clear the list and remove all data in it.
	 */
	public void clear();
	/**
	 * A basic method returning the length of the list
	 * @return the count of all data points in the list
	 */
	public int length();

	// public IList<T> filter(Function that returns a boolean depending on two inputs of T) TODO: learn functional programming in Java
}
