package org.andengine.util.adt.list;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 19:36:57 - 03.05.2012
 */
public interface ILongList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	boolean isEmpty();
	long get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	void add(final long pItem);
	void add(final int pIndex, final long pItem) throws ArrayIndexOutOfBoundsException;
	long remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	int size();
	void clear();
	long[] toArray();
}