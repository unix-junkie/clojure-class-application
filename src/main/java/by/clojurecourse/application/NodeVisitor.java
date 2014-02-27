/*-
 * $Id$
 */
package by.clojurecourse.application;

/**
 * @author Andrew ``Bass'' Shcheglov (mailto:andrewbass@gmail.com)
 */
@FunctionalInterface
public interface NodeVisitor {
	/**
	 * @param n
	 */
	void visit(final Node n);
}
