package ua.adeptius.spribe.test.secondtask;


public class Player {

    private String name;
    private double rating;

    public Player(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Player " + name + " with rating " + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (Double.compare(player.rating, rating) != 0) return false;
        return name != null ? name.equals(player.name) : player.name == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}
