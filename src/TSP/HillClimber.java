package TSP;

public class HillClimber {

    public static void main(String[] args) {
        // Create and add 100 cities
        for(int i = 0; i < 100; i++) {
            City city = new City();
            TourManager.addCity(city);
        }

        // Initialize intial solution
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();

        System.out.println("Initial solution distance: " + currentSolution.getDistance());

        // Set as current best
        Tour best = new Tour(currentSolution.getTour());


        for (int i = 0; i < 100; i++){
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

            // Decide if we should accept the neighbour and keep track of new best solution
            if (currentFitness > neighbourFitness) {
                currentSolution = new Tour(newSolution.getTour());
                best = new Tour(newSolution.getTour());
                System.out.println("Between solution distance:" + best.getDistance());
            }

        }
        System.out.println("Final solution distance: " + best.getDistance());
    }
}

