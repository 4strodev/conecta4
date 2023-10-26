import java.io.Serializable;

public class Board implements Serializable {
    private final Column[] board;
    private int turn = 1;
    public final int width;
    public final int height;
    public String name;

    public Board(String name, int width, int height) {
        this.name = name.replace(' ', '_');
        this.width = width;
        this.height = height;
        this.board = new Column[width];
        for (int i = 0; i < width; i++) {
            this.board[i] = new Column(height);
        }
    }

    public void makeMove(int column) throws Exception {
        boolean success = this.board[column].push(this.turn);
        if (!success) {
            throw new Exception("Column is full");
        }
        this.turn = this.turn == 1 ? 2 : 1;
    }

    public int checkWinner() {
        int winnerBox = 0;
        Vector2[] directions = {
                Vector2.up(),
                Vector2.up().sum(Vector2.right()),
                Vector2.right(),
                Vector2.down().sum(Vector2.right()),
                Vector2.down(),
                Vector2.down().sum(Vector2.left()),
                Vector2.left(),
                Vector2.up().sum(Vector2.left()),
        };
        // Iterating rows
        for (Vector2 direction :
                directions) {
            for (int w = 0; w < this.width; w++) {
                // Iterating column
                for (int h = 0; h < this.height; h++) {
                    if (this.checkWinner(w, h, direction)) {
                        try {
                            winnerBox = this.getBox(w, h);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }

        return winnerBox;
    }

    private boolean checkWinner(int column, int row, Vector2 direction) {
        int box;
        try {
            box = this.getBox(column, row);
        } catch (Exception e) {
            return false;
        }

        if (box == 0) {
            return false;
        }

        if (direction.equals(Vector2.zero())) {
            return false;
        }

        // Keep following direction
        for (int i = 0; i < 3; i++) {
            int box2;
            try {
                column += direction.vertical;
                row += direction.horizontal;
                box2 = this.getBox(column, row);
            } catch (Exception e) {
                return false;
            }

            if (box != box2) {
                return false;
            }
        }

        return true;
    }

    private int getBox(int column, int row) throws Exception {
        if (column < 0 || this.width <= column) {
            throw new Exception("Out of boundaries");
        }
        if (row < 0 || this.height <= row) {
            throw new Exception("Out of boundaries");
        }

        return this.board[column].getList()[row];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name: %s\n", this.name));
        stringBuilder.append(String.format("Turn: %s\n", this.turn));
        for (int h = this.height - 1; h >= 0; h--) {
            for (int w = 0; w < this.width; w++) {
                stringBuilder.append(" ");
                stringBuilder.append(this.board[w].getList()[h]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
