import java.util.Random;
import java.util.Scanner;

public class Lab10PartB {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Q1();
        Q2();
        Q3();
        Q4();
        scan.close();
    }

    public static void Q1() {
        while (true) {
            System.out.println("Pick a shape: square, rectangle, circle (or 'q' to quit)");
            String input = scan.nextLine();
            double a, b, r;
            // initiallised variables at the start and used a swicth statement
            switch (input) {
                case "q":
                    return;
                case "square":
                    System.out.println("Enter the length of side a: ");
                    a = Double.parseDouble(scan.nextLine());
                    System.out.println("The perimeter of the square is: " + a * 4);
                    System.out.println("The area of the square is: " + a * a);
                    break;

                case "rectangle":
                    System.out.println("Enter the length of side a: ");
                    a = Double.parseDouble(scan.nextLine());
                    System.out.println("Enter the length of side b: ");
                    b = Double.parseDouble(scan.nextLine());
                    System.out.println("The perimeter of the rectangle is: " + (2 * a + 2 * b));
                    System.out.println("The area of the rectangle is: " + (a * b));
                    break;

                case "circle":
                    System.out.println("Enter the radius: ");
                    r = Double.parseDouble(scan.nextLine());
                    System.out.println("The circumference of the circle is: " + (Math.PI * r * 2));
                    System.out.println("The area of the circle is: " + (Math.PI * r * r));
                    break;
            }
        }
    }

    public static void Q2() {
        System.out.println("Q2: Enter the current day (1-31): ");
        int num = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the current month: (1-12)");
        int num2 = Integer.parseInt(scan.nextLine());
        // creates a suffix variable to see if "st", "nd", "rd", or "th" is used
        String suffix;
        int numLastDigit = num % 10;
        if (num >= 1 && num <= 31) {
            if (numLastDigit == 1 && num != 11) {
                suffix = "st";
            } else if (numLastDigit == 2 && num != 12) {
                suffix = "nd";
            } else if (numLastDigit == 3 && num != 13) {
                suffix = "rd";
            } else {
                suffix = "th";
            }
            System.out.print("You selected " + num + suffix + " of ");
        } else {
            System.out.println("Invalid Day");
        }
        // creating an array and getting the number put in -1
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        if (num2 >= 1 && num2 <= 12) {
            System.out.println(months[num2 - 1]);
        } else {
            System.out.println("Invalid month");
        }
    }

    public static void Q3() {
        System.out.println("Q3: Enter how many numbers you want to check for primality: ");
        int n = Integer.parseInt(scan.nextLine());
        int counter = 0;
        boolean check = true;
        // makes sure the number is greater than 1 and then starting at w and building up every number gets divided by all numbers 2 until 1 less than itself it it doesnt get divided it adds to the count 
        if (n > 1) {
            for (int i = 2; i < n; i++) {
                check=true;
                for (int j = 2; j < i; j++) {
                    //System.out.println(i + " % " + j);
                    if (i % j == 0) {
                        check = false;
                    }
                }
                if(check){
                    counter++;
                }
            }
        }
        
        System.out.println("There are: " + counter + " primes between 0 and " + n);
    }

    public static void Q4() {
        Random rng = new Random();

        String next;
        System.out.println(
                "Q4: Let's play a game. Type \"A\" to attack, \"B\" to buff your next attack. Kill the enemy to win!");
        System.out.println(
                "Q4: You must roll higher than the enemy armor class (12) to hit. Roll 20 for a critical hit!");
        System.out.println("Q4: Your damage is 2-16 (2d8)");

        int enemyHP = 100;
        int a = 0;

        boolean check = false;
        while (true) {

            boolean doAttack = false;
            boolean check2 = false;
            while (!check2) {
                next = scan.nextLine();
                check2 = true;
                switch (next) {
                    case "A", "a":
                        doAttack = true;
                        break;
                    case "B", "b":
                        check = true;
                        System.out.println("Buffing! +5 to your next attack roll and damage");
                        break;
                    default:
                        System.out.println("Invalid input");
                        check2 = false;
                }
            }

            if (doAttack) {
                a++;
                int attackRoll = rng.nextInt(20) + 1;
                int damage = 0;
                System.out.print("You rolled: " + attackRoll);
                if (check) {
                    attackRoll += 5;
                    System.out.print(" + 5 (buff active)\n");
                } else {
                    System.out.println();
                }
                if (attackRoll >= 12) {
                    damage = rng.nextInt(8) + 1;
                    damage += rng.nextInt(8) + 1;
                    if (check) {
                        damage += 5;
                    }
                    if (attackRoll == 20 || (check && attackRoll == 20 + 5)) {
                        damage *= 2;
                        System.out.print("Critical hit! ");
                    }
                    System.out.print("You dealt " + damage + " damage");
                    if (check) {
                        System.out.print(" (buffed attack)");
                    }
                    enemyHP -= damage;
                    System.out.println("\nEnemy HP: " + Math.max(0, enemyHP));

                } else {
                    System.out.println("Miss");
                }

                check = false;
                if (enemyHP <= 0) {
                    System.out.println("Enemy died in " + a + " turns");
                    scan.close();
                    return;
                }
            }

        }
    }
}
