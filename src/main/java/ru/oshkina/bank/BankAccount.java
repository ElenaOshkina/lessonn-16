package ru.oshkina.bank;


import lombok.Data;

@Data
class BankAccount {
    private int balance = 50;

    /**
     * Снять со счета деньги
     * @param amount - сумма для снятия
     */
    public void withdraw(int amount) {
        balance = balance - amount;
    }
}
