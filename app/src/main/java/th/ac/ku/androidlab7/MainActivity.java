package th.ac.ku.androidlab7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawRectangleView drawRectangleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawRectangleView = new DrawRectangleView(this);
        setContentView(drawRectangleView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyApp", "action_up " + (MotionEvent.ACTION_UP == event.getAction()) + "," + event.getPointerCount());
        if (event.getPointerCount() >= 2){
            drawRectangleView.setRectanglePoint(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
            drawRectangleView.invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
            drawRectangleView.createNewRectangle();
        return super.onTouchEvent(event);
    }

    class DrawRectangleView extends View {
        private List<Rect> rectangles = new ArrayList<>();
        private Paint paint = new Paint();

        public DrawRectangleView(Context context) {
            super(context);
        }

        public DrawRectangleView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public DrawRectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public DrawRectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for (Rect rect : rectangles)
                canvas.drawRect(rect, paint);
        }

        public void setRectanglePoint(float x1, float y1, float x2, float y2){
            if (rectangles.size() < 1)
                rectangles.add(new Rect());
            Rect rect = rectangles.get(rectangles.size()-1);
            int xr1 = (int) ((x1<x2)?x1:x2);
            int xr2 = (int) ((x1<x2)?x2:x1);
            int yr1 = (int) ((y1<y2)?y1:y2);
            int yr2 = (int) ((y1<y2)?y2:y1);
            rect.set(xr1, yr1, xr2, yr2);

        }

        public void createNewRectangle(){
            Log.d("MyApp", "createNewRect " + rectangles.size());
            rectangles.add(new Rect());
        }
    }
}
