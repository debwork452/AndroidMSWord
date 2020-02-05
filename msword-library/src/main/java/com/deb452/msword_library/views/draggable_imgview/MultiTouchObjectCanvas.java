package com.deb452.msword_library.views.draggable_imgview;

public interface MultiTouchObjectCanvas<T> {

    public T getDraggableObjectAtPoint(PointInfo pointInfo);

    public boolean pointInObjectGrabArea(PointInfo pointInfo, T obj);

    public void getPositionAndScale(T obj, PositionAndScale positionAndScale);

    public boolean setPositionAndScale(T obj, PositionAndScale positionAndScale, PointInfo pointInfo);

    public void selectObject(T obj, PointInfo touchPoint);

    public void deselectAll();

    public void canvasTouched();
}
