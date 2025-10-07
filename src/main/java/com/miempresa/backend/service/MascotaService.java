package com.miempresa.backend.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaService {

    // Clase interna Mascota (o puedes tenerla en su propio archivo en /model)
    public static class Mascota {
        private int id;
        private String name;
        private float weight;

        public Mascota(int id, String name, float weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }

        public float getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Nombre: " + name + ", Peso: " + weight;
        }
    }

    public List<Mascota> bubbleSort(List<Mascota> mascotas) {
        int n = mascotas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (mascotas.get(j).getWeight() > mascotas.get(j + 1).getWeight()) {
                    Mascota temp = mascotas.get(j);
                    mascotas.set(j, mascotas.get(j + 1));
                    mascotas.set(j + 1, temp);
                }
            }
        }
        return mascotas;
    }
}
