/**
 * @ClassName Circle
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/8/30 17:27
 * @Version 1.0
 */
public class Circle extends GeoShape {

    private int r;

    @Override
    public double getArea(int r) {
        return PI*r*r;
    }

}
