package model.DAOs.Overview;

public class DAOOverview {

    

    


    

    
    private int activeBooks;
    private int borrowedBooks;
    private int overdueBooks;
    private int activePatrons;

    public int getActiveBooks() {
        return activeBooks;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getOverdueBooks() {
        return overdueBooks;
    }

    public int getActivePatrons() {
        return activePatrons;
    }

    public DAOOverview(int activeBooks, int borrowedBooks, int overdueBooks, int activePatrons) {
        this.activeBooks = activeBooks;
        this.borrowedBooks = borrowedBooks;
        this.overdueBooks = overdueBooks;
        this.activePatrons = activePatrons;
    }
    

    private String month;
    private String year;
}
