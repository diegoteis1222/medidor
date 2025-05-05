package com.cebem.medidor.repositories;

import lombok.Data;
import java.util.List;

@Data
public class PokeApiResponse {
    private List<TypeSlot> types;
    private Sprites sprites;
    private String name;

    @Data
    public static class TypeSlot {
        private Type type;
    }

    @Data
    public static class Type {
        private String name;
    }

    @Data
    public static class Sprites {
        private String front_default;
    }
}
