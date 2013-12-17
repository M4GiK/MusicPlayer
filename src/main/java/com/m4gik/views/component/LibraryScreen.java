/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Nov 30, 2013.
 */
package com.m4gik.views.component;

import java.util.Iterator;

import com.m4gik.views.utils.AudioCollection;
import com.m4gik.views.utils.AudioFile;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Slider.ValueOutOfBoundsException;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * TODO COMMENTS MISSING!
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class LibraryScreen implements ViewScreen {

    /**
     * 
     */
    private HorizontalLayout bottom = null;

    /**
     * 
     */
    private VerticalLayout content = null;

    /**
     * 
     */
    private VerticalLayout playerLayout = null;

    /**
     * Constructor for {@link LibraryScreen}
     * 
     * @param playerLayout
     *            The component responsible for playing music.
     */
    public LibraryScreen(VerticalLayout playerLayout) {
        setPlayerLayout(playerLayout);
    }

    /**
     * @param audioFile
     * @return
     */
    private CssLayout addDetails(AudioFile audioFile) {
        CssLayout details = new CssLayout();
        details.setWidth("100%");

        Label title = new Label("<h3>" + audioFile.getArtist() + "&ndash;"
                + audioFile.getTitle() + "</h3>", ContentMode.HTML);
        details.addComponent(title);
        title.setSizeUndefined();

        TabSheet tabs = new TabSheet();
        tabs.addStyleName(Runo.TABSHEET_SMALL);
        tabs.setWidth("100%");
        tabs.setHeight("180px");
        details.addComponent(tabs);

        // FormLayout l = new FormLayout();
        // tabs.addTab(l, "Info");
        // Label text = new Label("248 pages");
        //
        // text.setCaption("Hardcover:");
        // l.addComponent(text);
        // text = new Label(
        // "Princeton Architectural Press; 1 edition (May 1, 2008)");
        // text.setCaption("Publisher:");
        // l.addComponent(text);
        // text = new Label("English");
        // text.setCaption("Language:");
        // l.addComponent(text);
        // text = new Label("1568987706");
        // text.setCaption("ISBN-10:");
        // l.addComponent(text);
        // text = new Label("978-1568987705");
        // text.setCaption("ISBN-13:");
        // l.addComponent(text);
        // text = new Label("9.1 x 8.1 x 1.1 inches");
        // text.setCaption("Product Dimensions:");
        // l.addComponent(text);
        // text = new Label("2.2 pounds");
        // text.setCaption("Shipping Weight:");
        // l.addComponent(text);
        //
        // tabs.addTab(new Label(), "Reviews");
        // tabs.addTab(new Label(), "Personal");

        return details;
    }

    /**
     * TODO Comments missing. This method overrides an existing method.
     * 
     * @see com.m4gik.views.component.ViewScreen#build()
     */
    @Override
    public Layout build() {
        HorizontalLayout root = new HorizontalLayout();
        root.setSizeFull();
        root.setCaption("Media Library");
        root.setHeight("200%");
        root.setWidth("100%");

        this.content = new VerticalLayout();
        content.setSizeFull();
        root.addComponent(content);

        final GridLayout grid = new GridLayout(4, 1);
        Panel top = new Panel("Music Collection", grid);
        top.setSizeFull();
        top.addStyleName(Runo.PANEL_LIGHT);
        grid.setWidth("100%");
        grid.setMargin(true);
        grid.addStyleName(Runo.LAYOUT_DARKER);
        content.addComponent(top);
        content.setExpandRatio(top, 1);

        grid.addLayoutClickListener(new LayoutClickListener() {

            private static final long serialVersionUID = -1864555729437118182L;

            @Override
            public void layoutClick(LayoutClickEvent event) {
                for (Iterator<Component> it = grid.iterator(); it.hasNext();) {
                    Component c = it.next();
                    c.removeStyleName(Runo.CSSLAYOUT_SELECTABLE_SELECTED);

                }

                if (event.getChildComponent() != null) {
                    event.getChildComponent().addStyleName(
                            Runo.CSSLAYOUT_SELECTABLE_SELECTED);
                }
            }
        });

        buildAudioLibrary(grid, null);

        return root;

    }

    /**
     * This method builds audio library for current filter.
     * 
     * @param grid
     *            The object to locate the audio covers.
     * @param filter
     *            The filter to extract need music files.
     */
    private void buildAudioLibrary(GridLayout grid, String filter) {
        AudioCollection audio = new AudioCollection();

        for (final AudioFile audioFile : audio.getAudioCollection(filter)) {

            CssLayout select = new CssLayout();
            select.addStyleName(Runo.CSSLAYOUT_SELECTABLE);

            CssLayout musicFile = new CssLayout();
            musicFile.addStyleName(Runo.CSSLAYOUT_SHADOW);
            musicFile.addComponent(createImageCover(audioFile.getCover()));
            select.addComponent(musicFile);

            musicFile.addLayoutClickListener(new LayoutClickListener() {

                private static final long serialVersionUID = 5789650754220216969L;

                @Override
                public void layoutClick(LayoutClickEvent event) {
                    buildInformationPanel(audioFile);
                }
            });

            grid.addComponent(select);
            grid.setComponentAlignment(select, Alignment.MIDDLE_CENTER);
        }

        Label text = new Label(
                "Note: This track are on Crative Common license.");
        text.addStyleName(Runo.LABEL_SMALL);
        text.setWidth("90%");
        grid.addComponent(text);
        grid.setComponentAlignment(text, Alignment.MIDDLE_CENTER);

    }

    /**
     * @param audioFile
     */
    protected void buildInformationPanel(AudioFile audioFile) {
        if (bottom == null) {
            this.bottom = new HorizontalLayout();
            bottom.setWidth("100%");
            content.addComponent(bottom);

            VerticalLayout side = new VerticalLayout();
            side.setMargin(true);
            side.setSpacing(true);
            side.setWidth("170px");
            bottom.addComponent(side);

            CssLayout musicFile = new CssLayout();
            musicFile.addStyleName(Runo.CSSLAYOUT_SHADOW);
            musicFile.addComponent(createPlayImage(audioFile,
                    audioFile.getCover()));
            side.addComponent(musicFile);

            side.addComponent(setFavorite());
            side.addComponent(setRate());

            Component details = addDetails(audioFile);
            bottom.addComponent(details);
            bottom.setExpandRatio(details, 1);

        } else {
            bottom.removeAllComponents();
            content.removeComponent(bottom);
            this.bottom = null;
            buildInformationPanel(audioFile);
        }

    }

    /**
     * This method creates image for cover.
     * 
     * @param cover
     *            The external cover for current track.
     * @return The image with set resource.
     */
    private Image createImageCover(ExternalResource cover) {
        Image image = new Image(null, cover);
        image.setHeight("120px");
        image.setWidth("120px");

        return image;
    }

    /**
     * @param audioFile
     * @param cover
     * @return
     */
    private Component createPlayImage(final AudioFile audioFile,
            ExternalResource cover) {

        AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setWidth("120px");
        absoluteLayout.setHeight("120px");
        absoluteLayout.addComponent(createImageCover(cover));

        Image play = createImageCover(new ExternalResource(
                "http://www.gelab.com.tr/interfaces/gelab/images/PlayButton.png"));
        play.setWidth("50px");
        play.setHeight("50px");
        play.addClickListener(new ClickListener() {

            private static final long serialVersionUID = -5184601350921707969L;

            @Override
            public void click(ClickEvent event) {
                System.out.println(audioFile.getTitle());
            }
        });
        absoluteLayout.addComponent(play, "top: 30px; left: 30px;");
        return absoluteLayout;
    }

    /**
     * @return the playerLayout
     */
    public VerticalLayout getPlayerLayout() {
        return playerLayout;
    }

    /**
     * This method creates select filed to choose as a favorite or not for
     * current track.
     * 
     * @return NativeSelect element with proper value.
     */
    private NativeSelect setFavorite() {
        // TODO: Add this element to database.
        NativeSelect favorite = new NativeSelect("Mark the track as:");
        favorite.setWidth("130px");
        favorite.addItem("Not favorite");
        favorite.addItem("Favorite");
        favorite.setNullSelectionAllowed(false);
        favorite.select("Not favorite");

        return favorite;
    }

    /**
     * @param playerLayout
     *            the playerLayout to set
     */
    public void setPlayerLayout(VerticalLayout playerLayout) {
        this.playerLayout = playerLayout;
    }

    /**
     * This method set rate for current track.
     * 
     * @return Slider element with proper value.
     */
    private Slider setRate() {
        // TODO: Add this element to database.
        Slider slider = new Slider("Rate the track:");
        slider.setWidth("130px");

        try {
            slider.setValue(0.0);
        } catch (ValueOutOfBoundsException e) {
            // Ignore
        }

        return slider;
    }

}
