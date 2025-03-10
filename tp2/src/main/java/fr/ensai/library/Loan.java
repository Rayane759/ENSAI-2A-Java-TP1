public abstract class Loan {
    protected Student student;
    protected Item item;
    private String startDate;
    private String returnDate;

    public Loan(Student student, Item item, String startDate) {
        this.student = student;
        this.item = item;
        this.startDate = startDate;
        this.returnDate = null;
    }

    /**
     * Updates the return date of the item loaned.
     * 
     * @param returnDate The return date.
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Item " + item + " borrowed by " + student;
    }

    public Item getItem() {
        return item
    }
    
}