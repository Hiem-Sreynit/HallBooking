import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static int row;
    public static int seat;
    public static String style = "-+".repeat(30);
    public static String history = "";

    public static void main(String[] args) {

        row = checkInputNumber("> Config total rows in hall: ");
        seat = checkInputNumber("> Config total seats in hall: ");
        history += LocalDate.now() + "";

        String[][] hall = new String[row][seat];

        char op;
        do {
            System.out.println();
            System.out.println("<A> Booking");
            System.out.println("<B> Hall");
            System.out.println("<C> Showtime");
            System.out.println("<D> Reboot Showtime");
            System.out.println("<E> History");
            System.out.println("<F> Exit");System.out.print("Please, Choose one option: ");
            op = input.next().toLowerCase().charAt(0);
            switch (op) {
                case 'a' -> {
                    booking(hall);
                }
                case 'b' -> {
                    halls(hall);
                }
                case 'c' -> {
                    showtime();
                }
            }
        }while (op != 'f');

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
        selectedShowtime(choice, hall);
        instruction(choice);

        /*System.out.print("> PLease select available seat: ");
        String seatChoice = input.nextLine();
        input.nextLine();

        System.out.print("> Please enter student id: ");
        int id = input.nextInt();

        System.out.print("Are you sure to book? (Y/n): ");
        char decision = input.next().toUpperCase().charAt(0);
        if (decision == 'Y') {
            System.out.println(style);
            System.out.println("# [" + seatChoice + "] booked successfully!");
            System.out.println(style);
        } else {

        }*/
        System.out.print("> Please select available seat(s): ");
        input.nextLine(); // Consume leftover newline
        String seatChoice = input.nextLine(); // e.g. "A-1,A-2"
        String[] selectedSeats = seatChoice.split(",");

        System.out.print("> Please enter student id: ");
        int id = input.nextInt();

        System.out.print("Are you sure to book? (Y/n): ");
        char decision = input.next().toUpperCase().charAt(0);

        if (decision == 'Y') {
            boolean allAvailable = true;

            // Check seat availability
            for (String seat : selectedSeats) {
                seat = seat.trim();
                int rowIndex = seat.charAt(0) - 'A';
                int seatIndex = Integer.parseInt(seat.split("-")[1]) - 1;

                if (hall[rowIndex][seatIndex] != null) {
                    System.out.println("# Seat " + seat + " is already booked!");
                    allAvailable = false;
                }
            }

            if (allAvailable) {
                // Book the seats
                for (String seat : selectedSeats) {
                    seat = seat.trim();
                    int rowIndex = seat.charAt(0) - 'A';
                    int seatIndex = Integer.parseInt(seat.split("-")[1]) - 1;
                    hall[rowIndex][seatIndex] = "BK"; // Mark as booked
                }

                System.out.println(style);
                System.out.println("# Seats " + String.join(", ", selectedSeats) + " booked successfully!");
                System.out.println(style);
            } else {
                System.out.println("# Booking failed due to already booked seats.");
            }
        }


    }
    public static void selectedShowtime (char choice, String[][] hallShow) {
        switch (choice) {
            case 'A' -> {
                System.out.println("# Hall A");
                rawHall(hallShow);
            }
            case 'B' -> {
                System.out.println("# Hall B");
                rawHall(hallShow);
            }
            case 'C' -> {
                System.out.println("# Hall C");
                rawHall(hallShow);
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
    public static void halls (String[][] hall) {
        for (int k = 0; k < 3; k++) {
            char hallType = (char) ('A' + k);
            System.out.println("\n# Hall " + hallType);
            rawHall(hall);
        }

    }
    public static void rawHall (String[][] hall) {
        for (int i = 0; i < hall.length; i++) {
            char row = (char) ('A' + i);
            for (int j = 0; j < hall.length; j++) {
                System.out.print("|" + row + "-" + (j + 1) + ":: AV|  ");
            }
            System.out.println();
        }
    }
    public static void bookingHeader () {
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
                hall[row][seat] = null;
            }
        }
    }
    public static void history (int[][] his) {
        
    }
}