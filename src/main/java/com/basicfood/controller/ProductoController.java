package com.basicfood.controller;

import com.basicfood.service.ProductoService;

public class ProductoController {
	private ProductoService service;

    public ProductoController() {
        this.service = new ProductoService();
    }

	public String mostrarCatalogo() {

        return this.service.mostrarCatalogo();
    }


}
