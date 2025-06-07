import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static String style = "-+".repeat(30);
    public static String history = "";
    private static String[][] hallA;
    private static String[][] hallB;
    private static String[][] hallC;

    public static void main(String[] args) {

        displayHeading();
        int row = checkInputNumber("> Config total rows in hall: ");
        int seat = checkInputNumber("> Config total seats in hall: ");
        history += LocalDate.now() + "";

        hallA = new String[row][seat];
        hallB = new String[row][seat];
        hallC = new String[row][seat];
        setDefault();
        System.out.println();

        char op;
        do {
            System.out.println();
            System.out.println("<A> Booking");
            System.out.println("<B> Hall");
            System.out.println("<C> Showtime");
            System.out.println("<D> Reboot Showtime");
            System.out.println("<E> History");
            System.out.println("<F> Exit");
            System.out.print("Please, Choose one option: ");
            op = input.next().toLowerCase().charAt(0);
            switch (op) {
                case 'a' -> {
                    booking(hallA);
                }
                case 'b' -> {
                    display();
                }
                case 'c' -> {
                    showtime();
                }
                case 'd' -> {
                    reboot(hallA);
                }
            }
        }while (op != 'f');

    }

    public static void displayHeading(){
        System.out.println("""
                 ██████ ███████ ████████  █████  ██████      ██   ██  █████  ██      ██     \s
                ██      ██         ██    ██   ██ ██   ██     ██   ██ ██   ██ ██      ██     \s
                ██      ███████    ██    ███████ ██   ██     ███████ ███████ ██      ██     \s
                ██           ██    ██    ██   ██ ██   ██     ██   ██ ██   ██ ██      ██     \s
                 ██████ ███████    ██    ██   ██ ██████      ██   ██ ██   ██ ███████ ███████\s
                                                                                            \s
                                                                                            \s
                """);
    }

    public static int checkInputNumber (String message) {
        do {
            try {
                System.out.print(message);
                return Integer.parseInt(input.nextLine());
            }catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input!");
                System.out.println("Please, input number.");
            }
        }while (true);
    }
    private static void booking(String[][] hall) {
        bookingHeader();
        System.out.print("> Please select showtime (A | B | C): ");
        char choice = input.next().toUpperCase().charAt(0);
        selectedShowtime(choice);
        instruction(choice);

        if (choice == 'A') {
            System.out.print("> PLease select available seat: ");
            String selectedSeat = input.nextLine();
            input.nextLine();
            String[] seats = selectedSeat.split(",");

            for (String s : seats) {
                for (int i = 0; i < hallA.length; i++) {
                    for (int j = 0; j < hallA[i].length; j++) {
                        if (hallA[i][j].contains(s)) {
                            hallA[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: BO|  ";
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void setDefault() {
        for (int i = 0; i < hallA.length; i++) {
            for (int j = 0; j < hallA[i].length; j++) {
                hallA[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: AV|  ";
                hallB[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: AV|  ";
                hallC[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: AV|  ";
            }
            System.out.println();
        }
    }

    public static void selectedShowtime (char choice) {
        switch (choice) {
            case 'A' -> {
                System.out.println("# Hall A");
                for (int i = 0; i < hallA.length; i++) {
                    for (int j = 0; j < hallA[i].length; j++) {
                        System.out.print(hallA[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: AV|  ");
                    }
                    System.out.println();
                }
            }
            case 'B' -> {
                System.out.println("# Hall B");
                for (int i = 0; i < hallB.length; i++) {
                    for (int j = 0; j < hallB[i].length; j++) {
                        System.out.print(hallB[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: AV|  ");
                    }
                    System.out.println();
                }
            }
            case 'C' -> {
                System.out.println("# Hall C");
                for (int i = 0; i < hallC.length; i++) {
                    for (int j = 0; j < hallC[i].length; j++) {
                        System.out.print(hallC[i][j] = "|" + (char) ('A' + i) + "-" + (j + 1) + ":: AV|  ");
                    }
                    System.out.println();
                }
            }
        }
    }
    public static void instruction (char choice) {
        System.out.println(style);
        System.out.println("# Instruction");
        if (choice == 'A'){
            System.out.println("# Single: A-1");
            System.out.println("# Multiple (Separate by comma): A-1,A-2");
        }else if (choice == 'B'){
            System.out.println("# Single: B-1");
            System.out.println("# Multiple (Separate by comma): B-1,B-2");
        }else if (choice == 'C') {
            System.out.println("# Single: C-1");
            System.out.println("# Multiple (Separate by comma): C-1,C-2");

        }
    }
    public static void display () {
        System.out.println("\n# Hall A");
        hallShow(hallA);
        System.out.println("\n# Hall B");
        hallShow(hallB);
        System.out.println("\n# Hall C");
        hallShow(hallC);

    }

    public static void hallShow(String[][] hall){
        for (String[] row : hall) {
            for (String seat : row) {
                System.out.print(seat);
            }
            System.out.println();
        }
    }
    /*public static void hall (String[][] hall) {
        for (int i = 0; i < hall.length; i++) {
            for (int j = 0; j < hall[i].length; j++) {
                System.out.print(hall[i][j]);
            }
            System.out.println();
        }
    }*/
    public static void bookingHeader () {
        System.out.println();
        System.out.println(style);
        System.out.println("> Start booking process");
        System.out.println(style);
        System.out.println("# Showtime Information");
        System.out.println("# A) Morning (10:00AM - 12:30PM)");
        System.out.println("# B) Afternoon (03:00PM - 05:30PM)");
        System.out.println("# C) Night (07:00PM - 09:30PM)");
        System.out.println(style);
    }
    public static void showtime () {
        System.out.println(style);
        System.out.println("# Daily showtime of CSTAD hall:");
        System.out.println("# A) Morning (10:00AM - 12:30PM)");
        System.out.println("# B) Afternoon (03:00PM - 05:30PM)");
        System.out.println("# C) Night (07:00PM - 09:30PM)");
        System.out.println(style);
    }
    public static void reboot (String[][] hall) {
        for (int i = 0; i < hall.length; i++) {
            for (int j = 0; j < hall.length; j++) {
                hall[i][j] = null;
            }
        }
    }
    public static void history (int[][] his) {

    }
}