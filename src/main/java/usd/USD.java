package usd;
import coin.Coin;

public class USD extends Coin {
    static final double exchangeRate = 0.28;

    @Override
    public double getValue(){
        return exchangeRate;
    }
    @Override
    public double calculate(double amount){
        return amount * getValue();
    }
}
