public class Vector2 {
    public final int vertical;
    public final int horizontal;
    public Vector2(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Vector2 sum(Vector2 vector2) {
        return new Vector2(this.vertical + vector2.vertical, this.horizontal + vector2.horizontal);
    }

    public static Vector2 zero() {
        return new Vector2(0, 0);
    }
    public static Vector2 up() {
        return new Vector2(1, 0);
    }
    public static Vector2 down() {
        return new Vector2(-1, 0);
    }
    public static Vector2 left() {
        return new Vector2(0, -1);
    }
    public static Vector2 right() {
        return new Vector2(0, 1);
    }

    public boolean equals(Vector2 vector2) {
        return this.vertical == vector2.vertical && this.horizontal == vector2.horizontal;
    }
}
