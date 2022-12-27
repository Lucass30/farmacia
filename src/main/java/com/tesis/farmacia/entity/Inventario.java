package com.tesis.farmacia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventarios")
public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name="direccion")
    private String direccion;

    @OneToMany(mappedBy = "inventario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemInventario> itemsInventario;

    public Inventario(){
        this.itemsInventario = new ArrayList<ItemInventario>();
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<ItemInventario> getItemsInventario() {
        return itemsInventario;
    }

    public void setItemsInventario(List<ItemInventario> itemsInventario) {
        this.itemsInventario = itemsInventario;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}
