package com.note_helper.igor.helper;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = getSupportFragmentManager();
        initializeActivityFragments();
        initializeNavigationDrawer(toolbar);

        initializeCollections();
        textPortugalView = (TextView) findViewById(R.id.text_portugal_words);
        textEnglishView = (TextView) findViewById(R.id.text_english_words);
        random = new Random();
        portugalWords = new LinkedList<>();
        englishWords = new LinkedList<>();
    }

    public void generatePortugal(View view) {
        textPortugalView.setText(portugalWords.get(random.nextInt(portugalWords.size())));
    }

    public void generateEnglish(View view) {
        textEnglishView.setText(englishWords.get(random.nextInt(englishWords.size())));
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
        Snackbar.make(view, "I have not implemented yet", Snackbar.LENGTH_LONG).show();
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
                .addDrawerItems(new PrimaryDrawerItem()
                                .withName(R.string.home).withIdentifier(1)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        if (manager.findFragmentByTag(WelcomeActivityFragment.TAG) == null) {
                                            transaction = manager.beginTransaction()
                                                    .replace(R.id.container_r, welcomeActivityFragment, WelcomeActivityFragment.TAG);
                                            transaction.commit();
                                        }
                                        return true;
                                    }
                                }),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.english)
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        if (manager.findFragmentByTag(EnglishActivityFragment.TAG) == null) {
                                            transaction = manager.beginTransaction()
                                                    .replace(R.id.container_r, englishActivityFragment);
                                            transaction.commit();
                                        }
                                        return true;
                                    }
                                }),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.portugal).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if (manager.findFragmentByTag(PortugalActivityFragment.TAG) == null) {
                                    transaction = manager.beginTransaction()
                                            .replace(R.id.container_r, portugalActivityFragment, PortugalActivityFragment.TAG);
                                    transaction.commit();
                                }
                                return true;
                            }
                        }),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.settings).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if (manager.findFragmentByTag(SettingActivityFragment.TAG) == null) {
                                    transaction = manager.beginTransaction()
                                            .replace(R.id.container_r, settingActivityFragment, SettingActivityFragment.TAG);
                                    transaction.commit();
                                }
                                return true;
                            }
                        }),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.about).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if (manager.findFragmentByTag(AboutActivityFragment.TAG) == null) {
                                    transaction = manager.beginTransaction()
                                            .replace(R.id.container_r, aboutActivityFragment, AboutActivityFragment.TAG);
                                    transaction.commit();
                                }
                                return true;
                            }
                        }),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.support).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                if (manager.findFragmentByTag(SupportActivityFragment.TAG) == null) {
                                    transaction = manager.beginTransaction()
                                            .replace(R.id.container_r, supportActivityFragment, SupportActivityFragment.TAG);
                                    transaction.commit();
                                }
                                return true;
                            }
                        })
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        return false;
                    }
                })
                .build();

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
