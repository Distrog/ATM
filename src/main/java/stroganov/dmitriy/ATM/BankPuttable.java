package stroganov.dmitriy.ATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankPuttable implements Puttable {

    @Override
    public void put(List<Banknote> banknotes, Map<Denomination, List<Banknote>> money) {
        banknotes.forEach(b -> {
            if (b == null) {
                return;
            }
            Denomination denomination = b.getDenomination();
            if (money.containsKey(denomination)) {
                money.get(denomination).add(b);
            } else {
                List<Banknote> addedBanknoteList = new ArrayList<>();
                addedBanknoteList.add(b);
                money.put(denomination, addedBanknoteList);
            }
        });
    }
}
