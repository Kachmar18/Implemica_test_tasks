import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {                                                                  // Class representing the graph structure
    private final Map<String, Integer> cityIndex;                              // matching city names with their indexes
    public final List<List<Region>> adjList; // List of nearby cities for storing neighbouring cities and connection costs

    public Graph(int numCities) {
        cityIndex = new HashMap<>();                                        // initialize the city name to index mapping
        adjList = new ArrayList<>(numCities);                            // create a list of nearby cities for each city
        for (int i = 0; i < numCities; i++) {
            adjList.add(new ArrayList<>());                            // initialize the list of neighbors for each city
        }
    }

    public void addCity(String name, int index) {
        cityIndex.put(name, index);                          // add the city name and its corresponding index to the map
    }

    public void addEdge(int fromIndex, int toIndex, int cost) {
        adjList.get(fromIndex).add(new Region(toIndex, cost));       // add an edge from one city to another with a cost
    }

    public int getCityIndex(String name) {
        return cityIndex.getOrDefault(name, -1); // get the index of a city by name, or return -1 if not found
    }

    public List<Region> getNeighbors(int cityIndex) {
        return adjList.get(cityIndex);                 // return the list of neighboring cities for the given city index
    }
}