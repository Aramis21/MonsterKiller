package org.andengine.util.adt.list;

/**
 * (c) 2013 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:18:45 - 19.01.2013
 */
public interface IByteList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	boolean isEmpty();
	byte get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	void add(final byte pItem);
	void add(final int pIndex, final byte pItem) throws ArrayIndexOutOfBoundsException;
	byte remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	int size();
	void clear();
	byte[] toArray();
}