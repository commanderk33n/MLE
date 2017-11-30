package TSP;

public class SimulatedAnnealing {

    // Calculate the acceptance probability
    public static double acceptanceProbability(int fitness, int newFitness, double temperature) {
        // If the new solution is better, accept it
        if (newFitness < fitness) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        System.out.println("Probability: " + Math.exp((fitness - newFitness) / temperature));
        return Math.exp((fitness - newFitness) / temperature);
    }

    public static void main(String[] args) {
        // Create and add 100 cities
        for(int i = 0; i < 100; i++) {
            City city = new City();
            TourManager.addCity(city);
        }

        // Set initial temp
        double temp = 10000;

        // Cooling rate
        double coolingRate = 0.003;

        // Initialize intial solution
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();

        System.out.println("Initial solution distance: " + currentSolution.getDistance());

        // Set as current best
        Tour best = new Tour(currentSolution.getTour());

        // Loop until system has cooled
        while (temp > 1) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            // Get a random positions in the tour
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());

            // Get the cities at selected positions in the tour
            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);

            // Get fitness of solutions
            int currentFitness = currentSolution.getDistance();
            int neighbourFitness = newSolution.getDistance();

            if (acceptanceProbability(currentFitness, neighbourFitness, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            // Keep track of the best solution found
            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
                System.out.println("Between solution distance:" + best.getDistance());
            }

            // Cooldown
            temp *= 1-coolingRate;
            System.out.println("Temperatur:" + temp);
        }

        System.out.println("Final solution distance: " + best.getDistance());
    }
}