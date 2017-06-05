package com.note_helper.igor.helper;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.KeyboardUtil;
import com.note_helper.igor.helper.dao.EnglishWordsEntity;
import com.note_helper.igor.helper.dao.PortugueseWordEntity;
import com.note_helper.igor.helper.fragments.AboutActivityFragment;
import com.note_helper.igor.helper.fragments.EnglishActivityFragment;
import com.note_helper.igor.helper.fragments.PortugalActivityFragment;
import com.note_helper.igor.helper.fragments.SettingActivityFragment;
import com.note_helper.igor.helper.fragments.SupportActivityFragment;
import com.note_helper.igor.helper.fragments.WelcomeActivityFragment;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private Drawer drawerResult;
    private static Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general);
        Realm.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        random = new Random();
        initializeNavigationDrawer(toolbar);
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

        KeyboardUtil.hideKeyboard(this);
        Snackbar.make(view, "I have not implemented yet", Snackbar.LENGTH_LONG).show();
    }

    public void configurationEngWords() {

        EnglishWordsEntity englishWords = new EnglishWordsEntity();
        EditText inputTextForWords = (EditText) findViewById(R.id.text_words_database);
        englishWords.setEng_words(inputTextForWords.getText().toString());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(englishWords);
        realm.commitTransaction();
        inputTextForWords.setText("");
        KeyboardUtil.hideKeyboard(this);
        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();

    }

    public void builder(View view) {

        EditText inputTextForWords = (EditText) findViewById(R.id.text_words_database);
        if ("".equals(inputTextForWords.getText().toString())) {
            Snackbar.make(view, "Can not be empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner_choice_language);
        String language = spinner.getSelectedItem().toString();

        switch (language) {
            case "English":
                configurationEngWords();
                break;
            case "Portuguese":
                configurationPorWords();
                break;
            default:
                break;
        }
    }

    public void configurationPorWords() {

        PortugueseWordEntity portugueseWordEntity = new PortugueseWordEntity();
        EditText inputTextForWords = (EditText) findViewById(R.id.text_words_database);
        portugueseWordEntity.setPor_words(inputTextForWords.getText().toString());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(portugueseWordEntity);
        realm.commitTransaction();
        inputTextForWords.setText("");
        KeyboardUtil.hideKeyboard(this);
        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();

    }

    public void getEngWordsFromDatabase(View view) {
        RealmResults<EnglishWordsEntity> engWords = getEngWords();
        TextView textEnglishWords = (TextView) findViewById(R.id.text_english_words);
        if (engWords.isEmpty()) {
            Snackbar.make(view, "Collection is empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        textEnglishWords.setText(engWords.get(random.nextInt(engWords.size())).getEng_words());
    }

    public void getPorWordsFromDatabase(View view) {
        RealmResults<PortugueseWordEntity> engWords = getPorWords();
        TextView textPortugueseWords = (TextView) findViewById(R.id.text_portugal_words);
        if (engWords.isEmpty()) {
            Snackbar.make(view, "Collection is empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        textPortugueseWords.setText(engWords.get(random.nextInt(engWords.size())).getPor_words());
    }

    public RealmResults<EnglishWordsEntity> getEngWords() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(EnglishWordsEntity.class).findAll();
    }

    public RealmResults<PortugueseWordEntity> getPorWords() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(PortugueseWordEntity.class).findAll();
    }

    private void initializeNavigationDrawer(Toolbar toolbar) {
        AccountHeader headerResult = createAccountHeader();
        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_layout)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
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
