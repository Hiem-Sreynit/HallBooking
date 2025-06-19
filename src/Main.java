import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static String style = "-+".repeat(30);
    static String[] bookingHistory = new String[100]; // simple array-based history
    static int historyCount = 0;
    private static String[][] hallA;
    private static String[][] hallB;
    private static String[][] hallC;
    static char choice;

    public static void main(String[] args) {

        displayHeading();
        int row = checkInputNumber("> Config total rows in hall: ");
        int seat = checkInputNumber("> Config total seats in hall: ");

        hallA = new String[row][seat];
        hallB = new String[row][seat];
        hallC = new String[row][seat];
        setDefault();


        char op;
        do {
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
                    booking();
                }
                case 'b' -> {
                    display();
                }
                case 'c' -> {
                    showtime();
                }
                case 'd' -> {
                    reboot();
                }
                case 'e' -> {
                    history();
                }
            }
        }while (op != 'f');

    }

    public static void displayHeading(){
        System.out.print("""
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
    private static void booking() {
        bookingHeader();
        System.out.print("> Please select showtime (A | B | C): ");
        choice = input.next().toUpperCase().charAt(0);
        String[][] selectedHall = null;
        String hallname = "";
        switch (choice) {
            case 'A' -> {
                selectedHall = hallA;
                hallname = "A";
            }
            case 'B' -> {
                selectedHall = hallB;
                hallname = "B";
            }
            case 'C' -> {
                selectedHall = hallC;
                hallname = "C";
            }
        }
        selectedShowtime();
        instruction();

        System.out.print("");
        Scanner scanner = new Scanner(System.in);
        System.out.print("> PLease select available seat: ");
        String userInput = scanner.nextLine();
        String[] seatsToBook = userInput.split(",");

        for (String s : seatsToBook) {
            String cleanSeat = s.trim();
            if (!cleanSeat.matches("[A-Za-z]-[0-9]+")) {
                System.out.println("Invalid seat format: " + cleanSeat + ". Please use A-1, B-2 format.");
                continue;
            }

            char rowChar = Character.toUpperCase(cleanSeat.charAt(0));
            int colNum = Integer.parseInt(cleanSeat.substring(cleanSeat.indexOf('-') + 1));

            int rowIndex = rowChar - 'A';
            int colIndex = colNum - 1;

            if (rowIndex >= 0 && rowIndex < selectedHall.length && colIndex >= 0 && colIndex < selectedHall[rowIndex].length) {
                if (selectedHall[rowIndex][colIndex].contains(":: AV")) {
                    selectedHall[rowIndex][colIndex] = "|" + rowChar + "-" + colNum + ":: BO|  ";
                } else {
                    System.out.println("Seat " + cleanSeat + " is already booked or not available.");
                }
            } else {
                System.out.println("Seat " + cleanSeat + " is out of bounds for this hall.");
            }
        }
        System.out.print("Enter customer Id: ");
        int id = input.nextInt();
        System.out.print("Are you sure want to book the seat(s) ?(Y/n): ");
        char ans = input.next().toUpperCase().charAt(0);

        System.out.println(style);
        for (int i = 0; i < seatsToBook.length; i++) {
            System.out.println("# [" + userInput + "] booked successfully!");
            break;
        }
        System.out.println(style);
        if (ans == 'Y') {
            StringBuilder bookedSeats = new StringBuilder();
            for (String s : seatsToBook) {
                bookedSeats.append(s.trim()).append(" ");
            }
            String record = LocalDate.now() + " | Showtime: " + choice + " | Customer ID: " + id + " | Seats: " + bookedSeats;
            if (historyCount < bookingHistory.length) {
                bookingHistory[historyCount++] = record;
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

    public static void selectedShowtime () {
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
    public static void instruction () {
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
        System.out.print(style);
        System.out.println("\n# Hall A");
        hallShow(hallA);
        System.out.println("\n# Hall B");
        hallShow(hallB);
        System.out.println("\n# Hall C");
        hallShow(hallC);
        System.out.println(style);

    }

    public static void hallShow(String[][] hall){
        for (String[] row : hall) {
            for (String seat : row) {
                System.out.print(seat);
            }
            System.out.println();
        }
    }
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
    public static void reboot () {
        setDefault();
        System.out.println(style);
        System.out.println("Rebooting successfully!");
        System.out.println(style);
    }
    public static void history() {
        System.out.println(style);
        System.out.println("# Booking History");
        if (historyCount == 0) {
            System.out.println("No bookings yet.");
        } else {
            for (int i = 0; i < historyCount; i++) {
                System.out.println((i + 1) + ". " + bookingHistory[i]);
            }
        }
        System.out.println(style);
    }



}