package com.joss.voodootvdb.views;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.utils.CustomTypefaceSpan;
import com.joss.voodootvdb.utils.DateFormatter;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oak.util.OakUtils;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 6:13 PM
 */
public class PersonHeaderView extends LinearLayout {

    @InjectView(R.id.person_background_image)
    ImageView bgImage;
    @InjectView(R.id.person_image)
    ImageView image;
    @InjectView(R.id.person_name)
    TextView name;
    @InjectView(R.id.person_born)
    TextView born;
    @InjectView(R.id.person_died)
    TextView died;
    @InjectView(R.id.person_bio)
    TextView bio;

    Context context;
    boolean backgroundSet = false;

    public PersonHeaderView(Context context) {
        this(context, null);
    }

    public PersonHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PersonHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_person_header, this, true);
        ButterKnife.inject(this);
    }

    public void setContent(Person person){
        name.setText(person.getName());
        born.setText(buildBornString(person));
        died.setText(buildDeathString(person));
        died.setVisibility(person.getDeath().isEmpty() ? GONE : VISIBLE);
        bio.setText(Html.fromHtml(person.getBiography()));

        if(!person.getImages().getHeadshot().getFull().isEmpty())
            Picasso.with(context)
                    .load(person.getImages().getHeadshot().getFull())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_vertical)
                    .error(R.drawable.placeholder_vertical)
                    .into(image);
    }

    public void setBackground(String url){
        if(!backgroundSet && url != null && !url.isEmpty()){
            backgroundSet = true;
            Picasso.with(context)
                    .load(url)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_vertical)
                    .error(R.drawable.placeholder_vertical)
                    .into(bgImage);
        }

    }

    private SpannableStringBuilder buildBornString(Person person) {
        String bornTitle = context.getString(R.string.person_born);

        Typeface bold = OakUtils.getStaticTypeFace(context, context.getString(R.string.font_roboto_bold));
        Typeface regular = OakUtils.getStaticTypeFace(context, context.getString(R.string.font_roboto_regular));

        String fullText = bornTitle;
        fullText += person.getBirthday().isEmpty() ? "" : " " + DateFormatter.format(person.getBirthday());
        fullText += person.getBirthplace().isEmpty() ? "" : " in " + person.getBirthplace();

        SpannableStringBuilder ss = new SpannableStringBuilder(fullText);
        ss.setSpan(new CustomTypefaceSpan("", bold), 0, bornTitle.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(new CustomTypefaceSpan("", regular), bornTitle.length(), fullText.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        return ss;
    }

    private SpannableStringBuilder buildDeathString(Person person) {
        String deathTitle = context.getString(R.string.person_died);

        Typeface bold = OakUtils.getStaticTypeFace(context, context.getString(R.string.font_roboto_bold));
        Typeface regular = OakUtils.getStaticTypeFace(context, context.getString(R.string.font_roboto_regular));

        String fullText = deathTitle;
        fullText += person.getDeath().isEmpty() ? "" : " " + DateFormatter.format(person.getDeath());

        SpannableStringBuilder ss = new SpannableStringBuilder(fullText);
        ss.setSpan(new CustomTypefaceSpan("", bold), 0, deathTitle.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(new CustomTypefaceSpan("", regular), deathTitle.length(), fullText.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        return ss;
    }
}
