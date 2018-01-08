package samuel.tddc73_password_strength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayoutMain = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutMain.setLayoutParams(params);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);

        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter(this);
        linearLayoutMain.addView(passwordStrengthMeter);
        setContentView(linearLayoutMain);
    }
}
