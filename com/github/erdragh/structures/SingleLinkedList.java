package com.github.erdragh.structures;

/**
 * An implementation of a single linked list that divides between structure and data and abstracts its list structure
 * @author Erdragh
 * @version 1.1
 * @see Node
 * @see DataNode
 * @see EndNode
 */

public class SingleLinkedList<T> implements IList<T> {

	/**
	 * This is the start node, in which the List stores it's first element. On initialization it is an <code>EndNode</code>.
	 * @see Node
	 * @see EndNode
	 * @see DataNode
	 */
	private Node<T> start;

	/**
	 * A simple constructor setting the start <code>Node</code> to be an instance of <code>EndNode</code>
	 * @see Node
	 * @see EndNode
	 */
	public SingleLinkedList() {
		start = new EndNode<T>();
	}

	/**
	 * A private class for the list structure. This is the base class for the two types of nodes. This implementation uses what in German would be called "Kompositum" and it distinctly divides structure and data.
	 */
	private abstract static class Node<T> {
		/**
		 * This method adds the parameter to the end of the list
		 * @param data the data that will be put into a <code>DataNode</code> at the end of the list.
		 * @return a <code>Node</code> that is used to set the next attribute in the <code>DataNode</code> so the nodes do not need to know it's previous node.
		 * @see DataNode
		 */
		public abstract Node<T> put(T data);
		/**
		 * This is the initial method for the process of "taking", which in this implementation means returning the data of the last element. Removing is done seperately in the List implementation, since the implementation of removing something also requires returning a <code>Node</code>, so that the next attribute can be set accordingly.
		 * @return the data element of the last element of the list. If the first element of the list is an <code>EndNode</code> this will be <code>null</code>.
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract T take();
		/**
		 * This is the actual recursive method for getting the data from the last element of the List.
		 * @param current the data of the previous node that called this method. This is used in the <code>EndNode</code> to actually return the relevant data.
		 * @return the data element of the last element of the List.
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract T take(T current);
		
		/**
		 * Recursively goes through the elements of the list.
		 * @param i the index from which the data will be returned
		 * @param current a helping parameter for the recursive nature of this function. It stores on which index we're currently.
		 * @return the data on the specified index
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract T get(int i, int current);
		/**
		 * This will set the data on the specified index to the given one.
		 * @param i the index on which the data will be inserted. If this is the index immediately after the end a new <code>Node</code> will be created in the <code>EndNode</code>
		 * @param current a helping parameter for the recursive nature of this function. It stores on which index we're currently.
		 * @param data the data to be inserted
		 * @return a <code>Node</code> that is used to set the next attribute in the <code>DataNode</code> so the nodes do not need to know its previous nodes
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract Node<T> set(int i, int current, T data);

		/**
		 * Removes the node at the given position.
		 * @param i the index of the data to be removed
		 * @param current a helping parameter for the recursive nature of this function. It stores on which index we're currently.
		 * @return a <code>Node</code> that is used to set the next attribute in the <code>DataNode</code> so the nodes do not need to know its previous nodes
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract Node<T> remove(int i, int current);
		/**
		 * Inserts the given data at the specified index in the list. This method does not replace the node at the index, it simply moves all data that comes after one index up. To replace the data at a given index, use the <code>set</code> method.
		 * @param i the index where the data is to be inserted
		 * @param current a helping parameter for the recursive nature of this function. It stores on which index we're currently.
		 * @param data the data to be inserted at the specified index
		 * @return a <code>Node</code> that is used to set the next attribute in the <code>DataNode</code> so the nodes do not need to know its previous nodes
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract Node<T> insert(int i, int current, T data);

		/**
		 * Removes all nodes after the given index
		 * @param i the index after which all nodes will be removed
		 * @param current a helping parameter for the recursive nature of this function. It stores on which index we're currently.
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract void removeAfter(int i, int current);
		/**
		 * Removes all nodes before a given index
		 * @param i the index before which all nodes will be removed
		 * @param current a helping parameter for the recursive nature of this function. It stores on which index we're currently.
		 * @return a <code>Node</code> that is used to set the next attribute in the <code>DataNode</code> so the nodes do not need to know its previous nodes
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract Node<T> removeBefore(int i, int current);
		//Disabled because it does not work and is not needed
		// /**
		//  * Removes the last element of the list, this is the init method
		//  * @return a node for the caller to set to next.
		//  */
		// public abstract Node<T> removeLast();
		// /**
		//  * Removes the last element of the list, this is the actual recursive method, to init this, call the init method.
		//  * @return a <code>Node</code> that is used to set the next attribute in the <code>DataNode</code> so the nodes do not need to know its previous nodes
		//  * @param caller the node that called the method recursively
		//  * @param prev the node before the node that called the methord recursively
		//  * @see DataNode
		//  * @see EndNode
		//  */
		// public abstract Node<T> removeLast(Node<T> caller, Node<T> prev);

		/**
		 * A getter method
		 * @return the next node if in a <code>DataNode</code> and the end node itself if in an <code>EndNode</code>
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract Node<T> getNext();
		/**
		 * A setter method
		 * @param next the new next element for <code>DataNode</code>. Will not do anything in <code>EndNode</code>
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract void setNext(Node<T> next);
		/**
		 * A getter method
		 * @return the data if in a <code>DataNode</code> and null if in an <code>EndNode</code>
		 * @see DataNode
		 * @see EndNode
		 */
		public abstract T getData();
		/**
		 * Recursively counts the number of nodes in the list.
		 * @param current a counter for the method to store how many nodes there have been so far.
		 * @return the count of nodes in the list
		 */
		public abstract int count(int current);
	}

	/**
	 * Inherits from <code>Node</code>, used to store data
	 * @see Node
	 */
	private static class DataNode<T> extends Node<T> {

		/**
		 * The next <code>Node</code> in the list structure can be either of <code>DataNode</code> or <code>EndNode</code>
		 * @see Node
		 * @see DataNode
		 * @see EndNode
		 */
		private Node<T> next;
		/**
		 * The data elements stored in the list
		 */
		private T data;

		/**
		 * A simple constructor for creating a new node for data
		 * @param next the node that will come after the one created in this constructor.
		 * @param data the data stored in the node
		 * @see Node
		 * @see EndNode
		 */
		public DataNode(Node<T> next, T data){
			this.next = next;
			this.data = data;
		} //187

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
			/*
			The next node will be determined by the next node itself with this recursive call, as results differ depending on whether next node is a DataNode or an EndNode.
			*/
			next = next.put(data);
			return this;
		}

		@Override
		public T take() {
			/*
			As this is the init method it will start the recursive call for returning the last data element in the list
			*/
			return next.take(data);
		}

		@Override
		public T take(T current) {
			/*
			As this is the actual recursive method we will always give this node's data so that when we reach the EndNode the data to be returned will be the last one before the EndNode.
			*/
			return next.take(data);
		}

		@Override
		public T get(int i, int current) {
			/*
			We will return this node's data if it is the one we're looking for, if not we will recursively call this method for the next node
			*/
			return (i == current++) ? data : next.get(i, current);
		}

		@Override
		public Node<T> set(int i, int current, T data) {
			/*
			If the specified index is this node, its data will be replaced with the given one. If not this method will be recursively called for the next node.
			*/
			if (i == current++) {
				this.data = data;
			} else {
				next = next.set(i, current, data);
			}
			//returns this node, because this method needs to return a Node for the special case in the EndNode to work.
			return this;
		}

		@Override
		public Node<T> remove(int i, int current) {
			/*
			If this is the node to be removed, give back the next attribute as a return value so that the previous node will have a reference to that instead of this node.
			*/
			if (i == current++) {
				return next;
			} else {
				/*
				If this is not the node to be removed then set the next attribute to whatever a recursive method call of this method in the next node returns. Returns itself so that the method can run recursively through the list and not interrupt the structure.
				*/
				next = next.remove(i, current);
				return this;
			}
		}

		@Override
		public Node<T> insert(int i, int current, T data) {
			/*
			if this is the index where the new data is to be inserted, make a new node to with this node as its next reference and return it so the previous node has a reference to the newly created node instead of this one.
			*/
			if (i == current++) {
				return new DataNode<T>(this, data);
			} else {
				/*
				if this is not the index where the node is to be removed set the next attribute to the return value of this method recursively called on the current next attribute. Returns itself so that the method can run recursively through the list and not interrupt the structure.
				*/
				next = next.insert(i, current, data);
				return this;
			}
		}

		@Override
		public void removeAfter(int i, int current) {
			/*
			if this is the index after which all is removed, set the reference to the next attribute to an EndNode, so the Garbage Collector eventually removes all leftover data.
			*/
			if (i == current++) {
				next = new EndNode<T>();
			} else {
				/*
				if this is not the index after which all will be removed, recursively call this method for the next node.
				*/
				next.removeAfter(i, current);
			}
		}

		@Override
		public Node<T> removeBefore(int i, int current) {
			/*
			if this is the node before which all is to be removed recursively return a reference to this node to the list for it to set its start reference to this node. This will result in all previous elements being discarded and eventually removed by the Garbage Collector
			*/
			if (i == current++) {
				return this;
			} else {
				/*
				if this is not the node before which all is to be removed recursively call this method in the next node in order to find and return the needed node.
				*/
				return next.removeBefore(i, current);
			}
		}

		// @Override
		// public Node<T> removeLast() {
		// 	//inits the recurive method
		// 	if (next.removeLast() != null) {
		// 		//this method call will have the next (usually second) node set the next attribute for this node (usually the start node) twice. This does still work, the fact that we give this node as the parameter twice is intended!
		// 		next.removeLast(this, this);
		// 		//returns this node so the list can set its start node to the correct node
		// 		return this;
		// 	} else {
		// 		//this is only executed if the start node is also the last datanode. This will return the EndNode to the list so the start node is an EndNode
		// 		return next;
		// 	}
		// }

		// @Override
		// public Node<T> removeLast(Node<T> caller, Node<T> prev) {
		// 	//Sets the next attribute to the return value of a recursive call to this method on the next attribute. This is done in order for the EndNode to be able to 
		// 	prev.setNext(caller);
		// 	caller.setNext(this);
		// 	next = next.removeLast(this, caller);
		// 	return this;
		// }

		@Override
		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public int count(int current) {
			//Counts up so the EndNode can return the actual value.
			return next.count(++current);
		}

	}

	/**
	 * Inherits from <code>Node</code>, used for the logic at the end of the list.
	 * @see Node
	 */
	private static class EndNode<T> extends Node<T> {

		@Override
		public Node<T> put(T data) {
			//returns the new DataNode with the given data so the previous node can set it as its next attribute.
			return new DataNode<T>(this, data);
		}

		@Override
		public T take() {
			//the only case this will be called is if the List has its start reference set to be an EndNode, e.g. no data is in the list.
			return null;
		}

		@Override
		public T take(T current) {
			//returns the data given to it from the previous node for it to be recursively returned to the list
			return current;
		}

		@Override
		public T get(int i, int current) {
			//Index out of bounds for the List
			return null;
		}

		@Override
		public Node<T> set(int i, int current, T data) {
			//Index out of bounds for the List
			if (i == current) {
				//but the index i immediately after the end, so we will insert a new Node/Element in this place
				return new DataNode<T>(this, data);
			} else {
				//pretty much do nothing
				return this;
			}
		}

		@Override
		public Node<T> remove(int i, int current) {
			//Index out of bounds for the List
			return this;
		}

		@Override
		public Node<T> insert(int i, int current, T data) {
			//Index out of bounds for the List
			if (i == current) {
				//The index i is immediately after the end, so we will insert a new Node/Element in this place
				return new DataNode<T>(this, data);
			} else {
				//pretty much do nothing
				return this;
			}
		}

		@Override
		public Node<T> getNext() {
			return this;
		}

		@Override
		public T getData() {
			return null;
		}

		@Override
		public void removeAfter(int i, int current) {
			//Do nothing, since index i is out of bounds for the List
		}

		@Override
		public Node<T> removeBefore(int i, int current) {
			//Give the reference to the end node (this) to the list recursively, effectively clearing the list.
			return this;
		}

		// @Override
		// public Node<T> removeLast() {
		// 	//return null for the test in the init method in the DataNode
		// 	return null;
		// }

		// @Override
		// public Node<T> removeLast(Node<T> caller, Node<T> prev) {
		// 	//doesnt set the callers next, instead sets the node before the caller's next. This removes all references to the last DataNode, therefore having them be deleted with the Garbage Collector
		// 	prev.setNext(this);
		// 	return null;
		// }

		@Override
		public void setNext(Node<T> next) {
			//Do nothing, since the EndNode does not have another node after it
		}

		@Override
		public int count(int current) {
			//Returns the count that has been counted up to in current
			return current;
		}

	}

	@Override
	public void put(T data) {
		//starts a recursive method. Sets start to be the return value since the start node may change if this is the first time data is inserted
		start = start.put(data);
	}

	@Override
	public void push(T data) {
		//Creates a new node that will be the new start, of which the next attribute will be the previous start. This way the element at the beginning of the list will be the new node.
		start = new DataNode<T>(start, data);
	}

	@Override
	public T take() {
		//Since this method is supposed to return the data and remove the last element. Since this is not really possible with a recursive method in a single link list we will first get the data then store it temporarily, then delete the last element (this is the hardest part, since it's only really possible in a double link list, which is why the actual method half resembles a double linked list)
		T data = start.take();
		// start.removeLast();
		remove(length()-1);
		return data;
	}

	@Override
	public T pull() {
		//Temporarily stores the data from the start node and then puts the reference to be the next node after the start node.
		T data = start.getData();
		start = start.getNext();
		return data;
	}

	@Override
	public T get(int i) {
		//inits the recursive method
		return start.get(i, 0);
	}

	@Override
	public void set(int i, T data) {
		//inits the recusrive method. The setting of the start node is in case of the special case scenario where a new node will be created at the end.
		start = start.set(i, 0, data);
	}

	@Override
	public void remove(int i) {
		//inits the recursive method
		start = start.remove(i, 0);
	}

	@Override
	public void insert(int i, T data) {
		//inits the recursive method
		start = start.insert(i, 0, data);
	}

	@Override
	public void removeAfter(int i) {
		//inits the recursive method
		start.removeAfter(i, 0);
	}

	@Override
	public void removeBefore(int i) {
		//inits the recursive method
		start = start.removeBefore(i, 0);
	}

	@Override
	public void clear() {
		//this basically deletes all references to the DataNodes
		start = new EndNode<T>();
	}

	@Override
	public int length() {
		//inits the recursive method
		return start.count(0);
	}
	
}
