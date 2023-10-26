public class Main {
    static String menu = """
            Main menu
            1. New game
            2. Load game
            3. Exit
            ->\s""";
    static String gameMenu = """
            Game menu
            1. Make a move
            2. Save
            ->\s""";
    public static void main(String[] args) {
        inputLoop:
        while (true) {
            int option = InputManager.nextInt(menu);
            switch (option) {
                case 1 -> newGame();
                case 2 -> loadGame();
                case 3 -> {
                    break inputLoop;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    public static boolean inclusiveBetween(int low, int max, int value) {
        return low <= value && value <= max;
    }

    public static void newGame() {
        String name = InputManager.nextLine("Put the game name: ").trim();
        Board board = new Board(name, 7, 6);
        startGame(board);
    }

    public static void loadGame() {
        String name = InputManager.nextLine("Put the game name: ");
        String path = String.format("%s.data", name);
        Board board;
        try {
            board = GameManager.loadGame(path);
        } catch (Exception e) {
            System.out.println("Cannot load game");
            return;
        }

        startGame(board);
    }

    public static void startGame(Board board) {
        boolean exit = false;
        int option;
        while (!exit) {
            System.out.println(board);
            option = InputManager.nextInt(gameMenu);
            switch (option) {
                case 1 -> {
                    makeMove(board);
                    int winnerBox = board.checkWinner();
                    if (winnerBox != 0) {
                        System.out.printf("Winner %d!\n", winnerBox);
                        exit = true;
                    }
                }
                case 2 -> {
                    String path = String.format("%s.data", board.name);
                    try {
                        System.out.println("Saving game");
                        GameManager.saveGame(path, board);
                        exit = true;
                    } catch (Exception e) {
                        System.out.println("Cannot save game");
                    }
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    public static void makeMove(Board board) {
        boolean exit = false;
        while (!exit) {
            int column = InputManager.nextInt("Put the column: ");

            if (!inclusiveBetween(0, board.width, column)) {
                System.out.println("Column out of range");
                continue;
            }

            try {
                board.makeMove(column);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            exit = true;
        }
    }
}