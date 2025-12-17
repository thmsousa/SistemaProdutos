package com.example.sistemaprodutos;

import jakarta.servlet.ServletContext;

public class Serial {
    public static int proximo(ServletContext aplicacao){
        int serial=(int) aplicacao.getAttribute("serial");
        aplicacao.setAttribute("serial",serial+1);
        return serial;
    }
}
