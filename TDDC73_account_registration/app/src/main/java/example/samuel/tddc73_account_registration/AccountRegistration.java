package example.samuel.tddc73_account_registration;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Pair;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observer;

/**
 *The component is made up of 9 main parts.
 * The headline, it has the setter setHeadlineText.
 *
 * These six parts a structured in a left and right column which are vertical linear layouts.
 * They also all have the function set{fieldname}Required which takes a boolean and sets if the field
 * is required to fill in to be able to register an account. If some field which is required is not
 * filled in, a warning will appear by the field.
 * Name field, it has the function removeName.
 * Username, it has the function removeUsername.
 * Password, it has the function removePassword.
 * Email, it has the function removeEmail.
 * Birthdate, it has the function removeDateOfBirth.
 * Gender, it has the function removeGender.
 *
 * Additional fields can be added. They can be set to required or not.
 *
 * Terms of condition checkbox, it has the function removeTerms and setTermsText.
 * Register account button.
 *
 * There is a function setTextSize which sets the text size of all the field except the headline.
 */

public class AccountRegistration extends LinearLayout {

    String headlineText;

    //Variables for left column texts
    int textHeight = 90;
    int textSize = 11;
    LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, textHeight);
    LinearLayout leftColumn;

    LinearLayout rightColumn;
    //Name field variables
    TextView textViewName;
    EditText editTextName;
    boolean nameRequired;
    boolean nameEntered = false;
    //Email variables
    TextView textViewEmail;
    EditText editTextEmail;
    boolean emailRequired;
    boolean emailEntered = false;
    //Date of birth variables
    LinearLayout linearLayoutBirth;
    TextView textViewDateOfBirth;
    Spinner day;
    Spinner month;
    Spinner year;
    String[] days;
    boolean dateOfBirthRequired;
    //Password variables
    TextView textViewPassword;
    EditText editTextPassword;
    boolean passwordRequired;
    boolean passwordEntered = false;
    //Username variables
    TextView textViewUsername;
    EditText editTextUsername;
    boolean usernameRequired;
    boolean usernameEntered = false;
    //Gender variables
    TextView textViewGender;
    Spinner genderDropdown;
    boolean genderRequired;
    //Terms of condition variables
    LinearLayout linearLayoutTerms;
    String termText;
    CheckBox acceptTerms;
    //Extra field variables
    ArrayList<Pair> extraRows = new ArrayList<>();
    ArrayList<Pair<String, Object>> accountFields = new ArrayList<>();

    CustomObservable customObservable;

    public AccountRegistration(Context context) {
        super(context);

        this.setOrientation(LinearLayout.VERTICAL);

        LinearLayout columnContainer = new LinearLayout(context);
        columnContainer.setOrientation(HORIZONTAL);
        columnContainer.setWeightSum(10);
        columnContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //Left column
        leftColumn = new LinearLayout(context);
        leftColumn.setOrientation(LinearLayout.VERTICAL);
        final LayoutParams leftColumnParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 2);
        leftColumn.setLayoutParams(leftColumnParams);
        //Right column
        rightColumn = new LinearLayout(context);
        rightColumn.setOrientation(LinearLayout.VERTICAL);
        LayoutParams rightColumnParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 8);
        rightColumn.setLayoutParams(rightColumnParams);
        //Headline
        TextView headline = new TextView(context);
        headlineText = "Register a new account";
        headline.setText(headlineText);
        headline.setTextSize(20);
        headline.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        this.addView(headline);
        //Register button
        Button registerButton = new Button(context);
        registerButton.setText("Register account");
        registerButton.setOnClickListener(new OnClickListener() {
            /**
             * Checks if all the field that are required are set, if not field in, it displays error.
             * @param view
             */

            @Override
            public void onClick(View view) {
                Boolean canSubmit = true;
                if (nameRequired && !nameEntered){
                    editTextName.setError("Enter full name");
                    canSubmit = false;
                }
                if (usernameRequired && !usernameEntered){
                    editTextUsername.setError("Enter username");
                    canSubmit = false;
                }
                if (passwordRequired && !passwordEntered){
                    editTextPassword.setError("Enter Password");
                    canSubmit = false;
                }
                if (emailRequired && !emailEntered){
                    editTextEmail.setError("Enter email");
                    canSubmit = false;
                }
                if(dateOfBirthRequired){
                    if(year.getSelectedItemPosition() == 0 || month.getSelectedItemPosition() == 0
                            || day.getSelectedItemPosition() == 0){
                        textViewDateOfBirth.setError("Choose a birthdate");
                        canSubmit = false;
                    }
                    else{
                        textViewDateOfBirth.setError(null);
                    }
                }
                if(genderRequired){
                    if (genderDropdown.getSelectedItemPosition() == 0){
                        textViewGender.setError("Choose a gender");
                        canSubmit = false;
                    }
                    else {
                        textViewGender.setError(null);
                    }
                }
                if(!acceptTerms.isChecked()){
                    acceptTerms.setError("Accept the terms");
                    canSubmit = false;
                }
                if(acceptTerms.isChecked()){
                    acceptTerms.setError(null);
                }
                for(Pair<EditText, Pair<Boolean, ArrayList<Boolean>>> rowPair: extraRows){
                    if(rowPair.second.first && !rowPair.second.second.get(0)){
                        rowPair.first.setError("Fill in the field");
                        canSubmit = false;
                    }
                }
                if (canSubmit){
                    //Collect all the data from all the fields and call the observer with the data.
                    ArrayList<Pair<String, String>> accountInfo = new ArrayList<>();
                    for(Pair<String, Object> pair: accountFields){
                        //Text fields
                        if ( pair.second.getClass().getSimpleName().equals("EditText")){
                            EditText editText = (EditText)pair.second;
                            accountInfo.add(new Pair<String, String>(pair.first, editText.getText().toString()));

                        }
                        //Date of birth field
                        else if(pair.second.getClass().getSimpleName().equals("LinearLayout")) {
                            LinearLayout linearLayout = (LinearLayout) pair.second;
                            Spinner spinner = (Spinner)linearLayout.getChildAt(0);
                            String year = spinner.getSelectedItem().toString();
                            Spinner spinner1 = (Spinner)linearLayout.getChildAt(1);
                            String month = spinner1.getSelectedItem().toString();
                            Spinner spinner2 = (Spinner)linearLayout.getChildAt(2);
                            String day = spinner2.getSelectedItem().toString();

                            accountInfo.add(new Pair<String, String>(pair.first, year+"-"+month+"-"+day));
                        }
                        //Gender field
                        else if(pair.second.getClass().getSimpleName().equals("Spinner")) {
                            Spinner spinner= (Spinner) pair.second;
                            accountInfo.add(new Pair<String, String>(pair.first, spinner.getSelectedItem().toString()));
                        }
                    }
                    customObservable.sendAccount(accountInfo);
                }
            }
        });
        //Terms of condition
        linearLayoutTerms = new LinearLayout(context);
        linearLayoutTerms.setOrientation(HORIZONTAL);
        acceptTerms = new CheckBox(context);
        termText = "I accept the terms of condition for this service";
        TextView textViewTermText = new TextView(context);
        textViewTermText.setText(termText);
        textViewTermText.setTextSize(textSize);
        linearLayoutTerms.addView(acceptTerms);
        linearLayoutTerms.addView(textViewTermText);

        //Add the fields and set the initial values if the field is required or not.
        //The set functions are public
        addName(context);
        setNameRequired(true);
        addUsername(context);
        setUsernameRequired(true);
        addPassword(context);
        setPasswordRequired(true);
        addEmail(context);
        setEmailRequired(false);
        addDateOfBirth(context);
        setDateOfBirthRequired(false);
        addGender(context);
        setGenderRequired(false);

        columnContainer.addView(leftColumn);
        columnContainer.addView(rightColumn);
        this.addView(columnContainer);
        this.addView(linearLayoutTerms);
        this.addView(registerButton);
    }

    void addName(Context context){
        textViewName = new TextView(context);
        textViewName.setTextSize(textSize);
        textViewName.setGravity(Gravity.CENTER_VERTICAL);
        textViewName.setLayoutParams(textParams);
        leftColumn.addView(textViewName);

        editTextName = new EditText(context);
        accountFields.add(new Pair<String, Object>("Name", editTextName));
        rightColumn.addView(editTextName);
    }

    void addUsername(Context context){
        textViewUsername = new TextView(context);
        textViewUsername.setTextSize(textSize);
        textViewUsername.setGravity(Gravity.CENTER_VERTICAL);
        textViewUsername.setLayoutParams(textParams);
        leftColumn.addView(textViewUsername);

        editTextUsername = new EditText(context);
        accountFields.add(new Pair<String, Object>("Username", editTextUsername));
        rightColumn.addView(editTextUsername);
    }

    void addPassword(Context context){
        textViewPassword = new TextView(context);
        textViewPassword.setText("Password");
        textViewPassword.setTextSize(textSize);
        textViewPassword.setLayoutParams(textParams);
        textViewPassword.setGravity(Gravity.CENTER_VERTICAL);
        leftColumn.addView(textViewPassword);

        editTextPassword = new EditText(context);
        accountFields.add(new Pair<String, Object>("Password", editTextPassword));
        editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        rightColumn.addView(editTextPassword);
    }

    void addEmail(Context context){
        textViewEmail = new TextView(context);
        textViewEmail.setTextSize(textSize);
        textViewEmail.setLayoutParams(textParams);
        textViewEmail.setGravity(Gravity.CENTER_VERTICAL);
        leftColumn.addView(textViewEmail);

        editTextEmail = new EditText(context);
        accountFields.add(new Pair<String, Object>("Email", editTextEmail));
        rightColumn.addView(editTextEmail);
    }
    void addDateOfBirth(final Context context){
        textViewDateOfBirth = new TextView(context);
        textViewDateOfBirth.setTextSize(textSize);
        textViewDateOfBirth.setLayoutParams(textParams);
        textViewDateOfBirth.setGravity(Gravity.CENTER_VERTICAL);
        leftColumn.addView(textViewDateOfBirth);

        linearLayoutBirth = new LinearLayout(context);
        linearLayoutBirth.setOrientation(HORIZONTAL);

        year = new Spinner(context);
        String[] years = getYearList();
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, years);
        year.setAdapter(yearAdapter);
        linearLayoutBirth.addView(year);

        month = new Spinner(context);
        String[] months = new String[]{"Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, months);
        month.setAdapter(monthAdapter);
        linearLayoutBirth.addView(month);

        day = new Spinner(context);
        days = new String[]{"Day"};
        final ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, days);
        day.setAdapter(dayAdapter);
        day.setOnTouchListener(new OnTouchListener() {
            /**
             * If the month is selected, display the right amount of days for that month in the
             * day dropdown.
             * @param view
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    days = getDayList(month.getSelectedItemPosition() - 1);
                    day.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, days));
                }
                return false;
            }
        });
        linearLayoutBirth.addView(day);
        accountFields.add(new Pair<String, Object>("DateOfBirth", linearLayoutBirth));
        rightColumn.addView(linearLayoutBirth);
    }

    String[] getYearList(){
        String[] yearList = new String[101];
        yearList[0] = "Year";
        for (int i = 0; i < 100; i++){
            Integer year = 2018 - i;
            yearList[i + 1] = year.toString();
        }
        return yearList;
    }
    String[] getDayList(Integer month){
        Calendar mycal = new GregorianCalendar(2018, month , 1);
        Integer daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] dayList = new String[daysInMonth + 1];
        dayList[0] = "Day";
        for(Integer i = 1; i < daysInMonth + 1; i ++){
            Integer dayNumber = i ;
            dayList[i] = dayNumber.toString();
        }
        return  dayList;
    }

    void addGender(Context context){
        textViewGender = new TextView(context);
        textViewGender.setTextSize(textSize);
        textViewGender.setLayoutParams(textParams);
        textViewGender.setGravity(Gravity.CENTER_VERTICAL);
        leftColumn.addView(textViewGender);

        genderDropdown = new Spinner(context);
        String[] items = new String[]{"Not chosen", "Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
        genderDropdown.setAdapter(adapter);
        accountFields.add(new Pair<String, Object>("Gender", genderDropdown));
        rightColumn.addView(genderDropdown);
    }

    public void setNameRequired(boolean nameRequired){
        this.nameRequired = nameRequired;
        if(nameRequired){
            textViewName.setText("Full name*");
            editTextName.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    if(editable.toString().matches(".*[A-z].*")){
                       nameEntered = true;
                    }
                    else {
                        nameEntered = false;
                    }
                }
            });
        }
        else{
            textViewName.setText("Full name");
        }
    }

    public void setUsernameRequired(boolean usernameRequired){
        this.usernameRequired = usernameRequired;
        if(usernameRequired){
            textViewUsername.setText("Username*");
            editTextUsername.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    usernameEntered = editable.toString().matches(".*[0-z].*");
                }
            });
        }
        else{
            textViewUsername.setText("Username");
        }
    }

    public void setGenderRequired(boolean genderRequired) {
        this.genderRequired = genderRequired;
        if(genderRequired){
            textViewGender.setText("Gender*");
        }
        else{
            textViewGender.setText("Gender");
        }
    }

    public void setEmailRequired(boolean emailRequired) {
        this.emailRequired = emailRequired;
        if(emailRequired){
            textViewEmail.setText("Email*");
            editTextEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    emailEntered = editable.toString().matches(".*[0-z].*");
                }
            });
        }
        else{
            textViewEmail.setText("Email");
        }
    }

    public void setDateOfBirthRequired(boolean dateOfBirthRequired) {
        this.dateOfBirthRequired = dateOfBirthRequired;
        if(dateOfBirthRequired){
            textViewDateOfBirth.setText("Birthdate*");
        }
        else{
            textViewDateOfBirth.setText("Birthdate ");
        }
    }

    public void setPasswordRequired(boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
        if(passwordRequired){
            textViewPassword.setText("Password*");
            editTextPassword.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    passwordEntered = editable.toString().matches(".*[0-z].*");
                }
            });
        }
        else{
            textViewPassword.setText("Password");
        }
    }


    public void addRow(String rowName, boolean required, Context context){
        try{
            final ArrayList<Boolean> rowEntered = new ArrayList();
            rowEntered.add(false);

            final TextView row = new TextView(context);
            row.setTextSize(textSize);
            row.setText(rowName);
            row.setLayoutParams(textParams);
            row.setGravity(Gravity.CENTER_VERTICAL);
            leftColumn.addView(row);

            final EditText rowInput = new EditText(context);
            if (required){
                row.setText(rowName + "*");
                rowInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {

                        if(editable.length() > 0 ){

                            rowEntered.remove(0);
                            rowEntered.add(true);
                        }
                        else{
                            rowEntered.remove(0);
                            rowEntered.add(false);
                        }
                    }
                });
            }

            rightColumn.addView(rowInput);
            Pair<View, Pair<Boolean, ArrayList<Boolean>>> pair = new Pair(rowInput, new Pair<>(required, rowEntered));
            accountFields.add(new Pair<String, Object>(rowName, rowInput));
            extraRows.add(pair);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't parse input, please try again");
        }
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTermsText(String termText) {
        this.termText = termText;
    }
    public void removeName(){
        leftColumn.removeView(textViewName);
        rightColumn.removeView(editTextName);
    }
    public void removeUsername(){
        leftColumn.removeView(textViewUsername);
        rightColumn.removeView(editTextUsername);
    }
    public void removePassword(){
        leftColumn.removeView(textViewPassword);
        rightColumn.removeView(editTextPassword);
    }
    public void removeEmail(){
        leftColumn.removeView(textViewEmail);
        rightColumn.removeView(editTextEmail);
    }
    public void removeDateOfBirth(){
        leftColumn.removeView(textViewDateOfBirth);
        rightColumn.removeView(linearLayoutBirth);
    }
    public void removeGender(){
        leftColumn.removeView(textViewGender);
        rightColumn.removeView(genderDropdown);
    }

    public void removeTerms(){
        this.removeView(linearLayoutTerms);
    }
    public void setHeadlineText(String headlineText) {
        this.headlineText = headlineText;
    }

    public void addObserver(Observer observer){
        customObservable = new CustomObservable();
        customObservable.addObserver(observer);
    }
}
