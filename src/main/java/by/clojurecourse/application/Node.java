/*-
 * $Id$
 */
package by.clojurecourse.application;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Andrew ``Bass'' Shcheglov (mailto:andrewbass@gmail.com)
 */
public final class Node implements Iterable<Node> {
	private final List<Node> children = new ArrayList<>();

	private final List<Integer> numbers = new ArrayList<>();

	/**
	 * A flag indicating whether a node has already been visited. While not
	 * necessary for trees, this flag is required for generic graphs
	 * containing cycles.
	 */
	private boolean visited;

	/**
	 * Constructs a tree from its string representation.
	 *
	 * @param ns
	 */
	public Node(final String ... ns) {
		this(asList(ns));
	}

	/**
	 * Constructs a tree from its string representation.
	 *
	 * @param ns
	 */
	public Node(final List<String> ns) {
		int depth = -1;
		final List<String> children2 = new ArrayList<>();
		for (final String n : ns) {
			switch(n) {
			case "[":
				depth++;
				if (depth > 0) {
					children2.add(n);
				}
				break;
			case "]":
				if (depth > 0) {
					children2.add(n);
					if (depth == 1) {
						this.children.add(new Node(children2));
						children2.clear();
					}
				}
				depth--;
				break;
			default:
				final int value;
				try {
					value = Integer.parseInt(n);
				} catch (final NumberFormatException nfe) {
					throw new IllegalArgumentException(n);
				}
				if (depth == 0) {
					this.numbers.add(Integer.valueOf(value));
				} else if (depth > 0) {
					children2.add(n);
				} else {
					/*
					 * Safety net.
					 */
					System.err.println("ERR: " + this);
					System.err.println("ERR: " + n);
					assert false: depth;
				}
				break;
			}
		}
	}

	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * @param visited
	 */
	public void setVisited(final boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return a scalar value (the sum of all integers in {@link #numbers})
	 *         associated with this node.
	 */
	public int getValue() {
		int value = 0;
		for (final Integer number : this.numbers) {
			value += number.intValue();
		}
		return value;
	}

	/**
	 * @see Iterable#iterator()
	 */
	@Override
	public Iterator<Node> iterator() {
		return unmodifiableList(this.children).iterator();
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (final Integer number : this.numbers) {
			builder.append(number.intValue()).append(", ");
		}

		for (final Node child : this.children) {
			builder.append(child).append(", ");
		}

		/*
		 * Assuming a node holds at least 1 number and/or 1 child.
		 */
		if (!this.numbers.isEmpty() || !this.children.isEmpty()) {
			final int length = builder.length();
			builder.delete(length - 2, length);
		}

		builder.append(']');
		return builder.toString();
	}
}
