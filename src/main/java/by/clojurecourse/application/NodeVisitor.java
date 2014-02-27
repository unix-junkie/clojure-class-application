/*-
 * $Id$
 */
package by.clojurecourse.application;

/**
 * @author Andrew ``Bass'' Shcheglov (mailto:andrewbass@gmail.com)
 */
public interface NodeVisitor {
	/**
	 * @param n
	 */
	void visit(final Node n);
}
