package john.is.hot.backend;

public class Friends {
    public String name;
    public Double bill = 00.00;



    public Friends(String name) {
        this.name = name;
    }

    void addBill(double price){
        bill+=price;
        bill = Math.floor(bill * 100) / 100;
    }

    Double getBill(){
        return bill;
    }
}
