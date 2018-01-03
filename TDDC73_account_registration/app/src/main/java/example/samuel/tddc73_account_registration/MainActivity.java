package example.samuel.tddc73_account_registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayoutMain = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutMain.setLayoutParams(params);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);

        AccountRegistration accountRegistration = new AccountRegistration(this);
        linearLayoutMain.addView(accountRegistration);
        setContentView(linearLayoutMain);
    }
}
