/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Nov 11, 2013.
 */
package com.m4gik;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.security.ShiroSecurityNavigator;

import com.m4gik.persistence.HibernateUtil;
import com.m4gik.persistence.Persiste;
import com.vaadin.annotations.Theme;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author m4gik <michal.szczygiel@wp.pl>
 */
@Component
@Scope("prototype")
@Theme("runo")
public class musicPlayerUI extends UI implements ErrorHandler {

    /**
     * Auto generated serial version UID.
     */
    private static final long serialVersionUID = -957809569849200836L;

    /**
     * Exception on action
     */
    @Override
    public void error(com.vaadin.server.ErrorEvent event) {
        // connector event
        if (event.getThrowable().getCause() instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) event
                    .getThrowable().getCause();
            Notification.show(exception.getMessage(),
                    Notification.Type.ERROR_MESSAGE);

            // Cleanup view. Now Vaadin ignores errors and always shows the
            // view. :-(
            // since beta10
            setContent(null);
            return;
        }

        // Error on page load. Now it doesn't work. User sees standard error
        // page.
        if (event.getThrowable() instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) event
                    .getThrowable();

            Label label = new Label(exception.getMessage());
            label.setWidth(-1, Unit.PERCENTAGE);

            Link goToMain = new Link("Go to main", new ExternalResource("/"));

            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(label);
            layout.addComponent(goToMain);
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            layout.setComponentAlignment(goToMain, Alignment.MIDDLE_CENTER);

            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setSizeFull();
            mainLayout.addComponent(layout);
            mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

            setContent(mainLayout);
            Notification.show(exception.getMessage(),
                    Notification.Type.ERROR_MESSAGE);
            return;
        }

        DefaultErrorHandler.doDefault(event);
    }

    /**
     * Initialize Vaadin session, Shiro security and Hibernate session.
     * 
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(final VaadinRequest request) {
        VaadinSession.getCurrent().setErrorHandler(this);
        setSizeFull();

        ShiroSecurityNavigator navigator = new ShiroSecurityNavigator(this,
                this);

        Persiste.getInstance(HibernateUtil.getSessionFactory().openSession());
    }
}
