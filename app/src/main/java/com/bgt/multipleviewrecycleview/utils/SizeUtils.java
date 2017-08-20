package com.bgt.multipleviewrecycleview.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class SizeUtils {

    private static final float DEFAULT_ASPECT_RATIO = 1.78f;

    private static WindowManager windowManager;

    private static WindowManager getWindowManager(Context context){
        if(windowManager == null){
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        return windowManager;
    }

    public static int getScreenWidth(Context c){
        WindowManager wm = getWindowManager(c);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    public static int getScreenHeight(Context c){
        WindowManager wm = getWindowManager(c);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.y;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(Context context,float dp){

        return (int) (dp * ((float)getDisplayMetrics(context).densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static int convertPixelsToDp(Context context, float px){
        return (int) (px / ((float)getDisplayMetrics(context).densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static DisplayMetrics getDisplayMetrics(Context ctx) {
        DisplayMetrics dm = new DisplayMetrics();

        WindowManager wm = getWindowManager(ctx);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm;
    }

    public static int getDefaultImageHeight(Context context){
        return (int) (getScreenWidth(context) / DEFAULT_ASPECT_RATIO);
    }

    public static int getImageHeight(Context context, float ratio){
        return (int) (getScreenWidth(context) / ratio);
    }
}
