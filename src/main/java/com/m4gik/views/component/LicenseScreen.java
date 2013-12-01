/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 1, 2013.
 */
package com.m4gik.views.component;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * Class represents the view for License.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class LicenseScreen implements ViewScreen {

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

        Label text = new Label(string, Label.CONTENT_XHTML);

        if (style != null) {
            text.setStyleName(style);
        }

        texts.addComponent(text);
        texts.setExpandRatio(text, 1);
    }

    /**
     * Method builds license layout with texts.
     * 
     * @return The VerticalLayout with texts.
     * 
     * @see com.m4gik.views.component.ViewScreen#build()
     */
    @Override
    public Layout build() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setCaption("Welcome");

        Panel welcome = new Panel("License");
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

        return layout;
    }

}
