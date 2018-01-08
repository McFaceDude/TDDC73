package example.samuel.tddc73_account_registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayoutMain = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutMain.setLayoutParams(params);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);


        AccountRegistration accountRegistration = new AccountRegistration(this);
        accountRegistration.addObserver(this);

        linearLayoutMain.addView(accountRegistration);
        setContentView(linearLayoutMain);
    }

    /**
     *The account information will be sent here when the account is created in the format ArrayList<Pair<String, String>>
     */
    @Override
    public void update(Observable observable, Object o) {
        //Handle the account information
    }
}
