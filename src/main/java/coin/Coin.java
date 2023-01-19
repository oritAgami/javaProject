package coin;
import interfaces.ICalculate;
public abstract class Coin implements ICalculate{
    public abstract double getValue();
    public abstract double calculate(double value);


}
