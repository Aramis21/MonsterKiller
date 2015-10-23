package org.andengine.util.adt.list;

/**
 * (c) 2013 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:05:14 - 19.01.2013
 */
public interface IBooleanList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	boolean isEmpty();
	boolean get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	void add(final boolean pItem);
	void add(final int pIndex, final boolean pItem) throws ArrayIndexOutOfBoundsException;
	boolean remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	int size();
	void clear();
	int[] toArray();
}