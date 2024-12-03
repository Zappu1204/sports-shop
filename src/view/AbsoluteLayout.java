package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class AbsoluteLayout implements LayoutManager2, Serializable {
    static final long serialVersionUID = -1919857869177070440L;
    protected Hashtable constraints = new Hashtable();

    public AbsoluteLayout() {
    }

    public void addLayoutComponent(String var1, Component var2) {
        throw new IllegalArgumentException();
    }

    public void removeLayoutComponent(Component var1) {
        this.constraints.remove(var1);
    }

    public Dimension preferredLayoutSize(Container var1) {
        int var2 = 0;
        int var3 = 0;
        Enumeration var4 = this.constraints.keys();

        while(var4.hasMoreElements()) {
            Component var5 = (Component)var4.nextElement();
            AbsoluteConstraints var6 = (AbsoluteConstraints)this.constraints.get(var5);
            Dimension var7 = var5.getPreferredSize();
            int var8 = var6.getWidth();
            if (var8 == -1) {
                var8 = var7.width;
            }

            int var9 = var6.getHeight();
            if (var9 == -1) {
                var9 = var7.height;
            }

            if (var6.x + var8 > var2) {
                var2 = var6.x + var8;
            }

            if (var6.y + var9 > var3) {
                var3 = var6.y + var9;
            }
        }

        return new Dimension(var2, var3);
    }

    public Dimension minimumLayoutSize(Container var1) {
        int var2 = 0;
        int var3 = 0;
        Enumeration var4 = this.constraints.keys();

        while(var4.hasMoreElements()) {
            Component var5 = (Component)var4.nextElement();
            AbsoluteConstraints var6 = (AbsoluteConstraints)this.constraints.get(var5);
            Dimension var7 = var5.getMinimumSize();
            int var8 = var6.getWidth();
            if (var8 == -1) {
                var8 = var7.width;
            }

            int var9 = var6.getHeight();
            if (var9 == -1) {
                var9 = var7.height;
            }

            if (var6.x + var8 > var2) {
                var2 = var6.x + var8;
            }

            if (var6.y + var9 > var3) {
                var3 = var6.y + var9;
            }
        }

        return new Dimension(var2, var3);
    }

    public void layoutContainer(Container var1) {
        Component var3;
        AbsoluteConstraints var4;
        int var6;
        int var7;
        for(Enumeration var2 = this.constraints.keys(); var2.hasMoreElements(); var3.setBounds(var4.x, var4.y, var6, var7)) {
            var3 = (Component)var2.nextElement();
            var4 = (AbsoluteConstraints)this.constraints.get(var3);
            Dimension var5 = var3.getPreferredSize();
            var6 = var4.getWidth();
            if (var6 == -1) {
                var6 = var5.width;
            }

            var7 = var4.getHeight();
            if (var7 == -1) {
                var7 = var5.height;
            }
        }

    }

    public void addLayoutComponent(Component var1, Object var2) {
        if (!(var2 instanceof AbsoluteConstraints)) {
            throw new IllegalArgumentException();
        } else {
            this.constraints.put(var1, var2);
        }
    }

    public Dimension maximumLayoutSize(Container var1) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public float getLayoutAlignmentX(Container var1) {
        return 0.0F;
    }

    public float getLayoutAlignmentY(Container var1) {
        return 0.0F;
    }

    public void invalidateLayout(Container var1) {
    }
}
