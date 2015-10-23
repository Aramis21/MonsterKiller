package org.andengine.util.adt.list;

/**
 * (c) 2013 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:18:46 - 19.01.2013
 */
public interface IDoubleList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	boolean isEmpty();
	double get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	void add(final double pItem);
	void add(final int pIndex, final double pItem) throws ArrayIndexOutOfBoundsException;
	double remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	int size();
	void clear();
	double[] toArray();
}