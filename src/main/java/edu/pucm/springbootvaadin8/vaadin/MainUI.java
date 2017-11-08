package edu.pucm.springbootvaadin8.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.access.ViewInstanceAccessControl;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import edu.pucm.springbootvaadin8.utilidades.IConstantes;
import edu.pucm.springbootvaadin8.vaadin.principal.AppPanel;
import edu.pucm.springbootvaadin8.vaadin.seguridad.LoginPanel;

@Title("Aplicacion de prueba en Vaadin")
@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
//Representa la anotación que habilita una vista de Vaadin
@SpringUI(path = "/")
@SpringViewDisplay //anotación para habilitar las llamadas a los view
public class MainUI extends UI implements ViewDisplay, ViewInstanceAccessControl {

    private Panel panelPrincipal;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();

        panelPrincipal = new Panel();
        panelPrincipal.setSizeFull();

        root.setMargin(false);
        root.addComponent(panelPrincipal);
        root.setExpandRatio(panelPrincipal, 1.0f);

        setContent(root);
        if(getSession().getAttribute(IConstantes.USUARIO_AUTENTICADO)==null){ //no logueado
            //enviando la vista correspondiente al login
            getUI().getNavigator().navigateTo(LoginPanel.VIEW_NAME);
        }else{
            getUI().getNavigator().navigateTo(AppPanel.VIEW_NAME);
        }
    }

    @Override
    public void showView(View view) {
        
        panelPrincipal.setContent((Component) view);
    }

    /**
     * Filtro para validar la navegacion
     * @param ui
     * @param beanName
     * @param view
     * @return
     */
    @Override
    public boolean isAccessGranted(UI ui, String beanName, View view) {
        System.out.println(String.format("Verificando la seguridad-> [beanName:%s], [view: %s]", beanName, view.toString()));
        if(view instanceof LoginPanel){
           return true;
        }
        return ui.getSession().getAttribute(IConstantes.USUARIO_AUTENTICADO)!=null;
    }
}
