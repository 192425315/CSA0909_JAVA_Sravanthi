
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Team {
    final String name;
    final int points;
    final int goalDiff;

    Team(String name, int points, int goalDiff) {
        this.name = name;
        this.points = points;
        this.goalDiff = goalDiff;
    }

    @Override
    public String toString() {
        return name + "(" + points + "," + goalDiff + ")";
    }
}

class Leaderboard<T> {
    private final List<T> items = new ArrayList<>();
    private final Comparator<? super T> order;

    Leaderboard(Comparator<? super T> order) {
        this.order = order;
    }

    void add(T item) {
        items.add(item);
    }

    // Accepts a list of T or a list of its subtypes.
    void addAll(List<? extends T> newItems) {
        items.addAll(newItems);
    }

    // Returns a sorted copy without changing the original list.
    List<T> ranking() {
        List<T> result = new ArrayList<>(items);
        result.sort(order);
        return result;
    }
}

public class Main {
    public static void main(String[] args) {

        // Higher points, higher goal difference, then name A-Z.
        Comparator<Team> byRank =
            Comparator.comparingInt((Team t) -> t.points)
                .reversed()
                .thenComparing(
                    Comparator.comparingInt(
                        (Team t) -> t.goalDiff
                    ).reversed()
                )
                .thenComparing(t -> t.name);

        Leaderboard<Team> board =
            new Leaderboard<>(byRank);

        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        System.out.println("Sports Leaderboard:");
        System.out.println(board.ranking());

        // Additional test with ties in points and goal difference.
        List<Team> extraTeams = new ArrayList<>();
        extraTeams.add(new Team("Lions", 12, 5));
        extraTeams.add(new Team("Tigers", 12, 5));
        extraTeams.add(new Team("Wolves", 12, 3));

        board.addAll(extraTeams);

        System.out.println("\nLeaderboard after adding extra teams:");
        System.out.println(board.ranking());

        // Demonstrate the risk of subtraction in a comparator.
        Comparator<Integer> faulty =
            (a, b) -> a - b;

        int a = Integer.MAX_VALUE;
        int b = 0;
        int c = Integer.MIN_VALUE;

        System.out.println("\nFaulty Comparator Test:");
        System.out.println("Compare MAX_VALUE with 0: "
            + faulty.compare(a, b));
        System.out.println("Compare 0 with MIN_VALUE: "
            + faulty.compare(b, c));
        System.out.println("Compare MAX_VALUE with MIN_VALUE: "
            + faulty.compare(a, c));

        System.out.println("\nCorrect Comparator Test:");
        Comparator<Integer> correct =
            Integer::compare;

        System.out.println("Compare MAX_VALUE with MIN_VALUE: "
            + correct.compare(a, c));
    }
}
