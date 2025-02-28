import java.util.Random;
/**
 * 
 *  This is a Java-based simulation that models the survival of four individuals (doctor, farmer, carpenter, and hunter) in a world affected by various disasters.
 * 
 * @author Adam
 * @since 2022 
 */
import java.util.Random;

// Main class for the Survival Simulation
public class SurvivalSimulation {
    // Inner class representing each person in the simulation
    static class Person {
                // Role of the person (doctor, farmer, carpenter, hunter)
        String role;
                // Health attribute starting at 10
        int health = 10;
                // Food attribute starting at 10
        int food = 10;
                // Shelter attribute starting at 10
        int shelter = 10;

                // Constructor to initialize the person's role
        Person(String role) {
            this.role = role;
        }

                // Check if the person is still alive (health and food > 0)
        boolean isAlive() {
            return health > 0 && food > 0;
        }

                // Display the current status of the person
        void displayStatus() {
            System.out.printf("%s : %d %d %d\n", role, health, food, shelter);
        }
    }

        // Array of people participating in the simulation
    static Person[] people = {
        new Person("doctor"),
        new Person("farmer"),
        new Person("carpenter"),
        new Person("hunter")
    };

        // Random generator for simulating events
    static Random random = new Random();
    static int days = 0;
        // Mode of the simulation: society or anarchy
    static String mode;

        // Main method to run the simulation
    public static void main(String[] args) {
        mode = args.length > 0 ? args[0] : "society";
        System.out.println("Running in " + mode + " mode ...\n");

        while (true) {
            days++;
            System.out.println("Day " + days + ": " + getDisaster());
            System.out.println("------------------------------");

            applySkills();
            decrementFood();

            applyDisasters();
            displayStatus();

            checkDeaths();

            if (allDead() || days >= 365) break;
        }

        System.out.println("\n" + days + " days");
    }

        // Method to randomly generate a disaster
    static String getDisaster() {
        String[] disasters = {"none", "hurricane", "famine", "disease", "wolves"};
        return disasters[random.nextInt(disasters.length)];
    }

        // Apply skills for each person based on their role and the simulation mode
    static void applySkills() {
        for (Person p : people) {
            if (!p.isAlive()) continue;

            switch (p.role) {
                case "doctor":
                    if (p.food == 1) p.food += 1;
                    else {
                        if (mode.equals("society"))
                            for (Person other : people) other.health = Math.min(other.health + 2, 10);
                        else
                            p.health = Math.min(p.health + 2, 10);
                    }
                    break;
                case "farmer":
                    if (days % 3 == 0) {
                        if (mode.equals("society"))
                            for (Person other : people) other.food = Math.min(other.food + 3, 10);
                        else
                            p.food = Math.min(p.food + 3, 10);
                    }
                    break;
                case "carpenter":
                    if (p.food == 1) p.food += 1;
                    else {
                        if (mode.equals("society"))
                            for (Person other : people) other.shelter = Math.min(other.shelter + 1, 10);
                        else
                            p.shelter = Math.min(p.shelter + 2, 10);
                    }
                    break;
                case "hunter":
                    if (random.nextInt(5) == 0) {
                        if (mode.equals("society")) {
                            // Hunter distributes food to all in society mode
                            for (Person other : people) {
                                other.food = Math.min(other.food + 2, 10);
                            }
                        } else {
                            // Hunter keeps food for themselves in anarchy mode
                            p.food = Math.min(p.food + 2, 10);
                        }
                    }
                    break;
            }
        }
    }

        // Decrease food by 1 for each person at the end of the day
    static void decrementFood() {
        for (Person p : people) {
            if (p.isAlive()) p.food--;
        }
    }

        // Apply the effects of disasters on each person
    static void applyDisasters() {
        String disaster = getDisaster();
        for (Person p : people) {
            if (!p.isAlive()) continue;

            switch (disaster) {
                case "hurricane":
                    if (p.shelter == 0) p.health -= 5;
                    else p.shelter = Math.max(p.shelter - 3, 0);
                    break;
                case "famine":
                    p.food = Math.max(p.food - 2, 0);
                    break;
                case "disease":
                    p.health = Math.max(p.health - 2, 0);
                    break;
                case "wolves":
                    if (p.role.equals("hunter")) p.health = Math.max(p.health - 1, 0);
                    else {
                        if (mode.equals("society")) {
                            Person hunter = findHunter();
                            if (hunter != null && hunter.health > 0) p.health = Math.max(p.health - 1, 0);
                            else p.health = Math.max(p.health - 3, 0);
                        } else {
                            p.health = Math.max(p.health - 3, 0);
                        }
                    }
                    break;
            }
        }
    }

        // Find the hunter in the group (if alive) to apply wolf protection rules
    static Person findHunter() {
        for (Person p : people) {
            if (p.role.equals("hunter") && p.isAlive()) return p;
        }
        return null;
    }

        // Display the status of all people
    static void displayStatus() {
        for (Person p : people) {
            if (p.isAlive()) p.displayStatus();
        }
    }

        // Check if any person has died (health or food = 0)
    static void checkDeaths() {
        for (Person p : people) {
            if (!p.isAlive()) System.out.println(p.role + " is dead");
        }
    }

        // Check if all people are dead to end the simulation
    static boolean allDead() {
        for (Person p : people) {
            if (p.isAlive()) return false;
        }
        return true;
    }
}
