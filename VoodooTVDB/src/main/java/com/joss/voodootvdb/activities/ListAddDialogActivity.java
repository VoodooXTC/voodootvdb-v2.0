package com.joss.voodootvdb.activities;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseArray;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.ListsResponse.Items;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.list_items.ListItemsProvider;
import com.joss.voodootvdb.provider.lists.ListsColumns;
import com.joss.voodootvdb.provider.lists.ListsCursor;
import com.joss.voodootvdb.provider.lists.ListsProvider;
import com.joss.voodootvdb.utils.GGson;
import com.joss.voodootvdb.utils.Utils;

import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 4/7/15
 * Time: 5:03 PM
 */
public class ListAddDialogActivity extends BaseActivity implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnDismissListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = ListAddDialogActivity.class.getSimpleName();
    public static final String TYPE = "type";
    public static final String JSON = "json";
    public static final String TYPE_MOVIE = CustomListItem.TYPE_MOVIE;
    public static final String TYPE_SHOW = CustomListItem.TYPE_SHOW;
    public static final String TYPE_EPISODE = CustomListItem.TYPE_EPISODE;
    public static final String TYPE_PERSON = CustomListItem.TYPE_PERSON;

    private static final int LISTS_CALLBACK = 8462;

    public static void startActivity(Context context, Movie movie){
        Intent i = new Intent(context, ListAddDialogActivity.class);
        i.putExtra(TYPE, TYPE_MOVIE);
        i.putExtra(JSON, GGson.toJson(movie));
        context.startActivity(i);
    }

    public static void startActivity(Context context, Show show){
        Intent i = new Intent(context, ListAddDialogActivity.class);
        i.putExtra(TYPE, TYPE_SHOW);
        i.putExtra(JSON, GGson.toJson(show));
        context.startActivity(i);
    }

    public static void startActivity(Context context, Episode episode){
        Intent i = new Intent(context, ListAddDialogActivity.class);
        i.putExtra(TYPE, TYPE_EPISODE);
        i.putExtra(JSON, GGson.toJson(episode));
        context.startActivity(i);
    }

    public static void startActivity(Context context, Person person){
        Intent i = new Intent(context, ListAddDialogActivity.class);
        i.putExtra(TYPE, TYPE_PERSON);
        i.putExtra(JSON, GGson.toJson(person));
        context.startActivity(i);
    }

    AlertDialog dialog;
    List<CustomList> lists;
    SparseArray<Boolean> selection;

    String type;
    Movie movie;
    Show show;
    Episode episode;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);
        handleIntent(getIntent(), savedInstanceState);

        getLoaderManager().initLoader(LISTS_CALLBACK, null, this);
        Api.getLists(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent, null);
    }

    private void handleIntent(Intent intent, Bundle savedInstanceState) {
        getContent(savedInstanceState == null ? intent.getExtras() : savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putAll(getIntent().getExtras());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                ListsColumns.CONTENT_URI,
                ListsColumns.FULL_PROJECTION,
                null,
                null,
                ListsColumns.NAME + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            ListsCursor cursor = new ListsCursor(data);
            List<CustomList> customLists = ListsProvider.get(cursor);

            // Add Watchlist
            customLists.add(0, ListsProvider.getWatchlist(this));

            if(!ListsProvider.equals(lists, customLists)){

                lists = customLists;
                selection = ListItemsProvider.getSelection(this, getTraktId(), lists);

                if(dialog != null && dialog.isShowing())
                    dialog.dismiss();

                dialog = new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.lists) + ": " + getName())
                        .setPositiveButton(getString(R.string.done), new OnOkay())
                        .setNegativeButton(getString(R.string.nah), new OnNah())
                        .setNeutralButton(getString(R.string.create), new OnCreate())
                        .setMultiChoiceItems(ListsProvider.get(lists), getSelection(selection), this)
                        .setOnDismissListener(this)
                        .create();
                dialog.show();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
        selection.put(lists.get(position).getIds().getTrakt(), isChecked);
    }

    private class OnOkay implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {

            for(int i = 0; i < selection.size(); i++){

                int listTraktId = selection.keyAt(i);
                boolean checked = selection.valueAt(i);
                boolean itemAlreadyInList = ListItemsProvider.contains(ListAddDialogActivity.this, listTraktId, getTraktId());

                if(checked && !itemAlreadyInList){

                    // Add item to list
                    if(listTraktId == CustomList.WATCHLIST_ID){
                        Api.addWatchlistItems(ListAddDialogActivity.this, getItems());
                    }else{
                        Api.addListItems(ListAddDialogActivity.this, listTraktId, getItems());
                    }
                }else if(!checked && itemAlreadyInList){

                    // Remove Item from list
                    if(listTraktId == CustomList.WATCHLIST_ID){
                        Api.removeWatchlistItems(ListAddDialogActivity.this, getItems());
                    }else{
                        Api.removeListItems(ListAddDialogActivity.this, listTraktId, getItems());
                    }
                }
            }

            dialog.dismiss();
        }
    }

    private class OnNah implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }
    private class OnCreate implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            Utils.toast(ListAddDialogActivity.this, "Create a list dialog");
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        finish();
    }

    private void getContent(Bundle b) {
        type = b.getString(TYPE);
        String json = b.getString(JSON);

        switch (type){
            case TYPE_MOVIE:
                movie = GGson.fromJson(json, Movie.class);
                break;
            case TYPE_SHOW:
                show = GGson.fromJson(json, Show.class);
                break;
            case TYPE_EPISODE:
                episode = GGson.fromJson(json, Episode.class);
                break;
            case TYPE_PERSON:
                person = GGson.fromJson(json, Person.class);
                break;
        }
    }

    private String getName(){
        switch (type){
            case TYPE_MOVIE:
                return movie.getTitle();
            case TYPE_SHOW:
                return show.getTitle();
            case TYPE_EPISODE:
                return episode.getTitle();
            case TYPE_PERSON:
                return person.getName();
            default:
                return "";
        }
    }

    private int getTraktId(){
        switch (type){
            case TYPE_MOVIE:
                return movie.getIds().getTrakt();
            case TYPE_SHOW:
                return show.getIds().getTrakt();
            case TYPE_EPISODE:
                return episode.getIds().getTrakt();
            case TYPE_PERSON:
                return person.getIds().getTrakt();
            default:
                return 0;
        }
    }

    private Items getItems() {
        switch (type){
            case TYPE_MOVIE:
                return new Items().add(movie);
            case TYPE_SHOW:
                return new Items().add(show);
            case TYPE_EPISODE:
                return new Items().add(episode);
            case TYPE_PERSON:
                return new Items().add(person);
            default:
                return new Items();
        }
    }

    private boolean[] getSelection(SparseArray<Boolean> selection) {
        boolean[] sel = new boolean[lists.size()];
        for(int i = 0; i < lists.size(); i++){
            sel[i] = selection.get(lists.get(i).getIds().getTrakt(), false);
        }
        return sel;
    }

}
