package com.note_helper.igor.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.note_helper.igor.helper.sqlite_db.SqliteHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private Drawer drawerResult;

    private FragmentManager manager;
    private EnglishActivityFragment englishActivityFragment;
    private PortugalActivityFragment portugalActivityFragment;
    private WelcomeActivityFragment welcomeActivityFragment;
    private AboutActivityFragment aboutActivityFragment;
    private SupportActivityFragment supportActivityFragment;
    private SettingActivityFragment settingActivityFragment;
    private FragmentTransaction transaction;
    private TextView textPortugalView;
    private TextView textEnglishView;
    private List<String> portugalWords;
    private List<String> englishWords;
    private Random random;
    private SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = getSupportFragmentManager();
        initializeActivityFragments();
        random = new Random();
        textPortugalView = (TextView) findViewById(R.id.text_portugal_words);
        textEnglishView = (TextView) findViewById(R.id.text_english_words);
        portugalWords = new LinkedList<>();
        englishWords = new LinkedList<>();
        initializeCollections();
        initializeNavigationDrawer(toolbar);
        sqliteHelper = new SqliteHelper(this);
    }

    public void generatePortugal(View view) {
        textPortugalView.setText(portugalWords.get(new Random().nextInt(portugalWords.size())));
    }

    public void generateEnglish(View view) {
        textEnglishView.setText(englishWords.get(new Random().nextInt(englishWords.size())));
    }

    @Override
    public void onBackPressed() {

        if (drawerResult != null && drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void getStarted(View view) {
        drawerResult.openDrawer();
    }

    public void sendMail(View view) {
//        Snackbar.make(view, "I have not implemented yet", Snackbar.LENGTH_LONG).show();
    }

    public void addEngWords(View view) {

        String approximately = "Approximately";

        SQLiteDatabase sqLiteDatabase = sqliteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SqliteHelper.ENGLISH_WORDS, approximately);
        sqLiteDatabase.insert(SqliteHelper.ENG_WRD, null, contentValues);
    }

    public void getEngWords(View view) {

        SQLiteDatabase writableDatabase = sqliteHelper.getWritableDatabase();
        Cursor query = writableDatabase.query(SqliteHelper.ENGLISH_WORDS, null, null, null, null, null, null);

        while (query.moveToNext()) {
            int columnIndex = query.getColumnIndex(SqliteHelper.KEY_ID_FOR_ENG);

            String columnName = query.getColumnName(columnIndex);
            System.out.println(columnName);
            System.out.println(columnName);
        }
        query.close();
    }

    private void initializeCollections() {
        portugalWords.add("Sentir");
        portugalWords.add("Andar");
        portugalWords.add("Pão");
        portugalWords.add("Cabeça");
        portugalWords.add("Cabalo");
        portugalWords.add("Ter de");
        portugalWords.add("Ну і слова тут");

        englishWords.add("Imperative");
        englishWords.add("Approximately");
        englishWords.add("Unbelievable");
        englishWords.add("Marvelous");
        englishWords.add("Excellent");
        englishWords.add("Be Happy");
        englishWords.add("All Ok");
        englishWords.add("I'm Igor");
        englishWords.add("Your friend");
        englishWords.add("Truth");
        englishWords.add("Improve to");
        englishWords.add("My Sister");
        englishWords.add("You are kindness");
        englishWords.add("Obrigada");
    }

    private void initializeActivityFragments() {
        englishActivityFragment = new EnglishActivityFragment();
        portugalActivityFragment = new PortugalActivityFragment();
        settingActivityFragment = new SettingActivityFragment();
        supportActivityFragment = new SupportActivityFragment();
        welcomeActivityFragment = new WelcomeActivityFragment();
        aboutActivityFragment = new AboutActivityFragment();
    }

    private void initializeNavigationDrawer(Toolbar toolbar) {
        AccountHeader headerResult = createAccountHeader();
        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withAccountHeader(headerResult)
                .withActionBarDrawerToggleAnimated(true)
                .withHasStableIds(true)
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.home).withIdentifier(0))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.english).withIdentifier(1))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.portugal).withIdentifier(2))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.settings).withIdentifier(3))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.about).withIdentifier(4))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.support).withIdentifier(5))
                .withSelectedItem(0)
                .withFireOnInitialOnClick(true)
                .withOnDrawerItemClickListener(this)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        Fragment fragment= null;
        switch ((int) drawerItem.getIdentifier()) {
            case 0:
                fragment = new WelcomeActivityFragment();
                break;
            case 1:
                fragment = new EnglishActivityFragment();
                break;
            case 2:
                fragment = new PortugalActivityFragment();
                break;
            case 3:
                fragment = new SettingActivityFragment();
                break;
            case 4:
                fragment = new AboutActivityFragment();
                break;
            case 5:
                fragment = new SupportActivityFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_r, fragment)
                    .commitAllowingStateLoss();
        }
        return false;
    }

    private AccountHeader createAccountHeader() {
        IProfile profile = new ProfileDrawerItem()
                .withName("Tetyana Zakharchenko")
                .withEmail("Zakharchenko_t@gmail.com")
                .withIcon(getResources().getDrawable(R.drawable.tetyana2));

        return new AccountHeaderBuilder()
                .withDividerBelowHeader(true)
                .withActivity(this)
                .addProfiles(profile)
                .withHeaderBackground(R.drawable.profile)
                .build();
    }
}
