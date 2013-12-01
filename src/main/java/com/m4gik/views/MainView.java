package com.m4gik.views;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.m4gik.views.component.BillingScreen;
import com.m4gik.views.component.CategoryTree;
import com.m4gik.views.component.LibraryScreen;
import com.m4gik.views.component.LicenseScreen;
import com.m4gik.views.component.WelcomeScreen;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * @author xpoft, m4gik <michal.szczygiel@wp.pl>
 */
@Component
@Scope("prototype")
@VaadinView(MainView.NAME)
public class MainView extends Panel implements View {

    /**
     * 
     */
    public static final String NAME = "";

    /**
     * Auto generated serial version UID.
     */
    private static final long serialVersionUID = 8265140038040050015L;

    private TabSheet music = new TabSheet();

    private TabSheet right = new TabSheet();

    /**
     * 
     */
    private Label usernameLabel = new Label();

    // private Label rolesLabel = new Label();

    /**
     * @param left
     */
    private void addAccordion(Panel left) {
        Accordion accordion = new Accordion();
        accordion.setSizeFull();
        left.setContent(accordion);

        accordion.addTab(buildTree(), "Category");
        accordion.addTab(new Label(""), "Discover");
        accordion.addTab(new Label(""), "Quick Search");
    }

    /**
     * @param split
     */
    private void addPanelLeft(HorizontalSplitPanel split) {
        Panel left = new Panel("Music explorer");
        left.setSizeFull();
        split.setFirstComponent(left);

        addAccordion(left);
    }

    /**
     * @param split
     */
    private void addPanelRight(final HorizontalSplitPanel split) {
        right.setSizeFull();
        split.setSecondComponent(right);
        split.setLocked(true);

        right.addTab(buildWelcomeScreen(), "Welcome");
        right.addTab(buildMusic(split), "Music Player");
        right.addTab(buildLicenseScreen(), "License");
        right.addListener(new Listener() {

            private static final long serialVersionUID = 3585077764011982717L;

            @Override
            public void componentEvent(Event event) {
                if (right.getSelectedTab() == music) {
                    split.setSplitPosition(25, Sizeable.Unit.PERCENTAGE);
                    split.setLocked(false);
                    split.setHeight("350px");
                } else {
                    split.setSplitPosition(1, Sizeable.Unit.PIXELS);
                    split.setLocked(true);
                    split.setHeight("350px");
                }
            }
        });
    }

    /**
     * @param root
     */
    private void addSlogan(VerticalLayout root) {
        Label slogan = new Label("All music now without limits");
        slogan.addStyleName(Runo.LABEL_SMALL);
        slogan.setSizeUndefined();
        root.addComponent(slogan);
        root.setComponentAlignment(slogan, Alignment.TOP_CENTER);
    }

    /**
     * @param root
     */
    private void addSpace(VerticalLayout root) {
        Label space = new Label("");
        space.setHeight("20px");
        root.addComponent(space);
    }

    /**
     * @param root
     */
    private void addSplitPanel(VerticalLayout root) {
        final HorizontalSplitPanel split = new HorizontalSplitPanel();
        split.setStyleName(Runo.SPLITPANEL_REDUCED);
        split.setSplitPosition(1, Sizeable.Unit.PIXELS);
        split.setLocked(true);
        root.addComponent(split);
        root.setExpandRatio(split, 1);
        split.setHeight("350px");

        addPanelLeft(split);
        addPanelRight(split);
    }

    /**
     * @param root
     */
    private void addTitle(VerticalLayout root) {
        Label title = new Label("Music Player");
        title.addStyleName(Runo.LABEL_H1);
        title.setSizeUndefined();
        root.addComponent(title);
        root.setComponentAlignment(title, Alignment.TOP_CENTER);

        addSlogan(root);
    }

    /**
     * @return
     */
    private Layout buildBillingScreen() {
        return new BillingScreen().build();
    }

    /**
     * @param split
     * @return
     */
    private Layout buildLibraryScreen(HorizontalSplitPanel split) {
        return new LibraryScreen(split).build();
    }

    /**
     * @return
     */
    private Layout buildLicenseScreen() {
        return new LicenseScreen().build();
    }

    public ComponentContainer buildMusic(HorizontalSplitPanel split) {
        music.addStyleName(Runo.TABSHEET_SMALL);
        music.setSizeFull();

        music.addTab(buildLibraryScreen(split));
        music.addTab(buildBillingScreen());

        return music;
    }

    /**
     * @return
     */
    private Layout buildTree() {
        return new CategoryTree().build();
    }

    /**
     * @return
     */
    private Layout buildWelcomeScreen() {
        return new WelcomeScreen(right, music).build();
    }

    /**
     * This method overrides an existing method.
     * 
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Subject subject = SecurityUtils.getSubject();

        usernameLabel.setValue((String) subject.getPrincipal());
        // rolesLabel.setValue("");
    }

    /**
     * Initial layout for {@link MainView}
     */
    private void initLayout() {
        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSizeFull();
        setContent(root);

        addTitle(root);
        addSpace(root);
        addSplitPanel(root);

        // setSizeFull();
        // VerticalLayout layout = new VerticalLayout();
        // layout.setSpacing(true);
        // layout.setMargin(true);
        //
        // HorizontalLayout usernameLayout = new HorizontalLayout();
        // usernameLayout.setSpacing(true);
        // usernameLayout.addComponent(new Label("Username:"));
        // usernameLayout.addComponent(usernameLabel);
        //
        // //HorizontalLayout userRolesLayout = new HorizontalLayout();
        // //userRolesLayout.setSpacing(true);
        // //userRolesLayout.addComponent(new Label("Roles:"));
        // //userRolesLayout.addComponent(rolesLabel);
        //
        // layout.addComponent(usernameLayout);
        // //addComponent(userRolesLayout);
        //
        // Link roleUserView = new
        // Link("Role \"user\" View (disabled, if user doesn't have access)",
        // new ExternalResource("#!" + RoleUserView.NAME));
        // Link roleAdminView = new
        // Link("Role \"admin\" View (disabled, if user doesn't have access)",
        // new ExternalResource("#!" + RoleAdminView.NAME));
        // Link authenticatedView = new
        // Link("@RequiresAuthentication View (disabled, if user doesn't have access)",
        // new ExternalResource("#!" + AuthenticatedView.NAME));
        // Link guestView = new
        // Link("@RequiresGuest View (disabled, if user doesn't have access)",
        // new ExternalResource("#!" + GuestView.NAME));
        // Link userView = new
        // Link("@RequiresUser View (disabled, if user doesn't have access)",
        // new ExternalResource("#!" + UserView.NAME));
        //
        // roleUserView.setEnabled(ShiroSecurityNavigator.hasAccess(RoleUserView.class));
        // roleAdminView.setEnabled(ShiroSecurityNavigator.hasAccess(RoleAdminView.class));
        // authenticatedView.setEnabled(ShiroSecurityNavigator.hasAccess(AuthenticatedView.class));
        // guestView.setEnabled(ShiroSecurityNavigator.hasAccess(GuestView.class));
        // userView.setEnabled(ShiroSecurityNavigator.hasAccess(UserView.class));
        //
        // layout.addComponent(roleUserView);
        // layout.addComponent(roleAdminView);
        // layout.addComponent(authenticatedView);
        // layout.addComponent(guestView);
        // layout.addComponent(userView);
        // layout.addComponent(new
        // Link("Role \"admin\" View (throw exception, if user doesn't have access)",
        // new ExternalResource("#!" + RoleAdminView.NAME)));
        //
        // layout.addComponent(new Link("Logout", new
        // ExternalResource("/logout/")));
        //
        // setContent(layout);

    }

    /**
     * 
     */
    @PostConstruct
    public void PostConstruct() {
        initLayout();
    }
}
