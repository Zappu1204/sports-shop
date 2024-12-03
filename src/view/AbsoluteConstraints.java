package view;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

public class AbsoluteConstraints implements Serializable {
    static final long serialVersionUID = 5261460716622152494L;
    public int x;
    public int y;
    public int width;
    public int height;

    public AbsoluteConstraints(Point var1) {
        this(var1.x, var1.y);
    }

    public AbsoluteConstraints(int var1, int var2) {
        this.width = -1;
        this.height = -1;
        this.x = var1;
        this.y = var2;
    }

    public AbsoluteConstraints(Point var1, Dimension var2) {
        this.width = -1;
        this.height = -1;
        this.x = var1.x;
        this.y = var1.y;
        if (var2 != null) {
            this.width = var2.width;
            this.height = var2.height;
        }

    }

    public AbsoluteConstraints(int var1, int var2, int var3, int var4) {
        this.width = -1;
        this.height = -1;
        this.x = var1;
        this.y = var2;
        this.width = var3;
        this.height = var4;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return super.toString() + " [x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + "]";
    }
}
