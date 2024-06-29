package com.kumulus.crudpessoa.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

public class Mensagens {
    public static void criarMensagem(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage("globalMessages", new FacesMessage(severity, summary, detail));
        PrimeFaces.current().ajax().update("globalMessages");
        PrimeFaces.current().executeScript("PF('messagesDialog').show();");
    }
}
