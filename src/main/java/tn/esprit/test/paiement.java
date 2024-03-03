package tn.esprit.test;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;

public class paiement {

    public static void main(String[] args) {
        // Set your secret key here
        Stripe.apiKey = "sk_test_51Opa2DF0Qy9fQwwPNstG4UlPkc5crZ7biWlPecNH2TWInFRlz987gMGbseHxQiRVHmk6V0b91UXQsJIONpwVPYtH00qCLoux1d";

        try {
        // Retrieve your account information
            Account account = Account.retrieve();
            System.out.println("Account ID: " + account.getId());
        // Print other account information as needed
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
