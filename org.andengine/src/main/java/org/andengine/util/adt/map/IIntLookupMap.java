package org.andengine.util.adt.map;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 15:31:34 - 26.04.2012
 */
public interface IIntLookupMap<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	void add(final T pItem, final int pValue);
	T item(final int pValue);
	int value(final T pItem);
}
