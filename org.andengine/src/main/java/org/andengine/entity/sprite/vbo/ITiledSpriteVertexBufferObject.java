package org.andengine.entity.sprite.vbo;

import org.andengine.entity.sprite.TiledSprite;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:39:06 - 28.03.2012
 */
public interface ITiledSpriteVertexBufferObject extends ISpriteVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	void onUpdateColor(final TiledSprite pTiledSprite);
	void onUpdateVertices(final TiledSprite pTiledSprite);
	void onUpdateTextureCoordinates(final TiledSprite pTiledSprite);
}