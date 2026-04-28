package stroganov.dmitriy.ATM;

import java.util.*;

public class MoneyStorage {

    private Map<Denomination, List<Banknote>> money;

    private Puttable puttable;

    private Gettable gettable;

    public MoneyStorage(Puttable payable, Gettable gettable) {
        this.money = new HashMap<>();
        this.puttable = payable;
        this.gettable = gettable;
    }

    public MoneyStorage() {
        this.money = new HashMap<>();
    }

    public void put(List<Banknote> banknotes) {
        puttable.put(banknotes, money);
    }

    public List<Banknote> get(int moneySum) {
        return gettable.get(moneySum, money);
    }
}
