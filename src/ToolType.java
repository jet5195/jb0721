public enum ToolType {
    LADDER("Ladder", 1.99, true, true, false),
    CHAINSAW("Chainsaw", 1.49, true, false, true),
    JACKHAMMER("Jackhammer", 2.99, true, false, false);

    public final String label;
    public final double dailyCharge;
    public final boolean weekdayCharge;
    public final boolean weekendCharge;
    public final boolean holidayCharge;

    ToolType(String label, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.label = label;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
}