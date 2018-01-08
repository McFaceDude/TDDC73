package example.samuel.tddc73_account_registration;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Send the account information to the observers
 */

public class CustomObservable extends Observable {
    public void sendAccount(ArrayList accountInfo)
    {
        setChanged();
        notifyObservers(accountInfo);
    }
}
