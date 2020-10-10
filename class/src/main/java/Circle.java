import org.jetbrains.annotations.NotNull;

/**
 * @ClassName Circle
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/8/30 17:27
 * @Version 1.0
 */
public class Circle implements Comparable {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle radius: " + radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return ((Circle) o).radius == radius;

    }

    @Override
    public int hashCode() {
        return new Double(radius).hashCode();
    }

    @Override
    public int compareTo(@NotNull Object o) {
        Circle circle = (Circle)o;
        return Double.compare(radius, circle.radius);
    }
}

