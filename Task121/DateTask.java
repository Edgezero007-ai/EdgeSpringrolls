package Task121;

public class DateTask {
    // Private instance variables for encapsulation
    private byte day;
    private byte month;
    private short year;

    // No-args constructor initializing to default date
    public DateTask() {
        this(1, 1, 1); 
    }

    // Constructor that takes 3 arguments
    public DateTask(int m, int d, int y) {
        if (valid(d, m, y)) {
            this.day = (byte) d;
            this.month = (byte) m;
            this.year = (short) y;
        } else {
            this.day = 0;
            this.month = 0;
            this.year = 0;
        }
    }

    // Methods
    @Override
    public String toString() {
        return month + "/" + day + "/" + year; // Formatting output
    }

    public void setDate(int m, int d, int y) {
        if (valid(d, m, y)) {
            day = (byte) d;
            year = (short) y;
            month = (byte) m;
        } else {
            day = 0;
            year = 0;
            month = 0;
        }
    }

    public static void leapYears() {
        for (int i = 1980; i <= 2023; i += 4) {
            if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)) {
                System.out.println("The year " + i + " is a leap year");
            }
        }
    }

    // Getter methods
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // Setter methods with validation
    public void setDay(int day) {
        if (valid(day, month, year)) {
            this.day = (byte) day;
        } else {
            this.day = 0;
        }
    }

    public void setMonth(int month) {
        if (valid(day, month, year)) {
            this.month = (byte) month;
        } else {
            this.month = 0;
        }
    }

    public void setYear(int year) {
        if (valid(day, month, year)) {
            this.year = (short) year;
        } else {
            this.year = 0;
        }
    }

    private boolean valid(int day, int month, int year) {
        if (day > 31 || day < 1 || month > 12 || month < 1 || year < 1) {
            System.out.println("Attempting to create a non-valid date " + month + "/" + day + "/" + year);
            return false;
        }
        switch (month) {
            case 4, 6, 9, 11 -> { return day <= 30; }
            case 2 -> { return day <= 28 || (day == 29 && year % 4 == 0); }
        }
        return true;
    }
}
