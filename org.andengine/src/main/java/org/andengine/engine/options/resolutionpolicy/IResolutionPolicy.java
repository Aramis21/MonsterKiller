package org.andengine.engine.options.resolutionpolicy;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 11:02:35 - 29.03.2010
 */
public interface IResolutionPolicy {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	void onMeasure(final Callback pResolutionPolicyCallback, final int pWidthMeasureSpec, final int pHeightMeasureSpec);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	interface Callback {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		void onResolutionChanged(final int pWidth, final int pHeight);
	}
}
