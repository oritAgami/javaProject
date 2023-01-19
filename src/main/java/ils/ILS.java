package ils;
import coin.Coin;
public class ILS extends Coin {
    static final double exchangeRate = 3.52;

    @Override
    public double getValue() {
        return exchangeRate;
    }
    @Override
    public double calculate(double amount) {
        return amount * getValue();
    }

}