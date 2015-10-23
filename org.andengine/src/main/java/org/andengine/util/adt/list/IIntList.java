package org.andengine.util.adt.list;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 19:21:53 - 03.05.2012
 */
public interface IIntList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	boolean isEmpty();
	int get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	void add(final int pItem);
	void add(final int pIndex, final int pItem) throws ArrayIndexOutOfBoundsException;
	int remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	int size();
	void clear();
	int[] toArray();
}