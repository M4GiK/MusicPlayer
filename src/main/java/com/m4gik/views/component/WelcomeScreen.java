/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 1, 2013.
 */
package com.m4gik.views.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * The class represents proper view to display Welcome Screen.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class WelcomeScreen implements ViewScreen {

    private TabSheet music = null;

    private TabSheet right = null;

    /**
     * @param right
     * @param music
     */
    public WelcomeScreen(TabSheet right, TabSheet music) {
        setRight(right);
        setMusic(music);
    }

    /**
     * Method adds for spacer between texts.
     * 
     * @param texts
     *            Texts to add.
     */
    private void addSpacer(HorizontalLayout texts) {
        Label text = new Label("");
        text.setWidth("20px");
        texts.addComponent(text);
    }

    /**
     * Method adds Text to the layout.
     * 
     * @param texts
     *            The layout to add texts.
     * @param string
     *            The text to add to the layout.
     */
    private void addText(HorizontalLayout texts, String string, String style) {
        addSpacer(texts);

        @SuppressWarnings("deprecation")
        Label text = new Label(string, Label.CONTENT_XHTML);

        if (style != null) {
            text.setStyleName(style);
        }

        texts.addComponent(text);
        texts.setExpandRatio(text, 1);
    }

    /**
     * Method builds Layout with texts and buttons for display Welcome Screen..
     * 
     * @return Layout with texts and buttons needs to build Welcome Screen.
     * 
     * @see com.m4gik.views.component.ViewScreen#build()
     */
    @SuppressWarnings("deprecation")
    @Override
    public Layout build() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setCaption("Welcome");

        Panel welcome = new Panel("Music player");
        welcome.setSizeFull();
        welcome.addStyleName(Runo.PANEL_LIGHT);
        layout.addComponent(welcome);
        layout.setExpandRatio(welcome, 1);

        CssLayout margin = new CssLayout();
        // margin.setMargin(true);
        margin.setWidth("100%");
        welcome.setContent(margin);

        Label title = new Label("Music player");
        title.addStyleName(Runo.LABEL_H1);
        // margin.addComponent(title);

        HorizontalLayout texts = new HorizontalLayout();
        texts.setSpacing(true);
        texts.setWidth("100%");
        margin.addComponent(texts);

        addText(texts, "<h3>Everything You Need Is Here</h3>"
                + "<p>Everything you see inside this application...</p>", null);
        addText(texts, "<h3>Everything You Need Is Here</h3>"
                + "<p>Everything you see inside this application...</p>", null);
        addText(texts, "<h3>Everything You Need Is Here</h3>"
                + "<p>Everything you see inside this application...</p>", null);

        layout.addComponent(new Label("<hr />", Label.CONTENT_XHTML));

        texts = new HorizontalLayout();
        texts.addStyleName(Runo.LAYOUT_DARKER);
        texts.setSpacing(true);
        texts.setWidth("100%");
        texts.setMargin(true);
        layout.addComponent(texts);

        addText(texts, "<h4>About This Application</h4>Lorem Impsum.",
                Runo.LABEL_SMALL);

        addSpacer(texts);

        Button musicNext = new Button("Lets music »",
                new Button.ClickListener() {

                    private static final long serialVersionUID = -6212824257096684411L;

                    public void buttonClick(ClickEvent event) {
                        right.setSelectedTab(music);
                    }
                });

        musicNext.setWidth("100%");
        musicNext.addStyleName(Runo.BUTTON_DEFAULT);
        musicNext.addStyleName(Runo.BUTTON_BIG);
        texts.addComponent(musicNext);
        texts.setComponentAlignment(musicNext, Alignment.BOTTOM_LEFT);
        texts.setExpandRatio(musicNext, 1);

        addSpacer(texts);

        return layout;
    }

    /**
     * @return the music
     */
    public TabSheet getMusic() {
        return music;
    }

    /**
     * @return the right
     */
    public TabSheet getRight() {
        return right;
    }

    /**
     * @param music
     *            the music to set
     */
    public void setMusic(TabSheet music) {
        this.music = music;
    }

    /**
     * @param right
     *            the right to set
     */
    public void setRight(TabSheet right) {
        this.right = right;
    }
}
