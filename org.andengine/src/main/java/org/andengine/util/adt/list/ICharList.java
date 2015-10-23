package org.andengine.util.adt.list;

/**
 * (c) 2013 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:13:13 - 10.01.2013
 */
public interface ICharList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	boolean isEmpty();
	char get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	void add(final char pItem);
	void add(final int pIndex, final char pItem) throws ArrayIndexOutOfBoundsException;
	char remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	int size();
	void clear();
	char[] toArray();
}