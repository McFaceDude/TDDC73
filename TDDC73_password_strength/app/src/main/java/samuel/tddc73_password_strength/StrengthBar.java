package samuel.tddc73_password_strength;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by samuel on 12/29/17.
 */


public class StrengthBar extends View {

    Paint backgroundPaint = new Paint();
    Paint barPaint = new Paint();
    Integer strengthLevel = 0;
    Integer barWidth;
    Integer barHeight = 20;
    String strengthText = "";
    Integer strengthColor;
    Integer numberOfReq = 4;

    public StrengthBar(Context context) {
        super(context);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        barWidth = Math.round(displayMetrics.widthPixels);
    }

    /**
     * Sets all the values of the strength bar including color and text. Calls the draw method with
     * invalidate.
     * Is called from PasswordStrengthMeter.
     * @param strength  Integer, it's the strength of the password which is calculated in
     *                  PasswordStrengthMeter in the calculateStrength function.
     */
    public void setValues(Integer strength){
        this.strengthLevel = strength;
        if(strengthLevel == 0){
            barPaint.setColor(Color.LTGRAY);
            strengthColor = Color.BLACK;
            strengthText = "Password is to short";
        }
        else if(strengthLevel == 1){
            barPaint.setColor(Color.RED);
            strengthColor = Color.RED;
            strengthText = "Weak";
        }
        else if(strengthLevel == 2){
            barPaint.setColor(Color.YELLOW);
            strengthColor = Color.YELLOW;
            strengthText = "Fair";
        }
        else if(strengthLevel == 3){
            barPaint.setColor(Color.BLUE);
            strengthColor = Color.BLUE;
            strengthText = "Good";
        }
        else if(strengthLevel == 4){
            barPaint.setColor(Color.GREEN);
            strengthColor = Color.GREEN;
            strengthText = "Strong";
        }
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        backgroundPaint.setColor(Color.LTGRAY);
        canvas.drawRect(0,0, barWidth, barHeight, backgroundPaint);
        canvas.drawRect(0,0,(barWidth /numberOfReq)*strengthLevel, barHeight, barPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(barWidth, barHeight);
    }
    public String getStrengthText() {
        return strengthText;
    }
    public void setBarWidth(Integer barWidth) {
        this.barWidth = barWidth;
    }

    public void setBarHeight(Integer barHeight) {
        this.barHeight = barHeight;
    }

    public Integer getStrengthColor() {
        return strengthColor;
    }
    public void setNumberOfReq(Integer numberOfReq) {
        this.numberOfReq = numberOfReq;
    }
    public Integer getNumberOfReq() {
        return numberOfReq;
    }
}
