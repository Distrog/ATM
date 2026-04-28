package stroganov.dmitriy.ATM;

import java.util.List;
import java.util.Map;

public interface Gettable {

    List<Banknote> get(int moneySum, Map<Denomination, List<Banknote>> money);
}
