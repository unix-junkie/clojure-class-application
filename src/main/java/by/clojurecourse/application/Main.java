/*-
 * $Id$
 */
package by.clojurecourse.application;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author Andrew ``Bass'' Shcheglov (mailto:andrewbass@gmail.com)
 */
public final class Main {
	private Main() {
		assert false;
	}

	/**
	 * Breadth-first search (BFS).
	 *
	 * @param root
	 * @param visitor
	 */
	public static void breadthFirst(final Node root, final NodeVisitor visitor) {
		final Queue<Node> roots = new ArrayDeque<>();
		roots.add(root);

		while (!roots.isEmpty()) {
			final Node r = roots.poll();

			for (final Node c : r) {
				if (c.isVisited()) {
					continue;
				}

				roots.add(c);
			}
			visitor.visit(r);
		}
	}

	/**
	 * Depth-first search (DFS), iterative version.
	 *
	 * @param root
	 * @param visitor
	 * @see #depthFirstRecursive
	 * @see <a href = "http://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a>
	 */
	public static void depthFirstIterative(final Node root, final NodeVisitor visitor) {
		final Deque<Node> stack = new ArrayDeque<>();

		stack.push(root);

		while (!stack.isEmpty()) {
			final Node r = stack.pop();

			for (final Node c : r) {
				if (c.isVisited()) {
					continue;
				}

				stack.push(c);
			}
			visitor.visit(r);
		}
	}

	/**
	 * Depth-first search (DFS), recursive version.
	 *
	 * @param root
	 * @param visitor
	 * @see #depthFirstIterative
	 * @see <a href = "http://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a>
	 */
	public static void depthFirstRecursive(final Node root, final NodeVisitor visitor) {
		visitor.visit(root);

		for (final Node child : root) {
			if (child.isVisited()) {
				continue;
			}

			depthFirstRecursive(child, visitor);
		}
	}

	/**
	 * @param args
	 */
	public static void main(final String args[]) {
		final String tree[] = {
				"[", "1", "[", "2", "3", "]", "4", "[", "5", "[", "6", "7", "]", "]", "[", "8", "]", "]"
		};
		final Node root = new Node(tree);
		System.out.println(root);
		final NodeVisitor visitor = n -> {
			System.out.println(n.getValue());
		};
//		depthFirstIterative(root, visitor);
		depthFirstRecursive(root, visitor);
//		breadthFirst(root, visitor);
	}
}
