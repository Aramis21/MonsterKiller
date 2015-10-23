package org.andengine.entity.scene.menu.animator;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.VerticalAlign;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 10:50:36 - 02.04.2010
 */
public interface IMenuSceneAnimator {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	float getOffsetX();
	void setOffsetX(final float pOffsetX);
	float getOffsetY();
	void setOffsetY(final float pOffsetY);
	float getMenuItemSpacing();
	void setMenuItemSpacing(final float pMenuItemSpacing);
	HorizontalAlign getHorizontalAlign();
	void setHorizontalAlign(final HorizontalAlign pHorizontalAlign);
	VerticalAlign getVerticalAlign();
	void setVerticalAlign(final VerticalAlign pVerticalAlign);

	void buildMenuSceneAnimations(final MenuScene pMenuScene);
	void resetMenuSceneAnimations(final MenuScene pMenuScene);
}
