package edu.pucm.springbootvaadin8.vaadin.principal;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.NavigationNotificationButton;
import com.github.appreciated.app.layout.component.NotificationAppBarButton;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import edu.pucm.springbootvaadin8.utilidades.IConstantes;
import edu.pucm.springbootvaadin8.vaadin.seguridad.LoginPanel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView()
public class AppPanel extends Panel implements View {
    public static final String VIEW_NAME = "app-panel";

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();
    private VerticalLayout holder;

    @Autowired
    SpringNavigator springNavigator;


    @PostConstruct
    private void init(){
        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(Behaviour.LEFT_HYBRID);
        setContent(holder);
        holder.setSizeFull();
        notifications.setNotificationClickedListener(newStatus -> Notification.show(newStatus.getTitle()));

    }



    private void setDrawerVariant(Behaviour variant) {
        holder.removeAllComponents();

        AppLayout drawer = AppLayoutBuilder.get()
                .withBehaviour(variant)
                .withTitle("Demo")
                .addToAppBar(getVariantCombo(variant))
                .addToAppBar(new NotificationAppBarButton(notifications))
                .addToAppBar(new Button("Salir", event -> {
                    getUI().getSession().close();
                    getUI().getPage().setLocation("/");
                }))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.MATERIAL)
                .add(new MenuHeader("Registro", "Version 0.9.9", new ThemeResource("logo.png")), AppLayoutBuilder.Position.HEADER)
                .add("Home", VaadinIcons.HOME, badge, View1.class)
                .add(
                        SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                                .add("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                                .add("Contact", VaadinIcons.CONNECT, View3.class)
                                .add("More", VaadinIcons.COG, View4.class)
                                .build())
                .add("Menu", VaadinIcons.MENU, View5.class)
                .add("Elements", VaadinIcons.LIST, View6.class)
                //.add("Evento", VaadinIcons.AIRPLANE, PanelEvento.class)
                .addClickable("Click Me", VaadinIcons.QUESTION, (Button.ClickListener) event -> springNavigator.navigateTo(LoginPanel.VIEW_NAME))
                .add(new NavigationNotificationButton("News", VaadinIcons.BELL, notifications), AppLayoutBuilder.Position.FOOTER)
                .build();

        holder.addComponent(drawer);

        //springNavigator.init(this, drawer.getContentHolder());
    }

    ComboBox getVariantCombo(Behaviour variant) {
        ComboBox<Behaviour> variants = new ComboBox<>();
        variants.addStyleNames(ValoTheme.COMBOBOX_BORDERLESS, ValoTheme.CHECKBOX_SMALL, ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        variants.setWidth("300px");
        variants.setItems(Behaviour.LEFT,
                Behaviour.LEFT_OVERLAY,
                Behaviour.LEFT_RESPONSIVE,
                Behaviour.LEFT_HYBRID,
                Behaviour.LEFT_RESPONSIVE_HYBRID,
                Behaviour.LEFT_RESPONSIVE_HYBRID_NO_APP_BAR,
                Behaviour.LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR,
                Behaviour.LEFT_RESPONSIVE_OVERLAY,
                Behaviour.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR,
                Behaviour.LEFT_RESPONSIVE_SMALL,
                Behaviour.LEFT_RESPONSIVE_SMALL_NO_APP_BAR);
        variants.setValue(variant);
//        variants.addValueChangeListener(valueChangeEvent -> setDrawerVariant(valueChangeEvent.getValue()));
        variants.addValueChangeListener((HasValue.ValueChangeListener<Behaviour>) event -> setDrawerVariant(event.getValue()));
        return variants;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }


    public static class View1 extends HorizontalLayout implements View {
        public View1() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View2 extends HorizontalLayout implements View {
        public View2() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View3 extends HorizontalLayout implements View {
        public View3() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View4 extends HorizontalLayout implements View {
        public View4() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View5 extends HorizontalLayout implements View {
        public View5() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View6 extends HorizontalLayout implements View {
        public View6() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View7 extends HorizontalLayout implements View {
        public View7() {
            addComponent(new Label(getClass().getName()));
        }
    }

    
}
