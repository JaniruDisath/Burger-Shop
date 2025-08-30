package org.example.service.burger;

public class BurgerNumber {

    public int getValidBurgerNumber(String burgerNumberString) {

        if (burgerNumberString.isEmpty()) {
            throw new IllegalArgumentException("Please Enter Burger Number");
        }

        int burgerNumber = 0;

        try {
            burgerNumber = Integer.parseInt(burgerNumberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number for Burger Quantity");
        }

        if (burgerNumber <= 0) {
            throw new IllegalArgumentException("Please enter a positive quantity");
        }
        return burgerNumber;
    }
}
