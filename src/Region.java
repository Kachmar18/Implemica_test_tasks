class Region {                // Class representing a connection (edge) from one city to another with an associated cost
    int destination, cost;                                          // destination city index and cost of the connection

    public Region(int destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }
}
