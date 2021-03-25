package ru.oshkina.bank;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RyanAndMonicaJob implements Runnable {
    private final BankAccount account = new BankAccount();

    public void run() {
        int AMOUNT = 10;//снимать всегда будем эту сумму и не больше =)
        for (int x = 0; x < 5; x++) {
            makeWithdrawal(AMOUNT);
            if (account.getBalance() < 0) {
                System.out.println("Выход за допустимый лимит, баланс = " + account.getBalance());
            }
        }
    }

    /**
     * Выполянем снятие денег со счета
     * @param amount - сумма, которую хотим снять со счета
     */
    @SneakyThrows
    private void makeWithdrawal(int amount) {
        if (account.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + " собирается снять деньги, нужно " + amount + " рублей, баланс на момент проверки = " + account.getBalance());

            System.out.println(Thread.currentThread().getName() + " отвлекся/отвлеклась (на звонок/просмотор постов в интсте, на сон) z-z-z-z");
            Thread.sleep(500);

            System.out.println(Thread.currentThread().getName() + " вспомнил/вспомнила, что нужно снять все-таки деньги");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " успешно снял/сняла со счета " + amount + " рублей, баланс стал = " + account.getBalance());
        } else {
            System.out.println("Извините, денег для " + Thread.currentThread().getName()  + " нет =(");
        }
    }

}
