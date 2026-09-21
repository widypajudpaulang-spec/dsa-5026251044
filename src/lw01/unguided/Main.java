package lw01.unguided;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("rentals.txt");
        Scanner scanner = new Scanner(input);

        int total = scanner.nextInt();

        Rental[] rentals = new Rental[total];

        for (int i = 0; i < total; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                LaptopRental rental = new LaptopRental(id, days);
                rentals[i] = new RentalWithUnits(rental, units);
            } else {
                ProjectorRental rental = new ProjectorRental(id, days);
                rentals[i] = new RentalWithUnits(rental, units);
            }
        }

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }

    private static class RentalWithUnits extends Rental {
        private Rental rental;
        private int units;

        public RentalWithUnits(Rental rental, int units) {
            super(rental.getId(), rental.getDays());

            if (units <= 0) {
                throw new IllegalArgumentException("Units must be positive");
            }

            this.rental = rental;
            this.units = units;
        }

        @Override
        public int calculateCharge() {
            return rental.calculateCharge(units);
        }

        @Override
        public String label() {
            return rental.label();
        }
    }
}