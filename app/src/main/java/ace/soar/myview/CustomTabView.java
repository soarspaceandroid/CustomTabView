package ace.soar.myview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cathy on 2016/2/16.
 */
public class CustomTabView extends LinearLayout{

    private final static int ANIMATION_DURITION = 300;//  animation dur time
    private final static String ANIMATION_PROPERTITY_STRING = "backgroundColor"; // animation propertity

    private final static int DEFAULT_BACK_COLOR = Color.parseColor("#F4F4F4");

    /**average 15*/
    private final static int AVERAGE_COUNT = 15 ; // 15 个 -
    private final static int COUNT_2 = 2;
    private final static int COUNT_3 = 3;
    private final static int COUNT_4 = 4;

    /**position */
    public final static int POSITION_0 = 0;
    public final static int POSITION_1 = 1;
    public final static int POSITION_2 = 2;
    public final static int POSITION_3 = 3;
    public final static int POSITION_4 = 4;

    /**view posotion**/
    private final static int VIEW_POSITION_0 = 0;
    private final static int VIEW_POSITION_1 = 1;
    private final static int VIEW_POSITION_2 = 2;

    private RecyclerView mRecyclerView;
    private TabViewAdapter mAdapter;
    private Context mViewContext;
    public float mAverageWidth = 0;
    private List<TabViewData> dataList ;

    private int mLeftMargin = 0; // other width of view or layout
    private int mRightMargin = 0; // other width of view or layout

    private boolean mCanClickSelected = true; //  set can click the one which is selected
    private boolean mNeedAnimation = true ; // need animation

    private int mSelectColor = Color.parseColor("#808080");

    private View mExtralViewStart ;
    private View mExtralViewEnd ;

    public CustomTabView(Context context) {
        super(context);
    }

    public CustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomTabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initView(Context context){
        mViewContext = context;
        mRecyclerView = new RecyclerView(context);
        mRecyclerView.setBackgroundColor(DEFAULT_BACK_COLOR);
        dataList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter  = new TabViewAdapter(dataList, context);
        mRecyclerView.setAdapter(mAdapter);
        setGravity(Gravity.CENTER);
        mAverageWidth = (float)getScreenWidth() / AVERAGE_COUNT ;
        setOrientation(LinearLayout.HORIZONTAL);

        mExtralViewStart = new View(context);
        mExtralViewEnd = new View(context);
        mExtralViewStart.setBackgroundColor(DEFAULT_BACK_COLOR);
        mExtralViewEnd.setBackgroundColor(DEFAULT_BACK_COLOR);

    }

    public void setData(List<TabViewData> data){
        // set recycleview width
        if(data!=null){
            dataList = data;
            //  设置宽度消除边沿 宽度
            setRecyclerViewWid(mRecyclerView  , data.size());
        }
        mAdapter.setData(data);
    }

    /**
     *  The data for view and float count
     */
    public class TabViewData{
        private int imageId;
        private int imageSelectId;
        private int textId;
        private int floatCount;
        private int backColor ;

        public TabViewData(int imageId, int imageSelectId ,int textId, int floatCount) {
            this.imageId = imageId;
            this.textId = textId;
            this.floatCount = floatCount;
            this.imageSelectId = imageSelectId;
        }

        public int getBackColor() {
            return backColor;
        }

        public void setBackColor(int backColor) {
            this.backColor = backColor;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public int getTextId() {
            return textId;
        }

        public void setTextId(int textId) {
            this.textId = textId;
        }

        public int getFloatCount() {
            return floatCount;
        }

        public void setFloatCount(int floatCount) {
            this.floatCount = floatCount;
        }

        public int getImageSelectId() {
            return imageSelectId;
        }

        public void setImageSelectId(int imageSelectId) {
            this.imageSelectId = imageSelectId;
        }
    }


    /**
     * tab view adataper
     *
     */
    public class TabViewAdapter extends RecyclerView.Adapter<TabViewAdapter.ViewHolder> {
        private List<TabViewData> mDataset;
        private Context mContext;
        private int mItemWidth = 0;
        private OnTabClickListener mListener ;
        private int mSelectPosition = 0;
        private int mLastSelectPosition = 0;
        private boolean isClicked = false;

        // Provide a reference to the type of views that you are using
        // (custom viewholder)
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            private ImageView mImageView;
            private TextView mCountView;
            private LinearLayout mViewParent;
            private LinearLayout mViewLinear ;
            public ViewHolder(View view) {
                super(view);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClicked) {
                            return;
                        }
                        mLastSelectPosition = mSelectPosition;
                        if (mLastSelectPosition == Integer.parseInt(mImageView.getTag().toString()) && !mCanClickSelected) {
                            //  you  click the same one
                            return;
                        }
                        isClicked = true;
                        mSelectPosition = Integer.parseInt(mImageView.getTag().toString());
                        notifyDataSetChanged();
                        mListener.onTabClick(v, mSelectPosition);
                    }
                });
                mViewParent = (LinearLayout)view.findViewById(R.id.view_parent);
                mViewLinear = (LinearLayout)view.findViewById(R.id.view_linear);
                mTextView =(TextView)view.findViewById(R.id.text_id);
                mImageView =(ImageView)view.findViewById(R.id.image_id);
                mCountView =(TextView)view.findViewById(R.id.countview_id);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public TabViewAdapter(List<TabViewData> myDataset  , Context context) {
            mDataset = myDataset;
            mContext = context;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public TabViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_tab_view_child, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = mItemWidth;
            view.setLayoutParams(params);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            if(mNeedAnimation){
                holder.mImageView.setImageResource(mDataset.get(position).getImageId());
                if (mSelectPosition == position) {
                    holder.mImageView.setImageResource(mDataset.get(position).getImageSelectId());
                    startAnimation(holder.mImageView);
                    animBackColor(holder.mViewLinear, mDataset.get(position).getBackColor(), mSelectColor);
                } else if (mLastSelectPosition == position) {
                    holder.mImageView.setImageResource(mDataset.get(position).getImageId());
                    animBackColor(holder.mViewLinear, mSelectColor, mDataset.get(position).getBackColor());
                }
                animBackColor(holder.mViewParent, mDataset.get(position).getBackColor(), mDataset.get(position).getBackColor());
            }else{
                holder.mImageView.setImageResource(mDataset.get(position).getImageId());
                if (mSelectPosition == position) {
                    holder.mViewLinear.setBackgroundColor(mSelectColor);
                    holder.mImageView.setImageResource(mDataset.get(position).getImageSelectId());
                } else if (mLastSelectPosition == position) {
                    holder.mViewLinear.setBackgroundColor(mDataset.get(position).getBackColor());
                    holder.mImageView.setImageResource(mDataset.get(position).getImageId());
                }
                holder.mViewParent.setBackgroundColor(mDataset.get(position).getBackColor());
                isClicked = false;
            }
            holder.mTextView.setText(mContext.getString(mDataset.get(position).getTextId()));
            if(mDataset.get(position).getFloatCount() != 0 ) {
                holder.mCountView.setVisibility(View.VISIBLE);
                holder.mCountView.setText(""+mDataset.get(position).getFloatCount());
            }else{
                holder.mCountView.setVisibility(View.GONE);
            }
            //set click postion tag
            holder.mImageView.setTag(position);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        /**
         *  set new data and update
         * @param list
         */
        public void setData(List<TabViewData> list){
            if(list != null && list.size() != 0  ){
                mDataset = list;
                switch(list.size()){
                    case COUNT_2:
                        mItemWidth = (int) (5.5 * mAverageWidth) ;
                        break;
                    case COUNT_3:
                        mItemWidth = (int) (4 * mAverageWidth) ;
                        break;
                    case COUNT_4:
                        mItemWidth = (int) (4 * mAverageWidth);
                        break;
                    default:
                        mItemWidth = getScreenWidth() / list.size() ;
                        break;

                }
                notifyDataSetChanged();
            }
        }

        /**
         *  set listener
         */
        public void  setOnTabClickListener(OnTabClickListener listener){
            mListener = listener;
        }

        /**
         * set back color animation
         */
        private void animBackColor(View view , int color1 , int color2){
            if(color1 == color2){
                view.setBackgroundColor(color1);
            }else {
                ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(view,
                        ANIMATION_PROPERTITY_STRING,
                        new ArgbEvaluator(),
                        color1,
                        color2);
                backgroundColorAnimator.setDuration(ANIMATION_DURITION);
                backgroundColorAnimator.start();
            }
        }

        /**
         * LinarLayout animation
         * @param view
         */
        private void startAnimation(View view){
            final AnimatorSet set = new AnimatorSet();
            ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view , "scaleX" , 1f , 1.2f);
            ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view , "scaleY" , 1f , 1.2f);
            final ObjectAnimator animatorScaleX1 = ObjectAnimator.ofFloat(view , "scaleX" , 1.2f , 1f);
            final ObjectAnimator animatorScaleY1 = ObjectAnimator.ofFloat(view , "scaleY" , 1.2f , 1f);

            animatorScaleX.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    set.setDuration(ANIMATION_DURITION / 2);
                    set.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isClicked = false;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    set.playTogether(animatorScaleX1, animatorScaleY1);
                    set.start();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            set.setDuration(ANIMATION_DURITION/2);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.playTogether(animatorScaleX, animatorScaleY);
            set.start();
        }

        /**
         * set item status which is selected
         * @param position
         */
        public void changeSelectItem(int position){
            mLastSelectPosition = mSelectPosition;
            mSelectPosition = position;
            notifyDataSetChanged();
        }

        /**
         *  set default select item
         * @param position
         */
        public void setDefaultSelectItem(int position){
            mLastSelectPosition = position;
            mSelectPosition = position;
            notifyDataSetChanged();
        }

    }

    /**
     *  set view width
     */
    private  void setRecyclerViewWid(RecyclerView recyclerView , int count) {
        RecyclerView.Adapter recyclerViewAdapter = recyclerView.getAdapter();
        if (recyclerViewAdapter == null) {
            return;
        }
        ViewGroup.LayoutParams params =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , (int)mViewContext.getResources().getDimension(R.dimen.item_height));
        switch(count){
            case COUNT_2:
                params.width = getScreenWidth() - (int)(4*mAverageWidth) ;
                // Add  left
                ViewGroup.LayoutParams paramsExtral2 = new ViewGroup.LayoutParams((int)mAverageWidth, (int)mViewContext.getResources().getDimension(R.dimen.item_height));
                addView(mExtralViewStart , VIEW_POSITION_0 , paramsExtral2);
                addView(recyclerView , VIEW_POSITION_1 , params);
                addView(mExtralViewEnd , VIEW_POSITION_2 , paramsExtral2);
                break;
            case COUNT_3:
                params.width = getScreenWidth() - (int)(3*mAverageWidth) ;
                ViewGroup.LayoutParams paramsExtral3 = new ViewGroup.LayoutParams((int)mAverageWidth/2, (int)mViewContext.getResources().getDimension(R.dimen.item_height));
                addView(mExtralViewStart , VIEW_POSITION_0 , paramsExtral3);
                addView(recyclerView , VIEW_POSITION_1 , params);
                addView(mExtralViewEnd , VIEW_POSITION_2 , paramsExtral3);
                break;
            case COUNT_4:
                params.width = getScreenWidth() + (int)mAverageWidth ;
                addView(recyclerView , VIEW_POSITION_0 , params);
                break;
            default:
                params.width  =getScreenWidth();
                addView(recyclerView , VIEW_POSITION_0 , params);
                break;
        }
    }

    /**
     * get screen width
     * @return
     */
    private int getScreenWidth(){
        return mViewContext.getResources().getDisplayMetrics().widthPixels - mLeftMargin - mRightMargin;
    }


    /**
     *  set listener
     */
    public void  setOnTabClickListener(OnTabClickListener listener){
        mAdapter.setOnTabClickListener(listener);
    }

    /**
     * on  tab click interface
     */
    public interface OnTabClickListener{
        void onTabClick(View itemView , int position);
    }

    /**
     * set one tab's count
     * @param position Tab position
     * @param count  Tab count
     */
    public void setTabCount(int position , int count){
        if(count != 0 ){
            dataList.get(position).setFloatCount(count);
            if(mAdapter != null){
                mAdapter.setData(dataList);
            }
        }
    }

    /**
     * if the left has other view ,you must to set the margin before setData (px)
     * @param mOtherViewWidth
     */
    public void setLeftmargin(int mOtherViewWidth){
        mLeftMargin = mOtherViewWidth;
    }

    /**
     * if the right has other view ,you must to set the margin before setData (px)
     * @param mOtherViewWidth
     */
    public void setRightmargin(int mOtherViewWidth){
        mRightMargin = mOtherViewWidth;
    }

    /**
     * set backColor after setData
     * @param allBackColor
     * @param selectColor
     */
    public void setBackgroundColor(int allBackColor , int selectColor){
        for(TabViewData item : dataList){
            item.setBackColor(allBackColor);
        }
        mExtralViewStart.setBackgroundColor(allBackColor);
        mExtralViewEnd.setBackgroundColor(allBackColor);
        mSelectColor = selectColor;
    }

    /**
     * if the one is selected ,this method set it can click again
     *  true -- can click the same one
     *  false -- can't click the one which is selected
     *  default false (can't click)
     * @param canClick
     */

    public void setCanClickSelected(boolean canClick){
        mCanClickSelected = canClick;
    }

    /**
     * set value which is need animation when you click
     * @param needAnimation
     */
    public void setNeedAnimation(boolean needAnimation){
        mNeedAnimation = needAnimation;
    }

    /**
     * set the tab be selected
     * @param position
     */
    public void setSelecteItem(int position){
        if(mAdapter!=null){
            mAdapter.changeSelectItem(position);
        }
    }

    /**
     *  set default select item
     * @param position
     */
    public void setDefaultSelectItem(int position){
        if(mAdapter != null && position < dataList.size()){
            mAdapter.setDefaultSelectItem(position);
        }
    }

}


