/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Nov 30, 2013.
 */
package com.m4gik.views.component;

import com.vaadin.data.Item;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Slider.ValueOutOfBoundsException;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Runo;

/**
 * Class represents view for Billing and other things depends with pricing.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class BillingScreen implements ViewScreen {

    /**
     * Constructor for {@link BillingScreen}. Creates the all items responsible
     * for pricing.
     * 
     * @param root
     */
    public BillingScreen() {
    }

    /**
     * TODO Comments missing. This method overrides an existing method.
     * 
     * @see com.m4gik.views.component.ViewScreen#build()
     */
    @Override
    public Layout build() {
        AbsoluteLayout root = new AbsoluteLayout();
        root.setSizeFull();
        root.setCaption("Time Tracking");

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        Button b = new Button("+ Add Hours", new Button.ClickListener() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                Window w = new Window("Add Hours");
                w.addStyleName(Runo.WINDOW_DIALOG);
                w.setModal(true);
                w.setResizable(false);
                w.setCloseShortcut(KeyCode.ESCAPE, null);
                FormLayout l = new FormLayout();
                l.setSizeUndefined();
                w.setContent(l);
                l.setMargin(true);
                l.setSpacing(true);
                NativeSelect s = new NativeSelect("Hour Type:");
                s.addItem("Billed");
                s.addItem("Not billed");
                s.setNullSelectionAllowed(false);
                s.select("Billed");
                l.addComponent(s);
                s.focus();
                l.addComponent(new ComboBox("Description:"));
                l.addComponent(new TextField("Notes:"));
                HorizontalLayout buttons = new HorizontalLayout();
                buttons.setSpacing(true);
                Button b = new Button("Add");
                b.addStyleName(Runo.BUTTON_DEFAULT);
                buttons.addComponent(b);
                buttons.addComponent(new Button("Cancel"));
                l.addComponent(buttons);
                // event.getButton().getWindow().addWindow(w);
            }
        });
        b.addStyleName(Runo.BUTTON_DEFAULT);
        buttons.addComponent(b);
        Button c = new Button("Generate Report", new Button.ClickListener() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Window w = new Window("Generate Workhours Report");
                w.setPositionX(50);
                w.setPositionY(70);
                w.setResizable(false);
                w.setCloseShortcut(KeyCode.ESCAPE, null);
                GridLayout root = new GridLayout(2, 3);
                root.setMargin(true);
                root.setSpacing(true);
                w.setContent(root);
                CssLayout format = new CssLayout();
                OptionGroup opt = new OptionGroup("1. Select output format");
                opt.addItem("Excel sheet");
                opt.addItem("CSV plain text");
                opt.select("CSV plain text");
                format.addComponent(opt);
                ComboBox csv = new ComboBox();
                csv.setWidth("170px");
                csv.addItem("Separate by comma (,)");
                csv.addItem("Separate by colon (:)");
                csv.addItem("Separate by semicolon (;)");
                csv.setNullSelectionAllowed(false);
                csv.select("Separate by comma (,)");
                CssLayout margin = new CssLayout();
                // margin.setMargin(false, false, true, true);
                margin.addComponent(csv);
                format.addComponent(margin);
                root.addComponent(format);

                CssLayout res = new CssLayout();
                res.setCaption("Output resolution");
                Slider slider = new Slider();
                try {
                    slider.setValue(30.0);
                } catch (ValueOutOfBoundsException e) {
                    // Ignore
                }
                res.addComponent(slider);
                slider.setWidth("200px");
                HorizontalLayout labels = new HorizontalLayout();
                labels.setWidth("200px");
                Label text = new Label("Coarse info");
                text.setSizeUndefined();
                text.addStyleName(Runo.LABEL_SMALL);
                labels.addComponent(text);
                text = new Label("Fine details");
                text.setSizeUndefined();
                text.addStyleName(Runo.LABEL_SMALL);
                labels.addComponent(text);
                labels.setComponentAlignment(text, Alignment.TOP_RIGHT);
                res.addComponent(labels);
                root.addComponent(res);

                ComboBox rec = new ComboBox("2. Select recepient");
                rec.addItem("john.doe@example.org");
                rec.addItem("harry.driver@example.org");
                rec.addItem("guybrush.threepwood@melee.mi");
                rec.setNewItemsAllowed(true);
                root.addComponent(rec);
                rec.setWidth("188px");

                HorizontalLayout buttons = new HorizontalLayout();
                buttons.setSpacing(true);
                Button b = new Button("Generate");
                b.addStyleName(Runo.BUTTON_DEFAULT);
                buttons.addComponent(b);
                b = new Button("Cancel");
                buttons.addComponent(b);
                root.addComponent(buttons, 0, 2, 1, 2);
                root.setComponentAlignment(buttons, Alignment.TOP_RIGHT);

                // event.getButton().getWindow().addWindow(w);
            }
        });
        buttons.addComponent(c);
        root.addComponent(buttons, "top: 11px; right: 18px; z-index:1;");

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        root.addComponent(content);

        Panel top = new Panel("Browse & Edit Workhours", new VerticalLayout());
        top.setSizeFull();
        top.addStyleName(Runo.PANEL_LIGHT);
        top.getContent().setSizeFull();
        content.addComponent(top);
        content.setExpandRatio(top, 1);

        Table table = new Table();
        table.setSizeFull();
        table.setSelectable(true);
        table.setColumnReorderingAllowed(true);
        table.addStyleName(Runo.TABLE_BORDERLESS);
        // top.addComponent(table);
        table.addContainerProperty("info", Embedded.class, null);
        table.addContainerProperty("check", CheckBox.class, null);
        table.addContainerProperty("locked", Embedded.class, null);
        table.addContainerProperty("hours", String.class, "00:00:00");
        table.addContainerProperty("cost", String.class, "$0.00");
        table.addContainerProperty("start", String.class, "00/00/0000");
        table.addContainerProperty("end", String.class, "00/00/0000");
        table.addContainerProperty("note", Embedded.class, new Embedded(null,
                new ThemeResource("icons/16/note.png")));
        table.addContainerProperty("desc", String.class, null);
        table.setColumnHeaders(new String[] { "", "", "", "Hours", "Cost",
                "Start Date", "End Date", "Note", "Description" });
        table.setColumnAlignment("info", Table.ALIGN_CENTER);
        table.setColumnAlignment("check", Table.ALIGN_CENTER);
        table.setColumnAlignment("note", Table.ALIGN_CENTER);

        for (int j = 1; j < 6; j++) {
            Item i = table.addItem(j);
            i.getItemProperty("info").setValue(
                    create16pxIcon("icons/16/attention.png"));
            i.getItemProperty("check").setValue(new CheckBox(null, true));
            i.getItemProperty("note").setValue(
                    create16pxIcon("icons/16/note.png"));
        }
        table.select(1);
        Item i = table.getItem(1);
        i.getItemProperty("hours").setValue("07:02:18");
        i.getItemProperty("cost").setValue("$703.83");
        i.getItemProperty("start").setValue("1/17/10");
        i.getItemProperty("end").setValue("1/17/10");
        i.getItemProperty("desc").setValue("More revisions");

        i = table.getItem(2);
        i.getItemProperty("hours").setValue("04:00:00");
        i.getItemProperty("cost").setValue("$360.00");
        i.getItemProperty("start").setValue("1/14/10");
        i.getItemProperty("end").setValue("1/14/10");
        i.getItemProperty("desc").setValue("Algorithm selection");
        i.getItemProperty("note").setValue(null);

        i = table.getItem(3);
        i.getItemProperty("hours").setValue("02:34:45");
        i.getItemProperty("cost").setValue("$160.00");
        i.getItemProperty("start").setValue("1/13/10");
        i.getItemProperty("end").setValue("1/13/10");
        i.getItemProperty("desc").setValue("New features implementation");
        i.getItemProperty("note").setValue(null);

        i = table.getItem(4);
        i.getItemProperty("hours").setValue("0:14:00");
        i.getItemProperty("cost").setValue("$60.00");
        i.getItemProperty("start").setValue("1/6/10");
        i.getItemProperty("end").setValue("1/6/10");

        i = table.getItem(5);
        i.getItemProperty("hours").setValue("03:07:23");
        i.getItemProperty("cost").setValue("$560.00");
        i.getItemProperty("start").setValue("1/5/10");
        i.getItemProperty("end").setValue("1/5/10");
        i.getItemProperty("desc").setValue("More revisions");
        i.getItemProperty("note").setValue(null);

        content.addComponent(new Label("<hr />", Label.CONTENT_XHTML));

        VerticalLayout bottom = new VerticalLayout();
        bottom.setMargin(true);
        bottom.setSpacing(true);
        bottom.addStyleName(Runo.LAYOUT_DARKER);
        content.addComponent(bottom);

        HorizontalLayout line = new HorizontalLayout() {
            @Override
            public void addComponent(Component c) {
                super.addComponent(c);
                setComponentAlignment(c, Alignment.MIDDLE_LEFT);
                c.setSizeUndefined();
            }
        };
        line.setWidth("100%");
        line.setSpacing(true);
        Label first = new Label("Item Type:");
        line.addComponent(first);
        first.setWidth("80px");
        NativeSelect select = new NativeSelect();
        select.addItem("Timed");
        select.addItem("Not billable");
        select.setNullSelectionAllowed(false);
        select.select("Timed");
        line.addComponent(select);
        line.addComponent(new Label("Customer Hourly Rate:"));
        TextField tf = new TextField();
        tf.setInputPrompt("$45.00");
        line.addComponent(tf);
        tf.setWidth("100%");
        line.setExpandRatio(tf, 1);
        line.addComponent(new Button("Remove"));
        CheckBox cb = new CheckBox("Taxable");
        cb.setValue(true);
        line.addComponent(cb);
        bottom.addComponent(line);

        line = new HorizontalLayout() {
            @Override
            public void addComponent(Component c) {
                super.addComponent(c);
                setComponentAlignment(c, Alignment.MIDDLE_LEFT);
                c.setSizeUndefined();
            }
        };
        line.setWidth("100%");
        line.setSpacing(true);
        first = new Label("Hours:");
        line.addComponent(first);
        first.setWidth("80px");
        first = new Label("11:56:10 from 1 timing session.");
        line.addComponent(first);
        line.setExpandRatio(first, 1);
        line.addComponent(new Button("Timing Sessions"));
        line.addComponent(new Button("Quick Add"));
        line.addComponent(new Button("Quick Modify"));
        cb = new CheckBox("Included");
        cb.setValue(true);
        line.addComponent(cb);
        bottom.addComponent(line);

        line = new HorizontalLayout() {
            @Override
            public void addComponent(Component c) {
                super.addComponent(c);
                setComponentAlignment(c, Alignment.MIDDLE_RIGHT);
            }
        };
        line.setWidth("100%");
        line.setSpacing(true);
        first = new Label("Description:");
        line.addComponent(first);
        first.setWidth("80px");
        ComboBox combo = new ComboBox();
        combo.setInputPrompt("Add a description");
        combo.setNewItemsAllowed(true);
        line.addComponent(combo);
        combo.setWidth("100%");
        line.setExpandRatio(combo, 1);
        bottom.addComponent(line);

        line = new HorizontalLayout() {
            @Override
            public void addComponent(Component c) {
                super.addComponent(c);
                setComponentAlignment(c, Alignment.TOP_RIGHT);
            }
        };
        line.setWidth("100%");
        line.setSpacing(true);
        first = new Label("Notes:");
        line.addComponent(first);
        first.setWidth("80px");
        tf = new TextField();
        line.addComponent(tf);
        tf.setWidth("100%");
        tf.setHeight("4em");
        line.setExpandRatio(tf, 1);
        bottom.addComponent(line);

        return root;

    }

    /**
     * Creates icon with size 16px;
     * 
     * @param iconid
     * @return Component for embedding external objects.
     */
    private Embedded create16pxIcon(String iconid) {
        Embedded embedded = new Embedded(null, new ThemeResource(iconid));
        embedded.setStyleName("icon-16");
        return embedded;
    }
}
