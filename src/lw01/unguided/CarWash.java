package lw01.unguided;

public class CarWash extends WashService {

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int firstThreeDays = Math.min(days, 3);
        int extraDays = Math.max(days - 3, 0);

        return (firstThreeDays * 35000)
                + (extraDays * 25000)
                + 15000;
    }

    @Override
    public String label() {
        return "Car";
    }
}
