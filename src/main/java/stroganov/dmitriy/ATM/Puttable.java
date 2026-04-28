package stroganov.dmitriy.ATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Puttable {

    void put(List<Banknote> banknotes, Map<Denomination, List<Banknote>> money);
}
