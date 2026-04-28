package stroganov.dmitriy.ATM;

import java.util.List;

public class ATM {

    private MoneyStorage moneyStorage;

    public ATM(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    public void putMoney(List<Banknote> banknotes){
        moneyStorage.put(banknotes);
    }

    public List<Banknote> getMoney(Integer money){
        return moneyStorage.get(money);
    }

}
