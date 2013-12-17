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
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
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

        // HorizontalLayout size = new HorizontalLayout();
        // size.setSpacing(true);
        // size.addComponent(new Label("-"));
        // Slider slider = new Slider();
        // try {
        // slider.setValue(70.0);
        // } catch (ValueOutOfBoundsException e) {
        // // Ignore
        // }
        // slider.setWidth("200px");
        // size.addComponent(slider);
        // size.addComponent(new Label("+"));
        // root.addComponent(size, "top: 16px; right: 18px; z-index:1;");

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
        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setWidth("100%");
        content.addComponent(bottom);

        VerticalLayout side = new VerticalLayout();
        side.setMargin(true);
        side.setSpacing(true);
        side.setWidth("170px");
        bottom.addComponent(side);

        CssLayout book = new CssLayout();
        book.addStyleName(Runo.CSSLAYOUT_SHADOW);
        book.addComponent(new Embedded(null, new ThemeResource(
                "icons/16/calendar.png")));
        side.addComponent(book);
        NativeSelect read = new NativeSelect("Mark the book as:");
        read.setWidth("130px");
        read.addItem("Not Read");
        read.addItem("Read");
        read.setNullSelectionAllowed(false);
        read.select("Read");
        side.addComponent(read);
        read = new NativeSelect();
        read.setWidth("130px");
        read.addItem("Mine");
        read.addItem("Loaned");
        read.setNullSelectionAllowed(false);
        read.select("Loaned");
        side.addComponent(read);

        CssLayout details = new CssLayout();
        details.setWidth("100%");
        // details.setMargin(false, true, false, false);
        bottom.addComponent(details);
        bottom.setExpandRatio(details, 1);

        @SuppressWarnings("deprecation")
        Label title = new Label(
                "<h3>Graphic Design &ndash; The New Basics</h3>",
                Label.CONTENT_XHTML);
        details.addComponent(title);
        title.setSizeUndefined();

        TabSheet tabs = new TabSheet();
        tabs.addStyleName(Runo.TABSHEET_SMALL);
        tabs.setWidth("100%");
        tabs.setHeight("180px");
        details.addComponent(tabs);

        FormLayout l = new FormLayout();
        tabs.addTab(l, "Info");
        Label text = new Label("248 pages");

        text.setCaption("Hardcover:");
        l.addComponent(text);
        text = new Label(
                "Princeton Architectural Press; 1 edition (May 1, 2008)");
        text.setCaption("Publisher:");
        l.addComponent(text);
        text = new Label("English");
        text.setCaption("Language:");
        l.addComponent(text);
        text = new Label("1568987706");
        text.setCaption("ISBN-10:");
        l.addComponent(text);
        text = new Label("978-1568987705");
        text.setCaption("ISBN-13:");
        l.addComponent(text);
        text = new Label("9.1 x 8.1 x 1.1 inches");
        text.setCaption("Product Dimensions:");
        l.addComponent(text);
        text = new Label("2.2 pounds");
        text.setCaption("Shipping Weight:");
        l.addComponent(text);

        tabs.addTab(new Label(), "Reviews");
        tabs.addTab(new Label(), "Personal");
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
     * @return the playerLayout
     */
    public VerticalLayout getPlayerLayout() {
        return playerLayout;
    }

    /**
     * @param playerLayout
     *            the playerLayout to set
     */
    public void setPlayerLayout(VerticalLayout playerLayout) {
        this.playerLayout = playerLayout;
    }

}
