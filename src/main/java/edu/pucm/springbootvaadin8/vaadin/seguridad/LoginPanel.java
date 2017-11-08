package edu.pucm.springbootvaadin8.vaadin.seguridad;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import edu.pucm.springbootvaadin8.orm.Usuario;
import edu.pucm.springbootvaadin8.services.SeguridadService;
import edu.pucm.springbootvaadin8.utilidades.IConstantes;
import edu.pucm.springbootvaadin8.vaadin.principal.AppPanel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//

/**
 * Spring permite habilitar la navegación e inyeccion de los componentes
 * como estamos bajo el framework spring boot debemos de utilizar @AutoWired.
 */
@SpringView(name = LoginPanel.VIEW_NAME)
public class LoginPanel extends Panel implements View {
    //ver la convencion de nombre si lo omito.
    //http://vaadin.github.io/spring-tutorial/#_automatic_view_name_generation
    public static final String VIEW_NAME = "login-panel";

    @Autowired
    SeguridadService seguridadService;

    //propiedades
    TextField txtUsuario;
    PasswordField txtPassword;
    Button btnAceptar, btnCancelar;


    @PostConstruct
    private void init(){

        txtUsuario=new TextField("Usuario");
        txtPassword = new PasswordField("Contraseña");

        btnAceptar=new Button("Ingresar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                System.out.println("Validando Usuario y Contrasena: "+txtUsuario.getValue());
                Usuario usuario = seguridadService.validarUsuario(txtUsuario.getValue(), txtPassword.getValue());
                if(usuario!=null){
                    getUI().getSession().setAttribute(IConstantes.USUARIO_AUTENTICADO, usuario);
                    getUI().getNavigator().navigateTo(AppPanel.VIEW_NAME);
                }
            }
        });

        btnCancelar=new Button("Cancelar", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                System.out.println("Saliendo de la operacion....");
            }
        });

        FormLayout formLayout =new FormLayout(txtUsuario, txtPassword);
        HorizontalLayout hl =new HorizontalLayout();
        hl.addComponents(btnAceptar, btnCancelar);

        VerticalLayout vl = new VerticalLayout(formLayout, hl);
        vl.setSizeFull();

        //this.setSizeFull();
        this.setContent(vl);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Ventana de Login...");
    }
}
