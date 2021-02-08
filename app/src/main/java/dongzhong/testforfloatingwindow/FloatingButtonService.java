package dongzhong.testforfloatingwindow;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by dongzhong on 2018/5/30.
 */

public class FloatingButtonService extends Service {
    public static boolean isStarted = false;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private int nums[] = null;
    private View displayView;

    private RotateTextView numx, numy, num2, num1, numk, numq, numj, num10,
            num9, num8, num7, num6, num5, num4, num3, btClose, btReset;

    private FloatingOnTouchListener clickListener;

    private Handler changeNumHandler;

    public final static int Close = 17;
    public final static int Reset = 16;
    public final static int X = 15;
    public final static int Y = 14;
    public final static int Two = 2;
    public final static int A = 1;
    public final static int Three = 3;
    public final static int Four = 4;
    public final static int Five = 5;
    public final static int Six = 6;
    public final static int Seven = 7;
    public final static int Eight = 8;
    public final static int Nine = 9;
    public final static int Ten = 10;
    public final static int J = 11;
    public final static int Q = 12;
    public final static int K = 13;

    @Override
    public void onCreate() {
        super.onCreate();
        isStarted = true;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = DisplayUtil.dp2px(this, 80);
        layoutParams.height = DisplayUtil.dp2px(this, 640);
        layoutParams.x = 800;
        layoutParams.y = 200;
        nums = new int[15];

        changeNumHandler = new Handler(this.getMainLooper(), changeNumCallback);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            //打开记牌器界面
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            displayView = layoutInflater.inflate(R.layout.cardrecord_display, null);
            numx = displayView.findViewById(R.id.numx);
            numy = displayView.findViewById(R.id.numy);
            num1 = displayView.findViewById(R.id.num1);
            num2 = displayView.findViewById(R.id.num2);
            num3 = displayView.findViewById(R.id.num3);
            num4 = displayView.findViewById(R.id.num4);
            num5 = displayView.findViewById(R.id.num5);
            num6 = displayView.findViewById(R.id.num6);
            num7 = displayView.findViewById(R.id.num7);
            num8 = displayView.findViewById(R.id.num8);
            num9 = displayView.findViewById(R.id.num9);
            num10 = displayView.findViewById(R.id.num10);
            numj = displayView.findViewById(R.id.numj);
            numq = displayView.findViewById(R.id.numq);
            numk = displayView.findViewById(R.id.numk);
            btClose = displayView.findViewById(R.id.close);
            btReset = displayView.findViewById(R.id.reset);
            clickListener = new FloatingButtonService.FloatingOnTouchListener();
            numx.setOnTouchListener(clickListener);
            numy.setOnTouchListener(clickListener);
            num1.setOnTouchListener(clickListener);
            num2.setOnTouchListener(clickListener);
            num3.setOnTouchListener(clickListener);
            num4.setOnTouchListener(clickListener);
            num5.setOnTouchListener(clickListener);
            num6.setOnTouchListener(clickListener);
            num7.setOnTouchListener(clickListener);
            num8.setOnTouchListener(clickListener);
            num9.setOnTouchListener(clickListener);
            num10.setOnTouchListener(clickListener);
            numj.setOnTouchListener(clickListener);
            numq.setOnTouchListener(clickListener);
            numk.setOnTouchListener(clickListener);
            btClose.setOnTouchListener(clickListener);
            btReset.setOnTouchListener(clickListener);
            displayView.setOnTouchListener(clickListener);
            windowManager.addView(displayView, layoutParams);
        }
    }

    public void updateNumText(int action) {
        int whiteColor = Color.parseColor("#FFFFFF");
        switch (action) {
            case Close:
                windowManager.removeView(displayView);
                break;
            case Reset:
                reset();
                break;
            case X:
                numx.setText(String.valueOf(nums[0]));
                numx.setTextColor(whiteColor);
                break;
            case Y:
                numy.setText(String.valueOf(nums[1]));
                numy.setTextColor(whiteColor);
                break;
            case Two:
                num2.setText(String.valueOf(nums[2]));
                num2.setTextColor(whiteColor);
                break;
            case A:
                num1.setText(String.valueOf(nums[3]));
                num1.setTextColor(whiteColor);
                break;
            case Three:
                num3.setText(String.valueOf(nums[14]));
                num3.setTextColor(whiteColor);
                break;
            case Four:
                num4.setText(String.valueOf(nums[13]));
                num4.setTextColor(whiteColor);
                break;
            case Five:
                num5.setText(String.valueOf(nums[12]));
                num5.setTextColor(whiteColor);
                break;
            case Six:
                num6.setText(String.valueOf(nums[11]));
                num6.setTextColor(whiteColor);
                break;
            case Seven:
                num7.setText(String.valueOf(nums[10]));
                num7.setTextColor(whiteColor);
                break;
            case Eight:
                num8.setText(String.valueOf(nums[9]));
                num8.setTextColor(whiteColor);
                break;
            case Nine:
                num9.setText(String.valueOf(nums[8]));
                num9.setTextColor(whiteColor);
                break;
            case Ten:
                num10.setText(String.valueOf(nums[7]));
                num10.setTextColor(whiteColor);
                break;
            case J:
                numj.setText(String.valueOf(nums[6]));
                numj.setTextColor(whiteColor);
                break;
            case Q:
                numq.setText(String.valueOf(nums[5]));
                numq.setTextColor(whiteColor);
                break;
            case K:
                numk.setText(String.valueOf(nums[4]));
                numk.setTextColor(whiteColor);
                break;
        }
    }

    public void reset() {
        int color = Color.parseColor("#FA6637");
        num1.setText(String.valueOf(nums[3]));
        num1.setTextColor(color);
        num2.setText(String.valueOf(nums[2]));
        num2.setTextColor(color);
        num3.setText(String.valueOf(nums[14]));
        num3.setTextColor(color);
        num4.setText(String.valueOf(nums[13]));
        num4.setTextColor(color);
        num5.setText(String.valueOf(nums[12]));
        num5.setTextColor(color);
        num6.setText(String.valueOf(nums[11]));
        num6.setTextColor(color);
        num7.setText(String.valueOf(nums[10]));
        num7.setTextColor(color);
        num8.setText(String.valueOf(nums[9]));
        num8.setTextColor(color);
        num9.setText(String.valueOf(nums[8]));
        num9.setTextColor(color);
        num10.setText(String.valueOf(nums[7]));
        num10.setTextColor(color);
        numj.setText(String.valueOf(nums[6]));
        numj.setTextColor(color);
        numq.setText(String.valueOf(nums[5]));
        numq.setTextColor(color);
        numk.setText(String.valueOf(nums[4]));
        numk.setTextColor(color);
        numx.setText(String.valueOf(nums[0]));
        numx.setTextColor(color);
        numy.setText(String.valueOf(nums[1]));
        numy.setTextColor(color);
    }

    public void updateNum(int action) {
        changeNumHandler.sendEmptyMessageDelayed(action, 100);
    }

    private Handler.Callback changeNumCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what != 0 && displayView != null) {
                updateNumText(msg.what);
            }
            return false;
        }
    };

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (view.getId() == displayView.getId()) {
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (view.getId() == displayView.getId()) {
                        int nowX = (int) event.getRawX();
                        int nowY = (int) event.getRawY();
                        int movedX = nowX - x;
                        int movedY = nowY - y;
                        x = nowX;
                        y = nowY;
                        layoutParams.x = layoutParams.x + movedX;
                        layoutParams.y = layoutParams.y + movedY;

                        windowManager.updateViewLayout(view, layoutParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    int id = view.getId();
                    if (id == R.id.close) {
                        updateNum(Close);
                    } else if (id == R.id.reset) {
                        nums = new int[]{1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
                        updateNum(Reset);
                    } else if (id == R.id.numx) {
                        System.out.println(nums[0]);
                        if (nums[0] != 0) {
                            nums[0]--;
                            updateNum(X);
                        }
                    } else if (id == R.id.numy) {
                        if (nums[1] != 0) {
                            nums[1]--;
                            updateNum(Y);
                        }
                    } else if (id == R.id.num1) {
                        if (nums[3] > 0) {
                            nums[3]--;
                            updateNum(A);
                        }
                    } else if (id == R.id.num2) {
                        if (nums[2] > 0) {
                            nums[2]--;
                            updateNum(Two);
                        }
                    } else if (id == R.id.num3) {
                        if (nums[14] > 0) {
                            nums[14]--;
                            updateNum(Three);
                        }
                    } else if (id == R.id.num4) {
                        if (nums[13] > 0) {
                            nums[13]--;
                            updateNum(Four);
                        }
                    } else if (id == R.id.num5) {
                        if (nums[12] > 0) {
                            nums[12]--;
                            updateNum(Five);
                        }
                    } else if (id == R.id.num6) {
                        if (nums[11] > 0) {
                            nums[11]--;
                            updateNum(Six);
                        }
                    } else if (id == R.id.num7) {
                        if (nums[10] > 0) {
                            nums[10]--;
                            updateNum(Seven);
                        }
                    } else if (id == R.id.num8) {
                        if (nums[9] > 0) {
                            nums[9]--;
                            updateNum(Eight);
                        }
                    } else if (id == R.id.num9) {
                        if (nums[8] > 0) {
                            nums[8]--;
                            updateNum(Nine);
                        }
                    } else if (id == R.id.num10) {
                        if (nums[7] > 0) {
                            nums[7]--;
                            updateNum(Ten);
                        }
                    } else if (id == R.id.numj) {
                        if (nums[6] > 0) {
                            nums[6]--;
                            updateNum(J);
                        }
                    } else if (id == R.id.numq) {
                        if (nums[5] > 0) {
                            nums[5]--;
                            updateNum(Q);
                        }
                    } else if (id == R.id.numk) {
                        if (nums[4] > 0) {
                            nums[4]--;
                            updateNum(K);
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
