import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        String filename = "task2_input_data.txt";                         // name of the input file containing test data

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int s = Integer.parseInt(reader.readLine().trim());                                  // number of test cases

            for (int t = 0; t < s; t++) {
                int n = Integer.parseInt(reader.readLine().trim());         // number of cities in the current test case
                Graph graph = new Graph(n);                                        // create a new graph with 'n' cities

                for (int i = 0; i < n; i++) {
                    String cityName = reader.readLine().trim();                             // read the name of the city
                    graph.addCity(cityName, i);                              // add the city to the graph with its index

                    int p = Integer.parseInt(reader.readLine().trim());                  // number of neighboring cities
                    for (int j = 0; j < p; j++) {
                        String[] edgeInfo = reader.readLine().trim().split(" ");
                        int neighborIndex = Integer.parseInt(edgeInfo[0]) - 1;
                        int cost = Integer.parseInt(edgeInfo[1]);          // cost of connection to the neighboring city
                        graph.addEdge(i, neighborIndex, cost);                              // add the edge to the graph
                    }
                }

                int r = Integer.parseInt(reader.readLine().trim());                      // number of paths to calculate
                for (int i = 0; i < r; i++) {
                    String[] pathInfo = reader.readLine().trim().split(" ");
                    String sourceCity = pathInfo[0];                                                 // source city name
                    String destinationCity = pathInfo[1];                                       // destination city name

                    int sourceIndex = graph.getCityIndex(sourceCity);                 // get the index of the source city
                    int destinationIndex = graph.getCityIndex(destinationCity); // get the index of the destination city

                    if (sourceIndex == -1 || destinationIndex == -1) {
                        System.out.println("Path not found");               // case where one or both cities are missing
                    } else {
                        int cost = dijkstraMethod(graph, sourceIndex, destinationIndex);  // calculate the shortest path cost
                        System.out.println(cost != -1 ? cost : "Path not found");
                    }
                }

                if (t < s - 1) reader.readLine();                                   // skip the blank line between tests
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());                 // case of file read errors
        } catch (NumberFormatException e) {
            System.err.println("Error parsing a number: " + e.getMessage());             // case of number format errors
        }
    }

    public static int dijkstraMethod(Graph graph, int source, int destination) { //Dijkstra's algorithm to find the shortest path
        int[] minCost = new int[graph.adjList.size()];                   // array to store the minimum cost to each city
        Arrays.fill(minCost, Integer.MAX_VALUE);                       // initialize all costs to maximum possible value
        minCost[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // priority queue to select city with the smallest cost
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();                             // get the city with the smallest cost from the queue
            int currentIndex = current[0];                                                  // index of the current city
            int currentCost = current[1];                                              // cost to reach the current city

            if (currentIndex == destination) {
                return currentCost;                                // return the cost if the destination city is reached
            }

            if (currentCost > minCost[currentIndex]) {
                continue;                                       // skip if a shorter path to this city was already found
            }

            for (Region edge : graph.getNeighbors(currentIndex)) {     // loop through the neighbors of the current city
                int newCost = currentCost + edge.cost;               // calculate the cost to reach the neighboring city
                if (newCost < minCost[edge.destination]) {                                    // if this path is shorter
                    minCost[edge.destination] = newCost;                                      // update the minimum cost
                    pq.add(new int[]{edge.destination, newCost});              // add the neighbor to the priority queue
                }
            }
        }
        return -1;                                                                      // return -1 if no path is found
    }
}