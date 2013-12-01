/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 1, 2013.
 */
package com.m4gik.views.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.Runo;

/**
 * TODO COMMENTS MISSING!
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class CategoryTree implements ViewScreen {

    /**
     * TODO Comments missing. This method overrides an existing method.
     * 
     * @see com.m4gik.views.component.ViewScreen#build()
     */
    @SuppressWarnings("deprecation")
    @Override
    public Layout build() {
        CssLayout margin = new CssLayout();
        margin.setWidth("100%");
        // margin.setMargin(true, false, true, true);

        Tree t = new Tree();

        t.addItem("Archive");
        t.select("Archive");
        t.setItemIcon("Archive", new ThemeResource("icons/16/calendar.png"));
        createTreeItem(t, "January", "Archive");
        createTreeItem(t, "February", "Archive");
        createTreeItem(t, "March", "Archive");
        createTreeItem(t, "April", "Archive");
        createTreeItem(t, "May", "Archive");
        createTreeItem(t, "June", "Archive");
        createTreeItem(t, "July", "Archive");
        createTreeItem(t, "August", "Archive");
        createTreeItem(t, "September", "Archive");
        createTreeItem(t, "October", "Archive");
        createTreeItem(t, "November", "Archive");
        createTreeItem(t, "December", "Archive");

        t.addItem("Personal Files");
        t.setItemIcon("Personal Files", new ThemeResource(
                "icons/16/document.png"));
        createTreeItem(t, "Photos", "Personal Files");
        createTreeItem(t, "Videos", "Personal Files");
        createTreeItem(t, "Audio", "Personal Files");
        createTreeItem(t, "Documents", "Personal Files");
        t.expandItem("Personal Files");

        t.addItem("Company Storage");
        t.setItemIcon("Company Storage", new ThemeResource(
                "icons/16/folder.png"));
        createTreeItem(t, "Books", "Company Storage");
        createTreeItem(t, "Development", "Company Storage");
        createTreeItem(t, "Staff Peripherals", "Company Storage");
        createTreeItem(t, "Photo Enthusiasts", "Company Storage");
        t.expandItem("Company Storage");

        t.setItemIcon("Photo Enthusiasts", new ThemeResource(
                "icons/16/users.png"));
        t.setChildrenAllowed("Photo Enthusiasts", false);
        margin.addComponent(t);

        // Spacing
        margin.addComponent(new Label("&nbsp;", Label.CONTENT_XHTML));
        Label text = new Label(
                "The above tree and the example screens on the right don't actually do anything, they are here purely for show.");
        text.addStyleName(Runo.LABEL_SMALL);
        margin.addComponent(text);
        text.setWidth("90%");

        return margin;
    }

    /**
     * Creates item for tree object.
     * 
     * @param tree
     * @param caption
     * @param parent
     */
    public void createTreeItem(Tree tree, String caption, String parent) {
        tree.addItem(caption);

        if (parent != null) {

            tree.setChildrenAllowed(parent, true);
            tree.setParent(caption, parent);

            if (parent.equals("Archive")) {
                tree.setChildrenAllowed(caption, false);
            }

        }
    }

}
