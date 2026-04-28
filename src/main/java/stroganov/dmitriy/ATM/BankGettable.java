package stroganov.dmitriy.ATM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankGettable implements Gettable {
    @Override
    public List<Banknote> get(int moneySum, Map<Denomination, List<Banknote>> money) {
        if (moneySum <= 0) {
            throw new MoneyStorageException(
                    String.format("Передана отрицательная сумма %d", moneySum)
            );
        }

        List<Banknote> result = new ArrayList<>();

        int allMoney = money.values()
                .stream()
                .flatMap(v -> v.stream())
                .mapToInt(v -> v.getDenomination().getNominal())
                .sum();

        if (moneySum > allMoney) {
            throw new MoneyStorageException(
                    String.format("В хранилище банкомата недостаточно средств,всего %d рублей", allMoney)
            );
        }

        List<Denomination> denominations = money.keySet().stream()
                .sorted((k1, k2) -> Integer.compare(k2.getNominal(), k1.getNominal()))
                .toList();

        Map<Denomination, List<Banknote>> plannedRemoval = new HashMap<>();

        for (Denomination denomination : denominations) {
            moneySum = calculateCountBanknoteWithNominal(moneySum, denomination, result, money, plannedRemoval);
            if (moneySum == 0) {
                break;
            }
        }

        if (moneySum != 0) {
            throw new MoneyStorageException(
                    String.format("Невозможно выдать сумму %d рублей доступными купюрами", moneySum)
            );
        } else {
            plannedRemoval.keySet().forEach(denomination -> {
                money.get(denomination).removeAll(plannedRemoval.get(denomination));
            });
        }
        return result;
    }

    private int calculateCountBanknoteWithNominal(int moneySum, Denomination denomination, List<Banknote> result, Map<Denomination, List<Banknote>> money, Map<Denomination, List<Banknote>> plannedRemoval) {

        List<Banknote> banknotes = money.get(denomination);

        if (banknotes == null || banknotes.isEmpty()) {
            return moneySum;
        }

        int nominal = denomination.getNominal();

        int maxCount = moneySum / nominal;
        int availableCount = banknotes.size();
        int takeCount = Math.min(maxCount, availableCount);

        plannedRemoval.put(denomination, new ArrayList<>());

        for (int i = 0; i < takeCount; i++) {
            Banknote addedBanknote = banknotes.get(banknotes.size() - i);
            result.add(addedBanknote);
            plannedRemoval.get(denomination).add(addedBanknote);
        }

        return moneySum - takeCount * nominal;
    }
}
