/**
 * Created by User on 07.03.2017.
 */
public class Cards {
    long phone;



   long barcode;

    public Cards(){}

    public Cards(long phone, long barcode) {
        this.phone = phone;
        this.barcode = barcode;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String toString() {
        return "ph   "  + phone + " barcode  "+ barcode;
    }

}
