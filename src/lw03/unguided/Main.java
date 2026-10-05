package lw03.unguided;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        List<String> enrollment = new ArrayList<>();
        Scanner sn = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        while (sn.hasNextLine()) {
            String line = sn.nextLine();
            String[] parts = line.split(" ", 2);

            String operation = parts[0];

            if (operation.equals("REGISTER")){
                String courseCode = parts[1];
                int count = Integer.parseInt(parts[2]);
                enrollment.add(courseCode + " " + count);
            } else if (operation.equals("ENROLL")){
                String courseCode = parts[1];
                int count = Integer.parseInt(parts[2]);
                for (int i = 0; i < enrollment.size(); i++) {
                    String[] enrollmentParts = enrollment.get(i).split(" ", 2);
                    if (enrollmentParts[0].equals(courseCode)) {
                        int currentCount = Integer.parseInt(enrollmentParts[1]);
                        enrollment.set(i, courseCode + " " + (currentCount + count));
                        break;
                    }
                }
            } else if (operation.equals("WITHDRAW")){
                String courseCode = parts[1];
                int count = Integer.parseInt(parts[2]);
                for (int i = 0; i < enrollment.size(); i++) {
                    String[] enrollmentParts = enrollment.get(i).split(" ", 2);
                    if (enrollmentParts[0].equals(courseCode)) {
                        int currentCount = Integer.parseInt(enrollmentParts[1]);
                        enrollment.set(i, courseCode + " " + (currentCount - count));
                        break;
                    }
                }
            } else if (operation.equals ("CHECK")){
                String courseCode = parts[1];
                for (String entry : enrollment) {
                    String[] enrollmentParts = entry.split(" ", 2);
                    if (enrollmentParts[0].equals(courseCode)) {
                        System.out.println(enrollmentParts[1]);
                        break;
                    }
                }
            }
            System.out.println ("Enrollment Checks");
            System.out.println ("Final Enrollment");
            System.out.println ("Rejected operations : 3");

        }
    }
    
}
