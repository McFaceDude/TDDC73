package samuel.tddc73_password_strength;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Contains four components.
 * The passwordField where the password is entered.
 * The standardText field which is the text that says "Password strength" and is always black.
 * The strengthText field which says how strong the password is and has the same color as the bar.
 * The strengthBar which fills up and changes color depending on the strength of the password. It
 * has it's on java file and extends View.
 *
 * The class has 6 setters:
 * SetMinimumLength, sets the minimum amount of characters the password must be.
 * SetBarWidth, sets the width of the strengthBar.
 * SetBarHeight, sets the height of the strengthBar.
 *
 * If any of these Setters are changed, then the numberOfReq variable in StrengthBar is changed
 * to match the new number of requirements.
 * SetReqUpperCase, sets if the password requires upper case characters.
 * SetReqNumbers, sets if the password requires numbers.
 * SetReqSpecialCase, sets if the password requires special characters.
 */


public class PasswordStrengthMeter extends LinearLayout {
    Integer minimunLength = 7;
    TextView strengthText;
    TextView standardText;
    StrengthBar strengthBar;

    boolean reqUpperCase = true;
    boolean reqNumber = true;
    boolean reqSpecialCase = true;


    public PasswordStrengthMeter(final Context context) {
        super(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setOrientation(LinearLayout.VERTICAL);

        strengthBar = new StrengthBar(context);

        EditText passwordField = new EditText(context);
        standardText = new TextView(context);
        standardText.setTextColor(Color.BLACK);
        standardText.setText("Password strength: ");
        strengthText = new TextView(context);
        strengthText.setText("Enter a password");
        strengthText.setTextColor(Color.BLACK);

        strengthText.setLayoutParams(params);
        passwordField.setLayoutParams(params);

        LinearLayout linearLayoutText = new LinearLayout(context);
        LinearLayout.LayoutParams linearLayoutTextParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        linearLayoutText.setLayoutParams(linearLayoutTextParams);
        linearLayoutText.setOrientation(HORIZONTAL);
        linearLayoutText.addView(standardText);
        linearLayoutText.addView(strengthText);

        addView(passwordField);
        addView(linearLayoutText);
        addView(strengthBar);

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                strengthBar.setValues(calculateStrength(editable.toString()));
                if (editable.toString().equals("")){
                    strengthText.setText("Enter a password");
                }
                else{
                    strengthText.setText(strengthBar.getStrengthText());
                }
                strengthText.setTextColor(strengthBar.getStrengthColor());
            }
        });

    }

    /**
     * Returns an Integer of maximum 4 and minimum 0. If some of the requirements have been set to
     * false, the strength will be less and that requirement will not be checked.
     * If you want to change the algorithm you can override this method and replace it with
     * another method that returns a Integer from 0 to 4.
     * @param   password    String
     * @return  strength    Integer
     */
    public Integer calculateStrength(String password){

        Integer strength = 0;
        if(password.length() > minimunLength){
            strength += 1;
            if(password.matches(".*[0-9].*") && reqNumber){
                strength += 1;
            }
            if(password.matches(".*[A-Z].*") && reqUpperCase){
                strength += 1;
            }
            if(password.matches(".*[^0-z].*") && reqSpecialCase){
                strength += 1;
            }
        }
        return strength;
    }
    public void setMinimunLength(Integer minimumLength) {
        this.minimunLength = minimumLength;
    }
    public void setBarWidth(Integer barWidth) {
        strengthBar.setBarWidth(barWidth);
    }
    public void setBarHeight(Integer barHeight) {
        strengthBar.setBarHeight(barHeight);
    }
    public void setReqUpperCase(boolean reqUpperCase) {
        if (this.reqUpperCase != reqUpperCase){
            if (reqUpperCase){
                strengthBar.setNumberOfReq(strengthBar.getNumberOfReq() + 1);
            }
            else{
                strengthBar.setNumberOfReq(strengthBar.getNumberOfReq() - 1);
            }
        }
        this.reqUpperCase = reqUpperCase;
    }

    public void setReqNumber(boolean reqNumber) {
        if(this.reqNumber != reqNumber){
            if (reqNumber){
                strengthBar.setNumberOfReq(strengthBar.getNumberOfReq() + 1);
            }
            else{
                strengthBar.setNumberOfReq(strengthBar.getNumberOfReq() - 1);
            }
        }
        this.reqNumber = reqNumber;
    }

    public void setReqSpecialCase(boolean reqSpecialCase) {
        if(this.reqSpecialCase != reqSpecialCase){
            if (reqSpecialCase){
                strengthBar.setNumberOfReq(strengthBar.getNumberOfReq() + 1);
            }
            else{
                strengthBar.setNumberOfReq(strengthBar.getNumberOfReq() - 1);
            }
        }
        this.reqSpecialCase = reqSpecialCase;
    }
}

