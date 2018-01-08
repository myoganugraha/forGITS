package itk.myoganugraha.soalgits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.View;


/**
 * Created by M. Yoga Nugraha on 1/8/2018.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
    GestureDetector gestureDetector;

    public interface OnItemClickListener{
        public void onItemClickListener(View view, int position);
    }

    public RecyclerItemClickListener(Context mContext, OnItemClickListener mListener){
        this.mListener = mListener;
        gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());

        if(childView != null && mListener != null && gestureDetector.onTouchEvent(e)){
            mListener.onItemClickListener(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
