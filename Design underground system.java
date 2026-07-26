import java.util.*;

class UndergroundSystem {

    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    private Map<Integer, CheckIn> checkInMap;
    private Map<String, int[]> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn info = checkInMap.get(id);

        String route = info.station + "->" + stationName;
        int travelTime = t - info.time;

        routeMap.putIfAbsent(route, new int[2]);
        int[] data = routeMap.get(route);

        data[0] += travelTime;
        data[1]++;

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] data = routeMap.get(startStation + "->" + endStation);
        return (double) data[0] / data[1];
    }
}
