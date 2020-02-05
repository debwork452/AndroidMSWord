package com.deb452.msword_library.views.draggable_imgview;

public class PositionAndScale {
    private float xOff, yOff, scale, scaleX, scaleY, angle;
    private boolean updateScale, updateScaleXY, updateAngle;

    public void set(float xOff, float yOff, boolean updateScale, float scale,
                    boolean updateScaleXY, float scaleX, float scaleY,
                    boolean updateAngle, float angle) {
        this.xOff = xOff;
        this.yOff = yOff;
        this.updateScale = updateScale;
        this.scale = scale == 0.0f ? 1.0f : scale;
        this.updateScaleXY = updateScaleXY;
        this.scaleX = scaleX == 0.0f ? 1.0f : scaleX;
        this.scaleY = scaleY == 0.0f ? 1.0f : scaleY;
        this.updateAngle = updateAngle;
        this.angle = angle;
    }

    /**
     * Set position and optionally scale, anisotropic scale, and/or angle,
     * without changing the "update" flags.
     */
    protected void set(float xOff, float yOff, float scale,
                       float scaleX, float scaleY, float angle) {
        this.xOff = xOff;
        this.yOff = yOff;
        this.scale = scale == 0.0f ? 1.0f : scale;
        this.scaleX = scaleX == 0.0f ? 1.0f : scaleX;
        this.scaleY = scaleY == 0.0f ? 1.0f : scaleY;
        this.angle = angle;
    }

    public float getXOff() {
        return xOff;
    }

    public float getYOff() {
        return yOff;
    }

    public float getScale() {
        return !updateScale ? 1.0f : scale;
    }

    /** Included in case you want to support anisotropic scaling */
    public float getScaleX() {
        return !updateScaleXY ? 1.0f : scaleX;
    }

    /** Included in case you want to support anisotropic scaling */
    public float getScaleY() {
        return !updateScaleXY ? 1.0f : scaleY;
    }

    public float getAngle() {
        return !updateAngle ? 0.0f : angle;
    }

    public boolean isUpdateScale() {
        return updateScale;
    }

    public void setUpdateScale(boolean updateScale) {
        this.updateScale = updateScale;
    }

    public boolean isUpdateScaleXY() {
        return updateScaleXY;
    }

    public void setUpdateScaleXY(boolean updateScaleXY) {
        this.updateScaleXY = updateScaleXY;
    }

    public boolean isUpdateAngle() {
        return updateAngle;
    }

    public void setUpdateAngle(boolean updateAngle) {
        this.updateAngle = updateAngle;
    }
}
