package com.tesis.farmacia.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proveedor  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "direccion")
    private String direccion;

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrdenCompra> ordenCompras;

    public Proveedor(){
        this.ordenCompras= new ArrayList<OrdenCompra>();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<OrdenCompra> getOrdenCompras() {
        return ordenCompras;
    }

    public void setOrdenCompras(List<OrdenCompra> ordenCompras) {
        this.ordenCompras = ordenCompras;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}
