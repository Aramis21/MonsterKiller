package org.andengine.util.adt.bit;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * (c) 2013 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:14:12 - 02.03.2013
 */
public interface IBitVector {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	int getSize();
	int getBit(final int pIndex) throws IllegalArgumentException;
	void setBit(final int pIndex) throws IllegalArgumentException;
	void clearBit(final int pIndex) throws IllegalArgumentException;
	void setBit(final int pIndex, final boolean pTrue) throws IllegalArgumentException;
	boolean getBitAsBoolean(int pIndex) throws IllegalArgumentException;

	byte getByte(final int pIndex) throws IllegalArgumentException;
	void setByte(final int pIndex, final byte pByte) throws IllegalArgumentException;

	short getShort(final int pIndex) throws IllegalArgumentException;
	void setShort(final int pIndex, final short pShort) throws IllegalArgumentException;

	int getInt(final int pIndex) throws IllegalArgumentException;
	void setInt(final int pIndex, final int pInt) throws IllegalArgumentException;

	long getLong(final int pIndex) throws IllegalArgumentException;
	void setLong(final int pIndex, final long pLong) throws IllegalArgumentException;

	int getBits(final int pIndex, final int pLength) throws IllegalArgumentException;
	void setBits(final int pIndex, final byte pBits, final int pBitIndex, final int pBitCount) throws IllegalArgumentException;
	void setBits(final int pIndex, final short pBits, final int pBitIndex, final int pBitCount) throws IllegalArgumentException;
	void setBits(final int pIndex, final int pBits, final int pBitIndex, final int pBitCount) throws IllegalArgumentException;

	long getLongBits(int pIndex, int pLength) throws IllegalArgumentException;

	byte[] toByteArray();

	void save(final DataOutputStream pDataOutputStream) throws IOException;

	void clear();
	void fill(final byte pByte);
}