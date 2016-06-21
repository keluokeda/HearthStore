package com.wangxiaqiwuhai.com.hearthstore.card;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wangxiaqiwuhai.com.hearthstore.R;
import com.wangxiaqiwuhai.com.hearthstore.interfaces.IHandCardManager;
import com.wangxiaqiwuhai.com.hearthstore.manager.GameManager;
import com.wangxiaqiwuhai.com.hearthstore.utils.DensityUtil;

/**
 * 卡牌view
 */
public class CardView extends FrameLayout implements View.OnClickListener {
    private TextView tvAttack;
    private TextView tvHealth;
    private TextView tvCost;
    private TextView tvName;
    private TextView tvDescription;
    private View viewQuality;
    private TextView tvRace;
    private View baseView;
    private TextView tvHero;
    private Card mCard;

    public CardView(Context context) {
        super(context);
        initView();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        baseView = LayoutInflater.from(getContext()).inflate(R.layout.card_view, this);
        tvAttack = (TextView) baseView.findViewById(R.id.attack);
        tvHealth = (TextView) baseView.findViewById(R.id.health);
        tvCost = (TextView) baseView.findViewById(R.id.cost);
        tvName = (TextView) baseView.findViewById(R.id.name);
        tvDescription = (TextView) baseView.findViewById(R.id.description);
        viewQuality = baseView.findViewById(R.id.quality);
        tvRace = (TextView) findViewById(R.id.race);
        tvHero = (TextView) baseView.findViewById(R.id.heroclass);

        this.setOnClickListener(this);
    }

    public void initData(Card card) {
        this.mCard = card;
        this.setTag(card);
        initCardData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width;
        int height;
        width = DensityUtil.dip2px(getContext(), 100);
        height = DensityUtil.dip2px(getContext(), 160);
        setMeasuredDimension(width, height);
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec(height, heightMode));

    }

    public void initCardData() {
        tvCost.setText("" + mCard.mCost);
        if (!(mCard instanceof SpellCard)) {
            tvAttack.setText("" + mCard.mAttack);
            tvHealth.setText("" + mCard.mHealth);

        }else {
            tvAttack.setText("");
            tvHealth.setText("");
        }

        tvName.setText(mCard.mCardName);
        tvDescription.setText(mCard.mDescription);
        viewQuality.setBackgroundResource(mCard.mQuality.getColorResource());
        if (TextUtils.isEmpty(mCard.mRace.toString())) {
            tvRace.setVisibility(GONE);
        } else {
            tvRace.setText(mCard.mRace.toString());
        }
        tvName.setBackgroundColor(mCard.mCardHeroClass.getColor());
        tvHero.setText(mCard.mCardHeroClass.toString());
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onClick(View v) {
        if(v==this){
            GameManager.getInstance().useCardFromHand(mCard);
        }
    }
}
